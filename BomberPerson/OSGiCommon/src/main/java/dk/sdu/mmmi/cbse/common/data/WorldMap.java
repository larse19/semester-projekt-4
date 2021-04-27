/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.FlushablePool;
import com.badlogic.gdx.utils.Pool;
/**
 *
 * @author soulb
 */


public class WorldMap extends ApplicationAdapter {
    
    private ShapeRenderer sr;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private MapObjects wallObjects;

    public MapObjects getWallObjects() {
        return wallObjects;
    }

    public void setWallObjects(MapObjects wallObjects) {
        this.wallObjects = wallObjects;
    }

    
    
    public ShapeRenderer getSr() {
        return sr;
    }

    public void setSr(ShapeRenderer sr) {
        this.sr = sr;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public void setRenderer(OrthogonalTiledMapRenderer renderer) {
        this.renderer = renderer;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }
    
    @Override
    public void create(){
        
        /*
        // there are several other types, Rectangle is probably the most common one
        for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {

            Rectangle rectangle = rectangleObject.getRectangle();
            if (Intersector.overlaps(rectangle, player.getRectangle()) {
                // collision happened
    }
}
        
        for (int y = 0; y < Gdx.graphics.getHeight(); y++) {
            for (int x = 0; x < Gdx.graphics.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                if (cell != null)
                {
                    Rectangle rect = wallPool.obtain();
                    rect.set(x, y, 32, 32);
                    walls.add(rect);
                    System.out.println(x + ", " + y);
                }
            }
            
        }*/
    }
    
    
    
}
