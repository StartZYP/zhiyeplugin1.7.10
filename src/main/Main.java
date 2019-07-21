package main;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import command.Command;
import config.ConfigData;
import netWork.ReciveMessage;
import netWork.SendMessge;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import redis.PlayerRedisData;
import rpg.Config;
import rpg.States;
import skillData.event.SkillEvent;

import java.util.Iterator;
import java.util.Map;


public class Main extends JavaPlugin{

    public static Plugin plugin;
    public static YamlConfiguration playerdata;

    @Override
    public void onEnable() {
        getServer().getMessenger().registerIncomingPluginChannel(this,"professionalskills", new ReciveMessage());
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "professionalskills");
        plugin=this;
        ConfigData.PlayerDatainit(this);
        ConfigData.loadConfig(this);
        Config.loadConfig(this);
        Config.getStatesData();
        Bukkit.getPluginCommand("zy").setExecutor(new Command());
        Bukkit.getPluginCommand("sx").setExecutor(new States());
        Bukkit.getPluginManager().registerEvents(new States(),this);
        Bukkit.getPluginManager().registerEvents(new SkillEvent(),this);
        ZYKZZ.loadSkillConfig();
        ZYNH.loadSkillConfig();
        //ConfigData.readData(this);
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player:Bukkit.getServer().getOnlinePlayers()){
                    SendMessge.sendRpgInfo(player);
                }
            }
        }.runTaskTimer(Main.plugin,20l,40l);
        //System.out.println("职业加载");
        //System.out.println(ZYNH.instanceZYNH().toString());
    }


    @Override
    public void onDisable() {
        super.onDisable();
        Iterator i$ = States.playerZDL.entrySet().iterator();

        while (i$.hasNext()) {
            Map.Entry<String, Integer> s = (Map.Entry) i$.next();
            Config.config.set("zdl." + (String) s.getKey(), s.getValue());
        }

        Config.save(this);
    }
}
