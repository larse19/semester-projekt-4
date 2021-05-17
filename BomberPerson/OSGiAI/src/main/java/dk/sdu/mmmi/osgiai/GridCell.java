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
public class GridCell {
    private int x;
    private int y;
    private boolean blocked;
    private boolean isPath;
    private float f, g, h;

    public GridCell(int x, int y, boolean blocked) {
        this.x = x;
        this.y = y;
        this.blocked = blocked;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
    
    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public boolean isPath() {
        return isPath;
    }

    public void setIsPath(boolean isPath) {
        this.isPath = isPath;
    }

    public String toString(){
        return "" + blocked;
    }
    
}
