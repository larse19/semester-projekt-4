package dk.sdu.mmmi.osgibomb;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonexplosion.ExplosionSPI;
import dk.sdu.mmmi.explosion.ExplosionSystem;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;
import java.util.ArrayList;
import java.util.List;

public class BombController implements IEntityProcessingService, BombSPI{
    
    private ExplosionSPI espi = new ExplosionSystem();
    private int blastRadius = 3;
    
    @Override
    public void process(GameData gameData, World world) {

        for(Entity bomb : world.getEntities(ClassicBomb.class)){
            
            boolean furtherTop = true, furtherRight = true, furtherBottom = true, furtherLeft = true;
            List<Integer[]> spawnPlaces = new ArrayList<>();
           
            LifePart lifePart = bomb.getPart(LifePart.class);
            TimerPart timerPart = bomb.getPart(TimerPart.class);
            PositionPart pp = bomb.getPart(PositionPart.class);
             
            if(lifePart.getLife() <= 0){
                timerPart.setExpiration(0);
            }
            
            if(timerPart.getExpiration() <= 0){
                TiledMapTileLayer layer = (TiledMapTileLayer) world.getWorldMap().getMap().getLayers().get("Walls");
                for (int i = 1; i < blastRadius; i++) {
                    int distance = 32 * i;
                    
                    //check top
                    if(furtherTop){
                        if(isCellBlocked((int) pp.getX(), (int) pp.getY() + distance, layer)){
                            furtherTop = false;
                            if(isCellDestructable((int) pp.getX(), (int) pp.getY() + distance, layer)){
                                Integer[] coords = {(int) pp.getX(), (int) pp.getY() + distance};
                                spawnPlaces.add(coords);
                            }
                        }else{
                            Integer[] coords = {(int) pp.getX(), (int) pp.getY() + distance};
                            spawnPlaces.add(coords);
                        }
                    }
                    //Check right
                    if(furtherRight){
                        if(isCellBlocked((int) pp.getX() + distance, (int) pp.getY(), layer)){
                            furtherRight = false;
                            if(isCellDestructable((int) pp.getX() + distance, (int) pp.getY(), layer)){
                                Integer[] coords = {(int) pp.getX() + distance, (int) pp.getY()};
                                spawnPlaces.add(coords);
                            }
                        }else{
                            Integer[] coords = {(int) pp.getX() + distance, (int) pp.getY()};
                            spawnPlaces.add(coords);
                        }
                    }
                    if(furtherBottom){
                        if(isCellBlocked((int) pp.getX(), (int) pp.getY() - distance, layer)){
                            furtherBottom = false;
                            if(isCellDestructable((int) pp.getX(), (int) pp.getY() - distance, layer)){
                                Integer[] coords = {(int) pp.getX(), (int) pp.getY() - distance};
                                spawnPlaces.add(coords);
                            }
                        }else{
                            Integer[] coords = {(int) pp.getX(), (int) pp.getY() - distance};
                            spawnPlaces.add(coords);
                        }
                    }
                    if(furtherLeft){
                        if(isCellBlocked((int) pp.getX() - distance, (int) pp.getY(), layer)){
                            furtherLeft = false;
                            if(isCellDestructable((int) pp.getX() - distance, (int) pp.getY(), layer)){
                                Integer[] coords = {(int) pp.getX() - distance, (int) pp.getY()};
                                spawnPlaces.add(coords);
                            }
                        }else{
                            Integer[] coords = {(int) pp.getX() - distance, (int) pp.getY()};
                            spawnPlaces.add(coords);
                        }
                    }
                }
                
                //Explosion at bombs location
                Integer[] coords = {(int) pp.getX(), (int) pp.getY()};
                spawnPlaces.add(coords);
                
                for (Integer[] spawnPlace : spawnPlaces) {
                    espi.createExplosion(spawnPlace[0], spawnPlace[1], world);
                }
                
                world.removeEntity(bomb);
            }
            timerPart.process(gameData, bomb);
            lifePart.process(gameData, bomb);
        }
    }
    
    private boolean isCellDestructable(float x, float y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("destructable");       
    }
    
    private boolean isCellBlocked(float x, float y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");       
    }

    @Override
    public void createBomb(Entity entity, World world, GameData gameData) {
        Entity bomb = new ClassicBomb();
        bomb.add(new LifePart(1, 0));
        bomb.add(new TimerPart(2));
        PositionPart playerPP = entity.getPart(PositionPart.class);
        int x = (int)(playerPP.getX() / 32) * 32;
        int y = (int)(playerPP.getY() / 32) * 32;
        bomb.add(new PositionPart(x,y));
        bomb.create();
        bomb.getSprite().setPosition(x, y);
        
        boolean existingBomb = false;
        
        for (Entity otherBomb : world.getEntities(ClassicBomb.class)){
            PositionPart pp = otherBomb.getPart(PositionPart.class);
            if (pp.getX() == x && pp.getY() == y){
                existingBomb = true;
                break;
            }
        }
        if (!existingBomb){
            world.addEntity(bomb);
        }
    }
}
