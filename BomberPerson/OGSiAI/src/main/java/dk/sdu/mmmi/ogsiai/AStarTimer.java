package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;


public class AStarTimer extends Entity{

    public AStarTimer(float timer){
        this.add(new TimerPart(timer));
    }
    
}
