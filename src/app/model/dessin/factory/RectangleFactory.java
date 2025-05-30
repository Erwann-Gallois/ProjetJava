package app.model.dessin.factory;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * Classe RectangleFactory
 * Représente une fabrique de rectangles.
 * Elle implémente l'interface FormeFactory.
 */
public class RectangleFactory implements FormeFactory {
    
    /**
     * Permet de créer une forme de type Rectangle ou Carré.
     *
     * @param x1 Coordonnée x du premier point.
     * @param y1 Coordonnée y du premier point.
     * @param x2 Coordonnée x du deuxième point.
     * @param y2 Coordonnée y du deuxième point.
     * @return Un objet de type Shape représentant le rectangle ou le carré.
     */
    @Override
    public Shape createForme(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        return new Rectangle(x, y, width, height);
    }
}