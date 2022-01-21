package me.jaymar921.economy.Vault;

import me.jaymar921.economy.Economy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultSupport {

    public Economy economy;
    public String EconomyName;

    Economy plugin;

    public VaultSupport(Economy plugin) {
        this.plugin = plugin;
        StartVault();
    }

    public void StartVault() {
        if(registerVault())
            plugin.getLogger().info(ChatColor.GREEN+" Economy was set to secondary Economy");
    }

    public boolean registerVault() {
        RegisteredServiceProvider<Economy> eco = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if(economy == null) {
            try {
                economy = eco.getProvider();
            }catch (Exception e){
                plugin.getLogger().info(ChatColor.RED+"This plugin was set to secondary economy!");
                plugin.getLogger().info(ChatColor.RED+"NO primary economy detected!");
                plugin.getLogger().info(ChatColor.YELLOW+"Change the "+ChatColor.AQUA+"Separate_Economy"+ChatColor.YELLOW+" value to "+ChatColor.GREEN+"false");
                plugin.getLogger().info(ChatColor.YELLOW+"at config.yml then restart the server.");
                plugin.getRegistryConfiguration().separate_economy = false;
                return false;
            }
            if(eco.getProvider().getName()!=null)
                EconomyName = eco.getPlugin().getName();
            else
                EconomyName = "Foreign";
            plugin.getDataHandler().getPluginsRegistered().add(eco.getPlugin().getName());
            plugin.getLogger().info(ChatColor.GREEN+"Primary Economy: "+ChatColor.YELLOW+EconomyName);
        }
        return (economy!=null);
    }
}


