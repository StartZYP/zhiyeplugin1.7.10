package skillData.zykzz;

import main.SkillUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import skillData.Skill;

public class Slp extends Skill {

    public String reduceDamage;

    public Slp() {
        super("4");
    }

    public void setReduceDamage(String string){
        reduceDamage=string;
    }

    public float getReduceDamage(){
        int re= SkillUtil.getCaluteValue(reduceDamage.replace("level",String.valueOf(level)));
        if(((float)re)/100.0f>=1) {
            return 1;
        }else {
            return ((float)re)/100.0f;
        }
    }

    @Override
    public void doskill(Player player) {
        Location location=player.getLocation().clone();
        Vector vector=player.getLocation().getDirection();

        System.out.println("长度"+vector.length());
        player.teleport(location.add(vector).add(vector).add(vector));

    }
}
