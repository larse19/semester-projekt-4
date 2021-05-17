/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgiai;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;

/**
 *
 * @author TTengs
 */
public class AStar {
    private GridCell[][] stateSpace;

    public AStar() {
    }
    
    public void update(World world){
        stateSpace = setupStateSpace(world);
    }
    
    private GridCell[][] setupStateSpace(World world){
        TiledMap tiledmap = world.getWorldMap().getMap();
        if (tiledmap != null) {
            TiledMapTileLayer collisionLayer = (TiledMapTileLayer) tiledmap.getLayers().get("Walls");
            int mapWidth = tiledmap.getProperties().get("width", Integer.class);
            int mapHeight = tiledmap.getProperties().get("height", Integer.class);
            int tileWidth = tiledmap.getProperties().get("tilewidth", Integer.class);
            int tileHeight = tiledmap.getProperties().get("tileheight", Integer.class);
        
            GridCell[][] nodes = new GridCell[mapWidth][mapHeight];
        
            for (int y = 0; y < mapHeight; y++) {
                for (int x = 0; x < mapWidth; x++) {
                    if (isCellBlocked(x, y, collisionLayer)) {
                        nodes[x][y] = new GridCell(x * tileWidth, y * tileHeight, true);
                    } else {
                        nodes[x][y] = new GridCell(x * tileWidth, y * tileHeight, false);
                    }
                }
            }
//            if (world.getEntities(Rectangle.class).size() == 0) {
//                
//                //System.out.println("Yeds");
////                for (int y = 0; y < mapHeight; y++) {
////                    for (int x = 0; x < mapWidth; x++) {
////                            Rectangle erect = new Rectangle(nodes[x][y].getX(), nodes[x][y].getY(), nodes[x][y].isBlocked());
////                            erect.add(new PositionPart(x, y));
////                            world.addEntity(erect);
////                    }
////                }
//            }
            //System.out.println(nodes.toString());
            return nodes;
        }
        return null;
    }
    
    private boolean isCellBlocked(int x, int y, TiledMapTileLayer collisionLayer) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell(x, y);
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");       
    }
}
