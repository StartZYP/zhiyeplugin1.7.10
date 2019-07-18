package rpg;

public class TempAttr {
    public double damage;
    public double denfense;
    public double magic;
    public double damagespeed;
    public double health;
    public int movespeed;
    public int zdl;
    public int baoji;
    public int baojidamage;

    public TempAttr(double damage, double denfense, double magic, double damagespeed, double health, int movespeed, int zdl, int baoji, int baojidamage) {
        this.damage = damage;
        this.denfense = denfense;
        this.magic = magic;
        this.damagespeed = damagespeed;
        this.health = health;
        this.movespeed = movespeed;
        this.zdl = zdl;
        this.baoji = baoji;
        this.baojidamage = baojidamage;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getDenfense() {
        return denfense;
    }

    public void setDenfense(double denfense) {
        this.denfense = denfense;
    }

    public double getMagic() {
        return magic;
    }

    public void setMagic(double magic) {
        this.magic = magic;
    }

    public double getDamagespeed() {
        return damagespeed;
    }

    public void setDamagespeed(double damagespeed) {
        this.damagespeed = damagespeed;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getMovespeed() {
        return movespeed;
    }

    public void setMovespeed(int movespeed) {
        this.movespeed = movespeed;
    }

    public int getZdl() {
        return zdl;
    }

    public void setZdl(int zdl) {
        this.zdl = zdl;
    }

    public int getBaoji() {
        return baoji;
    }

    public void setBaoji(int baoji) {
        this.baoji = baoji;
    }

    public int getBaojidamage() {
        return baojidamage;
    }

    public void setBaojidamage(int baojidamage) {
        this.baojidamage = baojidamage;
    }
}
