package app2.model.dessin;

import java.awt.Shape;

public interface ShapeEvaluator {
    double compareShapes(Shape original, Shape drawn);
}
