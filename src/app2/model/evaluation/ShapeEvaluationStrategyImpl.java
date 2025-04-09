package app2.model.evaluation;

import app2.model.dessin.ShapeEvaluator;
import app2.model.dessin.ShapeEvaluatorCircle;
import app2.model.dessin.ShapeEvaluatorSquare;
import app2.model.dessin.factory.AbstractForme;
import app2.model.dessin.factory.CircleFactory;
import app2.model.dessin.factory.FormeFactory;
import app2.model.dessin.factory.RectangleFactory;

import java.awt.Shape;
import java.util.List;
import java.util.Map;

public class ShapeEvaluationStrategyImpl {

    private final ShapeEvaluator squareEvaluator = new ShapeEvaluatorSquare();
    private final ShapeEvaluator circleEvaluator = new ShapeEvaluatorCircle();

    // Méthode d'évaluation qui compare les formes dessinées avec les formes de référence
    public double evaluate(Map<String, Shape> userShapes, List<AbstractForme> referenceForms) {
        // Si le nombre de formes dessinées ne correspond pas au nombre de formes de référence, retour 0
        if (userShapes.size() != referenceForms.size()) {
            return 0.0;
        }

        double total = 0.0;
        int i = 0;

        // On parcourt les formes dessinées et de référence pour évaluer leur similarité
        for (Map.Entry<String, Shape> entry : userShapes.entrySet()) {
            AbstractForme reference = referenceForms.get(i);  // Forme de référence
            Shape drawn = entry.getValue();  // Forme dessinée par l'utilisateur
            i++;

            // Initialisation du score de la comparaison
            double score = 0.0;

            // Évaluation selon le type de forme
            if (reference instanceof RectangleFactory) {
                score = squareEvaluator.compareShapes(reference.createForme(0, 0, 0, 0), drawn);  // Comparaison carrée
            } else if (reference instanceof CircleFactory) {
                score = circleEvaluator.compareShapes(reference.createForme(0, 0, 0, 0), drawn);  // Comparaison circulaire
            }

            // Accumuler le score en s'assurant qu'il reste entre 0 et 100
            total += Math.max(0, Math.min(score, 100)); 
        }

        // Retourner la moyenne des scores
        return total / referenceForms.size();
    }
}
