package app.model.formes;

public class FormeCercle extends AbstractForme {

    /**
     * Constructeur de la classe FormeCercle
     *
     * @param nom Le nom de la forme
     * @param x   La coordonnée x du centre du cercle
     * @param y   La coordonnée y du centre du cercle
     * @param w   La largeur du cercle (diamètre)
     * @param h   La hauteur du cercle (diamètre)
     */
    public FormeCercle(String nom, int x, int y, int w, int h) {
        super(nom, x, y, w, h);
    }
}

