package app2.view;

import app2.model.dessin.ShapeDrawer;
import app2.model.dessin.factory.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*; // Importation de Shape

public class ShapeButtonPanel extends JPanel implements ActionListener {
    private JButton rectangleButton;
    private JButton circleButton;
    private JButton termineButton;
    private JButton generateShapesButton;
    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private ArrayList<Shape> shapesDrawn = new ArrayList<>(); // Utilisation de Shape
    private ShapeDrawer shapeDrawer;
    private boolean interactive = true;
    public FormeFactory shapeFactory;  // Factory qui crée des formes de type Shape
    private DrawingPanel drawingPanel;

    public ShapeButtonPanel(ShapeDrawer shapeDrawer2) {

        this.shapeFactory =  new RectangleFactory();
        
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
            drawingPanel.setFactory(new RectangleFactory());  // Factory pour créer des rectangles
            shapeFactory = new RectangleFactory();
            currentShape = "rectangle";
            shapeLabel.setText("Selected Shape: Rectangle");
        } else if (e.getSource() == circleButton) {
            nbreCircle++; // Incrémentation du compteur de cercles
            drawingPanel.setFactory(new CircleFactory()); // Factory pour créer des cercles
            shapeFactory = new CircleFactory();
            currentShape = "circle";
            shapeLabel.setText("Selected Shape: Circle");
        } else if (e.getSource() == termineButton) {
            // Implémentation de la logique pour finaliser les dessins
            finalizeDrawings();
        }
    }

    private void finalizeDrawings() {
        // Logique pour finaliser les dessins : évaluer la performance, etc.
        setInteractive(false);  // Geler l'interface

        Double res = shapeDrawer.evaluateDrawing(getVisibleRect(), getBounds()); // -> Mettre à jour avec logique d'évaluation des shapes

        // Pour l'instant, juste un message de fin
        JOptionPane.showMessageDialog(this, "Les dessins sont terminés, vous avez obtenue un score de "+res);

        // Par exemple, appeler shapeDrawer pour évaluer les dessins
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

    public void setDrawingPanel(DrawingPanel panel){
        drawingPanel = panel;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    
        // Désactive tous les boutons
        for (Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(interactive);
            }
        }
    }

    public ArrayList<Shape> getShapesDrawn() {  // Liste des formes dessinées sous forme de Shape
        return shapesDrawn;
    }

    public void setRandomShapeButtonEnabled(boolean b) {
        generateShapesButton.setEnabled(b);
    }

    public FormeFactory getFactory() {
        return shapeFactory;
    }
}
