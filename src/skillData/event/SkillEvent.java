package skillData.event;

import config.ConfigData;
import netWork.SendMessge;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import redis.PlayerRedisData;
import skillerData.Career;


public class SkillEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player=event.getPlayer();
        String classname = ConfigData.data.getString("zhiyeinfo." + player.getName());
        if (classname==null){
                return;
        }
        Career career = ConfigData.LoadPlayerData(classname);
        PlayerRedisData.playerCareerMap.put(player.getName(),career);
        SendMessge.sendSkillInfo(player);
    }

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        Career career = PlayerRedisData.getCareer(event.getPlayer().getName());
        if (career!=null){
            ConfigData.SavePlayerData(career,event.getPlayer());
        }
    }





}
