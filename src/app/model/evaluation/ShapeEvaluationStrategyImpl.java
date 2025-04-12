package app.model.evaluation;

import java.awt.Shape;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.model.dessin.ShapeEvaluator;
import app.model.dessin.ShapeEvaluatorCircle;
import app.model.dessin.ShapeEvaluatorSquare;
import app.model.dessin.ShapeEvaluatorTriangle;
import app.model.dessin.factory.AbstractFormeFactory;
import app.model.dessin.factory.CircleFactory;
import app.model.dessin.factory.FormeFactory;
import app.model.dessin.factory.RectangleFactory;
import app.model.dessin.factory.TriangleFactory;

public class ShapeEvaluationStrategyImpl {

    private final ShapeEvaluator squareEvaluator = new ShapeEvaluatorSquare();
    private final ShapeEvaluator circleEvaluator = new ShapeEvaluatorCircle();
    private final ShapeEvaluator triangleEvaluator = new ShapeEvaluatorTriangle();

    // Méthode d'évaluation qui compare les formes dessinées avec les formes de référence
    public double evaluate(ArrayList<Shape> userShapes, ArrayList<Shape> referenceForms) {
        double total = 0.0;

        // On parcourt les formes dessinées et de référence pour évaluer leur similarité
        for (Shape reference : userShapes) {

            // Initialisation du score de la comparaison
            double score = 0.0;

            for (Shape drawn: referenceForms){
                double tempo = 0.0;
                // Évaluation selon le type de forme
                if (reference instanceof RectangleFactory) {
                    tempo = squareEvaluator.compareShapes(reference, drawn);  // Comparaison carrée
                } else if (reference instanceof CircleFactory) {
                    tempo = circleEvaluator.compareShapes(reference, drawn);  // Comparaison circulaire
                } else if (reference instanceof TriangleFactory) {
                    tempo = triangleEvaluator.compareShapes(reference, drawn); // Comparaison triangulaire
                }
                if(tempo > score){
                    score = tempo;
                }
            }
            // Accumuler le score en s'assurant qu'il reste entre 0 et 100
            total += Math.max(0, Math.min(score, 100)); 
        }

        // Retourner la moyenne des scores
        return total / referenceForms.size();
    }
}
