/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.data.WorldMap;

/**
 *
 * @author Emil
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
        //world.setWorldMap(null);
    }
       
    
    public void createMap() {
        map = new TiledMap();
        
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/mapforproject.tmx"); 
        
        renderer = new OrthogonalTiledMapRenderer(map);
        sr = new ShapeRenderer();
        Gdx.gl.glLineWidth(3);

        camera = new OrthographicCamera(); 
        
    }
}
