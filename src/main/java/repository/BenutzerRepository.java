package repository;

import core.model.Repository;
import entity.Benutzer;

public class BenutzerRepository extends Repository {

    public BenutzerRepository(Boolean naturalCase) {
        super(naturalCase);
        setEntity(new Benutzer());
    }

}
