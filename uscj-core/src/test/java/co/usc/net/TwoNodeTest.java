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

package co.usc.net;

import co.usc.blockchain.utils.BlockGenerator;
import co.usc.config.TestSystemProperties;
import co.usc.net.messages.BlockMessage;
import co.usc.net.simples.SimpleNode;
import co.usc.net.sync.SyncConfiguration;
import co.usc.test.World;
import co.usc.validators.DummyBlockValidationRule;
import org.ethereum.core.Block;
import org.ethereum.core.Blockchain;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by ajlopez on 5/14/2016.
 */
public class TwoNodeTest {
    private static SimpleNode createNode(int size) {
        final World world = new World();
        final BlockStore store = new BlockStore();
        final Blockchain blockchain = world.getBlockChain();

        List<Block> blocks = new BlockGenerator().getBlockChain(blockchain.getBestBlock(), size);

        for (Block b: blocks)
            blockchain.tryToConnect(b);

        BlockNodeInformation nodeInformation = new BlockNodeInformation();
        SyncConfiguration syncConfiguration = SyncConfiguration.IMMEDIATE_FOR_TESTING;
        TestSystemProperties config = new TestSystemProperties();
        BlockSyncService blockSyncService = new BlockSyncService(config, store, blockchain, nodeInformation, syncConfiguration);
        NodeBlockProcessor processor = new NodeBlockProcessor(store, blockchain, nodeInformation, blockSyncService, syncConfiguration);
        NodeMessageHandler handler = new NodeMessageHandler(new TestSystemProperties(), processor, null, null, null, null, new DummyBlockValidationRule());

        return new SimpleNode(handler);
    }

    @Test
    @Ignore
    public void buildBlockchainAndSynchronize() {
        SimpleNode node1 = createNode(100);
        SimpleNode node2 = createNode(0);

        node1.sendStatusTo(node2);

        Assert.assertEquals(100, node1.getBestBlock().getNumber());
        Assert.assertEquals(100, node2.getBestBlock().getNumber());
        Assert.assertEquals(node1.getBestBlock().getHash(), node2.getBestBlock().getHash());
    }

    @Test
    @Ignore
    public void buildBlockchainPartialAndSynchronize() {
        SimpleNode node1 = createNode(0);
        SimpleNode node2 = createNode(0);

        List<Block> blocks = new BlockGenerator().getBlockChain(10);

        for (Block block : blocks) {
            BlockMessage message = new BlockMessage(block);
            node1.receiveMessageFrom(null, message);

            if (block.getNumber() <= 5)
                node2.receiveMessageFrom(null, message);
        }

        node1.sendStatusTo(node2);

        Assert.assertEquals(10, node1.getBestBlock().getNumber());
        Assert.assertEquals(10, node2.getBestBlock().getNumber());
        Assert.assertEquals(node1.getBestBlock().getHash(), node2.getBestBlock().getHash());
    }
}