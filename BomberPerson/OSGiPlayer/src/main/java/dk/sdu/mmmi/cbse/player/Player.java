/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */

public class Player extends Entity{
      
    
    public Player(){
    }


    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/playerSprite.png"), 0, 0, 22, 20));
    }
   
}
