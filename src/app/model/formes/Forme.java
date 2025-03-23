package app.model.formes;

import java.awt.Color;

import util.ModeleEcoutable;

public interface Forme extends ModeleEcoutable {

    /**
     * Accesseur au nom de l'état courrant de la forme
     * @return nom de la forme
     */
    public String getActualName();

    /**
     * Accesseur du x du milieu de la forme
     * @return entier axe x milieu
     */
    public Integer getMidX();

    /**
     * Accesseur du y du milieu de la forme
     * @return entier axe y milieu
     */
    public Integer getMidY();

    /**
     * Setter du milieu x
     * @param x milieu x
     */
    public void setMidX(Integer x);

    /**
     * Setter du milieu y
     * @param y milieu y
     */
    public void setMidY(Integer y);

    /**
     * Accesseur à la couleur de la forme
     * @return couleur de la forme
     */
    public Color getColor();

}
