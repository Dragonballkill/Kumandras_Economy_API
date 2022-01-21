package me.jaymar921.economy.datahandlers.Configurations.LangConfig;

import me.jaymar921.economy.Economy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class TurkishConfig {
    private Economy plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    public TurkishConfig(Economy plugin) {
        this.plugin = plugin;
        //save / initialize the config
        saveDefaultConfig();
    }

    public void reloadConfig() {
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "Languages/Turkish_lang.yml");

        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);

        InputStream defaultStream = this.plugin.getResource("Languages/Turkish_lang.yml");
        if(defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getConfig() {
        if(this.dataConfig == null)
            reloadConfig();

        return this.dataConfig;
    }

    public void saveConfig() {
        if(this.dataConfig == null || this.configFile == null)
            return;

        try {
            this.getConfig().save(this.configFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to "+this.configFile, e);
        }
    }

    public void saveDefaultConfig() {
        if(this.configFile == null)
            this.configFile = new File(this.plugin.getDataFolder(), "Languages/Turkish_lang.yml");

        if(!this.configFile.exists()) {
            this.plugin.saveResource("Languages/Turkish_lang.yml", false);
        }
    }
}


