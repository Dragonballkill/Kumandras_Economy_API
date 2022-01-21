package me.jaymar921.economy.InventoryGUI;

import me.jaymar921.economy.ItemHandler.PlayerHeads;
import me.jaymar921.economy.Economy;
import me.jaymar921.economy.economy.PlayerStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceGUI {

    private final String currency_prefix = Economy.getPlugin(Economy.class).getRegistryConfiguration().currency_prefix;
    private final DecimalFormat fmt = new DecimalFormat("###,###,###,###.##");
    private final Map<String,String> lang = Economy.getPlugin(Economy.class).getDataHandler().getLanguageData();

    public Inventory BalanceInventory(Player player, PlayerStatus status, List<String> plugins){
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.BLUE+""+ChatColor.BOLD+player.getName()+"'s "+lang.get("Account"));

        ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.setDisplayName(" ");
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        for (int i = 0; i < 27; i++){
            gui.setItem(i, item);
        }

        item = new PlayerHeads().getPlayerHead(player);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+lang.get("KDAccount"));
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("AccName")+ChatColor.GOLD+" "+ChatColor.BOLD+player.getName());
        lore.add(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("Bal")+" "+ChatColor.GOLD+ChatColor.BOLD+fmt.format(status.getBalance())+currency_prefix);
        lore.add(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("PlayerLevel")+ChatColor.GOLD+" "+ChatColor.BOLD+player.getLevel());
        lore.add(ChatColor.GRAY+"┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄");
        lore.add(ChatColor.AQUA+lang.get("Jobs"));
        if(status.getJobs().size()==0)
            lore.add(ChatColor.RED+"-"+lang.get("NoJob"));
        else
            for(String jobs : status.getJobs())
                lore.add(ChatColor.YELLOW+"-"+jobs);
        meta.setLore(lore);
        item.setItemMeta(meta);

        gui.setItem(11, item);

        if(Economy.getPlugin(Economy.class).getRegistryConfiguration().separate_economy) {
            item = new ItemStack(Material.PAPER);
            meta = item.getItemMeta();
            assert meta != null;
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.addEnchant(Enchantment.DURABILITY, 1, true);
            meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + lang.get("Exchange"));
            lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + lang.get("CurrencyStatus"));
            lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "‣1.00 "+Economy.getPlugin(Economy.class).getRegistryConfiguration().foreign_economy+" "+lang.get("Money"));
            lore.add(ChatColor.YELLOW + "" + ChatColor.BOLD + "    ↓");
            lore.add(ChatColor.GOLD + "" + ChatColor.BOLD + "‣"+Economy.getPlugin(Economy.class).getRegistryConfiguration().currency_economy + " "+ lang.get("KDEco") + " "+lang.get("Money"));
            meta.setLore(lore);
            item.setItemMeta(meta);

            gui.setItem(13, item);
        }

        item = new ItemStack(Material.ENDER_EYE);
        meta = item.getItemMeta();
        assert meta != null;
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.setDisplayName(ChatColor.LIGHT_PURPLE+""+ChatColor.BOLD+"Plugin Supports");
        lore = new ArrayList<>();
        lore.add(ChatColor.GOLD+lang.get("SupportedPlugins"));
        lore.add(" ");
        if(!plugins.isEmpty()) {
            for (String p_names : plugins)
                lore.add(ChatColor.DARK_GREEN + "" + p_names);
        }else{
            lore.add(ChatColor.RED+lang.get("None")+" --");
        }
        meta.setLore(lore);
        item.setItemMeta(meta);

        gui.setItem(15, item);

        return gui;
    }



}



