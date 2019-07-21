package rpg;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

public class Config {
    public static YamlConfiguration config;
    public static String damage;
    public static String denfense;
    public static String magic;
    public static String damagespeed;
    public static String health;
    public static String movespeed;
    public static String zdl;
    public static String baoji;
    public static String baojidamage;

    public Config() {
    }

    public static void loadConfig(Plugin plugin) {
        File loc = new File(plugin.getDataFolder(), "rpg.yml");
        if (!loc.exists()) {
            plugin.saveResource("rpg.yml",true);
            loc = new File(plugin.getDataFolder(), "rpg.yml");
        }
        config = YamlConfiguration.loadConfiguration(loc);
    }

    public static void getStatesData() {
        damage = config.getString("state.damage");
        System.out.println(damage);
        denfense = config.getString("state.denfense");
        System.out.println(denfense);
        magic = config.getString("state.magic");
        System.out.println(magic);
        damagespeed = config.getString("state.damagespeed");
        System.out.println(damagespeed);
        health = config.getString("state.health");
        System.out.println(health);
        movespeed = config.getString("state.movespeed");
        System.out.println(movespeed);
        zdl = config.getString("state.zdl");
        System.out.println(movespeed);
        baoji=config.getString("state.baoji");
        baojidamage=config.getString("state.baojidamage");

        Iterator i$ = config.getConfigurationSection("zdl").getValues(false).entrySet().iterator();

        while(i$.hasNext()) {
            Map.Entry<String, Object> playerzdl = (Map.Entry)i$.next();
            System.out.println((String)playerzdl.getKey());
            States.playerZDL.put(playerzdl.getKey(), config.getInt("zdl." + (String)playerzdl.getKey()));
        }

    }

    public static void save(Plugin plugin) {
        File loc = new File(plugin.getDataFolder(), "rpg.yml");
        if (!loc.exists()) {
            plugin.saveResource("rpg.yml",true);
            loc = new File(plugin.getDataFolder(), "rpg.yml");
        }

        try {
            config.save(loc);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }
}
