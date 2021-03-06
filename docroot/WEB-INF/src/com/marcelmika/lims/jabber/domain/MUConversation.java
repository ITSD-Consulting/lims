
/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Marcel Mika, marcelmika.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.marcelmika.lims.jabber.domain;

import com.marcelmika.lims.jabber.JabberUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smackx.muc.Affiliate;
import org.jivesoftware.smackx.muc.DefaultParticipantStatusListener;
import org.jivesoftware.smackx.muc.DiscussionHistory;
import org.jivesoftware.smackx.muc.MultiUserChat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com/lims
 * Date: 11/24/13
 * Time: 11:18 PM
 * @deprecated
 */
public class MUConversation {

    // Conversation
    private String conversationId;
    // Jabber
    private MultiUserChat muc;
    private DiscussionHistory history = new DiscussionHistory();
    // Buddies
    private Buddy owner;
    private List<Buddy> participants = new ArrayList<Buddy>();
    // Messages
    private LinkedList<MessageDeprecated> messages = new LinkedList<MessageDeprecated>();
    private int lastMessageSent = 0;

    public MUConversation(String conversationId, Buddy owner, MultiUserChat muc) {
//        System.out.println("[CREATING CONVERSATION] " + buddy.getScreenName());

        this.conversationId = conversationId;
        this.owner = owner;
        this.muc = muc;
        // Join conversation
        join(muc);
        // Message, Buddy, etc. listeners
        attachListeners(muc);
        // Owners of the room
        fetchOwners(muc);
        // Amount of discussion history
        // @todo: move constant to the portlet properties
        history.setMaxStanzas(1000);
    }

    public void restart() {
        // Delete old stuff
        messages.clear();
        participants.clear();
        // Reset creating flow
        join(muc);
        attachListeners(muc);
        fetchOwners(muc);
    }

    private void attachListeners(MultiUserChat muc) {
        // Message listener
        muc.addMessageListener(new PacketListener() {
            public void processPacket(Packet packet) {
                if (packet instanceof org.jivesoftware.smack.packet.Message) {
                    MessageDeprecated msg = new MessageDeprecated((org.jivesoftware.smack.packet.Message) packet);
                    // Extract  jabber name
                    msg.setFrom(JabberUtil.getResource(msg.getFrom()));
//                    msg.setCompanyId(owner.getCompanyId());

                    // Add only messages with sender
                    if (msg.getFrom() != null) {
                        messages.add(msg);
//                        System.out.println("[CONVERSATION][" + owner.getScreenName()x + "] " + msg.getFrom() + ": " + msg.getBody());
                    } else {
//                        System.out.println("[CONVERSATION][" + owner.getScreenName() + "] Message with no sender: " + msg.getBody());
                    }
                }
            }
        });

        // Buddies listener
        muc.addParticipantStatusListener(new DefaultParticipantStatusListener() {
            @Override
            public void ownershipGranted(String participant) {
                super.ownershipGranted(participant);
//                System.out.println("[OWNERSHIP][GRANTED] " + JabberUtil.getResource(participant));
                // @todo: add to participants
            }

            @Override
            public void ownershipRevoked(String participant) {
                super.ownershipRevoked(participant);
//                System.out.println("[OWNERSHIP][REVOKED] " + JabberUtil.getResource(participant));
                // Participant screen name
                String screenName = JabberUtil.getResource(participant);
                // Remove from participants
                for (int i = 0; i < participants.size(); i++) {
                    if (participants.get(i).getScreenName().equals(screenName)) {
                        participants.remove(i);
                        break;
                    }
                }
            }
        });
    }

    private void fetchOwners(MultiUserChat muc) {
        try {
            // Fetch owners
            Collection<Affiliate> owners = muc.getOwners();
            // Map jabber owners to participants
            for (Affiliate affiliate : owners) {
                String screenName = JabberUtil.getScreenName(affiliate.getJid());
                // Fetch buddy by screen name
//                Buddy buddy = BuddyLocalServiceUtil.getBuddyByScreenName(owner.getCompanyId(), screenName);
                // Add to list
//                if (buddy != null) {
//                    addParticipant(buddy);
//                }
            }
        } catch (XMPPException ex) {
//            System.out.println("[CONVERSATION][ERR] " + ex.getMessage());
        }
    }

