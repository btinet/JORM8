package repository;

import core.model.Repository;
import entity.Kollegiat;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class KollegiatRepository extends Repository {

    public KollegiatRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Kollegiat());
        setAlias("k");
    }

    public Kollegiat getKollegiatJoinAntrag(int kid) {

        try {
            return (Kollegiat) this.createQueryBuilder()
                  .select("k.name, k.vorname, COUNT(a.aid) AS 'anzahl' ")
                  .innerJoin("Antrag a","k.kid","a.kid")
                  .andWhere("k.kid = ?")
                  .setParameter(1,kid)
                  .getQuery()
                    .getOnOrNullResult()
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
