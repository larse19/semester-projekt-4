package dk.sdu.mmmi.ogsiai;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.TimerPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.player.Player;
import dk.sdu.mmmi.osgicommonenemy.Enemy;
import java.util.ArrayList;

public class AISystem implements IEntityProcessingService{
    
    @Override
    public void process(GameData gameData, World world) {
        for(Entity timer : world.getEntities(AStarTimer.class)){
            TimerPart tp = timer.getPart(TimerPart.class);
            tp.process(gameData, timer);
            if(tp.getExpiration() < 0){
                Entity player = world.getEntities(Player.class).get(0);
                GridCell goalCell = getPlayerCell(player);
                for(Entity enemy : world.getChildrenOfParent(Enemy.class)){
                    GridCell[][] stateSpace = setupStateSpace(world);
                    GridCell initialState = getEntityGridCell(stateSpace, enemy);
                    renderPath(world, aStarSearch(stateSpace, initialState, goalCell));
                    tp.setExpiration(1);
                }
            }
        }
    }
    
    // Astar search for a given entity
    private ArrayList<Node> aStarSearch (GridCell[][] stateSpace, GridCell initialState, GridCell goalCell){
        ArrayList<Node> fringe = new ArrayList<>();
        Node node = new Node(initialState);
        fringe.add(node);
        int searchDepth = 0;
        while (!fringe.isEmpty()){
            node = aStarNode(fringe, goalCell);
            if(node.getDepth() > 50 || searchDepth > 100){
                return new ArrayList<Node>();
            }
            System.out.println("Astar node: " + node.toString());
            if (node.getState().equals(goalCell)){
                return node.path();
            }            
            ArrayList<Node> children = expand(stateSpace, node);
            fringe.addAll(children);
            searchDepth++;
        }
        return new ArrayList<Node>();
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
    
    private GridCell getPlayerCell(Entity player){
        PositionPart playerPP = player.getPart(PositionPart.class);
        int playerX = (int)(playerPP.getX() / 32) * 32; //Converts the entity pos to a multiplier of 32
        int playerY = (int)(playerPP.getY() / 32) * 32;
        return new GridCell(playerX, playerY, true);
    }
    
    //Gets the node from the fringe with the lowest f value. Removes it from fringe
    public Node aStarNode(ArrayList<Node> fringe, GridCell goalCell){
        Node lowest = fringe.get(0);
        for(Node node : fringe){
            if(node.getF(goalCell) < lowest.getF(goalCell)){
                lowest = node;
                               
            }
        }
        fringe.remove(lowest); 
        return lowest;
    }
    
    // Returns the node on stateSpace at the entitis location
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
    
    private ArrayList<Node> expand(GridCell[][] stateSpace, Node node){
        ArrayList<Node> successors = new ArrayList<>();
        int[] nodeCoordsInArray = getNodeCoordsInArray(stateSpace, node);
        ArrayList<GridCell> children = successorFunction(stateSpace, nodeCoordsInArray[0], nodeCoordsInArray[1]);
        for(GridCell child : children){
            Node s = new Node(child);
            s.setParent(node);
            s.setDepth(node.getDepth() + 1);
            successors.add(s);
        }
        return successors;        
    }

    //Returns a two-dimensional array with nodes for each cell, and checks if each cell is walkable or not
    private GridCell[][] setupStateSpace(World world){
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
            for(Entity e : world.getEntities(Rectangle.class)){
                world.removeEntity(e);
            }
            for (int y = 0; y < stateSpace.length; y++) {
                for (int x = 0; x < stateSpace[y].length; x++) {
                        Rectangle erect = new Rectangle(stateSpace[y][x].getX(), stateSpace[y][x].getY(), stateSpace[y][x].isBlocked(), stateSpace[y][x].isPath());
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
                System.out.println("Path: " + node.toString());
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
