package app.model.evaluation;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.model.dessin.factory.CircleFactory;
import app.model.dessin.factory.RectangleFactory;
import app.model.dessin.factory.TriangleFactory;

/**
 * Classe ShapeEvaluationStrategyImpl
 * Représente une stratégie d'évaluation des formes.
 * Elle évalue la similarité entre les formes dessinées par l'utilisateur et les formes de référence.
 */
public class ShapeEvaluationStrategyImpl {

    private final ShapeEvaluator squareEvaluator = new ShapeEvaluatorSquare();
    private final ShapeEvaluator circleEvaluator = new ShapeEvaluatorCircle();
    private final ShapeEvaluator triangleEvaluator = new ShapeEvaluatorTriangle();

    /**
     * Évalue la similarité entre les formes dessinées par l'utilisateur et les formes de référence.
     *
     * @param userShapes      Les formes dessinées par l'utilisateur.
     * @param referenceForms  Les formes de référence.
     * @return Un score de similarité entre 0 et 100.
     */
    public double evaluate(HashMap<String, Shape> referenceForms, ArrayList<Shape> userShapes) {
        double total = 0.0;

        // On parcourt les formes dessinées et de référence pour évaluer leur similarité
        for (Shape reference : userShapes) {

            // Initialisation du score de la comparaison
            double score = 0.0;

            System.out.println(score);

            for (Map.Entry<String, Shape> entry : referenceForms.entrySet()) {
                String key = entry.getKey();
                Shape drawn = entry.getValue();
                double tempo = 0.0;
                // Évaluation selon le type de forme
                if (key.toLowerCase().contains("rectangle") && reference instanceof Rectangle2D) {
                    score = squareEvaluator.compareShapes(reference, drawn);
                } else if (key.toLowerCase().contains("circle") && reference instanceof Ellipse2D) {
                    score = circleEvaluator.compareShapes(reference, drawn);
                } else if (key.toLowerCase().contains("triangle") && reference instanceof Polygon) {
                    score = triangleEvaluator.compareShapes(reference, drawn);
                }
                if(tempo > score){
                    score = tempo;
                }
            }
            // Accumuler le score en s'assurant qu'il reste entre 0 et 100
            System.out.println(score);
            total += score; 
        }

        // Retourner la moyenne des scores
        return total / referenceForms.size();
    }
}
 