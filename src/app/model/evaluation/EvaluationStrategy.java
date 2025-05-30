package app.model.evaluation;

import java.util.Map;
import java.awt.Shape;

/**
 * Interface EvaluationStrategy
 * Représente une stratégie d'évaluation.
 * Elle permet d'évaluer la similarité entre les formes dessinées par le joueur et les formes de référence.
 */
public interface EvaluationStrategy {

    /**
     * Évalue la similarité entre les formes du joueur et les formes de référence.
     *
     * @param playerShapes    Les formes dessinées par le joueur.
     * @param referenceShapes Les formes de référence.
     * @return Un score de similarité entre 0 et 100.
     */
    double evaluate(Map<String, Shape> playerShapes, Map<String, Shape> referenceShapes);
}
