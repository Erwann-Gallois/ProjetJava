package app.model.formes;

public class FormeCarre extends AbstractForme{
    /**
     * Constructeur de la classe FormeCarre
     *
     * @param nom Le nom de la forme
     * @param x   La coordonnée x du coin supérieur gauche
     * @param y   La coordonnée y du coin supérieur gauche
     * @param h   La hauteur de la forme (carré)
     * @param w   La largeur de la forme (carré)
     */
    public FormeCarre(String nom, int x, int y, int h, int w){
        super(nom, x, y, w, h);
    }
}
