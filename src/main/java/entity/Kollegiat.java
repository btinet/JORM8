package entity;

import core.model.Entity;
import repository.LehrkraftRepository;

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

    public Kollegiat setName(String name) {
        Name = name;
        return this;
    }

    public String getVorname() {
        return Vorname;
    }

    public Kollegiat setVorname(String vorname) {
        Vorname = vorname;
        return this;
    }

    public Integer getTutorID() {
        return TutorID;
    }

    public Lehrkraft getTutor(){
        LehrkraftRepository lehrkraftRepository = new LehrkraftRepository(true);
        return (Lehrkraft) lehrkraftRepository.find(this.TutorID,"LID");
    }

    public Kollegiat setTutorID(Integer tutorID) {
        TutorID = tutorID;
        return this;
    }

    public Integer getBetreuerID() {
        return BetreuerID;
    }

    public Lehrkraft getBetreuer(){
        LehrkraftRepository lehrkraftRepository = new LehrkraftRepository(true);
        return (Lehrkraft) lehrkraftRepository.find(this.BetreuerID,"LID");
    }

    public Kollegiat setBetreuerID(Integer betreuerID) {
        BetreuerID = betreuerID;
        return this;
    }

}
