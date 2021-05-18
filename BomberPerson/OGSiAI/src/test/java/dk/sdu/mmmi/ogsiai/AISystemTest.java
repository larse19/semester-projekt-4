/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ander
 */
public class AISystemTest {
    
    public AISystemTest() {
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
    public void testAStarNode(){
        ArrayList<Node> fringe = new ArrayList<>();
        fringe.add(new Node(new GridCell(5, 5, false)));
        fringe.add(new Node(new GridCell(4, 4, false)));
        fringe.add(new Node(new GridCell(0, 0, false)));
        GridCell goalCell = new GridCell(1, 1, false);
        AISystem instance = new AISystem();
        System.out.println(fringe.toString());
        for(Node n : fringe){
            System.out.println(n.getF(goalCell));
        }
        Node node = instance.aStarNode(fringe, goalCell);
        System.out.println(fringe.toString());
        System.out.println(node.toString());
    }

    /**
     * Test of process method, of class AISystem.
     */
    /*
    @org.junit.jupiter.api.Test
    public void testProcess() {
        System.out.println("process");
        GameData gameData = null;
        World world = null;
        AISystem instance = new AISystem();
        instance.process(gameData, world);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
