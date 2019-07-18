package ZhiYe;

import config.ConfigData;
import skillData.zylh.*;
import skillerData.Career;
import skillerData.CareerEnum;

public class ZYNH {
    //普通
    public static int ptMaxLevel=1;
    public static String ptskilldamage="1";
    public static String ptskillrange="1";
    public static String ptskillcd="1";
    //杀戮之力
    public static int slzlMaxLevel=1;
    public static String slzlskillup="1";
    //浴火光环
    public static int yhghMaxLevel=1;
    public static String yhghskillhp="1";
    public static String yhghskillcd="1";
    //剑刃破
    public static int jrpMaxLevel=1;
    public static String jrpskilldamage="1";
    public static String jrpskillrange="1";
    public static String jrpskillcd="1";
    //戮神召唤
    public static int lszhMaxLevel=1;
    public static String lszhskilldamage="1";
    public static String lszhskillcd="1";
    public static String lszhskillrange="1";

    public static void loadSkillConfig(){
        ptMaxLevel= ConfigData.cfg.getInt("classes.zylh.putong.maxlevel");
        ptskilldamage=ConfigData.cfg.getString("classes.zylh.putong.skilldamage");
        ptskillrange=ConfigData.cfg.getString("classes.zylh.putong.skillrange");
        ptskillcd=ConfigData.cfg.getString("classes.zylh.putong.skillcd");

        slzlMaxLevel=ConfigData.cfg.getInt("classes.zylh.slzl.maxlevel");
        slzlskillup=ConfigData.cfg.getString("classes.zylh.slzl.up");

        yhghMaxLevel=ConfigData.cfg.getInt("classes.zylh.yhgh.maxlevel");
        yhghskillhp=ConfigData.cfg.getString("classes.zylh.yhgh.addhealth");
        yhghskillcd=ConfigData.cfg.getString("classes.zylh.yhgh.skillcd");

        jrpMaxLevel=ConfigData.cfg.getInt("classes.zylh.jrp.maxlevel");
        jrpskilldamage=ConfigData.cfg.getString("classes.zylh.jrp.skilldamage");
        jrpskillrange=ConfigData.cfg.getString("classes.zylh.jrp.skillrange");
        jrpskillcd=ConfigData.cfg.getString("classes.zylh.jrp.skillcd");

        lszhMaxLevel=ConfigData.cfg.getInt("classes.zylh.lszh.maxlevel");
        lszhskilldamage=ConfigData.cfg.getString("classes.zylh.lszh.skilldamage");
        lszhskillrange=ConfigData.cfg.getString("classes.zylh.lszh.skillrange");
        lszhskillcd=ConfigData.cfg.getString("classes.zylh.lszh.skillcd");
    }


    public static Career instanceZYNH(){
        Pt pt=new Pt();
        pt.setMaxlevel(ptMaxLevel);
        pt.setDamage(ptskilldamage);
        pt.setAround(ptskillrange);
        pt.setCd(ptskillcd);
        pt.name="普通攻击";

        Slzl slzl=new Slzl();
        slzl.up=slzlskillup;
        slzl.setMaxlevel(slzlMaxLevel);
        slzl.setCd("1");
        slzl.setAround("1");
        slzl.name="杀戮之力";

        Yhgh yhgh=new Yhgh();
        yhgh.setMaxlevel(yhghMaxLevel);
        yhgh.hp=yhghskillhp;
        yhgh.setCd(yhghskillcd);
        yhgh.setAround("1");
        yhgh.name="浴火光环";

        Jrp jrp =new Jrp();
        jrp.setMaxlevel(jrpMaxLevel);
        jrp.setAround(jrpskillrange);
        jrp.setDamage(jrpskilldamage);
        jrp.setCd(jrpskillcd);
        jrp.name="剑刃破";

        Lszh lszh=new Lszh();
        lszh.setMaxlevel(lszhMaxLevel);
        lszh.setCd(lszhskillcd);
        lszh.setDamage(lszhskilldamage);
        lszh.setAround(lszhskillrange);
        lszh.name="戮神召唤";

        Career career=new Career(CareerEnum.BONEFIRE);
        career.skills[0]=(pt);
        career.skills[1]=(slzl);
        career.skills[2]=(yhgh);
        career.skills[3]=(jrp);
        career.skills[4]=(lszh);
        career.skillID = 1;
        return career;
    }
}
