package entity;

import core.model.Entity;

public class Thema extends Entity {

    protected Integer TID;
    protected String Leitfrage;
    protected String Erlaeuterung;
    protected String PruefJahr;

    @Override
    public String toString(){
        return this.getLeitfrage();
    }

    public Integer getTID() {
        return TID;
    }

    public String getLeitfrage() {
        return Leitfrage;
    }

    public void setLeitfrage(String leitfrage) {
        Leitfrage = leitfrage;
    }

    public String getErlaeuterung() {
        return Erlaeuterung;
    }

    public void setErlaeuterung(String erlaeuterung) {
        Erlaeuterung = erlaeuterung;
    }

    public String getPruefJahr() {
        return PruefJahr;
    }

    public void setPruefJahr(String pruefJahr) {
        PruefJahr = pruefJahr;
    }
}
