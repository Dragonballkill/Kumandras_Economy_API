package me.jaymar921.economy.datahandlers;

import me.jaymar921.economy.Economy;
import me.jaymar921.economy.datahandlers.Configurations.CustomQuest;
import me.jaymar921.economy.datahandlers.Configurations.QuestConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.LinkedList;
import java.util.List;

public class QuestDataHandler {


    private final QuestConfiguration questConfiguration;
    private CustomQuest customQuest;
    private List<QuestData> questData;
    private boolean hasAnimalQuest = false;
    private boolean hasVillagerQuest = false;
    public QuestDataHandler(Economy main){
        questData = new LinkedList<>();
        questConfiguration = new QuestConfiguration(main);
        if(main.getVersion().support_1_17()){
            main.getLogger().info(ChatColor.GREEN+"Loaded ["+loadData()+ "] Economy Quests");
            loadQuests();
            if(Bukkit.getServer().getPluginManager().getPlugin("CustomEnchantments")!=null){
                if(!Bukkit.getServer().getPluginManager().getPlugin("CustomEnchantments").getDescription().getAuthors().contains("JayMar921"))
                    return;
                customQuest = new CustomQuest(main);
                main.getLogger().info(ChatColor.GREEN+"Loaded ["+loadCustomQuest()+ "] Quests from Custom Enchantments");
            }
        }else{
            main.getLogger().info(ChatColor.GREEN+"Quests only support 1.17");
        }
    }
    public void loadQuests(){
        for(QuestData q : questData)
            if(q.getType().toLowerCase().contains("villager"))
                hasVillagerQuest = true;
            else if(q.getType().toLowerCase().contains("animal"))
                hasAnimalQuest = true;
    }


    public List<QuestData> getQuestData() {
        return questData;
    }
    public boolean hasAnimalQuest(){return hasAnimalQuest;}
    public boolean hasVillagerQuest(){return hasVillagerQuest;}
    @SuppressWarnings("unchecked")
    public int loadData(){
        if(questConfiguration.getConfig().contains("QUESTS"))
            questData = (List<QuestData>) questConfiguration.getConfig().getList("QUESTS");
        return questData.size();
    }

    @SuppressWarnings("unchecked")
    public int loadCustomQuest(){
        if(customQuest.getConfig().contains("QUESTS")){
            List<QuestData> data = (List<QuestData>) customQuest.getConfig().getList("QUESTS");
            for(QuestData d : data)
                questData.add(d);
            return data.size();
        }
        return 0;
    }


}


