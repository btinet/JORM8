package entity;

import core.model.Entity;
import repository.LehrkraftRepository;

public class Kollegiat extends Entity {

    protected Integer kid;
    protected String name;
    protected String vorname;
    protected Integer tutorId;
    protected Integer betreuerId;

    @Override
    public String toString(){
        return this.getVorname() + " " + this.getName();
    }

    public Integer getKID() {
        return kid;
    }

    public String getName() {
        return name;
    }

    public Kollegiat setName(String name) {
        this.name = name;
        return this;
    }

    public String getVorname() {
        return vorname;
    }

    public Kollegiat setVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public Integer getTutorID() {
        return tutorId;
    }

    public Lehrkraft getTutor(){
        LehrkraftRepository lehrkraftRepository = new LehrkraftRepository(true);
        return (Lehrkraft) lehrkraftRepository.find(this.tutorId,"lid");
    }

    public Kollegiat setTutorID(Integer tutorID) {
        this.tutorId = tutorID;
        return this;
    }

    public Integer getBetreuerID() {
        return betreuerId;
    }

    public Lehrkraft getBetreuer(){
        LehrkraftRepository lehrkraftRepository = new LehrkraftRepository(true);
        return (Lehrkraft) lehrkraftRepository.find(this.betreuerId,"lid");
    }

    public Kollegiat setBetreuerID(Integer betreuerID) {
        betreuerId = betreuerID;
        return this;
    }

}
