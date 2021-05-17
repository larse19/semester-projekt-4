package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;

public class LifeHeart extends Entity{
    
    int x, y;

    public LifeHeart(int x, int y){
        super(false);
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/heart.png"), 0, 0, 32, 32));
        this.getSprite().setPosition(x, y);
    }
    
}
