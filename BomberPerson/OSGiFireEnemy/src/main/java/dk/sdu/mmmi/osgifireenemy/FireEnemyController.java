/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgifireenemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonexplosion.Explosion;
import dk.sdu.mmmi.osgicommonenemy.Enemy;
import dk.sdu.mmmi.commonexplosion.ExplosionSPI;
import dk.sdu.mmmi.explosion.ExplosionSystem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author EC
 */
public class FireEnemyController implements IEntityProcessingService, ExplosionSPI{
    
    private ExplosionSPI espi = new ExplosionSystem();
    private int blastRadius = 3;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity fireEnemy : world.getEntities(FireEnemy.class)) {
           
           LifePart lp = fireEnemy.getPart(LifePart.class);
           lp.process(gameData, fireEnemy);
           
           boolean furtherTop = true, furtherRight = true, furtherBottom = true, furtherLeft = true;
           List<Integer[]> spawnPlaces = new ArrayList<>();
           PositionPart pp = fireEnemy.getPart(PositionPart.class);
           pp.process(gameData, fireEnemy);
           
           MovingPart movingPart = fireEnemy.getPart(MovingPart.class);
           TimerPart tp = fireEnemy.getPart(TimerPart.class);
           
           if(pp.getX() < 40){
                movingPart.setRight(true);
                movingPart.setLeft(false);
           } 
           else if(pp.getX() > 550){
                movingPart.setRight(false);
                movingPart.setLeft(true);
            }
           
           movingPart.process(gameData, fireEnemy);
           
           if (tp.getExpiration() <= 0){
           //if (lp.getLife() >= 0){
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
//                Integer[] coords = {(int) pp.getX(), (int) pp.getY()};
//                spawnPlaces.add(coords);
                
                for (Integer[] spawnPlace : spawnPlaces) {
                    espi.createExplosion(spawnPlace[0], spawnPlace[1], world);
                } 
                tp.setExpiration(4.5f);
              }
              tp.process(gameData, fireEnemy);

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
  public void createExplosion(int x, int y, World world) {
        Entity explosion = new Explosion();
        explosion.add(new PositionPart(x, y));
        explosion.add(new TimerPart(0.5f));
        explosion.add(new DamagePart(1));
        explosion.create();
        explosion.getSprite().setPosition(x, y);
        world.addEntity(explosion);
   }
}
