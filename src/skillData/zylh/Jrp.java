package skillData.zylh;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import skillData.Skill;
import org.bukkit.util.Vector;


public class Jrp extends Skill {
    public Jrp() {
            super("3");
        }

    @Override
    public void doskill(Player player, LivingEntity entity) {

        Location entityLocation=entity.getLocation();
        Vector vector=entityLocation.getDirection();
        Location playerLocation=entityLocation.add(-vector.getX(),-vector.getY(),-vector.getZ());
        player.teleport(playerLocation);

    }
}
