package core.model;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import controller.AuthenticationController;
import core.global.Database;
import core.global.Response;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public abstract class Repository {

    protected Entity entity;

    protected Boolean naturalCase = false;

    protected Boolean ucFirst = false;
    protected String alias;
    protected QueryBuilder queryBuilder;

    public Repository(Boolean naturalCase){
        if(naturalCase){
            this.naturalCase = naturalCase;
        }
    }

    public Repository setEntity(Entity entity){
        this.entity =  entity;
        return this;
    }

    public Repository setUcFirst()
    {
        this.ucFirst = true;
        return this;
    }

    public Repository setAlias(String alias) {
        this.alias = alias;
        return this;
    }

    protected QueryBuilder createQueryBuilder()
    {
        return this.queryBuilder = new QueryBuilder(this.naturalCase,this.ucFirst,this.entity,this.alias);
    }

    public Entity find(int id){
        return this.doFind(id, "id");
    }

    public Entity find(int id,String field){
        return this.doFind(id, field);
    }

    public Entity findOneBy(HashMap<String, String> condition){
        try {
            try {
                QueryBuilder query = this.createQueryBuilder()
                        .selectOrm()
                        ;
                int i = 1;
                for (Map.Entry<String, String> entry : condition.entrySet()){
                    if ("null".equals(entry.getValue())) {
                        query.andWhere(entry.getKey() + " IS NULL");
                    } else {
                        query.andWhere(entry.getKey() + " = ?");
                        query.setParameter(i, entry.getValue());
                        i++;
                    }
                }
                return query.getQuery()
                        .getOneOrNullResult()
                        ;

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            this.catchException(e);
        }
        return null;
    }


    public ArrayList<? extends Entity> findBy(HashMap<String, String> condition){
        try {
            try {
                QueryBuilder query = this.createQueryBuilder()
                        .selectOrm()
                        ;
                int i = 1;
                for (Map.Entry<String, String> entry : condition.entrySet()){
                    if ("null".equals(entry.getValue())) {
                        query.andWhere(entry.getKey() + " IS NULL");
                    } else {
                        query.andWhere(entry.getKey() + " = ?");
                        query.setParameter(i, entry.getValue());
                        i++;
                    }
                }
                return query.getQuery()
                        .getResult()
                        ;

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            this.catchException(e);
        }
        return null;
    }

    public ArrayList<? extends Entity> findAll(){
        try {
            try {
                return this.createQueryBuilder().selectOrm().getQuery().getResult();
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            this.catchException(e);
        }
        return null;
    }

    public ArrayList<? extends Entity> findAll(HashMap<String, String> orderBy){
        try {
            try {
                return this.createQueryBuilder()
                        .selectOrm()
                        .orderBy(orderBy)
                        .getQuery()
                        .getResult()
                        ;

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            this.catchException(e);
        }
        return null;
    }

    protected Entity doFind(int id, String field){
        try {
            try {
                return this.createQueryBuilder()
                        .selectOrm()
                        .andWhere(field + " = ?")
                        .setParameter(1,id)
                        .getQuery()
                        .getOneOrNullResult()
                        ;

            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            this.catchException(e);
        }
        return null;
    }

    private void catchException(SQLException e){
        if (e instanceof CommunicationsException){
            System.err.println("Verbindung unterbrochen");
            Database.destroyConnection();
            Response.redirectToController(new AuthenticationController()::index);
        }
        System.err.println(e.getErrorCode());
    }

}
