/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GridCell;
import dk.sdu.mmmi.cbse.common.data.World;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
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
        int size = 19;
        stateSpace = new GridCell[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
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
        GridCell goalCell = new GridCell(1, 1, false);
        Node bestNode = new Node(new GridCell(0, 0, false), goalCell);
        PriorityQueue<Node> fringe = new PriorityQueue<>(new NodeComparator());
        fringe.add(new Node(new GridCell(5, 5, false), goalCell));
        fringe.add(new Node(new GridCell(4, 4, false), goalCell));
        for(int i = 3; i < 50; i++){
            fringe.add(new Node(new GridCell(i, i, false), goalCell));
        }
        fringe.add(bestNode);
        
        
        AISystem instance = new AISystem();
    
        final long startTime = System.currentTimeMillis();
        Node node = instance.aStarNode(fringe);
        final long endTime = System.currentTimeMillis();
        Boolean existsInFringe = fringe.contains(node);
        
        assertFalse(existsInFringe);
        assertEquals(bestNode, node);
        System.out.println("Total execution time: " + (endTime - startTime));
//        System.out.println(fringe.toString());
//        System.out.println(node.toString());
    }
    
    @Test
    public void testAStar(){
        
        System.out.println("Testing Astar search algorithm");
        
        AISystem aISystem = new AISystem();
        GridCell start = stateSpace[0][0];
        GridCell goal = stateSpace[0][stateSpace.length -1];
        final long startTime = System.currentTimeMillis();
        ArrayList<Node> path = new ArrayList<>();
        for(int i = 0; i < 50; i++){
             path = aISystem.aStarSearch(stateSpace, start, goal);
        }
        final long endTime = System.currentTimeMillis();
        Boolean result = true;
        Collections.reverse(path);
        for (int y = 0; y < path.size(); y++) {
            if (!path.get(y).getState().equals(stateSpace[0][y])) {
                result = false;
                break;
            }
        }
        
        assertTrue(result);
        System.out.println("Total execution time for 50 itereations: " + (endTime - startTime));
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
