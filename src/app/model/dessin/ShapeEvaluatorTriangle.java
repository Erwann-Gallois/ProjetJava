package app.model.dessin;

import java.awt.Polygon;
import java.awt.Shape;

/**
 * Classe ShapeEvaluatorTriangle
 * Représente un évaluateur de formes pour les triangles.
 * Elle implémente l'interface ShapeEvaluator.
 */
public class ShapeEvaluatorTriangle implements ShapeEvaluator {

    /**
     * Compare les formes dessinées avec la forme originale.
     *
     * @param original La forme originale.
     * @param drawn    La forme dessinée.
     * @return Un score de similarité entre 0 et 100.
     */
    @Override
    public double compareShapes(Shape original, Shape drawn) {

        Polygon polyOriginal = (Polygon) original;
        Polygon polyDrawn = (Polygon) drawn;

        // Vérifie qu’il y a bien 3 points (triangle)
        if (polyOriginal.npoints != 3 || polyDrawn.npoints != 3) {
            return 0;
        }

        double score = 100;
        for (int i = 0; i < 3; i++) {
            score -= Math.abs(polyOriginal.xpoints[i] - polyDrawn.xpoints[i]);
            score -= Math.abs(polyOriginal.ypoints[i] - polyDrawn.ypoints[i]);
        }

        return Math.max(score, 0);
    }
}