package core.model;

import java.sql.SQLException;

/**
 * Der Entity-Manager verwaltet die Entitäten der jeweiligen Relationen
 */
public class EntityManager extends Repository {
    public EntityManager(Boolean naturalCase) {
        super(naturalCase);
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     */
    public void persist(Entity e){
        this.entity = e;
        this.persist(e, "id");
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     * @param field Feldname des Primärschlüssels, falls der Name vom Standard abweicht.
     */
    public void persist(Entity e, String field){
        this.entity = e;
        try {
            this.createQueryBuilder()
                    .insertOrm(field)
                    .getInsertQuery()
            ;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     * @param id Primärschlüssel des zu aktualisierenden Tupels.
     */
    public void persist(Entity e, int id){
        this.entity = e;
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     * @param id Primärschlüssel des zu aktualisierenden Tupels.
     * @param field Feldname, falls der Name des Primärschlüssels vom Standard abweicht.
     */
    public void persist(Entity e, int id, String field){
        this.entity = e;
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     * @param id Primärschlüssel des zu aktualisierenden Tupels.
     */
    public void remove(Entity e, int id){
        this.entity = e;
    }

    /**
     *
     * @param e Entity-Objekt, dass den Datensatz enthält.
     * @param id Primärschlüssel des zu aktualisierenden Tupels.
     * @param field Feldname, falls der Name des Primärschlüssels vom Standard abweicht.
     */
    public void remove(Entity e, int id, String field){
        this.entity = e;
    }

    protected void insert(){

    }

    protected void update(){

    }


}
