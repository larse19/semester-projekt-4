package dk.sdu.mmmi.explosion;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.commonexplosion.Explosion;
import dk.sdu.mmmi.commonexplosion.ExplosionSPI;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class ExplosionSystem implements IEntityProcessingService, ExplosionSPI{

    @Override
    public void process(GameData gameData, World world) {
        for(Entity explosion : world.getEntities(Explosion.class)){
            TimerPart tp = explosion.getPart(TimerPart.class);
            tp.process(gameData, explosion);
            if(tp.getExpiration()<=0){
                world.removeEntity(explosion);
            }
            
            // Blow up walls
            PositionPart positionPart = explosion.getPart(PositionPart.class);
            TiledMapTileLayer layer = (TiledMapTileLayer) world.getWorldMap().getMap().getLayers().get("Walls");
            if(isCellDestructable(positionPart.getX(), positionPart.getY(), layer)){
                layer.setCell((int)positionPart.getX() / 32, (int)positionPart.getY() / 32, null);
            }   

        }
    }
    
    private boolean isCellDestructable(float x, float y, TiledMapTileLayer collisionLayer) {
	TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
	return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("destructable");       
    }

    @Override
    public void createExplosion(int x, int y, World world) {
        Entity explosion = new Explosion();
        explosion.add(new PositionPart(x, y));
        explosion.add(new TimerPart(0.5f));
        explosion.add(new DamagePart(1));
        explosion.create();
        explosion.getSprite().setPosition(x, y);
        world.addEntity(explosion);
    }

}
