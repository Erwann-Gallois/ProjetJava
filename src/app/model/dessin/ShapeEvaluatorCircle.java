package app.model.dessin;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * Classe ShapeEvaluatorCircle
 * Représente un évaluateur de formes pour les cercles.
 * Elle implémente l'interface ShapeEvaluator.
 */
public class ShapeEvaluatorCircle implements ShapeEvaluator {

    /**
     * Compare deux formes de type cercle.
     *
     * @param original La forme originale.
     * @param drawn    La forme dessinée.
     * @return Un score de similarité entre 0 et 100.
     */
    @Override
    public double compareShapes(Shape original, Shape drawn) {
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
