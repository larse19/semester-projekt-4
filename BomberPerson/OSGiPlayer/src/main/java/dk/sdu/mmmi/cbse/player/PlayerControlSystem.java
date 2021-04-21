package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {

       for (Entity player : world.getEntities(Player.class)) {
            PositionPart positionPart = player.getPart(PositionPart.class);
            MovingPart movingPart = player.getPart(MovingPart.class);
//            LifePart lifePart = player.getPart(LifePart.class);

            movingPart.setLeft(gameData.getKeys().isDown(GameKeys.LEFT));
            movingPart.setRight(gameData.getKeys().isDown(GameKeys.RIGHT));
            movingPart.setUp(gameData.getKeys().isDown(GameKeys.UP));
            movingPart.setDown(gameData.getKeys().isDown(GameKeys.DOWN));

//            if (gameData.getKeys().isDown(GameKeys.SPACE)) {
//                Entity bullet = Lookup.getDefault().lookup(BulletSPI.class).createBullet(player, gameData);
//                world.addEntity(bullet);
//            }

            movingPart.process(gameData, player);
            positionPart.process(gameData, player);
//            lifePart.process(gameData, player);
//
           updateShape(player);
//
       }
    }

    private void updateShape(Entity entity) {
        
        PositionPart positionPart = entity.getPart(PositionPart.class);
        
        try{
            entity.getSprite().setPosition(positionPart.getX(), positionPart.getY());
        }catch(NullPointerException e){
            
        }
          
    }

}

