package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.osgibomb.BombController;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;

public class PlayerControlSystem implements IEntityProcessingService {
    
    private BombSPI bombSPI = new BombController();
    private boolean placingBomb = false;
    

    @Override
    public void process(GameData gameData, World world) {
       for (Entity player : world.getEntities(Player.class)) {
           
           LifePart lp = player.getPart(LifePart.class);
           lp.process(gameData, player);
           
           if(world.getEntities(LifeHeart.class).size() > lp.getLife()){
               for(Entity heart : world.getEntities(LifeHeart.class)){
                   PositionPart pp = heart.getPart(PositionPart.class);
                   if(pp.getX() == lp.getLife() * 32){
                       world.removeEntity(heart);
                   }
               }
           }
           /*
           for(int i = 1; i <= world.getEntities(LifeHeart.class).size(); i++){
                Entity heart = (Entity) world.getEntities(LifeHeart.class).toArray()[i-1];
                PositionPart pp = heart.getPart(PositionPart.class);
                pp.process(gameData, heart);
                if(i > lp.getLife()){
                    world.removeEntity(heart);
                }
           }*/
           
//        if(world.getEntities(LifeHeart.class).isEmpty() && !lp.isDead()){
////            Entity[] hearts = {
////                new LifeHeart(0,0),
////                new LifeHeart(32,0),
////                new LifeHeart(64,0),
////                new LifeHeart(96,0),
////                new LifeHeart(128,0),
////            };
//            for(int i = 0; i < hearts.length; i++){
//                hearts[i].add(new PositionPart(32 * i, 0));
//                world.addEntity(hearts[i]);
//                PositionPart hpp = hearts[i].getPart((PositionPart.class));
//                hpp.process(gameData, hearts[i]);
//            }
//        }
//
//           for (int i = 0; i < 5 - lp.getLife() ; i++) {
//               world.removeEntity(hearts[4 - i]);
//           }
           
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
                    bombSPI.createBomb(player, world, gameData);
                }
            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);

//
       }
    }
}

