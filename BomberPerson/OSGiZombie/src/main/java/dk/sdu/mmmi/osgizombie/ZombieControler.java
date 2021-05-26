/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgizombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GridCell;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.osgicommonenemy.Enemy;

/**
 *
 * @author andre
 */
public class ZombieControler implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for (Entity zombie : world.getEntities(Zombie.class)) {
           
           LifePart lp = zombie.getPart(LifePart.class);
           lp.process(gameData, zombie);
           
           if(lp.isDead()){
               System.out.println("you ded");
               world.removeEntity(zombie);
           }
           
           PositionPart positionPart = zombie.getPart(PositionPart.class);
           positionPart.process(gameData, zombie);
           MovingPart movingPart = zombie.getPart(MovingPart.class);
           
            Enemy zombie_ = (Enemy) zombie;
            if(zombie_.getPath().size() > 0){
                GridCell zombieCell = getEnemyCell(zombie);
                int currentIndex = zombie_.getPath().indexOf(zombieCell);
                if(zombie_.getPath().size() > currentIndex + 1){
                    if(positionPart.getX() < zombie_.getPath().get(currentIndex + 1 ).getX()){
                        movingPart.setRight(true);
                        movingPart.setLeft(false);
                    }else if(positionPart.getX() > zombie_.getPath().get(currentIndex + 1 ).getX()){
                        movingPart.setRight(false);
                        movingPart.setLeft(true);
                    }
                    if((int)positionPart.getX() == (int)zombie_.getPath().get(currentIndex + 1 ).getX()){
                        movingPart.setRight(false);
                        movingPart.setLeft(false);
                    }
                    if(positionPart.getY() < zombie_.getPath().get(currentIndex + 1 ).getY()){
                        movingPart.setUp(true);
                        movingPart.setDown(false);
                    }else if(positionPart.getY() > zombie_.getPath().get(currentIndex + 1 ).getY()){
                        movingPart.setUp(false);
                        movingPart.setDown(true);
                    }
                    if((int)positionPart.getY() == (int)zombie_.getPath().get(currentIndex + 1 ).getY()){
                        movingPart.setUp(false);
                        movingPart.setDown(false);
                    }
                }
            }else{
                if(positionPart.getX() < 40){
                    movingPart.setRight(true);
                    movingPart.setLeft(false);
                } 
                else if(positionPart.getX() > 550){
                     movingPart.setRight(false);
                     movingPart.setLeft(true);
                 }
            }
            
           
           
           movingPart.process(gameData, zombie);        
    }
  } 
    
    private GridCell getEnemyCell(Entity enemy){
        PositionPart playerPP = enemy.getPart(PositionPart.class);
        int playerX = (int)(playerPP.getX() / 32) * 32; //Converts the entity pos to a multiplier of 32
        int playerY = (int)(playerPP.getY() / 32) * 32;
        return new GridCell(playerX, playerY, true);
    }
}
