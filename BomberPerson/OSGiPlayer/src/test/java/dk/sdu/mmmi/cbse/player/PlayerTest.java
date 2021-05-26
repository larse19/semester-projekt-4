/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.osgibomb.BombController;
import dk.sdu.mmmi.osgibomb.ClassicBomb;
import dk.sdu.mmmi.osgicommonbomb.Bomb;
import dk.sdu.mmmi.osgicommonbomb.BombSPI;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author andre
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPlaceBomb() {
        System.out.println("Tests if bomb is placed");
        World world = new World();
        GameData gameData = new GameData();
        Player player = new Player();

        player.add(new PositionPart(0, 0));
        
        player.getBombSPI().createBomb(player, world, gameData);
     
        List<Entity> bombs = world.getEntities(ClassicBomb.class);
        int expResult = 1;
        assertEquals(expResult, bombs.size());
    }
    
}
