
package app2.view;

import javax.swing.*;

import app2.model.dessin.ShapeDrawer;
import app2.model.formes.Forme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private JButton termineButton;
    private JButton generateShapesButton;
    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private ArrayList<Forme> formesdessine = new ArrayList<>();
    private ShapeDrawer shapeDrawer;

    public ShapeButtonPanel(ShapeDrawer shapeDrawer2) {
        this.shapeDrawer = shapeDrawer2;
        setLayout(new FlowLayout());
        generateShapesButton = new JButton("Generer des formes");
        generateShapesButton.addActionListener(e -> shapeDrawer.displayRandomShapes());
        add(generateShapesButton);
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        termineButton = new JButton("Termine");
        shapeLabel = new JLabel("Selected Shape: Rectangle");

        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        termineButton.addActionListener(this);

        add(rectangleButton, BorderLayout.WEST);
        add(circleButton, BorderLayout.CENTER);
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
        } else if (e.getSource() == termineButton) {
            // Print all drawn shapes
            System.out.println("boop");
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

    public ArrayList<Forme> getFormesDessine() {
        return formesdessine;
    }

    public void setRandomShapeButtonEnabled(boolean b) {
        generateShapesButton.setEnabled(b);
    }

}
