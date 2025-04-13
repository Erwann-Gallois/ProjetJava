package app.model.dessin.factory;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CircleFactory implements FormeFactory, app3.factory.FormeFactory {

    /**
     * Permet de créer une forme de type cercle.
     *
     * @param x1 Coordonnée x du premier point.
     * @param y1 Coordonnée y du premier point.
     * @param x2 Coordonnée x du deuxième point.
     * @param y2 Coordonnée y du deuxième point.
     * @return Un objet de type Shape représentant le cercle.
     */
    @Override
    public Shape createForme(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int diameter = Math.max(width, height);
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
}

