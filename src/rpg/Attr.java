package rpg;

public class Attr {
    public double damage;
    public double denfense;
    public double magic;
    public double damagespeed;
    public double health;
    public int movespeed;
    public int zdl;
    public TempAttr tempattr;
    public int baoji;
    public int baojidamage;


    public Attr(double damage, double denfense, double magic, double damagespeed, double health, int movespeed, int zdl, int baoji, int baojidamage) {
        this.damage = damage;
        this.denfense = denfense;
        this.magic = magic;
        this.damagespeed = damagespeed;
        this.health = health;
        this.movespeed = movespeed;
        this.zdl = zdl;
        this.baoji=baoji;
        this.baojidamage=baojidamage;
    }



//    public Attr(double damage, double denfense, double magic, double damagespeed, double health, int movespeed, int zdl, int baoji, int baojidamage, Attr attr) {
//        this.damage = damage;
//        this.denfense = denfense;
//        this.magic = magic;
//        this.damagespeed = damagespeed;
//        this.health = health;
//        this.movespeed = movespeed;
//        this.zdl = zdl;
//        this.attr = attr;
//        this.baoji=baoji;
//        this.baojidamage=baojidamage;
//    }

    public Attr getAllAttr() {
        if (this.tempattr == null) {
            return this;
        } else {
            Attr newattr = new Attr(this.damage, this.denfense, this.magic, this.damagespeed, this.health, this.movespeed, this.zdl,this.baoji,this.baojidamage);
            newattr.damage = this.tempattr.getDamage() + this.damage;
            newattr.denfense = this.tempattr.getDenfense() + this.denfense;
            newattr.magic = this.tempattr.getMagic() + this.magic;
            newattr.damagespeed = this.tempattr.getDamagespeed() + this.damagespeed;
            newattr.health = this.tempattr.getHealth() + this.health;
            newattr.movespeed = this.tempattr.getMovespeed() + this.movespeed;
            newattr.zdl = this.tempattr.getZdl() + this.zdl;
            newattr.baoji=this.tempattr.getBaoji()+this.baoji;
            newattr.baojidamage=this.tempattr.getBaojidamage()+this.baojidamage;
            return newattr;
        }
    }

    public int getBaoji() {
        return baoji;
    }

    public int getBaojidamage() {
        return baojidamage;
    }

    public double getDamage() {
        return this.damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDenfense() {
        return this.denfense;
    }

    public void setDenfense(double denfense) {
        this.denfense = denfense;
    }

    public double getMagic() {
        return this.magic;
    }

    public void setDamagespeed(double damagespeed) {
        this.damagespeed = damagespeed;
    }

    public int getZdl() {
        return this.zdl;
    }

    public void setZdl(int zdl) {
        this.zdl = zdl;
    }

    public void setMagic(double magic) {
        this.magic = magic;
    }

    public double getDamagespeed() {
        return this.damagespeed;
    }

    public double getHealth() {
        return this.health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getMovespeed() {
        return this.movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public TempAttr getAttr() {
        return this.tempattr;
    }

    public void setAttr(TempAttr attr) {
        this.tempattr = attr;
    }
}
