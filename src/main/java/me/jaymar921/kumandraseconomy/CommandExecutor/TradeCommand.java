package me.jaymar921.economy.CommandExecutor;

import me.jaymar921.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TradeCommand implements CommandExecutor {
    static Economy plugin;
    public TradeCommand(Economy main){
        plugin = main;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(label.equalsIgnoreCase("kTrade")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                if(args.length>0){
                    if(args[0].equalsIgnoreCase("accept")){
                        if(plugin.getTradingHandler().getRequestTradePersonnel().containsKey(player.getUniqueId().toString())){
                            for(Player trader : Bukkit.getServer().getOnlinePlayers()){
                                if(trader.getUniqueId().toString().equals(plugin.getTradingHandler().getRequestTradePersonnel().get(player.getUniqueId().toString()))){
                                    player.openInventory(plugin.getTradingHandler().getTradeInventory().get(trader.getUniqueId().toString()));
                                    trader.openInventory(plugin.getTradingHandler().getTradeInventory().get(trader.getUniqueId().toString()));
                                    player.sendMessage(ChatColor.GREEN+"Trade Accepted");
                                    trader.sendMessage(ChatColor.GREEN+"Trade request was accepted");
                                    plugin.getTradingHandler().startActiveSession(trader, player);
                                    plugin.getTradingHandler().activateSession(trader.getUniqueId().toString());
                                    return true;
                                }
                            }
                        }else{
                            player.sendMessage(ChatColor.RED+"You do not have any trade request");
                            return true;
                        }
                    }else if(args[0].equalsIgnoreCase("deny")){

                    }else{
                        player.sendMessage(ChatColor.RED+"Invalid argument");
                        return true;
                    }
                }else{
                    player.sendMessage(ChatColor.RED+"You need to specify the sub command");
                }
            }else{
                plugin.getLogger().info(ChatColor.RED+"Only Players can use this command ["+label+"]");
            }
        }

        return false;
    }
}



