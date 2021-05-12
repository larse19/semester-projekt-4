/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgizombie;

import com.badlogic.gdx.Gdx;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.osgiai.AStar;
import dk.sdu.mmmi.osgicommonenemy.Enemy;

/**
 *
 * @author andre
 */
public class ZombieControler implements IEntityProcessingService{
    AStar aStar = new AStar();
    
    @Override
    public void process(GameData gameData, World world) {
        
        aStar.update(world);
        
        for (Entity zombie : world.getEntities(Enemy.class)) {
           
           LifePart lp = zombie.getPart(LifePart.class);
           lp.process(gameData, zombie);
           
           if(lp.isDead()){
               System.out.println("you ded");
               world.removeEntity(zombie);
           }
           
           PositionPart positionPart = zombie.getPart(PositionPart.class);
           positionPart.process(gameData, zombie);
           MovingPart movingPart = zombie.getPart(MovingPart.class);
           
           if(positionPart.getX() < 40){
                movingPart.setRight(true);
                movingPart.setLeft(false);
           } 
           else if(positionPart.getX() > 550){
                movingPart.setRight(false);
                movingPart.setLeft(true);
            }
           
           movingPart.process(gameData, zombie);
    }
  }
}
