package core.model;

import java.util.HashMap;

public class ResultSorter {

    protected HashMap<String, String> orderBy;

    public ResultSorter(){
        this.init();
    }

    public ResultSorter(String field, String value){
        this.init();
        this.add(field, value);
    }

    public ResultSorter(HashMap<String, String> orderBy){
        this.orderBy = orderBy;
    }

    protected void init(){
        this.orderBy = new HashMap<>();
    }

    public void add(String field, String value){
        this.orderBy.put(field, value);
    }

    public HashMap<String, String> getMap(){
        return this.orderBy;
    }

}
