package app3.model;

import app3.factory.*;
import appAncien.model.formes.AbstractForme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private JButton termineButton;
    private JButton undoButton;
    private JButton redoButton;
    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private DrawingPanel drawingPanel; // Reference to DrawingPanel
    private ArrayList<AbstractForme> formesDessine = new ArrayList<>();
    public FormeFactory shapeFactory;

    public ShapeButtonPanel(DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
        
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        termineButton = new JButton("Termine");
        shapeLabel = new JLabel("Selected Shape: Rectangle");
        this.undoButton = new JButton("undo");
        this.redoButton = new JButton("redo");

        undoButton.addActionListener(e -> drawingPanel.undo());
        redoButton.addActionListener(e -> drawingPanel.redo());
        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        termineButton.addActionListener(this);

        // Add buttons in order
        add(rectangleButton);
        add(circleButton);
        add(shapeLabel);
        add(termineButton);
        add(this.undoButton);
        add(this.redoButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rectangleButton) {
            nbreRectangle++;
            drawingPanel.setFactory(new RectangleFactory());
            shapeFactory = new RectangleFactory();
            currentShape = "rectangle";
            shapeLabel.setText("Selected Shape: Rectangle");
        } else if (e.getSource() == circleButton) {
            nbreRectangle++;
            drawingPanel.setFactory(new CircleFactory()); 
            shapeFactory = new CircleFactory();
            currentShape = "circle";
            shapeLabel.setText("Selected Shape: Circle");
        } else if (e.getSource() == termineButton) {
            // Implement logic to finalize drawings
        }
    }

    public String getCurrentShape() {
        return currentShape;
    }

    public int getNbreRectangle() {
        return nbreRectangle;
    }

    public int getNbreCircle() {
        return nbreCircle;
    }

    public FormeFactory getFactory() {
        return shapeFactory;
    }

    public ArrayList<AbstractForme> getFormesDessine() {
        return formesDessine;
    }
}
