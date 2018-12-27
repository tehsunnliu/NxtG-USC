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

package org.ethereum.config;

import co.usc.config.BridgeConstants;
import co.usc.config.BridgeTestNetConstants;
import co.usc.core.BlockDifficulty;
import co.usc.core.UscAddress;
import org.bouncycastle.util.encoders.Hex;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Describes different constants specific for a blockchain
 *
 * Created by Anton Nashatyrev on 25.02.2016.
 */
public class Constants {
    private static final int MAX_CONTRACT_SIZE = 0x6000;
    // we defined it be large enough for to allow large tx and also to have space still to operate on vm
    private static final BigInteger TRANSACTION_GAS_CAP = BigDecimal.valueOf(Math.pow(2,  60)).toBigInteger();
    public static final int DURATION_LIMIT = 8;
    private static final int MAX_ADDRESS_BYTE_LENGTH = 20;
    private int maximumExtraDataSize = 32;
    private int minGasLimit = 3000000;
    private int gasLimitBoundDivisor = 1024;

    // Private mining is allowed if difficulty is lower or equal than this value
    private final BlockDifficulty fallbackMiningDifficulty = new BlockDifficulty(BigInteger.valueOf((long) 14E15)); // 14 peta evert 14 secs = 1 peta/s.

    private static long blockPerDay = 24*3600 / 14;

    private long endOfFallbackMiningBlockNumber = blockPerDay*30*6; // Approximately 6 months of private mining fallback, then you're free my child. Fly, fly away.

    // 0.5 peta/s. This means that on reset difficulty will allow private mining.
    private final BlockDifficulty minimumDifficulty = new BlockDifficulty(BigInteger.valueOf((long) 14E15 / 2 )); // 0.5 peta/s.

    // Use this to test CPU-mining by Java client:
    // private BigInteger minimumDifficulty = BigInteger.valueOf((long) 14E4 / 2 ); // 0.005 mega/s.


    private BigInteger difficultyBoundDivisor = BigInteger.valueOf(2048);
    private int expDifficultyPeriod = 100000;

    private int uncleGenerationLimit = 7;
    private int uncleListLimit = 10;

    private int bestNumberDiffLimit = 100;

    private int newBlockMaxSecondsInTheFuture = 540;

    private final BigInteger minimumPayableGas = BigInteger.valueOf(200000);
    private final BigInteger federatorMinimumPayableGas = BigInteger.valueOf(50000);

    private static final BigInteger SECP256K1N = new BigInteger("fffffffffffffffffffffffffffffffebaaedce6af48a03bbfd25e8cd0364141", 16);

    private static final UscAddress BURN_ADDRESS = new UscAddress("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

    private static final byte CHAIN_ID = 50;

    public byte[] fallbackMiningPubKey0 = Hex.decode("048571af74dbb0260f2c643c4793b7daf2b61cabbeb4fd6c3c78c271418ceaa5f51767718768f0eac49f66e06395a2c64dcdd9a0b13fbb10aec971cf4caf2e3fc3");

    public byte[] fallbackMiningPubKey1 = Hex.decode("042878745b7986de1dc54348c7332624e14f720947f0db659265c7fd7e36adc2f0978bc31963821ef78cd7c8e986a257c37ac8cb763449e769b25c4361dcb64413");

    public static BigInteger getTransactionGasCap() {
        return TRANSACTION_GAS_CAP;
    }

    public static int getMaxContractSize() {
        return MAX_CONTRACT_SIZE;
    }

    public static int getMaxAddressByteLength() {
        return MAX_ADDRESS_BYTE_LENGTH;
    }

    // Average Time between blocks
    public int getDurationLimit() {
        return DURATION_LIMIT;
    }

    public BigInteger getInitialNonce() {
        return BigInteger.ZERO;
    }

    public byte[] getFallbackMiningPubKey0() {
            return fallbackMiningPubKey0;
    }

    public byte[] getFallbackMiningPubKey1() {
        return fallbackMiningPubKey1;
    }

    public int getMaximumExtraDataSize() {
        return maximumExtraDataSize;
    }

    public int getMinGasLimit() {
        return minGasLimit;
    }

    public int getGasLimitBoundDivisor() {
        return gasLimitBoundDivisor;
    }

    public BlockDifficulty getMinimumDifficulty() {
        return minimumDifficulty;
    }

    public BlockDifficulty getFallbackMiningDifficulty() { return fallbackMiningDifficulty; }

    public long getEndOfFallbackMiningBlockNumber() { return endOfFallbackMiningBlockNumber; }

    public BigInteger getDifficultyBoundDivisor() {
        return difficultyBoundDivisor;
    }

    public int getExpDifficultyPeriod() {
        return expDifficultyPeriod;
    }

    public int getUncleGenerationLimit() {
        return uncleGenerationLimit;
    }

    public int getUncleListLimit() {
        return uncleListLimit;
    }

    public int getBestNumberDiffLimit() {
        return bestNumberDiffLimit;
    }

    public static BigInteger getSECP256K1N() {
        return SECP256K1N;
    }

    public BridgeConstants getBridgeConstants() { return BridgeTestNetConstants.getInstance(); }

    public int getNewBlockMaxSecondsInTheFuture() {
        return this.newBlockMaxSecondsInTheFuture;
    }

    public UscAddress getBurnAddress() { return Constants.BURN_ADDRESS; }

    /**
     * EIP155: https://github.com/ethereum/EIPs/issues/155
     */
    public byte getChainId() { return Constants.CHAIN_ID; }

    public BigInteger getMinimumPayableGas() {
        return minimumPayableGas;
    }

    public BigInteger getFederatorMinimumPayableGas() {
        return federatorMinimumPayableGas;
    }
}