package dk.sdu.mmmi.ogsiai;

import dk.sdu.mmmi.cbse.common.data.GridCell;
import java.util.ArrayList;

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
    
    public ArrayList<Node> path (){
        Node currentNode = this;
        ArrayList<Node> path = new ArrayList<>();
        path.add(currentNode);
        while(currentNode.parent != null){
            currentNode = currentNode.parent;
            path.add(currentNode);
        }
        return path;
    }
    
    public float getH(GridCell goalCell){
        // final multiplier is weighted
        return ((Math.abs(this.getState().getX() - goalCell.getX()) +
                Math.abs(this.getState().getY() - goalCell.getY())) * 3 
                );
    }
    
    public float getG(){
        if(this.parent == null){
            return 0;
        }
        return 1 + this.parent.getG();
    }
    
    public float getF(GridCell goalCell){
        return getH(goalCell) + getG();
    }

    public GridCell getState() {
        return state;
    }

    public void setState(GridCell state) {
        this.state = state;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
    
    @Override
    public String toString(){
        return this.getState().getX() + "," + this.getState().getY() + " - Depth: " + this.getDepth();
    }
    
}
