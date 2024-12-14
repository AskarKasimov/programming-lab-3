package Creature;

import Creature.Reproduction.DivisionReproduction;
import Elder.Elder;
import Exceptions.ElderExceptions.ElderCantCreateCreaturesException;
import Exceptions.ElderExceptions.EldersSkillLevelNotEnoughException;
import Exceptions.CreatureExceptions.RejectingToWorkException;
import enums.MasterSkillEnum;

import java.lang.reflect.Constructor;

public class Shogoth extends Creature implements DivisionReproduction<Shogoth> {
    public Shogoth(Elder elder, String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) throws EldersSkillLevelNotEnoughException, ElderCantCreateCreaturesException {
        super(elder, name, requiredMasterSkill, intelligence, size);
    }

    public Shogoth(String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) {
        super(name, requiredMasterSkill, intelligence, size);
    }

    public Shogoth(String name) {
        super(name, MasterSkillEnum.ADEPT, 2.5f, 5.0f);
    }

    @Override
    public void makeWork() throws RejectingToWorkException {
        super.makeWork();
        this.improveIntelligence();
    }

    protected void improveIntelligence() {
        this.intelligence += (float) (Math.random() * 2);
    }

    public void addOrgan(Organ organ) {
        this.organs.add(organ);
    }

    @Override
    public Shogoth makeChild() {
        return new Shogoth(this.name + "2", this.requiredMasterSkill, this.intelligence, this.size);
    }

    public static class Builder {
        private String name;
        private MasterSkillEnum requiredMasterSkill = MasterSkillEnum.ADEPT;
        private float intelligence = 2.5f;
        private float size = 5.0f;
        private Elder elder;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setRequiredMasterSkill(MasterSkillEnum requiredMasterSkill) {
            this.requiredMasterSkill = requiredMasterSkill;
            return this;
        }

        public Builder setIntelligence(float intelligence) {
            this.intelligence = intelligence;
            return this;
        }

        public Builder setSize(float size) {
            this.size = size;
            return this;
        }

        public Builder setElder(Elder elder) {
            this.elder = elder;
            return this;
        }

        public Shogoth build() {
            try {
                Constructor<Shogoth> constructor;
                if (elder != null) {
                    constructor = Shogoth.class.getDeclaredConstructor(Elder.class, String.class, MasterSkillEnum.class, float.class, float.class);
                    return (Shogoth) constructor.newInstance(elder, name, requiredMasterSkill, intelligence, size);
                } else {
                    constructor = Shogoth.class.getDeclaredConstructor(String.class, MasterSkillEnum.class, float.class, float.class);
                    return (Shogoth) constructor.newInstance(name, requiredMasterSkill, intelligence, size);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to build Shogoth instance", e);
            }
        }
    }
}
