/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;


public class BombCounter extends Entity{
    
    int x, y;

    public BombCounter(int x, int y){
        super(false);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/bomb.png"), 0, 0, 32, 32));
        this.getSprite().setPosition(x, y);
    }
}
