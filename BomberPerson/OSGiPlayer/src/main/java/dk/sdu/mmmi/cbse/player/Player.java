/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.osgibomb.BombController;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;

/**
 *
 * @author Emil
 */

public class Player extends Entity{

    private int movementSpeed = 80;
    private int inventorySize = 2;
    private int blastRadius = 1;
    private int health = 5;
    private BombController bombSPI = new BombController();
    
    public Player(){
    }
    
    @Override
    public void create(){
        this.setSprite(new Sprite(new Texture("img/playerSprite.png"), 0, 0, 22, 20));
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
        MovingPart mp = this.getPart(MovingPart.class);
        mp.setMaxSpeed(movementSpeed);
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public void setInventorySize(int inventorySize) {
        this.inventorySize = inventorySize;
    }

    public int getExplosionRadius() {
        return blastRadius;
    }

    public void setExplosionRadius(int explosionRadius) {
        this.blastRadius = explosionRadius;
        BombController.setBlastRadius(explosionRadius);
    }

    public BombSPI getBombSPI() {
        return bombSPI;
    }

    public void setBombSPI(BombController bombSPI) {
        this.bombSPI = bombSPI;
    }

    public int getHealth() {
        try{
            LifePart lp = this.getPart(LifePart.class);
            int hp = lp.getLife();
            return hp;
        }catch(NullPointerException e){
            System.out.println("huh get");
            return this.health;
        }
        
    }

    public void setHealth(int health) {
        try{
            LifePart lp = this.getPart(LifePart.class);
            lp.setLife(health);
        }catch(NullPointerException e){ 
            System.out.println("huh set");
        }
        this.health = health;
    }
    
    
   
}
