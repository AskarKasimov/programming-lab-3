package enums;

public enum MasterSkillEnum {
    NOVICE, ADEPT, MASTER, TRANSCENDENT, SUPREME;

    public MasterSkillEnum nextLevel() {
        if (this != SUPREME)
            return MasterSkillEnum.values()[(this.ordinal() + 1) % MasterSkillEnum.values().length];
        return this;
    }
}
