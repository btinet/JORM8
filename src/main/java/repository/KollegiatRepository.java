package repository;

import core.model.Entity;
import core.model.QueryBuilder;
import core.model.Repository;
import entity.Kollegiat;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;

public class KollegiatRepository extends Repository {

    public KollegiatRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Kollegiat());
    }

    public void getKollegiatJoinAntrag(int kid) {

        try {
            this.createQueryBuilder()
                  .select("name, vorname, COUNT(a.aid) AS 'anzahl' ")
                  .innerJoin("Antrag a","kid")
                  .andWhere("kid = ?")
                  .setParameter(1,kid)
                  .getQuery()
          ;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
