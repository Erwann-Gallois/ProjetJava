package app2.model.dessin;

import app2.view.GameView;

import javax.swing.*;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class ShapeDrawer extends JFrame {
    private GameView gameView;
    private ArrayList<Shape> originalShapes;

    public ShapeDrawer(GameView gameView) {
        this.gameView = gameView;
        originalShapes = new ArrayList<>();
    }

    public void displayRandomShapes() {
        gameView.getDrawingPanel().getShapes().clear();
        originalShapes.clear();
        Random rand = new Random();
        int nb = rand.nextInt(1, 11);

        for (int i = 0; i < nb; i++) {
            Shape shape;
            if (rand.nextBoolean()) {
                // Carré
                int size = rand.nextInt(50, 250);
                int x = rand.nextInt(50, 500);
                int y = rand.nextInt(50, 500);
                shape = new java.awt.geom.Rectangle2D.Double(x, y, size, size);
                gameView.getDrawingPanel().addShape("square" + i, shape);
            } else {
                // Cercle
                int radius = rand.nextInt(25, 125);
                int x = rand.nextInt(50, 500);
                int y = rand.nextInt(50, 500);
                shape = new java.awt.geom.Ellipse2D.Double(x, y, radius * 2, radius * 2);
                gameView.getDrawingPanel().addShape("circle" + i, shape);
            }

            originalShapes.add(shape);
        }

        gameView.getDrawingPanel().repaint();
    }

    public void freezeDrawing(int seconds) {
        gameView.getDrawingPanel().setEnabled(false);
        new Timer(seconds * 1000, e -> {
            gameView.getDrawingPanel().setEnabled(true);
            System.out.println("finito");
        }).start();
    }

    public double evaluateDrawing(Shape original, Shape drawn) {
        ShapeEvaluator evaluator;
        
        if (original instanceof Rectangle2D && drawn instanceof Rectangle2D) {
            evaluator = new ShapeEvaluatorSquare();  // Création de l'évaluateur pour les carrés
        } else if (original instanceof Ellipse2D && drawn instanceof Ellipse2D) {
            evaluator = new ShapeEvaluatorCircle();  // Création de l'évaluateur pour les cercles
        } else {
            return 0.0;  // Retourner 0 si les formes sont de types différents
        }

        return evaluator.compareShapes(original, drawn);  // Appel à la méthode d'évaluation
    }

    public void showScore(double score) {
        gameView.updateScore(score);
        JOptionPane.showMessageDialog(gameView, "Votre score : " + (int) score + " / 100", "Résultat", JOptionPane.INFORMATION_MESSAGE);
    }

    public ArrayList<Shape> getOriginalShapes() {
        return originalShapes;
    }
}
