package ZhiYe;

import config.ConfigData;
import org.bukkit.entity.Player;
import skillData.zykzz.*;
import skillerData.Career;
import skillerData.CareerEnum;

public class ZYKZZ {


    //普通
    public static int ptMaxLevel=1;
    public static String ptskilldamage="1";
    public static String ptskillrange="1";
    public static String ptskillcd="1";
    //魔法印记
    public static int yjMaxLevel=1;
    public static String yjskilldamage="1";
    //雷电
    public static int ldMaxLevel=1;
    public static String ldskilldamage="1";
    public static String ldskillrange="1";
    public static String ldskillcd="1";
    //等离子光波
    public static int dlzgbMaxLevel=1;
    public static String dlzgbskilldamage="1";
    public static String dlzgbskillrange="1";
    public static String dlzgbskillcd="1";
    //弑裂破
    public static int slpMaxLevel=1;
    public static String slpskilldamage="1";
    public static String slpskillrange="1";
    public static String slpskillcd="1";
    public static String slpreducedamage="1";

    public static void loadSkillConfig(){
        loadPutongSkill();
        loadDLZGBSkill();
        loadLDSkill();
        loadSLPSkill();
        loadYJSkill();
    }
    public static void loadPutongSkill(){
        ptMaxLevel= ConfigData.cfg.getInt("classes.zykzz.putong.maxlevel");
        ptskilldamage=ConfigData.cfg.getString("classes.zykzz.putong.skilldamage");
        ptskillrange=ConfigData.cfg.getString("classes.zykzz.putong.skillrange");
        ptskillcd=ConfigData.cfg.getString("classes.zykzz.putong.skillcd");

    }
    //
    public static void loadYJSkill(){
        yjMaxLevel=ConfigData.cfg.getInt("classes.zykzz.yinji.maxlevel");
        yjskilldamage=ConfigData.cfg.getString("classes.zykzz.yinji.skilldamage");
    }
    //
    public static void loadLDSkill(){
        ldMaxLevel=ConfigData.cfg.getInt("classes.zykzz.leidian.maxlevel");
        ldskilldamage=ConfigData.cfg.getString("classes.zykzz.leidian.skilldamage");
        ldskillrange=ConfigData.cfg.getString("classes.zykzz.leidian.skillrange");
        ldskillcd=ConfigData.cfg.getString("classes.zykzz.leidian.skillcd");
    }
    //
    public static void loadDLZGBSkill(){
        dlzgbMaxLevel=ConfigData.cfg.getInt("classes.zykzz.dlzgb.maxlevel");
        dlzgbskilldamage=ConfigData.cfg.getString("classes.zykzz.dlzgb.skilldamage");
        dlzgbskillrange=ConfigData.cfg.getString("classes.zykzz.dlzgb.skillrange");
        dlzgbskillcd=ConfigData.cfg.getString("classes.zykzz.dlzgb.skillcd");
    }
    //
    public static void loadSLPSkill(){
        slpMaxLevel=ConfigData.cfg.getInt("classes.zykzz.slp.maxlevel");
        slpskilldamage=ConfigData.cfg.getString("classes.zykzz.slp.skilldamage");
        slpskillrange=ConfigData.cfg.getString("classes.zykzz.slp.skillrange");
        slpskillcd=ConfigData.cfg.getString("classes.zykzz.slp.skillcd");
        slpreducedamage=ConfigData.cfg.getString("classes.zykzz.slp.reducedamage");
    }

    public static Career instanceZykzz(){
        Pt pt=new Pt();
        pt.setAround(ptskillrange);
        pt.setCd(ptskillcd);
        pt.setDamage(ptskilldamage);
        pt.setMaxlevel(ptMaxLevel);
        pt.name="普通攻击";

        Mf mf=new Mf();
        mf.setMaxlevel(yjMaxLevel);
        mf.setDamage(yjskilldamage);
        mf.setAround("10");
        mf.setCd("1");
        mf.name="魔法印记";

        Ld ld=new Ld();
        ld.setAround(ldskillrange);
        ld.setDamage(ldskilldamage);
        ld.setCd(ldskillcd);
        ld.setMaxlevel(ldMaxLevel);
        ld.name="唤电";

        Slp slp=new Slp();
        slp.setReduceDamage(slpreducedamage);
        slp.setAround(slpskillrange);
        slp.setCd(slpskillcd);
        slp.setDamage(slpskilldamage);
        slp.setMaxlevel(slpMaxLevel);
        slp.name="弑裂破";

        Dlzgb dlzgb=new Dlzgb();
        dlzgb.setAround(dlzgbskillrange);
        dlzgb.setCd(dlzgbskillcd);
        dlzgb.setDamage(dlzgbskilldamage);
        dlzgb.setMaxlevel(dlzgbMaxLevel);
        dlzgb.name="等离子光波";

        Career career=new Career(CareerEnum.CONTROLLER);
        career.skills[0]=(pt);
        career.skills[1]=(mf);
        career.skills[2]=(ld);
        career.skills[3]=(dlzgb);
        career.skills[4]=(slp);
        career.skillID = 0;
        return career;
    }



}
