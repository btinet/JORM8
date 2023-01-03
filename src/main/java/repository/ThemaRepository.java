package repository;

import core.model.Repository;
import entity.Thema;

public class ThemaRepository extends Repository {

    public ThemaRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Thema());
    }

}
