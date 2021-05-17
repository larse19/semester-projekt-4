package dk.sdu.mmmi.osgiupgrades;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class UpgradePlugin implements IGamePluginService{

    @Override
    public void start(GameData gameData, World world) {
        Entity upgrade = new Upgrade(UpgradeType.BlastRadius);
        upgrade.add(new PositionPart(7 * 32, 9 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.MovementSpeed);
        upgrade.add(new PositionPart(9 * 32, 11 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.BlastRadius);
        upgrade.add(new PositionPart(11 * 32, 9 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.MovementSpeed);
        upgrade.add(new PositionPart(9 * 32, 7 * 32));
        world.addEntity(upgrade);
        
        
        upgrade = new Upgrade(UpgradeType.InventorySize);
        upgrade.add(new PositionPart(3 * 32, 15 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.Health);
        upgrade.add(new PositionPart(15 * 32, 15 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.Health);
        upgrade.add(new PositionPart(3 * 32, 3 * 32));
        world.addEntity(upgrade);
        upgrade = new Upgrade(UpgradeType.InventorySize);
        upgrade.add(new PositionPart(15 * 32, 3 * 32));
        world.addEntity(upgrade);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity entity : world.getEntities(Upgrade.class)){
            world.removeEntity(entity);
        }
    }

}
