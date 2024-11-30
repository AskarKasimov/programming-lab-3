package Creature;

import Elder.Elder;
import Exceptions.CreatureExceptions.CantChangeOwnerException;
import Exceptions.ElderExceptions.ElderCantCreateCreaturesException;
import Exceptions.ElderExceptions.EldersSkillLevelNotEnoughException;
import Exceptions.CreatureExceptions.RejectingToWorkException;
import enums.MasterSkillEnum;

import java.util.ArrayList;

public abstract class Creature {
    protected Elder owner;
    protected String name;
    protected MasterSkillEnum requiredMasterSkill;
    protected float intelligence;
    protected float size;
    protected ArrayList<Organ> organs = new ArrayList<>();

    public Creature(Elder elder, String name, MasterSkillEnum requiredMasterSkill, float intelligence, float size) throws EldersSkillLevelNotEnoughException, ElderCantCreateCreaturesException {
        if (elder.getMasterSkillLevel().ordinal() < requiredMasterSkill.ordinal())
            throw new EldersSkillLevelNotEnoughException("Elder " + elder.getName() + "`n skill level is not enough to create Shogoth!");
        if (!elder.isCanCreateCreatures())
            throw new ElderCantCreateCreaturesException("Elder " + elder.getName() + " can't create creatures anymore");
        this.owner = elder;
        try {
            elder.slaveCreature(this);
        } catch (CantChangeOwnerException _) {
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

    public void setOwner(Elder owner) throws CantChangeOwnerException {
        if (this.owner != null && owner.getMasterSkillLevel().ordinal() < this.owner.getMasterSkillLevel().ordinal())
            throw new CantChangeOwnerException("Current owner has more skill than new one, it can't be changed!");
        else this.owner = owner;
    }

    public void makeWork() throws RejectingToWorkException {
        if (this.intelligence > 8.5 && Math.random() > 0.3)
            throw new RejectingToWorkException("Creature rejects to work!");
        else {
            System.out.println("Существо " + this.name + " работает:");
            for (Organ organ : organs) {
                System.out.println("\t- " + organ.doProcess());
            }
            System.out.println();
        }
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
