package me.jaymar921.economy.InventoryGUI;

import me.jaymar921.economy.Economy;
import me.jaymar921.economy.datahandlers.DeliveryDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeliveryGUI {

    DeliveryDataHandler deliveryHandler;
    String _prefix = Economy.getPlugin(Economy.class).getRegistryConfiguration().currency_prefix;
    private final Map<String,String> lang = Economy.getPlugin(Economy.class).getDataHandler().getLanguageData();
    public DeliveryGUI(DeliveryDataHandler deliveryHandler){
        this.deliveryHandler = deliveryHandler;
    }

    public Inventory DeliveryUI(){
        Inventory gui = Bukkit.createInventory(null, 27, ChatColor.BLUE+""+ChatColor.BOLD+"Delivery Booking");

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

        item = new ItemStack(Material.BARREL);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("CheapDelivery"));
        List<String> lores = new ArrayList<>();
        lores.add(" ");
        lores.add(ChatColor.DARK_AQUA+lang.get("Slots")+" "+ChatColor.YELLOW+"5");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverTime")+" "+ChatColor.YELLOW+deliveryHandler.getCheap_delivery_timer()+"s");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverPrice")+" "+ChatColor.YELLOW+deliveryHandler.getCheap_delivery_price()+_prefix);
        lores.add(ChatColor.LIGHT_PURPLE+lang.get("ClickPay"));
        meta.setLore(lores);
        item.setItemMeta(meta);
        lores.clear();

        gui.setItem(10, item);

        item = new ItemStack(Material.CHEST);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("RegularDelivery"));
        lores = new ArrayList<>();
        lores.add(" ");
        lores.add(ChatColor.DARK_AQUA+lang.get("Slots")+" "+ChatColor.YELLOW+"10");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverTime")+" "+ChatColor.YELLOW+deliveryHandler.getRegular_delivery_timer()+"s");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverPrice")+" "+ChatColor.YELLOW+deliveryHandler.getRegular_delivery_price()+_prefix);
        lores.add(ChatColor.LIGHT_PURPLE+lang.get("ClickPay"));
        meta.setLore(lores);
        item.setItemMeta(meta);
        lores.clear();

        gui.setItem(12, item);

        item = new ItemStack(Material.GREEN_SHULKER_BOX);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("FastDelivery"));
        lores = new ArrayList<>();
        lores.add(" ");
        lores.add(ChatColor.DARK_AQUA+lang.get("Slots")+" "+ChatColor.YELLOW+"10");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverTime")+" "+ChatColor.YELLOW+deliveryHandler.getFast_delivery_timer()+"s");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverPrice")+" "+ChatColor.YELLOW+deliveryHandler.getFast_delivery_price()+_prefix);
        lores.add(ChatColor.LIGHT_PURPLE+lang.get("ClickPay"));
        meta.setLore(lores);
        item.setItemMeta(meta);
        lores.clear();

        gui.setItem(14, item);

        item = new ItemStack(Material.ENDER_CHEST);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GREEN+""+ChatColor.BOLD+lang.get("PriorityDelivery"));
        lores = new ArrayList<>();
        lores.add(" ");
        lores.add(ChatColor.DARK_AQUA+lang.get("Slots")+" "+ChatColor.YELLOW+"15");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverTime")+" "+ChatColor.YELLOW+deliveryHandler.getPriority_delivery_timer()+"s");
        lores.add(ChatColor.DARK_AQUA+lang.get("DeliverPrice")+" "+ChatColor.YELLOW+deliveryHandler.getPriority_delivery_price()+_prefix);
        lores.add(ChatColor.LIGHT_PURPLE+lang.get("ClickPay"));
        meta.setLore(lores);
        item.setItemMeta(meta);
        lores.clear();

        gui.setItem(16, item);



        return gui;
    }

    public Inventory getFiveSlotUI(String title, String recipient){
        Inventory ui = Bukkit.createInventory(null, 9, ChatColor.LIGHT_PURPLE+title);

        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        ui.setItem(0, item);
        ui.setItem(1, item);
        ui.setItem(7, item);

        item = new ItemStack(Material.DARK_OAK_SIGN);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW+"["+lang.get("Deliver")+"]");
        meta.setDisplayName(ChatColor.GREEN+lang.get("SendTo")+" "+recipient);
        item.setItemMeta(meta);
        ui.setItem(8,item);

        return ui;
    }

    public Inventory getTenSlotUI(String title, String recipient){
        Inventory ui = Bukkit.createInventory(null, 18, ChatColor.LIGHT_PURPLE+title);

        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        ui.setItem(0, item);
        ui.setItem(1, item);
        ui.setItem(9, item);
        ui.setItem(10, item);
        ui.setItem(7, item);
        ui.setItem(8, item);
        ui.setItem(16, item);

        item = new ItemStack(Material.DARK_OAK_SIGN);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW+"["+lang.get("Deliver")+"]");
        meta.setDisplayName(ChatColor.GREEN+lang.get("SendTo")+" "+recipient);
        item.setItemMeta(meta);
        ui.setItem(17,item);

        return ui;
    }

    public Inventory getFifteenSlotUI(String title, String recipient){
        Inventory ui = Bukkit.createInventory(null, 27, ChatColor.LIGHT_PURPLE+title);

        ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        item.setItemMeta(meta);

        ui.setItem(0, item);
        ui.setItem(1, item);
        ui.setItem(9, item);
        ui.setItem(10, item);
        ui.setItem(7, item);
        ui.setItem(8, item);
        ui.setItem(16, item);
        ui.setItem(17, item);
        ui.setItem(18, item);
        ui.setItem(19, item);
        ui.setItem(25, item);

        item = new ItemStack(Material.DARK_OAK_SIGN);
        meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.YELLOW+"["+lang.get("Deliver")+"]");
        meta.setDisplayName(ChatColor.GREEN+lang.get("SendTo")+" "+recipient);
        item.setItemMeta(meta);
        ui.setItem(26,item);

        return ui;
    }

}



