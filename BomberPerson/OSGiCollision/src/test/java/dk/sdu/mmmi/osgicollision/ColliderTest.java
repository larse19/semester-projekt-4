/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgicollision;

import com.badlogic.gdx.graphics.Texture;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.DamagePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.osgizombie.Zombie;
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
public class ColliderTest {
    
    public ColliderTest() {
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

    /**
     * Test of process method, of class Collider.
     */
    @Test
    public void testProcess() {
        System.out.println("Testing for damage");
        
        GameData gameData = new GameData();
        World world = new World();
       
        
        Entity player = new Player();
        player.add(new PositionPart(300 - 11, 300 - 10));
        player.add(new LifePart(5, 0));
        player.setHeight(20);
        player.setWidth(22);
        
        player.setPlayer(true);
        
        
        Entity zombie = new Zombie();
        zombie.add(new PositionPart(300 - 11, 300 - 10));
        zombie.add(new LifePart(1));
        zombie.add(new DamagePart(2));
        zombie.setHeight(20);
        zombie.setWidth(22);
        
        world.addEntity(zombie);
        world.addEntity(player);
        
        Collider instance = new Collider();

        int expResult = 3;
        instance.process(gameData, world);
        
        LifePart playerLife = player.getPart(LifePart.class);   
        int result = playerLife.getLife();
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of Collides method, of class Collider.
     */
    @Test
    public void testCollides() {
        System.out.println("Test for Collision");
      
        Entity player = new Player();
        player.add(new PositionPart(300 - 11, 300 - 10));
        player.setHeight(20);
        player.setWidth(22);
        
        Entity zombie = new Zombie();
        zombie.add(new PositionPart(300 - 11, 300 - 10));
        zombie.setHeight(20);
        zombie.setWidth(22);
        
        
        Collider instance = new Collider();
        Boolean result = instance.Collides(player, zombie);
        assertTrue(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
