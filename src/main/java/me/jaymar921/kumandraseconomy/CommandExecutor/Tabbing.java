package me.jaymar921.economy.CommandExecutor;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Tabbing implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){
        if(arguments.isEmpty()){
                arguments.add("Balance");
                arguments.add("Economy");
                arguments.add("Pay");
                arguments.add("Trade");
                arguments.add("Deliver");
                arguments.add("Shops");
                arguments.add("Jobs");
        }
        List<String> result = new ArrayList<>();
        if(args.length== 1){
             for(String args1: arguments) {
                 if(args1.toLowerCase().startsWith(args[0].toLowerCase())) {
                     if (args1.equalsIgnoreCase("Balance"))
                         if (!sender.hasPermission("economy.balance"))
                             continue;
                     if (args1.equalsIgnoreCase("Economy"))
                         if (!sender.hasPermission("economy.economy"))
                             continue;
                     if (args1.equalsIgnoreCase("Pay"))
                         if (!sender.hasPermission("economy.pay"))
                             continue;
                     if (args1.equalsIgnoreCase("Trade"))
                         if (!sender.hasPermission("economy.trade"))
                             continue;
                     if (args1.equalsIgnoreCase("Deliver"))
                         if (!sender.hasPermission("economy.deliver"))
                             continue;
                     if (args1.equalsIgnoreCase("Shops"))
                         if (!sender.hasPermission("economy.shop"))
                             continue;
                     if (args1.equalsIgnoreCase("Jobs"))
                         if (!sender.hasPermission("economy.job"))
                             continue;
                     result.add(args1);
                 }
             }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("economy")){
                for(String args1 : players())
                    if(args1.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(args1);
            }
        }
        if(args.length == 3){
            if(args[0].equalsIgnoreCase("economy")) {
                if("deposit".toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add("deposit");
                if("deduct".toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add("deduct");
                if("reset".toLowerCase().startsWith(args[2].toLowerCase()))
                    result.add("reset");
            }
        }
        if(args.length == 4){
            if(args[0].equalsIgnoreCase("economy")) {
                if(!"reset".toLowerCase().startsWith(args[2].toLowerCase()))
                    if("amount".toLowerCase().startsWith(args[3].toLowerCase()))
                        result.add("amount");
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("pay")){
                for(String args1 : players())
                    if(args1.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(args1);
            }
        }

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("pay")){
                if("amount".toLowerCase().startsWith(args[2].toLowerCase())    )
                    result.add("amount");
            }
        }
        if(args.length == 2){
            if(args[0].equalsIgnoreCase("trade")){
                for(String args1 : players())
                    if(args1.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(args1);
            }
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("Deliver")){
                for(String args1 : players())
                    if(args1.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(args1);
            }
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("Shops")){
                for(String args1 : shopCommands())
                    if(args1.toLowerCase().startsWith(args[1].toLowerCase())) {
                        if (args1.contains("Create"))
                            if (!sender.hasPermission("economy.shopAdmin"))
                                continue;
                        if (args1.contains("Modify"))
                            if (!sender.hasPermission("economy.shopAdmin"))
                                continue;
                        if (args1.contains("Delete"))
                            if (!sender.hasPermission("economy.shopAdmin"))
                                continue;
                        result.add(args1);
                    }
            }
        }

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("Shops")){
                if(args[1].equalsIgnoreCase("Create") || args[1].equalsIgnoreCase("Delete"))
                    if("ShopName".toLowerCase().startsWith(args[2].toLowerCase()))
                        result.add("ShopNameNoSpaces");
            }
        }

        if(args.length == 3){
            if(args[0].equalsIgnoreCase("Shops")){
                if(args[1].equalsIgnoreCase("Modify"))
                    if("Shop".toLowerCase().startsWith(args[2].toLowerCase()))
                        result.add("ShopUI");
            }
        }

        if(args.length == 4){
            if(args[0].equalsIgnoreCase("Shops")){
                if(args[1].equalsIgnoreCase("Modify"))
                    if(args[2].equalsIgnoreCase("ShopUI")) {
                        if ("Clone".toLowerCase().startsWith(args[3].toLowerCase()))
                            result.add("Clone");
                        if ("Price".toLowerCase().startsWith(args[3].toLowerCase()))
                            result.add("Price");
                    }
            }
        }

        return result;
    }

    private List<String> players(){
        List<String>names = new ArrayList<>();
        for(Player player : Bukkit.getServer().getOnlinePlayers())
            names.add(player.getName());
        return names;
    }

    private List<String> shopCommands(){
        List<String>commands = new ArrayList<>();
        commands.add("Create");
        commands.add("Delete");
        commands.add("Modify");
        return commands;
    }
    
}


