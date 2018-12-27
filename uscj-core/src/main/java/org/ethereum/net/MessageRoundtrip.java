/*
 * This file is part of USC
 * Copyright (C) 2016 - 2018 USC developer team.
 * (derived from ethereumJ library, Copyright (c) 2016 <ether.camp>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.ethereum.net;

import org.ethereum.net.message.Message;

/**
 * Utility wraps around a message to keep track of the number of times it has
 * been offered This class also contains the last time a message was offered and
 * is updated when an answer has been received to it can be removed from the
 * queue.
 *
 * @author Roman Mandeleil
 */
public class MessageRoundtrip {

    private final Message msg;
    long lastTimestamp = 0;
    long retryTimes = 0;
    boolean answered = false;

    public MessageRoundtrip(Message msg) {
        this.msg = msg;
        saveTime();
    }

    public boolean isAnswered() {
        return answered;
    }

    public void answer() {
        answered = true;
    }

    public long getRetryTimes() {
        return retryTimes;
    }

    public void incRetryTimes() {
        ++retryTimes;
    }

    public void saveTime() {
        lastTimestamp = System.currentTimeMillis();
    }

    public boolean hasToRetry() {
        return 20000 < System.currentTimeMillis() - lastTimestamp;
    }

    public Message getMsg() {
        return msg;
    }
}
