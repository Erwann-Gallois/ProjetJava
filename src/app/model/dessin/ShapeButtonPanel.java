package app.model.dessin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Shape;

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private JButton termineButton;
    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private ShapeProvider shapeProvider;

    public ShapeButtonPanel(ShapeProvider shapeProvider) {
        this.shapeProvider = shapeProvider;
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
            for (Shape shape : shapeProvider.getShapes().values()) {
                System.out.println("--------------------");
                System.out.println("Points: (" + shape.getBounds().x + ", " + shape.getBounds().y + ")");
                System.out.println("Width: " + shape.getBounds().width);
                System.out.println("Height: " + shape.getBounds().height);
                System.out.println("--------------------");
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
}
