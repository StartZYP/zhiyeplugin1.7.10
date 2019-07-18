package netWork;

import main.Main;
import net.minecraft.util.io.netty.buffer.ByteBuf;
import net.minecraft.util.io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import redis.PlayerRedisData;
import rpg.States;
import skillData.Skill;
import skillerData.Career;

import java.nio.charset.Charset;
import java.util.List;

public class SendMessge {

    public static void sendMessage(Player player,String mess){

    }

    public static void sendSkillInfo(Player player){
        Career career=PlayerRedisData.getCareer(player.getName());
        ByteBuf bb= Unpooled.buffer();
        bb.writeInt(0);
        player.sendPluginMessage(Main.plugin,"professionalskills",career.toString().getBytes(Charset.forName("GBK")));
        System.out.println(career.toString());
    }


    public static void sendRpgInfo(Player player){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("{\"packid\":10");
        stringBuilder.append(",\"attack\":"+ States.playerAttr.get(player.getName()).getDamage());
        stringBuilder.append(",\"coolingReduction\":"+1);
        stringBuilder.append(",\"attackSpeed\":"+States.playerAttr.get(player.getName()).getDamagespeed());
        stringBuilder.append(",\"hp\":"+States.playerAttr.get(player.getName()).getHealth());
        stringBuilder.append(",\"critDamage\":"+States.playerAttr.get(player.getName()).getBaojidamage());
        stringBuilder.append(",\"armor\":"+States.playerAttr.get(player.getName()).getDenfense());
        stringBuilder.append(",\"critRate\":"+States.playerAttr.get(player.getName()).getBaoji());
        stringBuilder.append(",\"movingSpeed\":"+States.playerAttr.get(player.getName()).getMovespeed());
        stringBuilder.append(",\"spellPower\":"+States.playerAttr.get(player.getName()).getMagic());
        stringBuilder.append(",\"zdl\":"+States.playerAttr.get(player.getName()).getZdl());
        stringBuilder.append("}");
        ByteBuf cc= Unpooled.buffer();
        cc.writeInt(10);
        player.sendPluginMessage(Main.plugin,"professionalskills",stringBuilder.toString().getBytes(Charset.forName("GBK")));

    }

    public static void sendAllPlayer(Player player,int careerID,int skillId,int packetid){
        World world=player.getWorld();
        List<Player> playerList=world.getPlayers();
        String s="{\"packid\":"+packetid+",\"id\":"+careerID+",\"name\":\""+player.getName()+"\",\"skilld\":"+skillId+"}";
        for (Player player1:playerList) {
            if(player1.getName().equals(player.getName()))continue;
            player1.sendPluginMessage(Main.plugin,"professionalskills",s.getBytes(Charset.forName("GBK")));
        }
    }
}
