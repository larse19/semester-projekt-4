package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class AIPlugin implements IGamePluginService{

    String id;
    
    @Override
    public void start(GameData gameData, World world) {
        Entity astarTimer = new AStarTimer(1f);
        id = world.addEntity(astarTimer);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(id);
    }

}
