
# Ulord-Sidechain With Unlimited Transaction Per Second (TPS)

The purpose of this project is to eliminate block's "Gas Limit" to achieve Unlimited TPS theoretically. However, the TPS will completely depend on the performance of the System, the higher the system configuration the higher the TPS.

To achieve this, we need to remove block's gas limit. By doing so we can put as many transactions as possible in a block in a given time frame. The main reason for keeping block's gas limit was to prevent infinite transaction loop which can bring the blockchain to a complete halt. However, removing block's gas limit will open the doors for the attackers. For this reason, we need another way to prevent such attacks, which can be achieved by keeping a maximum gas limit for the execution of a transaction called "**txMaxGasLimit**". txMaxGasLimit of a transaction is different from transaction gasLimit. gasLimit is user-defined whereas txMaxGasLimit may be computed as described below. In this way, we can still avoid infinite loop attack while making as many transactions as possible to be mined in a block.

Further more, this project plans to implement Tendermint like Consensus protocol. 

## txMaxGasLimit Calculation
Traditionally the block's gas limited is increased if the previous blocks total gas used is more than 2/3 of the block's gas limit and decreased if there are no or almost no transaction on it. Assuming a single transaction which consumes 2/3rd of blocks gas limit the gas limit is increased. 

On the other hand, we can calculate txMaxGasLimit in similar fashion, however, the only difference is that instead of comparing total gas used by transactions, we can select the transaction which consumed the most gas and compare its gasUsed with the txMaxGasLimit and adjust accordingly.
 
  
# Getting Started
Information about compiling and running a USC node can be found in the [wiki](https://github.com/UlordChain/Ulord-Sidechain/wiki).
# License
USC is licensed under the GNU Lesser General Public License v3.0, also included in our repository in the [COPYING.LESSER](https://github.com/UlordChain/Ulord-Sidechain/blob/master/COPYING.LESSER) file.
