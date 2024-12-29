package creature.reproduction;

import creature.Creature;

public interface DivisionReproduction<T extends Creature> {
    T makeChild();
}
