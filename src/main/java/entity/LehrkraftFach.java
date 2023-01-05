package entity;

import core.model.Entity;

public class LehrkraftFach extends Entity {

    protected Integer lfid;
    protected Integer lid;
    protected Integer fid;

    @Override
    public String toString(){
        return this.getLFID().toString();
    }

    public Integer getLFID() {
        return lfid;
    }

    public Integer getLID() {
        return lid;
    }

    public void setLID(Integer lid) {
        this.lid = lid;
    }

    public Integer getFID() {
        return fid;
    }

    public void setFID(Integer fid) {
        this.fid = fid;
    }
}