    private void join(MultiUserChat muc) {
        try {
            muc.join(owner.getScreenName(), null, history, SmackConfiguration.getPacketReplyTimeout());
        } catch (XMPPException ex) {
//            System.out.println(ex.getMessage());
        }
    }

    // @todo: This should probably be in a separate layer to avoid model specific call
//    private com.marcelmika.lims.model.Conversation getConversationModel() {
//        return null;
//    }

    public List<Buddy> getParticipants() {
        return participants;
    }

    private boolean containsParticipant(Buddy participant) {
        //@todo: Implement in a more efficient way
        for(Buddy p: participants)  {
//            if(p.getUserId() == participant.getUserId()) {
                return true;
//            }
        }
        return false;
    }


    public void addParticipant(Buddy participant) {
        // Add only if the participant isn't already here
        if(!containsParticipant(participant)) {
            participants.add(participant);
        }
    }

    public void addParticipants(List<Buddy> participants) {
        for (Buddy participant : participants) {
            addParticipant(participant);
        }
    }

    public List<MessageDeprecated> getMessages() {
        return messages;
    }

    public Buddy getOwner() {
        return owner;
    }

    public String getConversationId() {
        return this.conversationId;
    }

    public String getConversationName() {
        return null;
    }

//    public String getConversationType() {
//        return getConversationModel().getConversationType();
//    }

    public String getConversationVisibility() {
        return null;
    }

    public MessageDeprecated getLastMessage() {
        return messages.peekLast();
    }

    public int getLastMessageSent() {
        return lastMessageSent;
    }

    public void setLastMessageSent(int lastMessageSent) {
        this.lastMessageSent = lastMessageSent;
    }

    public int getIndexOfLastMessage() {
        return messages.indexOf(messages.peekLast()) + 1;
    }

    public int getUnreadMessages() {
        return 0;
    }

    /**
     * Returns conversation title. Adds count of other participants in
     * conversation if there is more than one Returns empty string if there is
     * no participant in the conversation
     *
     * @return conversationTitle
     */
    public String getTitle() {
        // Public Room:

        // Private Room
        // Last message
        MessageDeprecated message = getLastMessage();
        String title = "";
        if (message != null) {
//            Buddy buddy = BuddyLocalServiceUtil.getBuddyByScreenName(owner.getCompanyId(), message.getFrom());
//            title = (buddy != null) ? buddy.getFullName() : "";
//            if (participants.size() > 1) {
//                title += " (+" + (participants.size() - 1) + ")";
//            }
        }
        return title;
    }

    public JSONObject toJSON() {
        // Buddies
        JSONArray jsonBuddies = JSONFactoryUtil.createJSONArray();
        for (Buddy buddy : getParticipants()) {
//            jsonBuddies.put(buddy.toJSON());
        }

        // Conversation properites
        JSONObject jsonConversation = JSONFactoryUtil.createJSONObject();
        jsonConversation.put("buddies", jsonBuddies);
        jsonConversation.put("title", getTitle());
        jsonConversation.put("roomJID", getConversationId());
        jsonConversation.put("roomName", getConversationName());
//        jsonConversation.put("roomType", getConversationType());
        jsonConversation.put("roomVisibility", getConversationVisibility());

        // Last message
        MessageDeprecated lastMessage = getLastMessage();
        if (lastMessage != null) {
            jsonConversation.put("lastMessage", lastMessage.toJSON());
        }

        return jsonConversation;
    }

    public JSONObject toFullJSON() {
        JSONObject jsonConversation = toJSON();
        JSONArray jsonMessages = JSONFactoryUtil.createJSONArray();

        // Get only messages that havn't been sent yet
        List<MessageDeprecated> subList = messages.subList(lastMessageSent, getIndexOfLastMessage());

        // Iterate all messages in conversation
        for (MessageDeprecated message : subList) {
            JSONObject jsonMessage = message.toJSON();
            // Put to messages
            jsonMessages.put(jsonMessage);
        }

        // Put to conversation
        jsonConversation.put("unreadMessages", getUnreadMessages());
        jsonConversation.put("messages", jsonMessages);

        return jsonConversation;
    }
}
