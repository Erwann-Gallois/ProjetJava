package app.model.dessin;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

public class ShapeDrawer extends JFrame {
    private ShapeButtonPanel shapeButtonPanel;
    private DrawingPanel drawingPanel;
    private ArrayList<Shape> tab;

    public ShapeDrawer() {
        tab = new ArrayList<>();
        setTitle("Shape Drawer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        drawingPanel = new DrawingPanel(); // Pass null initially
        shapeButtonPanel = new ShapeButtonPanel(drawingPanel);
        drawingPanel.setShapeButtonPanel(shapeButtonPanel);


        // Add components to frame
        add(shapeButtonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        // Set the shape provider after initialization
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public ShapeButtonPanel getShapeButtonPanel() {
        return shapeButtonPanel;
    }

    public ArrayList<Shape> GetRandom(){
        return tab;
    }

    public void displayRandomShapes() {
        // Générer des formes aléatoires
        Random rand = new Random();
        int nb = rand.nextInt(5,10);
        for (int i = 0; i < nb; i++) {  // Par exemple, générer 5 formes
            if (rand.nextBoolean()) {
                // Créer un rectangle
                Rectangle forme = new Rectangle(rand.nextInt(800), rand.nextInt(600), rand.nextInt(800/4),rand.nextInt(600/3));
                drawingPanel.getShapes().put("rectangle" + i, forme);
                tab.add(forme);
            } else {
                // Créer un cercle
                Ellipse2D forme = new Ellipse2D.Double(rand.nextInt(800), rand.nextInt(600), rand.nextInt(800/4),rand.nextInt(600/3));
                drawingPanel.getShapes().put("circle" + i, forme);
                tab.add(forme);
            }
        }
        drawingPanel.repaint();
    }

    public void freeze(int i) {
        drawingPanel.setEnabled(false); // Désactiver le panneau de dessin
        shapeButtonPanel.setEnabled(false);
        new Timer(i * 1000, e -> {
            drawingPanel.setEnabled(true); // Réactiver le panneau après i secondes
            shapeButtonPanel.setEnabled(true);
        }).start();
    }
    
}
