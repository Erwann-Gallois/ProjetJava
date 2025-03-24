package app.model.dessin;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Shape;

public class ShapeDrawer extends JFrame {
    private ShapeButtonPanel shapeButtonPanel;
    private DrawingPanel drawingPanel;

    public ShapeDrawer() {
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
}
