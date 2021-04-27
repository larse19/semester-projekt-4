package dk.sdu.mmmi.osgibomb;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonexplosion.ExplosionSPI;
import dk.sdu.mmmi.explosion.ExplosionSystem;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;

public class BombController implements IEntityProcessingService, BombSPI{
    
    private ExplosionSPI espi = new ExplosionSystem();
    private int blastRadius = 3;
    
    @Override
    public void process(GameData gameData, World world) {
       
        for(Entity bomb : world.getEntities(ClassicBomb.class)){
           
            TimerPart timerPart = bomb.getPart(TimerPart.class);
            if(timerPart.getExpiration() <= 0){
                PositionPart pp = bomb.getPart(PositionPart.class);
                espi.createExplosion((int)pp.getX(), (int)pp.getY(), world);
                for (int i = 1; i <= blastRadius; i++) {
                    int distance = 32 * i;
                    espi.createExplosion((int)pp.getX() - distance, (int)pp.getY(), world);
                    espi.createExplosion((int)pp.getX() + distance, (int)pp.getY(), world);
                    espi.createExplosion((int)pp.getX(), (int)pp.getY() - distance, world);
                    espi.createExplosion((int)pp.getX(), (int)pp.getY() + distance, world);
                }
                world.removeEntity(bomb);
            }
            timerPart.process(gameData, bomb);
        }
    }

    @Override
    public void createBomb(Entity entity, World world, GameData gameData) {
        Entity bomb = new ClassicBomb();
        bomb.add(new TimerPart(2));
        PositionPart playerPP = entity.getPart(PositionPart.class);
        int x = (int)(playerPP.getX() / 32) * 32;
        int y = (int)(playerPP.getY() / 32) * 32;
        bomb.add(new PositionPart(x,y));
        bomb.create();
        bomb.getSprite().setPosition(x, y);
        
        boolean existingBomb = false;
        
        for (Entity otherBomb : world.getEntities(ClassicBomb.class)){
            PositionPart pp = otherBomb.getPart(PositionPart.class);
            if (pp.getX() == x && pp.getY() == y){
                existingBomb = true;
                break;
            }
        }
        if (!existingBomb){
            world.addEntity(bomb);
        }
    }
}
