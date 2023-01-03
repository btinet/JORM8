package repository;

import core.model.Repository;
import entity.Fach;

public class FachRepository extends Repository {

    public FachRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Fach());
    }

}
