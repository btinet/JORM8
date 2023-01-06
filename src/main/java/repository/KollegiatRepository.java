package repository;

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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

}
