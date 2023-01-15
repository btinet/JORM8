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

    public ArrayList<? extends Entity> findBySearchString(String searchString) {
        this.setAlias("k");

        try {
            return this.createQueryBuilder()
                    .selectOrm()
                    .orWhere("vorname LIKE ?")
                    .orWhere("name LIKE ?")
                    .setParameter(1,"%" + searchString + "%")
                    .setParameter(2,"%" + searchString + "%")
                    .getQuery()
                    .getResult()
                    ;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
