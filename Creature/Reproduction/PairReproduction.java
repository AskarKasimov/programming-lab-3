package Creature.Reproduction;

import Creature.Creature;
import Exceptions.CreatureExceptions.ReproductionExceptions.ReproductionWithItselfException;

public interface PairReproduction<T extends Creature> {
    T makeChildWithPartner(T partner);
}
