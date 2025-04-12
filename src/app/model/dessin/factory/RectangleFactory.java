package app.model.dessin.factory;
import java.awt.Rectangle;
import java.awt.Shape;

public class RectangleFactory implements FormeFactory, app3.factory.FormeFactory {
    @Override
    public Shape createForme(int x1, int y1, int x2, int y2) {
        int x = Math.min(x1, x2);
        int y = Math.min(y1, y2);
        int width = Math.abs(x1 - x2);
        int height = Math.abs(y1 - y2);
        // this.setX(x);
        // this.setY(y);
        // this.setWidth(width);
        // this.setHeight(height);
        return new Rectangle(x, y, width, height);
    }
}