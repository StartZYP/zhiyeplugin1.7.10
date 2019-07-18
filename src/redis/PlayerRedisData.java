package redis;

import org.bukkit.entity.Player;
import skillerData.Career;

import java.util.HashMap;
import java.util.Map;

public class PlayerRedisData {
    public static Map<String, Career> playerCareerMap=new HashMap<>();

    public static void initCareer(String playername,Career career){
        playerCareerMap.put(playername,career);
    }

    public static Career getCareer(String playername){
        if (playerCareerMap.containsKey(playername)){
            return playerCareerMap.get(playername);
        }
        return null;
    }

    public static void instanceCareer(){


    }



}
