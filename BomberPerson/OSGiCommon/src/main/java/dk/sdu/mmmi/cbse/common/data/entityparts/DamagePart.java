package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;


public class DamagePart implements EntityPart{
    
    private int damage;
    private boolean canDamageEnemies = true;
    
    public DamagePart(int damage){
        this.damage = damage;
    }
    public DamagePart(int damage, boolean canDamageEnemies){
        this.damage = damage;
        this.canDamageEnemies = canDamageEnemies;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isCanDamageEnemies() {
        return canDamageEnemies;
    }

    public void setCanDamageEnemies(boolean canDamageEnemies) {
        this.canDamageEnemies = canDamageEnemies;
    }

    @Override
    public void process(GameData gameData, Entity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
