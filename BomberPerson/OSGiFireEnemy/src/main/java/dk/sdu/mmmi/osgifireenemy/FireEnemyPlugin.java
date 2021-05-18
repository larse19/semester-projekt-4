/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgifireenemy;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 *
 * @author EC
 */
public class FireEnemyPlugin implements IGamePluginService{

    @Override
    public void start(GameData gameData, World world) {
        Entity fireEnemy = new FireEnemy();
        fireEnemy.add(new PositionPart(32, 550));
        fireEnemy.add(new LifePart(10));
        fireEnemy.add(new DamagePart(5));
        fireEnemy.add(new MovingPart(50, world));
        fireEnemy.add(new TimerPart(4.5f));
        world.addEntity(fireEnemy);       
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity fireEnemy : world.getEntities(FireEnemy.class)){
            world.removeEntity(fireEnemy);
        }
    }
    
    
}
