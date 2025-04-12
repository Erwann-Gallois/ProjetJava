package app.model.dessin;

import java.awt.Polygon;
import java.awt.Shape;

public class ShapeEvaluatorTriangle implements ShapeEvaluator {

    @Override
    public double compareShapes(Shape original, Shape drawn) {
        if (!(original instanceof Polygon) || !(drawn instanceof Polygon)) {
            return 0;
        }

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