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

    public GameView() {
        setTitle("Dessin de formes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        shapeDrawer = new ShapeDrawer(this);
        shapeButtonPanel = new ShapeButtonPanel(shapeDrawer);
        drawingPanel = new DrawingPanel(shapeButtonPanel);
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
    }

    public void updateScore(double score) {
        scoreLabel.setText("Score : " + score);
    }

    public void enableReplayButton() {
        replayButton.setEnabled(true);
    }

    public void restartGame() {
        // Logic to restart the game
        shapeButtonPanel = new ShapeButtonPanel(shapeDrawer);
        drawingPanel.repaint();
        scoreLabel.setText("Score : 0");
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setRandomShapesMode(boolean mode) {
        this.randomShapesMode = mode;
        if (randomShapesMode) {
            // Désactiver le bouton de dessin de formes aléatoires
            shapeButtonPanel.setRandomShapeButtonEnabled(false);
            shapeDrawer.displayRandomShapes();
        } else {
            shapeButtonPanel.setRandomShapeButtonEnabled(true);
        }
    }
}
