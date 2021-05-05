package dk.sdu.mmmi.osgiupgrade;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;


public class UpgradeSystem implements IEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        for(Entity upgrade : world.getEntities(TestUpgrade.class)){
            PositionPart pp = upgrade.getPart(PositionPart.class);
            pp.process(gameData, upgrade);
        }
    }

    public void createUpgrade(int x, int y, World world){
        Entity upgrade = new TestUpgrade();
        upgrade.add(new PositionPart(x, y));
        world.addEntity(upgrade);
    }
    
}
