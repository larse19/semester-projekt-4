package dk.sdu.mmmi.ogsiai;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GridCell;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.osgicommonenemy.Enemy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class AISystem implements IEntityProcessingService{
    
    @Override
    public void process(GameData gameData, World world) {
        // Uses an entity with a timerpart to only call astarsearch once a second,
        // in order to improve performance
        for(Entity timer : world.getEntities(AStarTimer.class)){
            TimerPart tp = timer.getPart(TimerPart.class);
            tp.process(gameData, timer);
            if(tp.getExpiration() < 0){
                
                List<Entity> players = world.getEntities(Player.class);
                if(!players.isEmpty()){
                    Entity player = players.get(0);
                    GridCell goalCell = getPlayerCell(player);
                    
                    //Calls astarsearch for all entities that inherit from Enemy
                    for(Entity enemy : world.getChildrenOfParent(Enemy.class)){
                        //Creates the stateSpace
                        GridCell[][] stateSpace = setupStateSpace(world);
                        //renderGridCells(world, stateSpace);
                        
                        //Gets the initialState (the gridcell where the enemy exists)
                        GridCell initialState = getEntityGridCell(stateSpace, enemy);
                        
                        //Run A star search
                        ArrayList<Node> path = aStarSearch(stateSpace, initialState, goalCell);
                        //renderPath(world, path);
                        
                        //Sets the path on the enemy object
                        Enemy enemy_ = (Enemy)enemy;
                        Collections.reverse(path);
                        enemy_.setPath(nodesToCells(path));
                        
                        //Reset timer
                        tp.setExpiration(1);
                    }
                }
                
            }
        }
    }
    
    // Astar search for a given entity
    public ArrayList<Node> aStarSearch (GridCell[][] stateSpace, GridCell initialState, GridCell goalCell){
        PriorityQueue<Node> fringe = new PriorityQueue<>(new NodeComparator());
        Node node = new Node(initialState, goalCell);
        fringe.add(node);
        int searchDepth = 0;
        
        while (!fringe.isEmpty()){
            node = aStarNode(fringe);
            
            //Escape clause, to prevent endless searching if no path is found
            if(node.getDepth() > 50 || searchDepth > 100){
                return new ArrayList<Node>();
            }
            
            //Found a path to the goal
            if (node.getState().equals(goalCell)){
                return node.path();
            }

            //Adds children to the fringe
            ArrayList<Node> children = expand(stateSpace, node, goalCell);
            fringe.addAll(children);
            searchDepth++;
        }
        return new ArrayList<Node>();
    }
    
    //Converts an arraylist of nodes to an arraylist of GridCells
    private ArrayList<GridCell> nodesToCells(ArrayList<Node> nodes){
        ArrayList<GridCell> cells = new ArrayList<>();
        for(Node node : nodes){
            cells.add(node.getState());
        }
        return cells;
    }
    
    //Returns array [x,y] with arrayCoords for given node in stateSpace
    private int[] getNodeCoordsInArray(GridCell[][] stateSpace, Node node){
        int[] coords = new int[2];
        boolean found = false;
        for(int y = 0; y < stateSpace.length; y++){
            if(!found){
                for(int x = 0; x < stateSpace[y].length; x++){
                    if(stateSpace[y][x].equals(node.getState())){
                        coords[0] = x;
                        coords[1] = y;
                        found = true;
                        break;
                    }
                }
            }else{
                break;
            }
        }
        return coords;
    }
    
    //Returns the gridcell containing the player
    private GridCell getPlayerCell(Entity player){
        PositionPart playerPP = player.getPart(PositionPart.class);
        int playerX = (int)(playerPP.getX() / 32) * 32; //Converts the entity pos to a multiplier of 32
        int playerY = (int)(playerPP.getY() / 32) * 32;
        return new GridCell(playerX, playerY, true);
    }
    
    //Gets the node from the fringe with the lowest f value. Removes it from fringe
    public Node aStarNode(PriorityQueue<Node> fringe){
        return fringe.remove();
        /*
        Node lowest = fringe.get(0);
        for(Node node : fringe){
            if(node.getF() < lowest.getF()){
                lowest = node;
            }
        }
        fringe.remove(lowest); 
        return lowest;
*/
    }
    
    // Returns the node from stateSpace, at the entitis location
    private GridCell getEntityGridCell(GridCell[][] stateSpace, Entity entity){
        PositionPart entityPP = entity.getPart(PositionPart.class);
        int entityX = (int)(entityPP.getX() / 32) * 32; //Converts the entity pos to a multiplier of 32
        int entityY = (int)(entityPP.getY() / 32) * 32;
        for(int y = 0; y < stateSpace.length; y++){
            for(int x = 0; x < stateSpace[y].length; x++){
                if(stateSpace[y][x].getX() == entityX && stateSpace[y][x].getY() == entityY){
                    return stateSpace[y][x];
                }
            }
        }
        return null;
    }
    
    //Gets the walkable neighbors of the node at the coordiantes
    private ArrayList<GridCell> successorFunction(GridCell[][] nodes, int x, int y){
        ArrayList<GridCell> neighbors = new ArrayList<>();
        try{
            if (!nodes[y - 1][x].isBlocked()){
                neighbors.add(nodes[y-1][x]);
            }
        }catch(IndexOutOfBoundsException e){}
        try{
            if (!nodes[y + 1][x].isBlocked()){
                neighbors.add(nodes[y + 1][x]);
            }
        }catch(IndexOutOfBoundsException e){}
        try{
            if (!nodes[y][x - 1].isBlocked()){
                neighbors.add(nodes[y][x - 1]);
            }
        }catch(IndexOutOfBoundsException e){}
        try{
            if (!nodes[y][x + 1].isBlocked()){
                neighbors.add(nodes[y][x + 1]);
            }
        }catch(IndexOutOfBoundsException e){}
        
        return neighbors;
    }
    
    //Loops through the successors of a node and returns an arraylist with the nodes
    private ArrayList<Node> expand(GridCell[][] stateSpace, Node node, GridCell goalCell){
        ArrayList<Node> successors = new ArrayList<>();
        
        //Gets the coordiantes (indexes) for the node in the statespace array
        int[] nodeCoordsInArray = getNodeCoordsInArray(stateSpace, node);
        ArrayList<GridCell> children = successorFunction(stateSpace, nodeCoordsInArray[0], nodeCoordsInArray[1]);
        
        //Loops through all children and creates nodes with the gridcells as states
        for(GridCell child : children){
            Node s = new Node(child, goalCell);
            s.setParent(node);
            s.setDepth(node.getDepth() + 1);
            successors.add(s);
        }
        return successors;        
    }

    //Returns a two-dimensional array with nodes for each cell, and checks if each cell is walkable or not
    public GridCell[][] setupStateSpace(World world){
        TiledMap tiledmap = world.getWorldMap().getMap();
        if (tiledmap != null) {
            TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tiledmap.getLayers().get("Walls");
            // mapWidth & mapHeight = 19
            int mapWidth = tiledmap.getProperties().get("width", Integer.class);
            int mapHeight = tiledmap.getProperties().get("height", Integer.class);
            // tileWidth & tileHeight = 32
            int tileWidth = tiledmap.getProperties().get("tilewidth", Integer.class);
            int tileHeight = tiledmap.getProperties().get("tileheight", Integer.class);
        
            GridCell[][] stateSpace = new GridCell[mapWidth][mapHeight];
            //path = new GridCell[mapWidth][mapHeight];    
            
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    if (isCellBlocked(x, y, collisionLayer)) {
                        stateSpace[y][x] = new GridCell(x * tileWidth, y * tileHeight, true);
                    } else {
                        stateSpace[y][x] = new GridCell(x * tileWidth, y * tileHeight, false);
                    }
                }
            }
            
            //renderGridCells(world, stateSpace);
            
            return stateSpace;
        }
        return null;
    }
    
    // Shows rectangles (for debugging)
    private void renderGridCells(World world, GridCell[][] stateSpace){
        System.out.println(stateSpace[0][0].isBlocked());
            for(Entity e : world.getEntities(Rectangle.class)){
                world.removeEntity(e);
            }
            for (int y = 0; y < stateSpace.length; y++) {
                for (int x = 0; x < stateSpace[y].length; x++) {
                        Rectangle erect = new Rectangle(stateSpace[y][x].getX(), stateSpace[y][x].getY(), stateSpace[y][x].isBlocked(), false);
                        erect.add(new PositionPart(x, y));
                        world.addEntity(erect);
                }
            }
    }
    
    // Shows the path (for debugging)
    private void renderPath(World world, ArrayList<Node> path){
        if(path != null){
            for(Entity e : world.getEntities(Rectangle.class)){
                    world.removeEntity(e);
            }
            for(Node node : path){
                Rectangle erect = new Rectangle(node.getState().getX(), node.getState().getY(), false, true);
                erect.add(new PositionPart(node.getState().getX(), node.getState().getX()));
                world.addEntity(erect);
            }
        }
    }
    
    //Checks if a given cell is blocked
    private boolean isCellBlocked(int x, int y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");       
    }
    
}
