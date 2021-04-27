/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgimap;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import dk.sdu.mmmi.cbse.common.data.WorldMap;

/**
 *
 * @author soulb
 */
public class MapPlugin implements IGamePluginService  {

    private ShapeRenderer sr;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    
    @Override
    public void start(GameData gameData, World world) {
        WorldMap map = new ClassicMap();
        world.setWorldMap(map);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.setWorldMap(null);
    }
        
//    private TiledMap createMap(GameData gameData) {
//        TiledMap map = new TiledMap();
//        
//        TmxMapLoader loader = new TmxMapLoader();
//        map = loader.load("maps/mapforproject.tmx"); 
//        
//        renderer = new OrthogonalTiledMapRenderer(map);
//        sr = new ShapeRenderer();
//        Gdx.gl.glLineWidth(3);
//
//        camera = new OrthographicCamera();
//       
//        return map;
//    }
    
    //@Override
    public void create() {
        map = new TiledMap();
        
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/mapforproject.tmx"); 
        
        renderer = new OrthogonalTiledMapRenderer(map);
        sr = new ShapeRenderer();
        Gdx.gl.glLineWidth(3);

        camera = new OrthographicCamera();
    }

    //@Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         
//            cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
//            cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
//            cam.update();

        TiledMapTileLayer layer0 = (TiledMapTileLayer) map.getLayers().get(0);
        Vector3 center = new Vector3(layer0.getWidth() * layer0.getTileWidth() / 2, layer0.getHeight() * layer0.getTileHeight() / 2, 0);  
        camera.position.set(center);
            
        //camera.position.set(player.getWidth(), player.getHeight(), 0);

        camera.update();            

        renderer.setView(camera);

        renderer.render();
    }
    
    //@Override
    public void resize(int width, int height) {
	camera.viewportWidth = width;
	camera.viewportHeight = height;    
    }

    //@Override
    public void pause() {
    }

    //@Override
    public void resume() {
    }

    //@Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        sr.dispose();
    }


}
