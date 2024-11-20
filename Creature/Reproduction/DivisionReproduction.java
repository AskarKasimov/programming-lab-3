package Creature.Reproduction;

import Creature.Creature;

public interface DivisionReproduction<T extends Creature> {
    T makeChild();
}
