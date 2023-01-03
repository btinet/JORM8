package entity;

import core.model.Entity;

public class LehrkraftFach extends Entity {

    protected Integer LFID;
    protected Integer LID;
    protected Integer FID;

    @Override
    public String toString(){
        return this.getLFID().toString();
    }

    public Integer getLFID() {
        return LFID;
    }

    public Integer getLID() {
        return LID;
    }

    public void setLID(Integer LID) {
        this.LID = LID;
    }

    public Integer getFID() {
        return FID;
    }

    public void setFID(Integer FID) {
        this.FID = FID;
    }
}
