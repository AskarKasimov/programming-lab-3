package skillenum;

public enum MasterSkillEnum {
    NOVICE, ADEPT, MASTER, TRANSCENDENT, SUPREME;

    public MasterSkillEnum nextLevel() {
        return MasterSkillEnum.values()[Math.max(this.ordinal() + 1, MasterSkillEnum.values().length - 1)];
    }
}
