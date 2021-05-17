/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgiai;

/**
 *
 * @author TTengs
 */
public class Node {
    private GridCell state;
    private Node parent = null;
    private int depth = 0;

    public Node(GridCell state, Node parent, int depth) {
        this.state = state;
        this.parent = parent;
        this.depth = depth;
    }

    public Node(GridCell state) {
        this.state = state;
    }
    
    public GridCell path (){
        return null;
    }
    
    public float getH(){
        return 0;
    }
    
    public float getG(){
        return 0;
    }
    
    public float getF(){
        return getH() + getG();
    }
}
