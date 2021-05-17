package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private Player player;
    
    private String blockedKey = "blocked";

    @Override
    public void start(GameData gameData, World world) {
        player = new Player();
        player.setPlayer(true);
        player.add(new PositionPart(300 - 11, 300 - 10));
        player.add(new MovingPart(player.getMovementSpeed(), world));
        player.add(new LifePart(player.getHealth()));
        for (int i = 0; i < player.getHealth(); i++) {
            Entity heart = new LifeHeart(64+(i* 32), 577);
            heart.add(new PositionPart(i * 32, 0));
            boolean existingHeart = false;
            for(Entity e : world.getEntities(LifeHeart.class)){
                PositionPart pp = e.getPart((PositionPart.class));
                if(pp.getX() == i * 32){
                    existingHeart = true;
                    break;
                }
            }
            if(!existingHeart){
                world.addEntity(heart);
            }
        }
        for (int i = 0; i < maxBombs; i++) {
            Entity bombCounter = new BombCounter(400+(i* 32), 577);
            bombCounter.add(new PositionPart(i * 32, 0));
            world.addEntity(bombCounter);
        }
        System.out.println("player created");
        if (world.getEntities(Player.class).isEmpty()){
            world.addEntity(player);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        for(Entity player : world.getEntities(Player.class)){
            world.removeEntity(player);
        }
    }
  
}

