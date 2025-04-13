package app.model.dessin.factory;

import java.awt.Shape;

/**
 * Interface FormeFactory
 * Représente une fabrique de formes.
 * Elle permet de créer des formes géométriques.
 */
public interface FormeFactory{

    /**
     * Permet de créer une forme.
     *
     * @param x1 Coordonnée x du premier point.
     * @param y1 Coordonnée y du premier point.
     * @param x2 Coordonnée x du deuxième point.
     * @param y2 Coordonnée y du deuxième point.
     * @return Un objet de type Shape représentant le rectangle ou le carré.
     */
    Shape createForme(int x1, int y1, int x2, int y2);
}
