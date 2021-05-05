package dk.sdu.mmmi.osgiupgrade;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;


public class TestUpgrade extends Upgrade{
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/upgrade.png")));
    }

}
