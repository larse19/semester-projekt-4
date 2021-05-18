package dk.sdu.mmmi.cbse.common.data;


public class GridCell{
    private int x;
    private int y;
    private boolean blocked;
    private boolean isPath;

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

    public boolean isPath() {
        return isPath;
    }

    public void setIsPath(boolean isPath) {
        this.isPath = isPath;
    }

    @Override
    public String toString(){
        return x + "," + y;
    }

    @Override
    public boolean equals(Object o){
        // If the object is compared with itself then return true  
        if (o == this) {
            return true;
        }
  
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof GridCell)) {
            return false;
        }
        
        GridCell gc = (GridCell)o;
        
        return (this.getX() == gc.getX() && this.getY() == gc.getY());
    }
}
