/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgiai;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import java.util.ArrayList;

/**
 *
 * @author TTengs
 */
public class AStar {
    private GridCell[][] stateSpace;
    private GridCell[][] path;
    
    public AStar() {
    }
    
    public void update(World world){
        stateSpace = setupStateSpace(world);
    }
    
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
        
            GridCell[][] nodes = new GridCell[mapWidth][mapHeight];
            path = new GridCell[mapWidth][mapHeight];    
            
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    if (isCellBlocked(x, y, collisionLayer)) {
                        nodes[x][y] = new GridCell(x * tileWidth, y * tileHeight, true);
                    } else {
                        nodes[x][y] = new GridCell(x * tileWidth, y * tileHeight, false);
                    }
                }
            }
            
            nodes[1][1].setIsPath(true);
            
            if (world.getEntities(Rectangle.class).isEmpty()) {
                
                System.out.println("Yeds");
                for (int y = 0; y < mapHeight; y++) {
                    for (int x = 0; x < mapWidth; x++) {
                            Rectangle erect = new Rectangle(nodes[x][y].getX(), nodes[x][y].getY(), nodes[x][y].isBlocked(), nodes[x][y].isPath());
                            erect.add(new PositionPart(x, y));
                            world.addEntity(erect);
                    }
                }
            }
            return nodes;
        }
        return null;
    }
    
    private GridCell[] aStarSearch (GridCell[][] nodes, int x, int y){
        ArrayList<GridCell> fringe = new ArrayList<>();
        GridCell initialNode = getEnemyCell(nodes);
        fringe.add(initialNode);
        while (!fringe.isEmpty()){
            Node node = aStarNode(fringe);
            if (getEnemyCell().equals(getPlayerCell())){
                return node.path();
            }
            GridCell children = getNeighbors(node);
            fringe.add(children);
        }
        return null;
    }
    
    private GridCell getNeighbors(GridCell node){
        ArrayList<GridCell> neighbors = new ArrayList<>();
        if (!node.setX(1).isBlocked()){
            
        }
        return null;
    }
    
    private boolean isCellBlocked(int x, int y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");       
    }
}
