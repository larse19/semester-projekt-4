/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.osgicommonenemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GridCell;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class Enemy extends Entity{

    private ArrayList<GridCell> path = new ArrayList<>();
    
    public Enemy(){
    }

    public ArrayList<GridCell> getPath() {
        return path;
    }

    public void setPath(ArrayList<GridCell> path) {
        this.path = path;
    }
    
    
   
}