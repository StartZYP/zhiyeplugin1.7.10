package skillerData;

public enum CareerEnum {
    CONTROLLER("战域控制者"),
    BONEFIRE("战域戮火");

    private String name;

    CareerEnum(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
