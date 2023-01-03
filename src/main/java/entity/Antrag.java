package entity;

import core.model.Entity;

public class Antrag extends Entity {

    protected Integer AID;
    protected Boolean alsEinzelpruefung;
    protected String genehmigtAm;
    protected Integer TID;
    protected Integer KID;

    @Override
    public String toString() {
        return this.getAID().toString();
    }

    public Integer getAID() {
        return AID;
    }

    public Boolean getAlsEinzelpruefung() {
        return alsEinzelpruefung;
    }

    public void setAlsEinzelpruefung(Boolean alsEinzelpruefung) {
        this.alsEinzelpruefung = alsEinzelpruefung;
    }

    public String getGenehmigtAm() {
        return genehmigtAm;
    }

    public void setGenehmigtAm(String genehmigtAm) {
        this.genehmigtAm = genehmigtAm;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public Integer getKID() {
        return KID;
    }

    public void setKID(Integer KID) {
        this.KID = KID;
    }
}


