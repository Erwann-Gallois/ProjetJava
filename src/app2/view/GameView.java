package app2.view;

import javax.swing.*;

import app2.model.dessin.ShapeDrawer;

import java.awt.*;

public class GameView extends JFrame {
    private DrawingPanel drawingPanel;
    private ShapeButtonPanel shapeButtonPanel;
    private JButton replayButton;
    private JLabel scoreLabel;
    private ShapeDrawer shapeDrawer;
    private boolean randomShapesMode = false;

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

        replayButton.setEnabled(false);
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

    public void updateScore(double score) {
        scoreLabel.setText("Score : " + score);
    }

    public void enableReplayButton() {
        replayButton.setEnabled(true);
    }

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

    public void restartGame() {
        drawingPanel.clearShapes();                  // Nettoie l’interface
        drawingPanel.setInteractive(false);          // Désactive dessin
        shapeButtonPanel.setInteractive(false);      // Désactive boutons
        scoreLabel.setText("Score : 0");
        replayButton.setEnabled(false);
    
        if (randomShapesMode) {
            startRandomPhase(); // Lance la logique de "joueur vs aléatoire"
        } else {
            // Mode classique : activer dessin direct
            drawingPanel.setInteractive(true);
            shapeButtonPanel.setInteractive(true);
        }
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setRandomShapesMode(boolean mode) {
        this.randomShapesMode = mode;
    }
}
