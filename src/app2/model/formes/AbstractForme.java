package app2.model.formes;

import java.io.Serializable;

public abstract class AbstractForme implements Serializable {
    private String nom;
    private int x, y, w, h;

    public AbstractForme(String nom, int x, int y, int w, int h) {
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public String getNom() { return nom; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getW() { return w; }
    public int getH() { return h; }
}
