package app.model.formes;

public class FormeTriangle extends AbstractForme{

    /**
     * Constructeur de la classe FormeTriangle
     *
     * @param nom Le nom de la forme
     * @param x   La coordonnée x du coin supérieur gauche
     * @param y   La coordonnée y du coin supérieur gauche
     * @param w   La largeur de la forme
     * @param h   La hauteur de la forme
     */
    public FormeTriangle(String nom, int x, int y, int w, int h) {
        super(nom, x, y, w, h);
    }
}
