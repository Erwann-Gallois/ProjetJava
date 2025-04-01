package app2.model.dessin;

import app2.model.formes.Forme;
import app2.model.formes.FormeCarre;
import app2.model.formes.FormeCercle;
import app2.view.GameView;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class ShapeDrawer extends JFrame {
    private GameView gameView;
    private ArrayList<Forme> originalShapes;
    
    public ShapeDrawer(GameView gameView) {
        this.gameView = gameView;
        originalShapes = new ArrayList<>();
    }

    public void displayRandomShapes() {
        Random rand = new Random();
        int nb = rand.nextInt(1, 11);
        for (int i = 0; i < nb; i++) {  // Par exemple, générer 5 formes
            if (rand.nextBoolean()) {
                // Créer un carré
                FormeCarre forme = new FormeCarre(rand.nextInt(50,500), rand.nextInt(50,500),rand.nextInt(50,250));
                originalShapes.add(forme);
                gameView.getDrawingPanel().addShape("square" + i, forme.transform());
            } else {
                // Créer un cercle
                FormeCercle forme = new FormeCercle(rand.nextInt(500), rand.nextInt(500), 50);
                originalShapes.add(forme);
                gameView.getDrawingPanel().addShape("circle" + i, forme.transform());
            }
        }
        gameView.getDrawingPanel().repaint();
    }

    // Fonction pour geler l'interface et empêcher les dessins pendant une durée donnée
    public void freezeDrawing(int seconds) {
        // Geler l'interface pour une durée donnée en secondes
        gameView.getDrawingPanel().setEnabled(false);
        new Timer(seconds * 1000, e -> {
            gameView.getDrawingPanel().setEnabled(true);  // Réactive la zone de dessin après la période de gel
            System.out.println("finito");
        }).start();
    }

    // Comparaison entre la forme originale et la forme dessinée par le joueur
    public double evaluateDrawing(Forme original, Forme drawn) {
        // Logique d'évaluation à ajouter ici (calcul de la similarité entre les formes)
        double score = 0.0;
        if (original.equals(drawn)) {
            score = 100.0;  // Forme parfaite
        } else {
            score = 50.0;  // Exemple de score (peut être amélioré)
        }
        return score;
    }

    // Fonction pour afficher le score dans la vue après la comparaison
    public void showScore(double score) {
        gameView.updateScore(score);
    }
}

