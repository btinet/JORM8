package repository;

import core.model.Repository;
import entity.Lehrkraft;

public class LehrkraftRepository extends Repository {

    public LehrkraftRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Lehrkraft());
    }

}
