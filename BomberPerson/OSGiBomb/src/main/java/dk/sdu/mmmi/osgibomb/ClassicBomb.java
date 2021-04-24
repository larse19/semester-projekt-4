package dk.sdu.mmmi.osgibomb;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.osgicommonbomb.Bomb;

public class ClassicBomb extends Bomb{
    
    public ClassicBomb(){
    }
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/bomb.png")));
    }
}
