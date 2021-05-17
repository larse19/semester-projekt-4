package dk.sdu.mmmi.test;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class processor implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        System.out.println("test");
    }

}
