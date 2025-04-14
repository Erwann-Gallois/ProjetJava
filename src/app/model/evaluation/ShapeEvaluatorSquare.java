package app.model.evaluation;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Classe ShapeEvaluatorSquare
 * Représente un évaluateur de formes pour les carrés.
 * Elle implémente l'interface ShapeEvaluator.
 */
public class ShapeEvaluatorSquare implements ShapeEvaluator {

    /**
     * Compare deux formes de type carré.
     *
     * @param original La forme originale.
     * @param drawn    La forme dessinée.
     * @return Un score de similarité entre 0 et 100.
     */
    @Override
    public double compareShapes(Shape original, Shape drawn) {

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
