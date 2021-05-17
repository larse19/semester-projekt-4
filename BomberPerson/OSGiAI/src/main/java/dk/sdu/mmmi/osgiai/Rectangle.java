/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgiai;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author TTengs
 */
public class Rectangle extends Entity{
    int x;
    int y;
    boolean blocked;

    public Rectangle(int x, int y, boolean blocked) {
        super(false);
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    
    @Override
    public void create(){
        if (blocked) {
            this.setSprite(new Sprite(new Texture("img/blocked.png")));
        } else {
            this.setSprite(new Sprite(new Texture("img/free.png")));
        }
        this.getSprite().setPosition(x, y);
    }
}
