package dk.sdu.mmmi.osgicommonbomb;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;


public interface BombSPI {
    
    void createBomb(Entity entity, World world, GameData gameData);
}
