package config;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import main.Main;
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

    public static void SavePlayerData(Career career,Player player){
        String save = "";
        if (career.skillID==0){
            save += CareerEnum.CONTROLLER.getName()+"-";
        }else if (career.skillID==1){
            save+=CareerEnum.BONEFIRE.getName()+"-";
        }
        for (int i=0;i<=4;i++){
            save += career.skills[i].level+"-";
        }
        data.set("zhiyeinfo."+player.getName(),save);
        try {
            data.save(new File(Main.plugin.getDataFolder(), "data.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Career LoadPlayerData(String playerdata){
        String[] PlayerDataArray = playerdata.split("-");
        Career career = null;
        if (CareerEnum.CONTROLLER.getName().equals(PlayerDataArray[0])) {
            career= ZYKZZ.instanceZykzz();
        }else if(CareerEnum.BONEFIRE.getName().equals(PlayerDataArray[0])){
            career= ZYNH.instanceZYNH();
        }
        for (int i=0;i<=4;i++){
            if (Integer.parseInt(PlayerDataArray[i+1])!=0){
                career.skills[i].learn =true;
                career.skills[i].level = Integer.parseInt(PlayerDataArray[i+1]);
            }

        }
        try {
            data.save(new File(Main.plugin.getDataFolder(), "data.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return career;
    }



    public static void PlayerDatainit(Plugin plugin){
        File loc = new File(plugin.getDataFolder(), "data.yml");
        if (!loc.exists()) {
            plugin.saveResource("data.yml",true);
            loc = new File(plugin.getDataFolder(), "data.yml");
        }
        data = YamlConfiguration.loadConfiguration(loc);
    }


//    public static void readData(Plugin plugin){
//        for (String name:data.getKeys(false)) {
//            String career = data.getString(name + ".careername");
//            Career career1 = null;
//            if (CareerEnum.BONEFIRE.getName().equals(career)) {
//                 career1= ZYKZZ.instanceZykzz();
//            }else if(CareerEnum.CONTROLLER.getName().equals(career)){
//                 career1= ZYNH.instanceZYNH();
//            }
//            career1.level=data.getInt(name+".level");
//            career1.fight=data.getInt(name+".fight");
//            career1.skills[0].level=data.getInt(name+".skillLevel0.level");
//            if(career1.skills[0].level>0) {
//                career1.skills[0].learn = true;
//            }
//            career1.skills[1].level=data.getInt(name+".skillLevel1.level");
//            if(career1.skills[1].level>0) {
//                career1.skills[1].learn = true;
//            }
//            career1.skills[2].level=data.getInt(name+".skillLevel2.level");
//            if(career1.skills[2].level>0) {
//                career1.skills[2].learn = true;
//            }
//            career1.skills[3].level=data.getInt(name+".skillLevel3.level");
//            if(career1.skills[3].level>0) {
//                career1.skills[3].learn = true;
//            }
//            career1.skills[4].level=data.getInt(name+".skillLevel4.level");
//            if(career1.skills[4].level>0) {
//                career1.skills[4].learn = true;
//            }
//        }
//    }
}
