package creature;

import elder.Elder;
import customexception.creature.CreatureCantChangeOwnerException;
import customexception.creature.RejectingToWorkException;
import customexception.elder.ElderCantCreateCreaturesException;
import customexception.elder.EldersSkillLevelNotEnoughException;
import skillenum.MasterSkillEnum;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Creature {
    protected Elder owner;
    protected String name;
    protected MasterSkillEnum requiredMasterSkill;
    protected float intelligence;
    protected float size;
    protected ArrayList<Organ> organs = new ArrayList<>();

    public Creature(Elder elder, String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) throws EldersSkillLevelNotEnoughException, ElderCantCreateCreaturesException {
        if (elder.getMasterSkillLevel().ordinal() < requiredMasterSkill.ordinal())
            throw new EldersSkillLevelNotEnoughException("elder " + elder.getName() + "`n skill level is not enough to create Shogoth!");
        if (!elder.isCanCreateCreatures())
            throw new ElderCantCreateCreaturesException("elder " + elder.getName() + " can't create creatures anymore");
        this.owner = elder;
        try {
            elder.slaveCreature(this);
        } catch (CreatureCantChangeOwnerException ignored) {
        }
        this.name = name;
        this.requiredMasterSkill = requiredMasterSkill;
        this.intelligence = intelligence;
        this.size = size;
    }

    public Creature(String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) {
        this.name = name;
        this.requiredMasterSkill = requiredMasterSkill;
        this.intelligence = intelligence;
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Creature creature = (Creature) o;
        return Float.compare(intelligence, creature.intelligence) == 0 && Float.compare(size, creature.size) == 0 && Objects.equals(owner, creature.owner) && Objects.equals(name, creature.name) && requiredMasterSkill == creature.requiredMasterSkill && Objects.equals(organs, creature.organs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name, requiredMasterSkill, intelligence, size, organs);
    }

    public void makeWork() throws RejectingToWorkException {
        if (this.intelligence > 4.5 && Math.random() > 0.3)
            throw new RejectingToWorkException("Creature rejects to work!");
        System.out.println("Существо " + this.name + " работает!");
//            for (Organ organ : organs) {
//                System.out.println("\t- " + organ.doProcess());
//            }
//            System.out.println();
    }

    @Override
    public String toString() {
        return "Существо: " + this.name +
                ", владелец: " + (this.owner == null ? "нет" : this.owner.getName()) +
                ", требуемый уровень мастерства: " + this.requiredMasterSkill +
                ", интеллект: " + this.intelligence +
                ", размер: " + this.size;
    }

    public String getName() {
        return name;
    }

    public Elder getOwner() {
        return owner;
    }

    public void setOwner(Elder newOwner) throws CreatureCantChangeOwnerException {
        if (this.owner == newOwner) return;
        if (this.owner != null && newOwner.getMasterSkillLevel().ordinal() < this.owner.getMasterSkillLevel().ordinal())
            throw new CreatureCantChangeOwnerException("Current owner has more skill than new one, it can't be changed!");

        this.owner = newOwner;
        if (!newOwner.getCreatures().contains(this))
            newOwner.slaveCreature(this);
    }

    public MasterSkillEnum getRequiredMasterSkill() {
        return requiredMasterSkill;
    }

    public float getIntelligence() {
        return intelligence;
    }

    public float getSize() {
        return size;
    }

    public ArrayList<Organ> getOrgans() {
        return organs;
    }
}
