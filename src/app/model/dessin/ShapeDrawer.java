package app.model.dessin;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Shape;
import java.util.ArrayList;

public class ShapeDrawer extends JFrame {
    private ShapeButtonPanel shapeButtonPanel;
    private DrawingPanel drawingPanel;
    private ArrayList<Shape> shapes = null;

    public ShapeDrawer() {
        setTitle("Shape Drawer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create panels
        shapeButtonPanel = new ShapeButtonPanel();
        drawingPanel = new DrawingPanel(shapeButtonPanel);

        shapes = drawingPanel.getShapes();

        // Add components to frame
        add(shapeButtonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }
}
