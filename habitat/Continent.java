package habitat;

import elder.Elder;

import java.util.ArrayList;
import java.util.List;

public class Continent implements Habitat {
    private final String name;
    private final ArrayList<String> cataclysms = new ArrayList<>(List.of("Землетрясение", "Наводнение", "Паводок", "Возгорание и пожар", "Оползень"));

    public Continent(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void castCataclysm() {
        if (Math.random() > Math.random())
            System.out.println("На " + this.name + "(континент) произошло " + this.cataclysms.get((int) (Math.random() * this.cataclysms.size())));
    }

    @Override
    public boolean canMigrateElder(Elder elder) {
        return true;
    }

    @Override
    public String toString() {
        return "Континент " + this.name;
    }
}
