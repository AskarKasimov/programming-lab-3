import Creature.Reptile;
import Exceptions.ReproductionWithItselfException;
import enums.MasterSkillEnum;

public class Main {
    public static void main(String[] args) {
        Reptile reptile = new Reptile("mark", MasterSkillEnum.MASTER, 0.5f, 0.5f);
        try {
            Reptile newReptile = reptile.makeChildWithPartner(new Reptile("eva", MasterSkillEnum.MASTER, 0.5f, 0.5f));
            System.out.println(newReptile);
        } catch (ReproductionWithItselfException e) {
            throw new RuntimeException(e);
        }
    }
}
