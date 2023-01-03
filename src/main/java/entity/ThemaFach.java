package entity;

import core.model.Entity;

public class ThemaFach extends Entity {

    protected Integer TFID;
    protected Integer TID;
    protected Integer FID;
    protected Boolean istRefFach;

    @Override
    public String toString(){
        return this.getTFID().toString();
    }

    public Integer getTFID() {
        return TFID;
    }

    public Integer getTID() {
        return TID;
    }

    public void setTID(Integer TID) {
        this.TID = TID;
    }

    public Integer getFID() {
        return FID;
    }

    public void setFID(Integer FID) {
        this.FID = FID;
    }

    public Boolean getIstRefFach() {
        return istRefFach;
    }

    public void setIstRefFach(Boolean istRefFach) {
        this.istRefFach = istRefFach;
    }
}
