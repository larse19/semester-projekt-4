package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class PlayerControlSystem implements IEntityProcessingService {
    
    private boolean placingBomb = false;

    @Override
    public void process(GameData gameData, World world) {
       for (Entity player : world.getEntities(Player.class)) {
            Player player_ = (Player)player;

            LifePart lp = player.getPart(LifePart.class);
            lp.process(gameData, player);

            if(world.getEntities(LifeHeart.class).size() > player_.getHealth()){
                for(Entity heart : world.getEntities(LifeHeart.class)){
                    PositionPart pp = heart.getPart(PositionPart.class);
                    if(pp.getX() >= player_.getHealth() * 32){
                        world.removeEntity(heart);
                    }
                }
            }
            if(world.getEntities(LifeHeart.class).size() < player_.getHealth()){
                 System.out.println("new heart");
                 int x = (player_.getHealth() - 1) * 32;
                 Entity heart = new LifeHeart(x, 0);
                 heart.add(new PositionPart(x, 0));
                 world.addEntity(heart);
            }
           
            if(lp.isDead()){
                System.out.println("you ded");
                world.removeEntity(player);
            }
           
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));

            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                placingBomb = true;            
            }else{
                if(placingBomb){
                    placingBomb = false;
                    player_.getBombSPI().createBomb(player, world, gameData);
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

//
       }
    }
}

