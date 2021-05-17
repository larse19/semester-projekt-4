package dk.sdu.mmmi.osgiupgrades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;


public class Upgrade extends Entity {
    
    private UpgradeType upgradeType;
    
    public Upgrade(UpgradeType upgradeType){
        this.upgradeType = upgradeType;
    }
    
    @Override
    public void create(){
        switch(upgradeType){
            case MovementSpeed:
                this.setSprite(new Sprite(new Texture("img/speedUpgrade.png")));
                break;
            case Health:
                this.setSprite(new Sprite(new Texture("img/healthUpgrade.png")));
                break;
            case BlastRadius:
                this.setSprite(new Sprite(new Texture("img/blastRadiusUpgrade.png")));
                break;
            case InventorySize:
                this.setSprite(new Sprite(new Texture("img/InventoryUpgrade.png")));
                break;
            default:
                this.setSprite(new Sprite(new Texture("img/upgrade.png")));
                break;
        }
        
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }

    public void setUpgradeType(UpgradeType upgradeType) {
        this.upgradeType = upgradeType;
    }
    
    
}
