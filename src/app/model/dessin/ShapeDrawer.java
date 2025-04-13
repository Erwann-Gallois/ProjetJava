package app.model.dessin;

import javax.swing.*;

import app.model.evaluation.ShapeEvaluationStrategyImpl;
import app.model.formes.AbstractForme;
import app.model.formes.FormeCarre;
import app.model.formes.FormeCercle;
import app.model.formes.FormeTriangle;
import app.model.niveau.Niveau;
import app.view.GameView;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class ShapeDrawer extends JFrame {
    private GameView gameView;
    private ArrayList<Shape> originalShapes;

    /**
     * Constructeur de la classe ShapeDrawer.
     * Initialise le panneau de dessin et la liste des formes originales.
     *
     * @param gameView La vue du jeu.
     */
    public ShapeDrawer(GameView gameView) {
        this.gameView = gameView;
        originalShapes = new ArrayList<>();
    }

    /**
     * Affiche un nombre aléatoire de cercles ou carrés sur le panneau de dessin.
     * Les formes générées sont stockées pour l'évaluation ultérieure.
     */
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

    /**
     * Affiche les formes d'un niveau spécifique sur le panneau de dessin.
     * Les formes sont créées à partir des données du niveau et stockées pour l'évaluation ultérieure.
     *
     * @param niveau le niveau dont les formes doivent être affichées
     */
    public void displayLevelShapes(Niveau niveau) {
        gameView.getDrawingPanel().getShapes().clear();
        originalShapes.clear();
        for (AbstractForme f : niveau.getFormes()) {
            Shape shape = null;

            if (f instanceof FormeCercle) {
                shape = new Ellipse2D.Double(f.getX(), f.getY(), f.getW(), f.getH());
            } else if (f instanceof FormeCarre) {
                shape = new Rectangle2D.Double(f.getX(), f.getY(), f.getW(), f.getH());
            } else if (f instanceof FormeTriangle) {
                int[] xPoints = {
                    f.getX() + f.getW() / 2,
                    f.getX(),
                    f.getX() + f.getW()
                };
                int[] yPoints = {
                    f.getY(),
                    f.getY() + f.getH(),
                    f.getY() + f.getH()
                };
                shape = new Polygon(xPoints, yPoints, 3);
            }

            if (shape != null) {
                gameView.getDrawingPanel().addShape(f.getNom(), shape);
                originalShapes.add(shape);
            }
        }
        gameView.getDrawingPanel().repaint();
    }

    /**
     * Fige le dessin pendant un certain nombre de secondes.
     * @param seconds
     */
    public void freezeDrawing(int seconds) {
        gameView.getDrawingPanel().setEnabled(false);
        new Timer(seconds * 1000, e -> {
            gameView.getDrawingPanel().setEnabled(true);
            System.out.println("finito");
        }).start();
    }

    /**
     * Évalue la précision du dessin de l'utilisateur par rapport à la forme originale.
     * @param original La forme originale à comparer.
     * @param drawn La forme dessinée par l'utilisateur.
     * @return Un score entre 0 et 100 représentant la précision du dessin.
     */
    public double evaluateDrawing(ArrayList<Shape> originals, ArrayList<Shape> drawns) {
        return new ShapeEvaluationStrategyImpl().evaluate(drawns, originals);
    }

    /**
     * Affiche le score de l'utilisateur dans une boîte de dialogue.
     * @param score Le score à afficher.
     */
    public void showScore(double score) {
        gameView.updateScore(score);
        JOptionPane.showMessageDialog(gameView, "Votre score : " + (int) score + " / 100", "Résultat", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Récupère la liste des formes originales.
     * @return La liste des formes originales.
     */
    public ArrayList<Shape> getOriginalShapes() {
        return originalShapes;
    }
}
