package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import static com.badlogic.gdx.math.MathUtils.map;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

    private String playerID;
    private Vector2 velocity = new Vector2();
    private Entity player;
    
    private float speed = 60 * 2;
    private float gravity = 60 * 1.8f;
    private float increment;
    private TiledMap map;
    private AssetManager assetManager;
    
    private TiledMapTileLayer collisionLayer;
    
    private String blockedKey = "blocked";

    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        player = createPlayer(gameData);
        playerID = world.addEntity(player);
    }

    private Entity createPlayer(GameData gameData) {


//        player = new Player(new Sprite(new Texture("img/88874.png")), (TiledMapTileLayer) map.getLayers().get(0));
//        player.setPosition(11 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 14) * player.getCollisionLayer().getTileHeight());
        float speed = 50;

        Entity player = new Player();
          
          
          
        player.add(new PositionPart(gameData.getDisplayWidth()/2, gameData.getDisplayHeight()/2));
        player.add(new MovingPart(speed));

        //player.setSprite(new Sprite(assetManager.get("resource img/playerSprite.png", Texture.class)));
        //player.setSprite(new Sprite(new Texture("./playerSprite.png")));
        player.setSpriteLocation("img/88874.png");
        player.setSpriteWidth(22);
        player.setSpriteHeight(20);

        //Entity player = new Player(new Sprite(new Texture("img/88874.png")), (TiledMapTileLayer) map.getLayers().get(0));
        //player.setPosition(11 * player.getCollisionLayer().getTileWidth(), (player.getCollisionLayer().getHeight() - 14) * player.getCollisionLayer().getTileHeight());
//        playerShip.setRadius(8);
//        playerShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
//        playerShip.add(new PositionPart(x, y, radians));
//        playerShip.add(new LifePart(400));

        //Gdx.input.setInputProcessor(player);
//        camera.position.set(player.getWidth(), player.getHeight(), 0);
//        camera.update();            
//
//        renderer.setView(camera);
//        renderer.render();
////
//        renderer.getBatch().begin();
//        player.draw(renderer.getBatch());
//        renderer.getBatch().end();
            
//
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        //assetManager.dispose();
        world.removeEntity(playerID);
    }
  
    
    
    
    /*
    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);
    }
    
    public void update(float delta) {
        // apply gravity
        //velocity.y -= gravity * delta;

        // clamp velocity
        if (velocity.y > speed) {
            velocity.y = speed;
        } else if (velocity.y < -speed) {
            velocity.y = -speed;
        }

        // save old position
        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;

        // move on x
        setX(getX() + velocity.x * delta);

        // calculate the increment for step in #collidesLeft() and #collidesRight()
        increment = collisionLayer.getTileWidth();
        increment = getWidth() < increment ? getWidth() / 2 : increment / 2;

        collisionX = collidesRight();

        // react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        // move on y
        setY(getY() + velocity.y * delta);

        // calculate the increment for step in #collidesBottom() and #collidesTop()
        increment = collisionLayer.getTileHeight();
        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;

        if (velocity.y < 0) // going down
        {
            collisionY = collidesBottom();
        } else if (velocity.y > 0) // going up
        {
            collisionY = collidesTop();
        }

        // react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }
        if (velocity.x < 0) // going left
        {
            collisionX = collidesLeft();
        } else if (velocity.x > 0) // going right
        {
            collisionX = collidesRight();
        }

        // react to x collision
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        // move on y
        setY(getY() + velocity.y * delta);

        // calculate the increment for step in #collidesBottom() and #collidesTop()
        increment = collisionLayer.getTileHeight();
        increment = getHeight() < increment ? getHeight() / 2 : increment / 2;

        if (velocity.y < 0) // going down
        {
            collisionY = collidesBottom();
        } else if (velocity.y > 0) // going up
        {
            collisionY = collidesTop();
        }

        // react to y collision
        if (collisionY) {
            setY(oldY);
            velocity.y = 0;
        }

        }

    //inputs 
    @Override
    public boolean keyDown(int i) {
        switch(i) {
        case Keys.W:
                velocity.y = speed;
                System.out.println("W");
                break;
        case Keys.A:
                velocity.x = -speed;
                System.out.println("A");
                break;   
        case Keys.D:
                velocity.x = speed;
                System.out.println("D");
                break;
        case Keys.S:
                velocity.y = -speed; 
                System.out.println("S");
                System.out.println(velocity.y);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int i) {
        switch(i) {
        case Keys.W:
                velocity.y = 0;
                break;
        case Keys.A:
                velocity.x = 0;
                break;
        case Keys.D:
                velocity.x = 0;
                break;
        case Keys.S:
                velocity.y = 0;
                System.out.println("S let go");
                System.out.println(velocity.y);
        }   
        return true;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;    
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;    
    }

    @Override
    public boolean scrolled(int i) {
        return false;    
    }
    
    private boolean isCellBlocked(float x, float y) {
	Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
	return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey(blockedKey);
	}

    public boolean collidesRight() {
	boolean collides = false;

	for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
		if(collides = isCellBlocked(getX() + getWidth(), getY() + step))
			break;

	return collides;
	}

    public boolean collidesLeft() {
	boolean collides = false;

	for(float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2)
		if(collides = isCellBlocked(getX(), getY() + step))
			break;

	return collides;
	}

    public boolean collidesTop() {
	boolean collides = false;

	for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
		if(collides = isCellBlocked(getX() + step, getY() + getHeight()))
			break;

	return collides;

	}

    public boolean collidesBottom() {
	boolean collides = false;

	for(float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2)
		if(collides = isCellBlocked(getX() + step, getY()))
			break;

	return collides;

	}

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }*/
}

