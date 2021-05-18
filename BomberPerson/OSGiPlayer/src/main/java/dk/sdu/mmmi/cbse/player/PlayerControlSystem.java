package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.osgibomb.ClassicBomb;

public class PlayerControlSystem implements IEntityProcessingService {
    
    private boolean placingBomb = false;

    @Override
    public void process(GameData gameData, World world) {
       for (Entity player : world.getEntities(Player.class)) {
            Player player_ = (Player)player;

            LifePart lp = player.getPart(LifePart.class);
            lp.process(gameData, player);

            //Heart system
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
                 int x = (player_.getHealth() + 1) * 32;
                 Entity heart = new LifeHeart(x, 577);
                 heart.add(new PositionPart(x, 577));
                 world.addEntity(heart);
            }
           
            //Player dies
            if(lp.isDead()){
                System.out.println("you ded");
                world.removeEntity(player);
            }
           
            //Movement
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));

            //Bomb inventory system
            int bombsInWorld = world.getEntities(ClassicBomb.class).size();
            int numOfDisplayedInventoryBombs = world.getEntities(BombCounter.class).size();
            int inventory = player_.getInventorySize() - bombsInWorld;
            
            if(inventory > numOfDisplayedInventoryBombs){
                for(int i = numOfDisplayedInventoryBombs; i < inventory; i++){
                    Entity bombCounter = new BombCounter(400+(i* 32), 577);
                    bombCounter.add(new PositionPart(400+(i* 32), 577));
                    world.addEntity(bombCounter);
                    PositionPart bombPP = bombCounter.getPart(PositionPart.class);
                    bombPP.process(gameData, player);
                    }    
            }
            
            bombsInWorld = world.getEntities(ClassicBomb.class).size();
            numOfDisplayedInventoryBombs = world.getEntities(BombCounter.class).size();
            inventory = player_.getInventorySize() - bombsInWorld;
            
            System.out.println("inv: " + inventory + " -disp: " + numOfDisplayedInventoryBombs + " -bombs: " + bombsInWorld);
            while(inventory < numOfDisplayedInventoryBombs){
                System.out.println("heh");
                for(Entity bomb : world.getEntities(BombCounter.class)){
                    PositionPart pp = bomb.getPart(PositionPart.class);
                    if(pp.getX() >= 400 + ((numOfDisplayedInventoryBombs - 1) * 32)){
                        world.removeEntity(bomb);
                        numOfDisplayedInventoryBombs--;
                    }
                }
            }
            
                      
            
            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                placingBomb = true;            
            }else{
                if(placingBomb){
                    placingBomb = false;
                    if(inventory > 0){
                        player_.getBombSPI().createBomb(player, world, gameData);
                    }
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

//
       }
    }
}

