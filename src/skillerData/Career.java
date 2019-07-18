package skillerData;

import skillData.Skill;

import java.util.ArrayList;
import java.util.List;

public class Career {
    public CareerEnum career;

    public int level=1;
    public int fight=11;
    //public int maxlevel;
    public int skillID;

    public Skill[] skills=new Skill[5];

    public Career(CareerEnum careerEnum){
        this.career=careerEnum;
    }

    @Override
    public String toString() {
        return "{" +
                "\"packid\":0" +
                ",\"career\":\"" +career.getName()+"\""+
                ",\"skillID\":"+skillID+
                ",\"level\":"+level+
                ",\"fight\":"+fight+
                ",\"skillLevel0\":"+skills[0].toString()+
                ",\"skillLevel1\":"+skills[1].toString()+
                ",\"skillLevel2\":"+skills[2].toString()+
                ",\"skillLevel3\":"+skills[3].toString()+
                ",\"skillLevel4\":"+skills[4].toString()+"}";
    }
}
