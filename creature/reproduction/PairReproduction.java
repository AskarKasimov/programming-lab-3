package creature.reproduction;

import creature.Creature;

public interface PairReproduction<T extends Creature> {
    T makeChildWithPartner(T partner);
}
