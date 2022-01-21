package me.jaymar921.conomy.Vault;

import me.jaymar921.conomy.conomy;
import me.jaymar921.conomy.economy.EconomyImplementer;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.ServicePriority;

public class Vault {

    conomy plugin;

    private Economy economy;
    public Vault(conomy plugin){
        this.plugin = plugin;
    }

    public void hook(){
        economy = new EconomyImplementer(plugin);
        Bukkit.getServicesManager().register(Economy.class, economy , plugin, ServicePriority.Normal);
        plugin.getLogger().info(ChatColor.GREEN+"Vault API hooked");
        plugin.getLogger().info(ChatColor.GREEN+"Economy was set to primary Economy");
    }

    public void unHook(){
        Bukkit.getServicesManager().unregister(Economy.class, economy);
    }
}



