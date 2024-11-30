package Elder;

import Creature.Creature;
import Exceptions.CreatureExceptions.CreatureCantChangeOwnerException;
import Exceptions.HabitatExceptions.HabitatException;
import Exceptions.HabitatExceptions.NoShogothsForOceanException;
import Habitat.Habitat;
import enums.MasterSkillEnum;

import java.util.ArrayList;

public class Elder {
    private final String name;
    private final ArrayList<Creature> creatures;
    private MasterSkillEnum masterSkillLevel;
    private Habitat habitat;
    private boolean canCreateCreatures;

    public Elder(String name, Habitat habitat, MasterSkillEnum masterSkillLevel) {
        this.name = name;
        this.masterSkillLevel = masterSkillLevel;
        this.canCreateCreatures = true;
        this.creatures = new ArrayList<>();
        if (habitat.canMigrateElder(this)) {
            this.habitat = habitat;
        } else {
            throw new NoShogothsForOceanException("Can't migrate " + this.name + " to " + habitat.getName() + ", because he doesn't have any shogoths");
        }
    }

    public void slaveCreature(Creature creature) throws CreatureCantChangeOwnerException {
        if (!creatures.contains(creature)) {
            creatures.add(creature);
            creature.setOwner(this); // Устанавливаем владельца у питомца
        }
    }

    public void improveMasterSkillLevel() {
        this.masterSkillLevel = this.masterSkillLevel.nextLevel();
    }

    public void setCanCreateCreatures(boolean canCreateCreatures) {
        this.canCreateCreatures = canCreateCreatures;
    }

    public void moveTo(Habitat habitat) {
        if (habitat.canMigrateElder(this))
            this.habitat = habitat;
        else
            throw new HabitatException("Can't migrate " + this.name + " to " + habitat.getName());
    }

    @Override
    public String toString() {
        StringBuilder creatures = new StringBuilder();
        for (Creature creature : this.creatures) {
            creatures.append("\t- ").append(creature).append("\n");
        }
        return "Старец: " + this.name +
                ", уровень мастерства: " + this.masterSkillLevel +
                ", место обитания: " + this.habitat +
                ", может создавать существ: " + this.canCreateCreatures +
                ", существа:\n" + creatures;
    }

    public void removeCreature(Creature creature) {
        this.creatures.remove(creature);
    }

    public ArrayList<Creature> getCreatures() {
        return creatures;
    }

    public String getName() {
        return name;
    }

    public MasterSkillEnum getMasterSkillLevel() {
        return masterSkillLevel;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public boolean isCanCreateCreatures() {
        return canCreateCreatures;
    }
}
