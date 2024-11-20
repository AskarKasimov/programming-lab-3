package Habitat;

import Elder.Elder;

public interface Habitat {
    String getName();

    void castCataclysm();
    boolean canMigrateElder(Elder elder);
}
