package app.view;

import app.model.dessin.ShapeDrawer;
import app.model.niveau.Niveau;
import app.model.niveau.NiveauxFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

public class GameView extends JFrame {
    private DrawingPanel drawingPanel;
    private ShapeButtonPanel shapeButtonPanel;
    private JButton replayButton;
    private JLabel scoreLabel;
    private ShapeDrawer shapeDrawer;
    private boolean randomShapesMode = false;

    /**
     * Constructeur de la classe GameView.
     * @param randomShapesMode Indique si le mode aléatoire est activé
     */
    public GameView(boolean randomShapesMode) {
        setTitle("Dessin de formes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        shapeDrawer = new ShapeDrawer(this);
        shapeButtonPanel = new ShapeButtonPanel(shapeDrawer);
        drawingPanel = new DrawingPanel(shapeButtonPanel);
        shapeButtonPanel.setDrawingPanel(drawingPanel);
        replayButton = new JButton("Rejouer");
        scoreLabel = new JLabel("Score : 0");

        // replayButton.setEnabled(false);
        replayButton.addActionListener(e -> restartGame());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(shapeButtonPanel, BorderLayout.NORTH);
        topPanel.add(replayButton, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(scoreLabel, BorderLayout.SOUTH);
        setRandomShapesMode(randomShapesMode);
    }

    /**
     * Met à jour le score affiché dans la vue.
     * @param score Le nouveau score à afficher
     */
    public void updateScore(double score) {
        scoreLabel.setText("Score : " + score);
    }

    /**
     * Désactive le bouton de rejouer.
     */
    public void enableReplayButton() {
        replayButton.setEnabled(true);
    }

    /**
     * Démarre la phase de jeu aléatoire.
     */
    private void startRandomPhase() {
        Timer generateShapes = new Timer(500, e -> {
            shapeDrawer.displayRandomShapes(); // Dessine formes IA
    
            // Timer unique pour nettoyer après 10 secondes
            Timer cleanup = new Timer(10_000, e2 -> {
                drawingPanel.clearShapes();             // Nettoyage
                drawingPanel.setInteractive(true);      // Activer dessin
                shapeButtonPanel.setInteractive(true);  // Activer boutons
            });
            cleanup.setRepeats(false);
            cleanup.start();
        });
        generateShapes.setRepeats(false);
        generateShapes.start();
    }

    /**
     * Démarre la phase de jeu pour un niveau.
     */
    private void startLevelPhase() {
        try {
            Map<String, Niveau> niveaux = NiveauxFactory.charger("dist/niveaux.ser");
            ArrayList<Niveau> niveauList = new ArrayList<>(niveaux.values());
            for (Niveau niv : niveauList)
            {
                drawingPanel.clearShapes();             // Nettoyage
                drawingPanel.setInteractive(false);     // Désactiver dessin
                Timer generateShapes = new Timer(500, e -> {
                    shapeDrawer.displayLevelShapes(niv);; // Dessine formes du niveau
            
                    // Timer unique pour nettoyer après 10 secondes
                    Timer cleanup = new Timer(10_000, e2 -> {
                        drawingPanel.clearShapes();             // Nettoyage
                        drawingPanel.setInteractive(true);      // Activer dessin
                        shapeButtonPanel.setInteractive(true);  // Activer boutons
                    });
                    cleanup.setRepeats(false);
                    cleanup.start();
                });
                generateShapes.setRepeats(false);
                generateShapes.start();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors du chargement ou de l'exécution des niveaux.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Redémarre le jeu en nettoyant l'interface et en réinitialisant le score.
     */
    public void restartGame() {
        drawingPanel.clearShapes();                  // Nettoie l’interface
        drawingPanel.setInteractive(false);          // Désactive dessin
        shapeButtonPanel.setInteractive(false);      // Désactive boutons
        scoreLabel.setText("Score : 0");
        // replayButton.setEnabled(false);
    
        if (randomShapesMode) {
            startRandomPhase(); // Lance la logique de "joueur vs aléatoire"
        } else {
            startLevelPhase();  // Lance la logique de "joueur vs niveau"
            drawingPanel.setInteractive(true);
            shapeButtonPanel.setInteractive(true);
        }
    }

    /**
     * Accesseur pour le panneau de dessin.
     * @return Le panneau de dessin
     */
    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    /**
     * Setter pour le mode aléatoire.
     * @param mode
     */
    public void setRandomShapesMode(boolean mode) {
        this.randomShapesMode = mode;
    }
}
