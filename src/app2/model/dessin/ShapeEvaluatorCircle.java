package app2.model.dessin;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class ShapeEvaluatorCircle implements ShapeEvaluator {
    @Override
    public double compareShapes(Shape original, Shape drawn) {
        if (!(original instanceof Ellipse2D) || !(drawn instanceof Ellipse2D)) {
            return 0;  // Si les deux formes ne sont pas des cercles, on retourne un score nul
        }

        Ellipse2D originalBounds = (Ellipse2D) original;
        Ellipse2D drawnBounds = (Ellipse2D) drawn;

        // Calcul de la différence entre les deux cercles (position, taille)
        double score = 100 - Math.abs(originalBounds.getX() - drawnBounds.getX())
                            - Math.abs(originalBounds.getY() - drawnBounds.getY())
                            - Math.abs(originalBounds.getWidth() - drawnBounds.getWidth())
                            - Math.abs(originalBounds.getHeight() - drawnBounds.getHeight());

        return score < 0 ? 0 : score; // Limite le score à 0 minimum
    }
}
