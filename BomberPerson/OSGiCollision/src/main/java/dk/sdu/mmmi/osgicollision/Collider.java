/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgicollision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 *
 * @author TTengs
 */
public class Collider implements IPostEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        //Loop through all entities in the world
        for (Entity entity : world.getEntities()){
            //Loop through all lifeparts on the entities in the world
            for (Entity colliderObject : world.getEntities()){
                
                // if the two entities are identical, skip the iteration
                if (entity.getID().equals(colliderObject.getID())) {
                    continue;
                }
                
                if(!entity.isIsCollidable() || !colliderObject.isIsCollidable()){
                    continue;
                }
                
                if (this.Collides(entity, colliderObject)){
                    //System.out.println(colliderObject.getClass());
                    //System.out.println(colliderObject.getPart(DamagePart.class) != null);
                    DamagePart dp = colliderObject.getPart(DamagePart.class);
                    if(dp != null){
                        LifePart lp = entity.getPart(LifePart.class);
                        if(lp != null){
                            lp.damage(dp.getDamage());
                        }
                    }
                }
                
            }
        }
    }
    
    public Boolean Collides(Entity entity, Entity entity2) {
        PositionPart entMov = entity.getPart(PositionPart.class);
        PositionPart entMov2 = entity2.getPart(PositionPart.class);
        float spriteHeight1 = entity.getSprite().getHeight();
        float spriteHeight2 = entity2.getSprite().getHeight();
        float spriteWidth1 = entity.getSprite().getWidth();
        float spriteWidth2 = entity2.getSprite().getWidth();
        
        //Collision
        if (entMov.getX() < (entMov2.getX() + spriteWidth2) &&
                (entMov.getX() + spriteWidth1) > entMov2.getX() &&
                entMov.getY() < (entMov2.getY() + spriteHeight2) &&
                (entMov.getY() + spriteHeight1) > entMov2.getY()){
            return true;
        } else {
            return false;
        }
        
    }
}
