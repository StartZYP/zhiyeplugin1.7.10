package config;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import redis.PlayerRedisData;
import skillerData.Career;
import skillerData.CareerEnum;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigData {
    public static FileConfiguration cfg;

    public static FileConfiguration data;

    public static void loadConfig(Plugin plugin) {
        File loc = new File(plugin.getDataFolder(), "config.yml");
        if (!loc.exists()) {
            plugin.saveDefaultConfig();
            loc = new File(plugin.getDataFolder(), "config.yml");
        }
        cfg = YamlConfiguration.loadConfiguration(loc);
    }

    public static void saveData(Plugin plugin){
        File loc = new File(plugin.getDataFolder(), "data.yml");
        if (!loc.exists()) {
            plugin.saveResource("data.yml",true);
            loc = new File(plugin.getDataFolder(), "data.yml");
        }
        data = YamlConfiguration.loadConfiguration(loc);

        for (Map.Entry<String, Career> m:PlayerRedisData.playerCareerMap.entrySet()) {
            Player player= Bukkit.getPlayer(m.getKey());
            Career career=m.getValue();
            //
            data.set(player.getName()+".careername",career.career.getName());
            data.set(player.getName()+".level",career.level);
            data.set(player.getName()+".fight",career.fight);
            //
            data.set(player.getName()+".skillLevel0.level",career.skills[0].level);
            //
            data.set(player.getName()+".skillLevel1.level",career.skills[1].level);
            //
            data.set(player.getName()+".skillLevel2.level",career.skills[2].level);
            //
            data.set(player.getName()+".skillLevel3.level",career.skills[3].level);
            //
            data.set(player.getName()+".skillLevel4.level",career.skills[4].level);
        }
        try {
            data.save(loc);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readData(Plugin plugin){
        File loc = new File(plugin.getDataFolder(), "data.yml");
        if (!loc.exists()) {
            plugin.saveResource("data.yml",true);
            loc = new File(plugin.getDataFolder(), "data.yml");
        }
        data = YamlConfiguration.loadConfiguration(loc);

        for (String name:data.getKeys(false)) {
            String career = data.getString(name + ".careername");
            Career career1 = null;
            if (CareerEnum.BONEFIRE.getName().equals(career)) {
                 career1= ZYKZZ.instanceZykzz();
            }else if(CareerEnum.CONTROLLER.getName().equals(career)){
                 career1= ZYNH.instanceZYNH();
            }
            career1.level=data.getInt(name+".level");
            career1.fight=data.getInt(name+".fight");
            career1.skills[0].level=data.getInt(name+".skillLevel0.level");
            if(career1.skills[0].level>0) {
                career1.skills[0].learn = true;
            }
            career1.skills[1].level=data.getInt(name+".skillLevel1.level");
            if(career1.skills[1].level>0) {
                career1.skills[1].learn = true;
            }
            career1.skills[2].level=data.getInt(name+".skillLevel2.level");
            if(career1.skills[2].level>0) {
                career1.skills[2].learn = true;
            }
            career1.skills[3].level=data.getInt(name+".skillLevel3.level");
            if(career1.skills[3].level>0) {
                career1.skills[3].learn = true;
            }
            career1.skills[4].level=data.getInt(name+".skillLevel4.level");
            if(career1.skills[4].level>0) {
                career1.skills[4].learn = true;
            }
        }
    }
}
