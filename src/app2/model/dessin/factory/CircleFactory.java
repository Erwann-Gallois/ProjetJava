package app2.model.dessin.factory;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class CircleFactory implements FormeFactory, app3.factory.FormeFactory {

    @Override
    public Shape createForme(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        int diameter = Math.max(width, height);
        // this.setX(x);
        // this.setY(y);
        // this.setWidth(width);
        // this.setHeight(height);
        return new Ellipse2D.Double(x, y, diameter, diameter);
    }
}

