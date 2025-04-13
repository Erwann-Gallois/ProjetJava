package app.model.dessin.factory;

import java.awt.Shape;
import java.awt.Polygon;

public class TriangleFactory implements FormeFactory, app3.factory.FormeFactory {

    /**
     * Permet de créer une forme de type Triangle.
     *
     * @param x1 Coordonnée x du premier point.
     * @param y1 Coordonnée y du premier point.
     * @param x2 Coordonnée x du deuxième point.
     * @param y2 Coordonnée y du deuxième point.
     * @return Un objet de type Shape représentant le Triangle.
     */
    @Override
    public Shape createForme(int x1, int y1, int x2, int y2) {
        int xGauche = Math.min(x1, x2);
        int xDroite = Math.max(x1, x2);
        int yHaut = Math.min(y1, y2);
        int yBas = Math.max(y1, y2);

        int xMilieu = (xGauche + xDroite) / 2;

        int[] xPoints = { xMilieu, xGauche, xDroite };
        int[] yPoints = { yHaut, yBas, yBas };

        return new Polygon(xPoints, yPoints, 3);
    }
}
