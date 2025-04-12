package appAncien.model.formes;

import java.io.Serializable;

public class FormePlace implements Serializable{
    private AbstractForme forme;
    private int x, y; 

    public FormePlace(AbstractForme forme, int x, int y)
    {
        this.forme = forme;
        this.x = x;
        this.y = y;

    }

    /**
     * Accesseur a la forme
     * @return forme
     */
    public AbstractForme getForme()
    {
        return forme;
    }

    /**
     * Accesseur a la position en x
     * @return x
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * Accesseur a la position en y
     * @return y
     */
    public int getY()
    {
        return y;
    }
}
