/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgizombie;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.osgicommonenemy.Enemy;

/**
 *
 * @author andre
 */
public class ZombiePlugin implements IGamePluginService{

    @Override
    public void start(GameData gameData, World world) {
        Entity zombie = new Enemy();
        
        zombie.add(new PositionPart(32, 32));
        zombie.add(new LifePart(1));
        zombie.add(new DamagePart(2));
        zombie.add(new MovingPart(100, world));
        world.addEntity(zombie);
        
    }

    @Override
    public void stop(GameData gameData, World world) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
