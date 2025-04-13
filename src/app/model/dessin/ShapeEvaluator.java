package app.model.dessin;

import java.awt.Shape;

public interface ShapeEvaluator {
    /**
     * Compare les formes dessinées avec la forme originale.
     *
     * @param original La forme originale.
     * @param drawn    La forme dessinée.
     * @return Un score de similarité entre 0 et 100.
     */
    double compareShapes(Shape original, Shape drawn);
}
