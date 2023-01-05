package entity;

import core.model.Entity;

public class Thema extends Entity {

    protected Integer tid;
    protected String leitfrage;
    protected String erlaeuterung;
    protected String pruefJahr;

    @Override
    public String toString(){
        return this.getLeitfrage();
    }

    public Integer getTID() {
        return tid;
    }

    public String getLeitfrage() {
        return leitfrage;
    }

    public void setLeitfrage(String leitfrage) {
        this.leitfrage = leitfrage;
    }

    public String getErlaeuterung() {
        return erlaeuterung;
    }

    public void setErlaeuterung(String erlaeuterung) {
        this.erlaeuterung = erlaeuterung;
    }

    public String getPruefJahr() {
        return pruefJahr;
    }

    public void setPruefJahr(String pruefJahr) {
        this.pruefJahr = pruefJahr;
    }
}
