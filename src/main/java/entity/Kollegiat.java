package entity;

import core.model.Entity;

public class Kollegiat extends Entity {

    protected Integer KID;
    protected String Name;
    protected String Vorname;
    protected Integer TutorID;
    protected Integer BetreuerID;

    @Override
    public String toString(){
        return this.getVorname() + " " + this.getName();
    }

    public Integer getKID() {
        return KID;
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

    public Integer getTutorID() {
        return TutorID;
    }

    public void setTutorID(Integer tutorID) {
        TutorID = tutorID;
    }

    public Integer getBetreuerID() {
        return BetreuerID;
    }

    public void setBetreuerID(Integer betreuerID) {
        BetreuerID = betreuerID;
    }

}
