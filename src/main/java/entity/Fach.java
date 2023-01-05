package entity;

import core.model.Entity;

public class Fach extends Entity {

    protected Integer fid;
    protected String bezeichnung;

    @Override
    public String toString(){
        return this.getBezeichnung();
    }

    public Integer getFID() {
        return fid;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        bezeichnung = bezeichnung;
    }
}
