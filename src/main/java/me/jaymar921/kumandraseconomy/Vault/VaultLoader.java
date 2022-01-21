package me.jaymar921.economy.Vault;

import me.jaymar921.economy.Economy;
import me.jaymar921.economy.economy.EconomyImplementer;
import net.milkbowl.vault.economy.Economy;

public class VaultLoader {

    Economy plugin;

    private Vault vault;
    private VaultSupport vaultSupport;


    public VaultLoader(Economy plugin){
        this.plugin = plugin;
    }

    public void registerVault(){
        if(plugin.getRegistryConfiguration().separate_economy){
            vaultSupport = new VaultSupport(plugin);
            plugin.getRegistryConfiguration().foreign_economy = vaultSupport.EconomyName;
        }else{
            vault = new Vault(plugin);
            vault.hook();
        }

    }

    public void unregisterVault(){
        if(!plugin.getRegistryConfiguration().separate_economy)
            vault.unHook();
    }

    public VaultSupport getVaultSupport(){return vaultSupport;}
}


