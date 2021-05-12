/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.TextureMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
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
        WorldMap worldMap = new ClassicMap();
        world.setWorldMap(worldMap);
        System.out.println("helooo");
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
    
    public void createMap() {
        map = new TiledMap();
        
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/mapforproject.tmx"); 
        
        renderer = new OrthogonalTiledMapRenderer(map);
        sr = new ShapeRenderer();
        Gdx.gl.glLineWidth(3);

        camera = new OrthographicCamera(); 
        
//        MapLayer layer = map.getLayers().get("Life");
//        TextureMapObject textureMapObject = new TextureMapObject();
//        textureMapObject.setName("plane");
//        textureMapObject.setX(250);
//        textureMapObject.setY(250);
//        layer.getObjects().add(textureMapObject);

    }

//    public void render() {
//        MapLayer layer = map.getLayers().get("Life");
//        MapObjects objects = layer.getObjects();
//    }
/*
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
    }*/


}
