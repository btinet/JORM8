package entity;

import core.model.Entity;

public class ThemaFach extends Entity {

    protected Integer tfid;
    protected Integer tid;
    protected Integer fid;
    protected Boolean istRefFach;

    @Override
    public String toString(){
        return this.getTFID().toString();
    }

    public Integer getTFID() {
        return tfid;
    }

    public Integer getTID() {
        return tid;
    }

    public void setTID(Integer tid) {
        this.tid = tid;
    }

    public Integer getFID() {
        return fid;
    }

    public void setFID(Integer fid) {
        this.fid = fid;
    }

    public Boolean getIstRefFach() {
        return istRefFach;
    }

    public void setIstRefFach(Boolean istRefFach) {
        this.istRefFach = istRefFach;
    }
}
