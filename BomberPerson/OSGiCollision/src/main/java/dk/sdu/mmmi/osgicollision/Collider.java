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
//                    System.out.println(colliderObject.getClass());
//                    System.out.println(colliderObject.getPart(DamagePart.class) != null);
                    DamagePart dp = colliderObject.getPart(DamagePart.class);
                    if(dp != null){
                        if(!dp.isCanDamageEnemies() && !entity.isPlayer()){
                        }else{
                            LifePart lp = entity.getPart(LifePart.class);
                            if(lp != null){
                                lp.damage(dp.getDamage());
                            }
                        }
                        
                    }
                }
                
            }
        }
    }
    
    public Boolean Collides(Entity entity, Entity entity2) {
        try{
            PositionPart entMov = entity.getPart(PositionPart.class);
            PositionPart entMov2 = entity2.getPart(PositionPart.class);
            float height1 = entity.getHeight();
            float height2 = entity2.getHeight();
            float width1 = entity.getWidth();
            float width2 = entity2.getWidth();

            //Collision
            return entMov.getX() < (entMov2.getX() + width2) &&
                    (entMov.getX() + width1) > entMov2.getX() &&
                    entMov.getY() < (entMov2.getY() + height2) &&
                    (entMov.getY() + height1) > entMov2.getY();
        }catch(NullPointerException e){
            return false;
        }
    }
}
