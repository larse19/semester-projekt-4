package dk.sdu.mmmi.commonexplosion;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;


public class Explosion extends Entity{

    public Explosion(){
        
    }
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/explosion.png")));
    }
    
}
