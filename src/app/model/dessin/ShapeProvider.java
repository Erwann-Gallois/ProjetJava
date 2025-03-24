package app.model.dessin;

import java.awt.Shape;
import java.util.Map;

public interface ShapeProvider {
    Map<String, Shape> getShapes();
}
