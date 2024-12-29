package habitat;

import elder.Elder;

public interface Habitat {
    String getName();

    void castCataclysm();

    boolean canMigrateElder(Elder elder);
}
