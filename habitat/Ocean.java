package habitat;

import creature.Creature;
import creature.Shogoth;
import elder.Elder;

import java.util.ArrayList;
import java.util.List;

public class Ocean implements Habitat {
    private final String name;
    private final ArrayList<String> cataclysms = new ArrayList<>(List.of("Цунами", "Ураган", "Торнадо", "Подводное извержение"));

    public Ocean(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void castCataclysm() {
        if (Math.random() > Math.random())
            System.out.println("В " + this.name + "(океан) произошло " + this.cataclysms.get((int) (Math.random() * this.cataclysms.size())));

    }

    @Override
    public boolean canMigrateElder(Elder elder) {
        for (Creature creature : elder.getCreatures())
            if (creature instanceof Shogoth)
                return true;
        return false;
    }

    @Override
    public String toString() {
        return "Океан " + this.name;
    }
}
