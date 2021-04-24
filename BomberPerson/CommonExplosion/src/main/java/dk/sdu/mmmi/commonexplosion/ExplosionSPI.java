package dk.sdu.mmmi.commonexplosion;

import dk.sdu.mmmi.cbse.common.data.World;


public interface ExplosionSPI {
    void createExplosion(int x, int y, World world);
}
