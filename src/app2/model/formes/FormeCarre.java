package app2.model.formes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class FormeCarre extends Forme {
    public FormeCarre(double midX, double midY, double sideLength) {
        super(midX, midY, sideLength, sideLength);  // La largeur et la hauteur sont égales
    }

    @Override
    public double area() {
        return getWidth() * getWidth();
    }

    @Override
    public Shape transform() {
        double x = getMidX() - getWidth() / 2;
        double y = getMidY() - getHeight() / 2;
        return new Rectangle((int) x, (int) y, (int) getWidth(), (int) getHeight());
    }
}
