package command;

import ZhiYe.ZYKZZ;
import ZhiYe.ZYNH;
import com.google.gson.Gson;
import main.Main;
import main.SkillUtil;
import netWork.SendMessge;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redis.PlayerRedisData;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        Player player= (Player) commandSender;
        if(strings.length==1){
            Gson gson = new Gson();
            if(strings[0].equals("zykzz")){
                if(PlayerRedisData.getCareer(player.getName())==null) {
                    PlayerRedisData.initCareer(player.getName(), ZYKZZ.instanceZykzz());
                    SendMessge.sendSkillInfo(player);
                    Main.plugin.getConfig().set("zhiyeinfo."+player.getPlayer().getName(),gson.toJson(PlayerRedisData.getCareer(player.getName())));
                    Main.plugin.saveConfig();
                }
            }else if(strings[0].equals("zylh")) {
                if (PlayerRedisData.getCareer(player.getName()) == null) {
                    PlayerRedisData.initCareer(player.getName(), ZYNH.instanceZYNH());
                    SendMessge.sendSkillInfo(player);
                    player.sendMessage("§e§l成功选择职业");
                    Main.plugin.getConfig().set("zhiyeinfo."+player.getPlayer().getName(),gson.toJson(PlayerRedisData.getCareer(player.getName())));
                    Main.plugin.saveConfig();
                }
            }
        }
        if(strings.length==2){
            if(strings[0].equals("zz")&&strings.length==2){
                if(strings[1].equals("zykzz")){
                    PlayerRedisData.initCareer(player.getName(), ZYKZZ.instanceZykzz());
                }else if(strings[1].equals("zylh")){
                    PlayerRedisData.initCareer(player.getName(), ZYNH.instanceZYNH());
                }
            }
        }
        return false;
    }


}
