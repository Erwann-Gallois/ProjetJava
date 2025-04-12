package appAncien.model.dessin;

import javax.swing.*;

import appAncien.model.formes.AbstractForme;
import appAncien.model.formes.FormeCarre;
import appAncien.model.formes.FormeCercle;
import appAncien.model.formes.FormeTriangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.awt.BorderLayout;
import java.awt.Shape;

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private JButton triangleButton;
    private JButton termineButton;

    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private int nbreTriangle = 0;

    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private ShapeProvider shapeProvider;
    private ArrayList<AbstractForme> formesdessine = new ArrayList<>();

    public ShapeButtonPanel(ShapeProvider shapeProvider) {
        this.shapeProvider = shapeProvider;
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        triangleButton = new JButton("Triangle");
        termineButton = new JButton("Termine");
        shapeLabel = new JLabel("Selected Shape: Rectangle");

        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        triangleButton.addActionListener(this);
        termineButton.addActionListener(this);

        add(rectangleButton, BorderLayout.WEST);
        add(circleButton, BorderLayout.CENTER);
        add(triangleButton, BorderLayout.CENTER);
        add(shapeLabel, BorderLayout.CENTER);
        add(termineButton, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rectangleButton) {
            nbreRectangle++;
            currentShape = "rectangle";
            shapeLabel.setText("Selected Shape: Rectangle");
        } else if (e.getSource() == circleButton) {
            nbreCircle++;
            currentShape = "circle";
            shapeLabel.setText("Selected Shape: Circle");
        } else if (e.getSource() == triangleButton) {
            nbreTriangle++;
            currentShape = "triangle";
            shapeLabel.setText("Selected Shape : Triangle");
        } else if (e.getSource() == termineButton) {
            rectangleButton.setEnabled(false);
            circleButton.setEnabled(false);
            triangleButton.setEnabled(false);
            // Print all drawn shapes
            for (Map.Entry<String, Shape> entry : shapeProvider.getShapes().entrySet()) {
                if (entry.getKey().contains("rectangle")) {
                    FormeCarre rect = new FormeCarre(entry.getValue().getBounds().width,
                            entry.getValue().getBounds().height);
                    formesdessine.add(rect);
                } else if (entry.getKey().contains("circle")) {
                    FormeCercle cercle = new FormeCercle(entry.getValue().getBounds().width,
                            entry.getValue().getBounds().height);
                    formesdessine.add(cercle);
                } else if (entry.getKey().contains("triangle")) {
                    FormeTriangle triangle = new FormeTriangle(entry.getValue().getBounds().width, 
                            entry.getValue().getBounds().height);
                    formesdessine.add(triangle);
                }
            }
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

    public int getNbreTriangle()
    {
        return nbreTriangle;
    }

    public ArrayList<AbstractForme> getFormesDessine() {
        return formesdessine;
    }
}
