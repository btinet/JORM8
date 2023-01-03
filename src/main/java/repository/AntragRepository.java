package repository;

import core.model.Repository;
import entity.Antrag;

public class AntragRepository extends Repository {

    public AntragRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Antrag());
    }

}
