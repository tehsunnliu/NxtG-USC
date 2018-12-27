/*
 * This file is part of USC
 * Copyright (C) 2016 - 2018 USC developer team.
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
package org.ethereum.config.blockchain;

import com.typesafe.config.Config;

public class HardForkActivationConfig {
    private final int shakespeareActivationHeight;

    private static final String PROPERTY_SHAKESPEARE_NAME = "shakespeare";

    public HardForkActivationConfig(Config config) {
        // If I don't have any config for shakespeareActivationHeight I will set it to 0
        this(config.hasPath(PROPERTY_SHAKESPEARE_NAME) ? config.getInt(PROPERTY_SHAKESPEARE_NAME) : 0);
    }

    public HardForkActivationConfig(int shakespeareActivationHeight) {
        this.shakespeareActivationHeight = shakespeareActivationHeight;
    }

    public int getShakespeareActivationHeight() {
        return shakespeareActivationHeight;
    }

}