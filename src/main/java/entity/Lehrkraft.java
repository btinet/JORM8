package entity;

import core.model.Entity;

public class Lehrkraft extends Entity {

    protected Integer LID;
    protected String Anrede;
    protected String Name;
    protected String Vorname;

    @Override
    public String toString(){
        return this.getVorname() + " " + this.getName();
    }

    public Integer getLID() {
        return LID;
    }

    public String getAnrede() {
        return Anrede;
    }

    public void setAnrede(String anrede) {
        Anrede = anrede;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVorname() {
        return Vorname;
    }

    public void setVorname(String vorname) {
        Vorname = vorname;
    }
}
