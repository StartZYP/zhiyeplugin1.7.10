package skillData.event;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import com.google.gson.Gson;
import main.Main;
import netWork.SendMessge;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import redis.PlayerRedisData;
import skillerData.Career;


public class SkillEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        final Player player=event.getPlayer();
        new BukkitRunnable(){
            @Override
            public void run() {
                SendMessge.sendRpgInfo(player);
            }
        }.runTaskTimer(Main.plugin,20l,40l);
        String classname = Main.plugin.getConfig().getString("zhiyeinfo." + player.getName());
        if (classname==null){
                return;
        }
        Gson gson = new Gson();
        Career career = gson.fromJson(classname, Career.class);
        System.out.println(career);
        PlayerRedisData.playerCareerMap.put(player.getName(),career);
        new BukkitRunnable(){
            @Override
            public void run() {
                SendMessge.sendSkillInfo(player);
            }
        }.runTaskLater(Main.plugin,20l);

    }

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        Career career = PlayerRedisData.getCareer(event.getPlayer().getName());
        if (career!=null){
            Gson gson = new Gson();
            Main.plugin.getConfig().set("zhiyeinfo."+event.getPlayer().getName(),gson.toJson(career));
            Main.plugin.saveConfig();
        }
    }

//    public static Map<Player,Boolean> playerBooleanMap=new HashMap<>();
//    @EventHandler
//    public void onPLayer(PlayerMoveEvent event){
//        Player player=event.getPlayer();
//        if(!playerBooleanMap.get(player)){
//            event.setCancelled(true);
//        }
//    }



}
