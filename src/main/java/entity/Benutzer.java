package entity;

import core.model.Entity;
import repository.LehrkraftRepository;

public class Benutzer extends Entity {

    protected Integer bid;

    protected Integer lid;

    protected String benutzerName;

    protected String passwort;

    public Integer getBid() {
        return bid;
    }

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getBenutzerName() {
        return benutzerName;
    }

    public void setBenutzerName(String benutzerName) {
        this.benutzerName = benutzerName;
    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Lehrkraft getLehrkraft(){
        LehrkraftRepository lehrkraftRepository = new LehrkraftRepository(true);
        return (Lehrkraft) lehrkraftRepository.find(this.lid,"LID");
    }
}
