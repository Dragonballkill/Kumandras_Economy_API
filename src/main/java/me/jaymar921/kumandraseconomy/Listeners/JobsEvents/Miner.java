package me.jaymar921.conomy.Listeners.JobsEvents;

import me.jaymar921.conomy.InventoryGUI.enums.JobList;
import me.jaymar921.conomy.conomy;
import me.jaymar921.conomy.economy.PlayerStatus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Miner extends Jobs implements Listener {

    public Miner(conomy main){
        super(main);
    }

    @EventHandler
    private void breakMine(BlockBreakEvent event){
        PlayerStatus playerStatus = main.getDataHandler().getStatusHolder().get(event.getPlayer().getUniqueId().toString());
        if(!playerStatus.getJobs().contains(JobList.MINER.toString()))return;
        if(blockedLocation.contains(event.getBlock().getLocation())) return;
        if(main.getRegistryConfiguration().consideredMineBlocks.contains(event.getBlock().getType())){
            double balance = playerStatus.getBalance();
            double income = main.getRegistryConfiguration().miningBlocks;
            playerStatus.setBalance(balance+income);
            addPlayer(event.getPlayer(), income);
            blockLocation(event.getBlock().getLocation());
        }
        if(main.getRegistryConfiguration().consideredOres.contains(event.getBlock().getType())){
            double balance = playerStatus.getBalance();
            double income = main.getRegistryConfiguration().miningOres;
            playerStatus.setBalance(balance+income);
            addPlayer(event.getPlayer(), income);
            blockLocation(event.getBlock().getLocation());
        }
    }
}



