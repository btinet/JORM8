package entity;

import core.model.Entity;

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
}
