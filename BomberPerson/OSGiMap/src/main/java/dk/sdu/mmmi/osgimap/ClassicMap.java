/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgimap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import dk.sdu.mmmi.cbse.common.data.WorldMap;


public class ClassicMap extends WorldMap {

    public ClassicMap() {
        
    }
   
    
    @Override
    public void create() {
        TiledMap map = new TiledMap();
        
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/mapforproject.tmx"); 
        this.setMap(map);
        
        this.setRenderer(new OrthogonalTiledMapRenderer(map));
        this.setSr(new ShapeRenderer());
        Gdx.gl.glLineWidth(3);

        //camera = new OrthographicCamera();
    }
}
