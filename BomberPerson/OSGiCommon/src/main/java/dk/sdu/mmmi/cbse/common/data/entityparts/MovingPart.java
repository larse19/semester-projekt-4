/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 *
 * @author Anders
 */
public class MovingPart implements EntityPart {

    private float dx, dy;
    private float maxSpeed;
    private boolean left, right, up, down;
    private World world;
    
    public MovingPart(float speed){
        this.maxSpeed = speed;
    }
    
    public MovingPart(float speed, World world){
        this.maxSpeed = speed;
        this.world = world;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
    
    private boolean isCellBlocked(float x, float y, TiledMapTileLayer collisionLayer) {
	Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
	return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
	}
    
    public boolean collidesRight(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
	boolean collides = false;

	for(float step = 0; step < entity.getSprite().getHeight(); step += collisionLayer.getTileHeight() / 2){
            if(collides = isCellBlocked(x + entity.getSprite().getWidth(), y + step, collisionLayer))
                break;
        }
		
	return collides;
	}

    public boolean collidesLeft(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
	boolean collides = false;

	for(float step = 0; step < entity.getSprite().getHeight(); step += collisionLayer.getTileHeight() / 2){
            if(collides = isCellBlocked(x, y + step, collisionLayer)){
                break;
            }
        }

	return collides;
	}

    public boolean collidesTop(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
	boolean collides = false;

	for(float step = 0; step < entity.getSprite().getWidth(); step += entity.getSprite().getHeight()/ 2){
            if(collides = isCellBlocked(x + step, y + entity.getSprite().getHeight(), collisionLayer)){
                break;
            }
        }

	return collides;

	}

    public boolean collidesBottom(float x, float y, TiledMapTileLayer collisionLayer, Entity entity) {
	boolean collides = false;

	for(float step = 0; step < entity.getSprite().getWidth(); step += entity.getSprite().getHeight()/ 2){
            if(collides = isCellBlocked(x + step, y, collisionLayer)){
                break;
            }
        }	

	return collides;

	}
    
    @Override
    public void process(GameData gameData, Entity entity){
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float dt = gameData.getDelta();
        float newX, newY;
        TiledMapTileLayer collisonLayer = (TiledMapTileLayer) world.getWorldMap().getMap().getLayers().get("Walls");
        
        if(left){
            dx -= maxSpeed * dt;
            if(collidesLeft(x + dx, y, collisonLayer, entity)){
                dx = 0;
            }
        }
        if(right){
            dx += maxSpeed * dt;
            if(collidesRight(x + dx, y, collisonLayer, entity)){
                dx = 0;
            }
        }
        if(up){
            dy += maxSpeed * dt;
            if(collidesTop(x, y + dy, collisonLayer, entity)){
                dy = 0;
            }
        }
        if(down){
            dy -= maxSpeed * dt;
            if(collidesBottom(x, y + dy, collisonLayer, entity)){
                dy = 0;
            }
        }
        
        newX = x + dx;
        newY = y + dy;
           
        dx = 0;
        dy = 0;
        
        positionPart.setX(newX);
        positionPart.setY(newY);

    }


}
