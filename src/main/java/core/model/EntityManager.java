package core.model;

/**
 * Der Entity-Manager verwaltet die Entitäten der jeweiligen Relationen
 */
public class EntityManager extends Repository {
    public EntityManager(Boolean naturalCase) {
        super(naturalCase);
    }

    public void persist(Entity e){

    }

    public void remove(Entity e){

    }

    protected void insert(Entity e){

    }

    protected void update(Entity e){

    }


}
