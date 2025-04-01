package app2.model.formes;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class FormeCercle extends Forme {
    public FormeCercle(double midX, double midY, double radius) {
        super(midX, midY, radius * 2, radius * 2);  // La largeur et la hauteur sont égales
    }

    @Override
    public double area() {
        double radius = getWidth() / 2;
        return Math.PI * radius * radius;
    }

    @Override
    public Shape transform() {
        double x = getMidX() - getWidth() / 2;
        double y = getMidY() - getHeight() / 2;
        return new Ellipse2D.Double(x, y, getWidth(), getHeight());
    }
}
