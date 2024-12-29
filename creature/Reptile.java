package creature;

import creature.reproduction.PairReproduction;
import elder.Elder;
import customexception.creature.reproduction.ReproductionWithItselfException;
import customexception.elder.ElderCantCreateCreaturesException;
import customexception.elder.EldersSkillLevelNotEnoughException;
import skillenum.MasterSkillEnum;

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
    public Reptile makeChildWithPartner(Reptile partner) {
        if (this == partner)
            throw new ReproductionWithItselfException("Reptile can't reproduce with itself");
        return new Reptile(this.name + partner.name, this.requiredMasterSkill, (this.intelligence + partner.intelligence) / 2, (this.size + partner.size) / 2);
    }
}
