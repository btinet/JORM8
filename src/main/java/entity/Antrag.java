package entity;

import core.model.Entity;

public class Antrag extends Entity {

    protected Integer aid;
    protected Boolean alsEinzelpruefung;
    protected String genehmigtAm;
    protected Integer tid;
    protected Integer kid;

    @Override
    public String toString() {
        return this.getAID().toString();
    }

    public Integer getAID() {
        return aid;
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
        return tid;
    }

    public void setTID(Integer TID) {
        this.tid = TID;
    }

    public Integer getKID() {
        return kid;
    }

    public void setKID(Integer KID) {
        this.kid = KID;
    }
}


