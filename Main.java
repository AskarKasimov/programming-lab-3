import creature.Organ;
import creature.Reptile;
import creature.Shogoth;
import customexception.creature.RejectingToWorkException;
import customexception.elder.ElderCantCreateCreaturesException;
import customexception.elder.EldersSkillLevelNotEnoughException;
import customexception.habitat.NoShogothsForOceanException;
import elder.Elder;
import habitat.Continent;
import habitat.Ocean;
import skillenum.MasterSkillEnum;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Shogoth builtShogoth = new Shogoth.Builder()
                .setName("Построенный Шогот")
                .setIntelligence(3.5f)
                .setSize(3.5f)
                .build();

        // сначала жили на суше, создав первых шоготов
        System.out.print("В древние времена старцы жили на суше, где создали первых шогготов. ");
        Continent europe = new Continent("Европа");

        ArrayList<Elder> elders = new ArrayList<>();

        Elder master = new Elder("Мастер", europe, MasterSkillEnum.MASTER);
        elders.add(master);
        Elder novice = new Elder("Ученик", europe, MasterSkillEnum.NOVICE);
        elders.add(novice);

        Shogoth shogothForMaster = null;
        try {
            shogothForMaster = new Shogoth(master, "Шогот1", MasterSkillEnum.MASTER, 2.5f, 1.5f);
        } catch (EldersSkillLevelNotEnoughException | ElderCantCreateCreaturesException ignored) {
        }
        Shogoth shogothForNovice = null;
        try {
            shogothForNovice = new Shogoth(novice, "Шогот2", MasterSkillEnum.NOVICE, 0.5f, 0.5f);
        } catch (EldersSkillLevelNotEnoughException | ElderCantCreateCreaturesException ignored) {
        }

        // потом переехали в глубины океана и жили пока шогготы не поумнели
        System.out.print("Потом старцы переехали в глубины океана и жили там долгое время. ");
        Ocean pacificOcean = new Ocean("Тихий океан");
        elders.forEach(elder -> {
            elder.moveTo(pacificOcean);
        });

        // потом обратно на новый континент
        System.out.print("В течение веков шло закономерное переселение Старцев из глубин моря на сушу ");
        Continent africa = new Continent("Африка");
        elders.forEach(elder -> {
            new Continent("Новый континент (" + elder.getName() + ")");
            elder.moveTo(africa);
        });
        System.out.print("-- этот процесс подстегивался рождением новых материков, хотя и океан никогда не пустовал. ");

        //трудности
        master.setCanCreateCreatures(false);
        Shogoth newShogoth = null;
        try {
            newShogoth = new Shogoth(novice, "Шогот3", MasterSkillEnum.MASTER, 3.5f, 3.5f);
        } catch (ElderCantCreateCreaturesException e) {
            System.out.print("Второй причиной миграции стали трудности по выращиванию ");
        } catch (EldersSkillLevelNotEnoughException ignored) {
        }

        while (true) {
            try {
                shogothForMaster.makeWork();
                shogothForNovice.makeWork();
            } catch (RejectingToWorkException e) {
                System.out.print("и удерживанию в повиновении шогготов, ");
                break;
            }
        }

        try {
            master.moveTo(pacificOcean);
        } catch (NoShogothsForOceanException e) {
            System.out.print("без которых жизнь под водой не могла продолжаться. ");
        }

        System.out.print("С течением времени, как скорбно поведали нам сюжеты на древних барельефах, был утрачен секрет создания жизни из неорганической материи, ");
        elders.forEach(elder -> {
            elder.setCanCreateCreatures(false);
        });
        System.out.print("и Старцам пришлось довольствоваться модификацией уже существующих форм. ");

        Reptile reptile = null;
        try {
            System.out.print("На суше у Старцев не было никаких проблем с громадными, но исключительно послушными рептилиями, ");
            reptile = new Reptile(master, "Рептилия", MasterSkillEnum.MASTER, 3.5f, 3.5f);
        } catch (EldersSkillLevelNotEnoughException | ElderCantCreateCreaturesException ignored) {
        }

        System.out.print("а вот размножавшиеся делением шогготы, ");
        Shogoth babyShogoth = shogothForMaster.makeChild();
        System.out.print("которые в результате случайного стечения обстоятельств нарастили до опасного предела интеллект (" + babyShogoth.getIntelligence() + "), беспокоили их чрезвычайно. ");

        System.out.print("Старцы всегда управляли шогготами с помощью гипноза, легко трансформируя эту внушаемую плотную плазму согласно потребностям и создавая на время нужные им члены и органы, ");
        shogothForMaster.addOrgan(new Organ("Биолопата", "Копаем копаем"));

        while (true) {
            try {
                shogothForMaster.makeWork();
            } catch (RejectingToWorkException rejectingToWorkException) {
                System.out.print("Казалось, у них развился мозг с неустойчивой системой связей, в котором иногда зарождался сильный волевой импульс, противоречивший воле хозяина. ");
                System.out.print(rejectingToWorkException.getMessage());
                break;
            }
        }
    }
}
