package app.model.dessin.factory;

import java.awt.Shape;
import java.awt.Polygon;

public class TriangleFactory implements FormeFactory, app3.factory.FormeFactory {

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
