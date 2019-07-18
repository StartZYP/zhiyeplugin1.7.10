package main;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import command.Command;
import config.ConfigData;
import netWork.ReciveMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import rpg.Config;
import rpg.States;
import skillData.event.SkillEvent;

import java.util.Iterator;
import java.util.Map;

import static config.ConfigData.loadConfig;
import static config.ConfigData.saveData;

public class Main extends JavaPlugin{

    public static Plugin plugin;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this,"professionalskills", new ReciveMessage());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "professionalskills");
        plugin=this;
        Config.loadConfig(this);
        Config.getStatesData();
        Bukkit.getPluginCommand("zy").setExecutor(new Command());
        Bukkit.getPluginCommand("sx").setExecutor(new States());
        Bukkit.getPluginManager().registerEvents(new States(),this);
        Bukkit.getPluginManager().registerEvents(new SkillEvent(),this);
        loadConfig(this);
        ZYKZZ.loadSkillConfig();
        ZYNH.loadSkillConfig();
        ConfigData.readData(this);
        System.out.println("职业加载");
        System.out.println(ZYNH.instanceZYNH().toString());
    }


    @Override
    public void onDisable() {
        ConfigData.saveData(this);
        super.onDisable();
        Iterator i$ = States.playerZDL.entrySet().iterator();

        while (i$.hasNext()) {
            Map.Entry<String, Integer> s = (Map.Entry) i$.next();
            Config.config.set("zdl." + (String) s.getKey(), s.getValue());
        }

        Config.save(this);
    }
}
