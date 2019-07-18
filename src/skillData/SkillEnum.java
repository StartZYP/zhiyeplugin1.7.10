package skillData;

public enum SkillEnum {
    CPT("战域控制者普通攻击"),
    MAGIC("魔法攻击"),
    HD("唤电"),
    DLZGB("等离子光波"),
    SLP("弑裂破"),
    LPT("战域戮火普通攻击"),
    SL("杀戮之力"),
    YH("浴火光环"),
    JRP("剑刃破"),
    LSZH("戮神召唤");

    private String name;
    SkillEnum(String name) {
        this.name=name;
    }

    public String getName() {
         return name;
    }
}
