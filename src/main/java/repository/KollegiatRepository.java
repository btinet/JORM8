package repository;

import core.model.Repository;
import entity.Kollegiat;

public class KollegiatRepository extends Repository {

    public KollegiatRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Kollegiat());
    }

}
