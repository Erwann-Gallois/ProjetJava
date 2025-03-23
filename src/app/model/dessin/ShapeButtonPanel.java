package app.model.dessin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;

    public ShapeButtonPanel() {
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Circle");
        shapeLabel = new JLabel("Selected Shape: Rectangle");

        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);

        add(rectangleButton);
        add(circleButton);
        add(shapeLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rectangleButton) {
            currentShape = "rectangle";
            shapeLabel.setText("Selected Shape: Rectangle");
        } else if (e.getSource() == circleButton) {
            currentShape = "circle";
            shapeLabel.setText("Selected Shape: Circle");
        }
    }

    public String getCurrentShape() {
        return currentShape;
    }
}
