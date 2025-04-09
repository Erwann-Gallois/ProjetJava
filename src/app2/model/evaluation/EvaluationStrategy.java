package app2.model.evaluation;

import java.util.Map;
import java.awt.Shape;

public interface EvaluationStrategy {
    double evaluate(Map<String, Shape> playerShapes, Map<String, Shape> referenceShapes);
}
