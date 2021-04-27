/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

/**
 *
 * @author Someone
 */
public class LifePart implements EntityPart {
    private boolean dead = false;
    private int life;
    private boolean isHit = false;
    private TimerPart timerPart;
    private float invincibilyTime = 1f;

    public LifePart(int life) {
        timerPart = new TimerPart(invincibilyTime);
        this.life = life;
    }
    
    public LifePart(int life, float invincibilyTime){
        this.invincibilyTime = invincibilyTime;
        timerPart = new TimerPart(invincibilyTime);
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }
    
    public boolean isDead() {
        return dead;
    }

    public void damage(int damage){
        if(timerPart.getExpiration() <= 0){
            life -= damage;
            timerPart.setExpiration(invincibilyTime);
        }
        
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        timerPart.process(gameData, entity);
        if (life <= 0) {
            dead = true;
        }
        
    }
}
