/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */


public class Player extends Entity {
       
    private TiledMapTileLayer collisionLayer;
    
        // constructor
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer) {
//        super(sprite);
        this.collisionLayer = collisionLayer;
    }
    
    public Player(){
        
    }
}
