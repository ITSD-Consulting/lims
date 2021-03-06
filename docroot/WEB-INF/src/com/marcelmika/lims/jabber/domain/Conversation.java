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

import com.liferay.portal.kernel.json.JSONObject;

import java.util.List;

/**
 * @author Ing. Marcel Mika
 * @link http://marcelmika.com/lims
 * Date: 11/24/13
 * Time: 11:18 PM
 * @deprecated
 */
public interface Conversation {

//    // Buddies
//    public com.marcelmika.lims.model.Buddy getOwner();
//
//    public List<com.marcelmika.lims.model.Buddy> getParticipants();
//
//    public void addParticipant(com.marcelmika.lims.model.Buddy participant);

    // Metadata
    public String getConversationId();

    public String getConversationName();

    public String getConversationType();

    public String getConversationVisibility();

    // Restart
    public void restart();

    // Message related stuff
    public List<MessageDeprecated> getMessages();

    public MessageDeprecated getLastMessage();

    public int getLastMessageSent();

    public void setLastMessageSent(int lastMessageSent);

    public int getIndexOfLastMessage();

    public int getUnreadMessages();

    // @todo: Move to separate json mapper
    public JSONObject toJSON();

    public JSONObject toFullJSON();
}
