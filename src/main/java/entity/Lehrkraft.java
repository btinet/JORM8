package entity;

import core.model.Entity;
import repository.BenutzerRepository;

import java.util.HashMap;

public class Lehrkraft extends Entity {

    protected Integer lid;
    protected String anrede;
    protected String name;
    protected String vorname;

    @Override
    public String toString(){
        return this.getAnrede() + " " + this.getName();
    }

    public Integer getLID() {
        return lid;
    }

    public String getAnrede() {
        return anrede;
    }

    public void setAnrede(String anrede) {
        this.anrede = anrede;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public Boolean isBenutzer(){

        HashMap<String, String> condition = new HashMap<>();
        condition.put("lid",this.lid.toString());

        BenutzerRepository benutzerRepository = new BenutzerRepository(true);
        return null != benutzerRepository.findOneBy(condition);
    }
}
