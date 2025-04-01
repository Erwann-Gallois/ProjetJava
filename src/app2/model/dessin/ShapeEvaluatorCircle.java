package app2.model.dessin;

import java.awt.geom.Ellipse2D;
import app2.model.formes.Forme;

public class ShapeEvaluatorCircle implements ShapeEvaluator {
    public double compareShapes(Forme original, Forme drawn) {
        Ellipse2D originalBounds = new Ellipse2D.Double(original.getMidX() - original.getWidth() / 2, 
                                                         original.getMidY() - original.getHeight() / 2, 
                                                         original.getHeight(), original.getWidth());
        Ellipse2D drawnBounds = new Ellipse2D.Double(drawn.getMidX() - drawn.getWidth() / 2, 
                                                    drawn.getMidY() - drawn.getHeight() / 2, 
                                                    drawn.getHeight(), drawn.getWidth());

        return 100 - Math.abs(originalBounds.getX() - drawnBounds.getX()) 
                    - Math.abs(originalBounds.getY() - drawnBounds.getY()) 
                    - Math.abs(originalBounds.getWidth() - drawnBounds.getWidth()) 
                    - Math.abs(originalBounds.getHeight() - drawnBounds.getHeight());
    }
}
