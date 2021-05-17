package dk.sdu.mmmi.osgiupgrades;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.player.Player;

public class UpgradeController implements IPostEntityProcessingService{
    
    @Override
    public void process(GameData gameData, World world) {
        for(Entity upgrade : world.getEntities(Upgrade.class)){
            PositionPart pp = upgrade.getPart(PositionPart.class);
            pp.process(gameData, upgrade);
            for(Entity e : world.getEntities(Player.class)){
                if(Collides(upgrade, e)){
                    Upgrade upgrade_ = (Upgrade)upgrade;
                    Player player = (Player)e;
                    switch(upgrade_.getUpgradeType()){
                        case MovementSpeed:
                            player.setMovementSpeed(player.getMovementSpeed() + 10);
                            System.out.println("MovementSpeed: " + player.getMovementSpeed());
                            break;
                        case BlastRadius:
                            player.setExplosionRadius(player.getExplosionRadius() + 1);
                            System.out.println("BlastRadius: " + player.getExplosionRadius());
                            break;
                        case Health:
                            player.setHealth(player.getHealth() + 1);
                            System.out.println("Health: " + player.getHealth());
                            break;
                        case InventorySize:
                            player.setInventorySize(player.getInventorySize() + 1);
                            System.out.println("Inventory size: " + player.getInventorySize());
                            break;
                    }
                    world.removeEntity(upgrade);
                    
                }
            }
        }
    }
    
    private Boolean Collides(Entity entity, Entity entity2) {
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
