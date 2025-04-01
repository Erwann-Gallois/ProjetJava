package app3.model;

import app3.factory.FormeFactory;
import app3.factory.RectangleFactory;
import java.awt.BorderLayout;
import javax.swing.*;

public class ShapeDrawer extends JFrame {
    private ShapeButtonPanel shapeButtonPanel;
    private DrawingPanel drawingPanel;
    private FormeFactory shapeFactory;

    public ShapeDrawer() {
        setTitle("Shape Drawer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        this.shapeFactory = new RectangleFactory();

        // Create panels
        this.drawingPanel = new DrawingPanel(shapeFactory);
        this.shapeButtonPanel = new ShapeButtonPanel(this.drawingPanel);
        drawingPanel.setShapeButtonPanel(shapeButtonPanel);


        // Add components to frame
        add(shapeButtonPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);

        // Set the shape provider after initialization
    }

    public DrawingPanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setFactory(FormeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
        // this.drawingPanel.setFactory(shapeFactory);
        this.drawingPanel.repaint();
    }

    public ShapeButtonPanel getShapeButtonPanel() {
        return shapeButtonPanel;
    }
}
