package skillData;

import org.bukkit.entity.Player;
import skillerData.Career;

import java.util.Calendar;

public class SkillCoolDown {

    public long[] cd=new long[5];
    public Career career;

    public boolean Iscd(int skillid){
        System.out.println("现在的时间"+(Calendar.getInstance().getTimeInMillis()-cd[skillid])/1000.0);
        System.out.println("cd"+career.skills[skillid].getCd());
        if((Calendar.getInstance().getTimeInMillis()-cd[skillid])/1000.0>=career.skills[skillid].getCd()){
            return false;
        }
        return true;
    }
}
