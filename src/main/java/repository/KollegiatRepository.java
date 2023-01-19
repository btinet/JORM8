package repository;

import core.model.Entity;
import core.model.Repository;
import entity.Kollegiat;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class KollegiatRepository extends Repository {

    public KollegiatRepository(Boolean naturalCase) {
        super(naturalCase);
        this.entity = new Kollegiat();
    }

    public ArrayList<HashMap<String, String>> getKollegiatJoinAntrag(int kid) {
        this.setAlias("k");

        try {
            return  this.createQueryBuilder()
                    .select("k.name, k.vorname, t.leitfrage")
                    .innerJoin("Antrag a","kid")
                    .innerJoin("Thema t","tid")
                    .getQuery()
                    .getListResult()
            ;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<HashMap<String, String>> findBySearchString(String searchString) {
        this.setAlias("k");

        try {
            return this.createQueryBuilder()
                    .select("k.name, k.vorname, t.name AS tutorName , t.anrede AS tutorAnrede, b.name AS betreuerName, b.anrede as betreuerAnrede")
                    .select(", a.alsEinzelPruefung AS 'antragCount'")
                    .innerJoin("Lehrkraft t","k.tutorId","t.lid")
                    .innerJoin("Lehrkraft b","k.betreuerId","b.lid")
                    .innerJoin("Antrag a","kid")
                    .orWhere("k.vorname LIKE ?")
                    .orWhere("k.name LIKE ?")
                    .setParameter(1,"%" + searchString + "%")
                    .setParameter(2,"%" + searchString + "%")
                    .getQuery()
                    .getListResult()
            ;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
