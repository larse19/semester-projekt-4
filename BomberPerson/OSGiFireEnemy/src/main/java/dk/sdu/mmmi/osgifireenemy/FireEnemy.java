/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgifireenemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.osgicommonenemy.Enemy;

/**
 *
 * @author soulb
 */
public class FireEnemy extends Enemy {
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/fireEnemy.png"), 0, 0, 22, 20));
    }
}
