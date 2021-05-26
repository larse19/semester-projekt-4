/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GridCell;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.osgimap.MapPlugin;
import java.util.ArrayList;
import java.util.Collections;
import net.java.games.input.Component;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author ander
 */
public class AISystemTest {
    private static GridCell[][] stateSpace;
    
    public AISystemTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        stateSpace = new GridCell[5][5];
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                stateSpace[y][x] = new GridCell(x, y, false);
            }
        }
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
        System.out.println("Testing for best node");
        Node bestNode = new Node(new GridCell(0, 0, false));
        ArrayList<Node> fringe = new ArrayList<>();
        fringe.add(new Node(new GridCell(5, 5, false)));
        fringe.add(new Node(new GridCell(4, 4, false)));
        fringe.add(bestNode);
        GridCell goalCell = new GridCell(1, 1, false);
        
        AISystem instance = new AISystem();
    
        Node node = instance.aStarNode(fringe, goalCell);
        Boolean existsInFringe = fringe.contains(node);
        
        assertFalse(existsInFringe);
        assertEquals(bestNode, node);
//        System.out.println(fringe.toString());
//        System.out.println(node.toString());
    }
    
    @Test
    public void testAStar(){
        System.out.println("Testing Astar search algorithm");
        AISystem aISystem = new AISystem();
        GridCell start = stateSpace[0][0];
        GridCell goal = stateSpace[0][stateSpace.length -1];
        ArrayList<Node> path = aISystem.aStarSearch(stateSpace, start, goal);

        Boolean result = true;
        Collections.reverse(path);
        for (int y = 0; y < path.size(); y++) {
            if (!path.get(y).getState().equals(stateSpace[0][y])) {
                result = false;
                break;
            }
        }
        
        assertTrue(result);
        
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
