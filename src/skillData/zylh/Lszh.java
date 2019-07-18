package skillData.zylh;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import skillData.Skill;

public class Lszh extends Skill {
    public Lszh() {
        super("4");
    }

    @Override
    public void doskill(Player player, LivingEntity entity) {
        Location playerLocation=player.getLocation();
        Location entityLocation=entity.getLocation();
        Vector vector=new Vector(
                entityLocation.getX()-playerLocation.getX()*1.5,
                entityLocation.getY()-playerLocation.getY()*1.5+0.2,
                entityLocation.getZ()-playerLocation.getZ()*1.5);
        player.setVelocity(vector);
    }
}
