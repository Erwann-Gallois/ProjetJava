package app2.model.dessin;

import java.awt.geom.Rectangle2D;
import app2.model.formes.Forme;

public class ShapeEvaluatorSquare implements ShapeEvaluator {
    public double compareShapes(Forme original, Forme drawn) {
        Rectangle2D originalBounds = new Rectangle2D.Double(original.getMidX() - original.getWidth() / 2, 
                                                             original.getMidY() - original.getHeight() / 2, 
                                                             original.getHeight(), original.getWidth());
        Rectangle2D drawnBounds = new Rectangle2D.Double(drawn.getMidX() - drawn.getWidth() / 2, 
                                                        drawn.getMidY() - drawn.getHeight() / 2, 
                                                        drawn.getHeight(), drawn.getWidth());

        return 100 - Math.abs(originalBounds.getX() - drawnBounds.getX()) 
                    - Math.abs(originalBounds.getY() - drawnBounds.getY()) 
                    - Math.abs(originalBounds.getWidth() - drawnBounds.getWidth()) 
                    - Math.abs(originalBounds.getHeight() - drawnBounds.getHeight());
    }
}
