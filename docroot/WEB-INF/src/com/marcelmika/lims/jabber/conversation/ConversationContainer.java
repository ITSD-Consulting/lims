
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

package com.marcelmika.lims.jabber.conversation;

import com.marcelmika.lims.jabber.domain.Conversation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com/lims
 * Date: 11/24/13
 * Time: 11:18 PM
 * @deprecated
 */
public class ConversationContainer {

    private HashMap<String, Conversation> conversations = new HashMap<String, Conversation>();

    /**
     * Adds conversation to the container. If the conversation is already in the
     * container nothing happens
     * @param conversationId conversation id
     * @param conversation conversation
     */
    public void addConversation(String conversationId, Conversation conversation) {
        if(conversation == null) {
            return;
        }

        if (!conversations.containsKey(conversationId)) {
            conversations.put(conversationId, conversation);
//            System.out.println("[CONTAINER][ADD] " + conversation.toJSON());
        } else {
//            System.out.println("[CONTAINER][ADD] " + conversationId + " is already in the container");
        }
    }

    /**
     * Returns conversation based on its id
     * @param conversationId conversation ID
     * @return Conversation
     */
    public Conversation getConversation(String conversationId) {
        return conversations.get(conversationId);
    }

    /**
     * Returns all conversations in the container
     * @return List<Conversation>
     */
    public List<Conversation> getAllConversations() {
        return new ArrayList<Conversation>(conversations.values());
    }

    /**
     * Removes conversation from the container
     * @param conversationId conversation ID
     */
    public void removeConversation(String conversationId) {
        conversations.remove(conversationId);
    }

    /**
     * Restarts all conversations in the container
     */
    public void restartConversations() {
        for (Conversation conversation : conversations.values()) {
            conversation.restart();
        }
    }
}
