package Creature;

import Creature.Reproduction.DivisionReproduction;
import Elder.Elder;
import Exceptions.ElderExceptions.ElderCantCreateCreaturesException;
import Exceptions.ElderExceptions.EldersSkillLevelNotEnoughException;
import Exceptions.CreatureExceptions.RejectingToWorkException;
import enums.MasterSkillEnum;

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
}
