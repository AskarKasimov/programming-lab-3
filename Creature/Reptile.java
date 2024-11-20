package Creature;

import Creature.Reproduction.PairReproduction;
import Elder.Elder;
import Exceptions.ElderExceptions.ElderCantCreateCreaturesException;
import Exceptions.ElderExceptions.EldersSkillLevelNotEnoughException;
import Exceptions.CreatureExceptions.ReproductionExceptions.ReproductionWithItselfException;
import enums.MasterSkillEnum;

public class Reptile extends Creature implements PairReproduction<Reptile> {

    public Reptile(Elder elder, String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) throws EldersSkillLevelNotEnoughException, ElderCantCreateCreaturesException {
        super(elder, name, requiredMasterSkill, intelligence, size);
    }

    public Reptile(String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) {
        super(name, requiredMasterSkill, intelligence, size);
    }

    public Reptile(String name) {
        super(name, MasterSkillEnum.NOVICE, 1.5f, 0.5f);
    }

    @Override
    public Reptile makeChildWithPartner(Reptile partner) throws ReproductionWithItselfException {
        if (this == partner)
            throw new ReproductionWithItselfException("Reptile can't reproduce with itself");
        return new Reptile(this.name + partner.name, this.requiredMasterSkill, (this.intelligence + partner.intelligence) / 2, (this.size + partner.size) / 2);
    }
}
