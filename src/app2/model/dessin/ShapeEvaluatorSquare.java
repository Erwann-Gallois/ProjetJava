package app2.model.dessin;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class ShapeEvaluatorSquare implements ShapeEvaluator {
    @Override
    public double compareShapes(Shape original, Shape drawn) {
        if (!(original instanceof Rectangle2D) || !(drawn instanceof Rectangle2D)) {
            return 0;  // Si les deux formes ne sont pas des carrés, on retourne un score nul
        }

        Rectangle2D originalBounds = (Rectangle2D) original;
        Rectangle2D drawnBounds = (Rectangle2D) drawn;

        // Calcul de la différence entre les deux carrés (position, taille)
        double score = 100 - Math.abs(originalBounds.getX() - drawnBounds.getX())
                            - Math.abs(originalBounds.getY() - drawnBounds.getY())
                            - Math.abs(originalBounds.getWidth() - drawnBounds.getWidth())
                            - Math.abs(originalBounds.getHeight() - drawnBounds.getHeight());

        return score < 0 ? 0 : score; // Limite le score à 0 minimum
    }
}
