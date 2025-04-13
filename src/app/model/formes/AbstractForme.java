package app.model.formes;

import java.io.Serializable;

public abstract class AbstractForme implements Serializable {
    private String nom;
    private int x, y, w, h;

    /**
     * Constructeur de la classe AbstractForme
     *
     * @param nom Le nom de la forme
     * @param x   La coordonnée x du coin supérieur gauche
     * @param y   La coordonnée y du coin supérieur gauche
     * @param w   La largeur de la forme
     * @param h   La hauteur de la forme
     */
    public AbstractForme(String nom, int x, int y, int w, int h) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * Accesseur pour recuperer le nom de la forme
     * @return Le nom de la forme
     */
    public String getNom() { return nom; }

    /**
     * Accesseur pour recuperer x 
     * @return x
     */
    public int getX() { return x; }

    /**
     * Accesseur pour recuperer y
     * @return y
     */
    public int getY() { return y; }

    /**
     * Accesseur pour recuperer la largeur de la forme
     * @return w 
     */
    public int getW() { return w; }

    /**
     * Accesseur pour recuperer la longueur de la forme
     * @return h
     */
    public int getH() { return h; }
}
