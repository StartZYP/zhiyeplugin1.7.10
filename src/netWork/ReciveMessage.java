package netWork;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.bukkit.scheduler.BukkitRunnable;
import redis.PlayerRedisData;
import skillData.SkillCoolDown;
import skillData.event.SkillEvent;
import skillerData.Career;
import skillerData.CareerEnum;

import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ReciveMessage implements PluginMessageListener {

    Map<Player, SkillCoolDown> playerSkillCoolDownMap=new HashMap<>();

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        String recive=new String(bytes, StandardCharsets.UTF_8);
        JsonObject jsonObject= (JsonObject) new JsonParser().parse(recive);
        String type=jsonObject.get("type").getAsString();

        System.out.println(jsonObject.toString());

        if(type.equals("startskill")){
            onStartSkill(jsonObject);
        }else if(type.equals("skillup")){
            onSkillUp(jsonObject);
        }

    }

    public void onStartSkill(JsonObject jsonObject){
        System.out.println(jsonObject.toString());
        int id=jsonObject.get("id").getAsInt();
        String playerName = jsonObject.get("name").getAsString();
        final Player player = Bukkit.getPlayer(playerName);

        System.out.println("开始执行技能"+id);

        if(PlayerRedisData.getCareer(player.getName())==null)return;
        System.out.println("有职业");
        SkillCoolDown skillCoolDown;
        if (playerSkillCoolDownMap.get(player)==null) {
            skillCoolDown = new SkillCoolDown();
            skillCoolDown.career=PlayerRedisData.getCareer(player.getName());
            skillCoolDown.cd[id]=0;
            playerSkillCoolDownMap.put(player,skillCoolDown);
        }else {
            skillCoolDown=playerSkillCoolDownMap.get(player);

        }
        if(skillCoolDown.Iscd(id)){
            return;
        }
        skillCoolDown.cd[id]=Calendar.getInstance().getTimeInMillis();

        System.out.println("可以释放");

        if(id!=4) {
            int entityid = jsonObject.get("entityid").getAsInt();
            LivingEntity vic = getEntity(entityid, player.getWorld().getLivingEntities());
            if(vic!=null){
                vic.damage(PlayerRedisData.getCareer(player.getName()).skills[id].getDamage());
            }
        }else if(id==4)
        {
            JsonArray entityid = jsonObject.get("entityid").getAsJsonArray();
            List<Integer> entities=new ArrayList<>();
            Iterator it=  entityid.iterator();
            while (it.hasNext()){
                JsonElement e= (JsonElement) it.next();
                entities.add(e.getAsInt());
            }
            List<LivingEntity> livingEntities=getEntities(entities,player.getWorld().getLivingEntities());
            for (LivingEntity livingEntity:livingEntities) {
                livingEntity.damage(PlayerRedisData.getCareer(player.getName()).skills[id].getDamage());
            }
        }
        //System.out.println(jsonObject.toString());
        if(PlayerRedisData.getCareer(player.getName()).career== CareerEnum.CONTROLLER){
            switch (id){
                case 0:
                    SendMessge.sendAllPlayer(player,0,id,Integer.valueOf("1"));
                    break;
                case 2:
                    SendMessge.sendAllPlayer(player,0,id,Integer.valueOf("2"));
                    break;
                case 3:
                    SendMessge.sendAllPlayer(player,0,id,Integer.valueOf("3"));
                    break;
                case 4:
                    PlayerRedisData.getCareer(player.getName()).skills[4].doskill(player);
//                    SkillEvent.playerBooleanMap.put(player,true);
//
//                    new BukkitRunnable(){
//                        @Override
//                        public void run() {
//                            PlayerRedisData.getCareer(player.getName()).skills[4].doskill(player);
//                            SkillEvent.playerBooleanMap.put(player,false);
//                        }
//                    }.runTaskLater(Main.plugin,20l);
                    SendMessge.sendAllPlayer(player,0,id,Integer.valueOf("4"));
                    break;
            }
        }else if(PlayerRedisData.getCareer(player.getName()).career== CareerEnum.BONEFIRE){
            switch (id){
                case 0:
                    SendMessge.sendAllPlayer(player,1,id,Integer.valueOf("5"));
                    break;
                case 2:
                    SendMessge.sendAllPlayer(player,1,id,Integer.valueOf("6"));
                    break;
                case 3:
                    int entityid = jsonObject.get("entityid").getAsInt();
                    LivingEntity vic = getEntity(entityid, player.getWorld().getLivingEntities());
                    PlayerRedisData.getCareer(player.getName()).skills[3].doskill(player,vic);
                    SendMessge.sendAllPlayer(player,1,id,Integer.valueOf("7"));
                    break;
                case 4:
                    int vicid = jsonObject.get("entityid").getAsInt();
                    LivingEntity vicc = getEntity(vicid, player.getWorld().getLivingEntities());
                    PlayerRedisData.getCareer(player.getName()).skills[4].doskill(player,vicc);
                    SendMessge.sendAllPlayer(player,1,id,Integer.valueOf("8"));
                    break;
            }
        }
    }



    public void onSkillUp(JsonObject jsonObject){
        Player player=Bukkit.getPlayer(jsonObject.get("name").getAsString());
        if(PlayerRedisData.getCareer(player.getName())==null)return;
        Career career=PlayerRedisData.getCareer(player.getName());
        if (career==null){
            player.sendMessage("§e§l请先选择职业");
            return;
        }
        int id=jsonObject.get("id").getAsInt();
        Inventory inc=player.getInventory();
        //ItemStack itemInHand = player.getItemInHand();
        boolean hasBook=false;
        for (int i=0;i<inc.getSize();i++) {
            if(inc.getItem(i)==null||inc.getItem(i).getType()== Material.AIR)continue;
            if(!inc.getItem(i).hasItemMeta())continue;
            ItemMeta itemMeta=inc.getItem(i).getItemMeta();
            if(!itemMeta.hasLore())continue;
            int amount=inc.getItem(i).getAmount();
            List<String> lores=itemMeta.getLore();
            for (String skill:lores) {
                String s = ChatColor.stripColor(skill);
                //System.out.println(skill);
                if(s.contains("["+career.skills[id].name+"]")&&s.contains(String.valueOf(career.skills[id].level+1))){
                    if(amount==1){
                        inc.setItem(i,new ItemStack(Material.AIR));
                        hasBook=true;
                        break;
                    }else {
                        ItemStack itemStack=inc.getItem(i);
                        itemStack.setAmount(amount-1);
                        inc.setItem(i,itemStack);
                        hasBook=true;
                        break;
                    }
                }
            }
        }
        if(!hasBook){
            player.sendMessage("§e§l你身上没有合适的技能书,需要"+career.skills[id].name+"等级"+career.skills[id].level);
            return;
        }
        if(career.skills[id].level<career.skills[id].getMaxlevel()){
            career.skills[id].level=career.skills[id].level+1;
            career.skills[id].learn=true;
        }
        SendMessge.sendSkillInfo(player);
    }

    public LivingEntity getEntity(int entityid, List<LivingEntity> livingEntities){
        for (LivingEntity livingEntity:livingEntities) {
            if(livingEntity.getEntityId()==entityid){
                return livingEntity;
            }
        }
        return null;
    }

    public List<LivingEntity> getEntities(List<Integer> entityid, List<LivingEntity> livingEntities){
        List<LivingEntity> livingEntities1=new ArrayList<>();
        for (int eid:entityid) {
            for (LivingEntity livingEntity:livingEntities) {
                if(livingEntity.getEntityId()==eid){
                    livingEntities1.add(livingEntity);
                    break;
                }
            }
        }
        return livingEntities1;
    }
}
