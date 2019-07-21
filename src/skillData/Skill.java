package skillData;

import main.SkillUtil;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Skill {


    public String cd;

    public String around;

    public String damage;

    public int level=0;

    private int maxlevel=1;

    private String id;

    public boolean learn=false;

    public String name;

    public Skill(String id){
        this.id=id;
    }

    public void setDamage(String damage){
        this.damage=damage;
    }

    public int getDamage(double Damge,double Magic){
        return SkillUtil.getCaluteValue(damage.replace("level",String.valueOf(level)).replace("Damage",String.valueOf(Damge)).replace("Magic",String.valueOf(Magic)));
    }

    public int getMaxlevel(){
        return maxlevel;
    }


    public void setMaxlevel(int maxlevel){
        this.maxlevel=maxlevel;
    }

    public void setCd(String cd){
        this.cd=cd;
    }

    public int getCd(){
        String s=cd.replace("level",String.valueOf(level));
        System.out.println(s);
        System.out.println(level);
        System.out.println(SkillUtil.getCaluteValue(cd.replace("level",String.valueOf(level))));
        return SkillUtil.getCaluteValue(cd.replace("level",String.valueOf(level)));
    }

    public void setAround(String around){
        this.around=around;
    }

    public int getAround(){
        System.out.println(SkillUtil.getCaluteValue(around.replace("level",String.valueOf(level))));
        return SkillUtil.getCaluteValue(around.replace("level",String.valueOf(level)));
    }

    public void doskill(Player player){

    }

    public void doskill(Player player, LivingEntity entity) {

    }
    @Override
    public String toString() {
        System.out.println(getCd());
        System.out.println(getAround());
        return "{" +
                "\"id\":" + id +
                ",\"name\":\""+name+"\""+
                ",\"coolDown\":" + getCd() +
                ",\"learn\":"+learn+
                ",\"around\":" + getAround() +
                ",\"level\":" + level +
                ",\"maxlevel\":"+maxlevel+
                '}';
    }
}
