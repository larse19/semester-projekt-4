package dk.sdu.mmmi.cbse.common.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class World {

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private WorldMap worldMap;


    public WorldMap getWorldMap() {
        return worldMap;
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }
    
    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }
    
    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
        List<Entity> r = new ArrayList<>();
        for (Entity e : getEntities()) {
            for (Class<E> entityType : entityTypes) {
                //System.out.println(entityType + " : " + e.getClass() + " : " + entityType.equals(e.getClass()));
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
    
    public <E extends Entity> List<Entity> getChildrenOfParent(Class<E>... parentTypes){
        List<Entity> r = new ArrayList<>();
        for(Entity e : getEntities()){
            for (Class<E> parentType : parentTypes){
                if(e.getClass().getSuperclass().equals(parentType)){
                    r.add(e);
                }
            }
        }
        return r;
    } 

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

}
