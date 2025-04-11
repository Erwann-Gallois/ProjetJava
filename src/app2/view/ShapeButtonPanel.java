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
    private JButton triangleButton;
    private JButton termineButton;
    private JButton generateShapesButton;
    private JButton undoButton;
    private JButton redoButton;
    private int nbreRectangle = 0;
    private int nbreCircle = 0;
    private int nbreTriangle = 0;
    private String currentShape = "rectangle";
    private JLabel shapeLabel;
    private ArrayList<Shape> shapesDrawn = new ArrayList<>(); // Utilisation de Shape
    private ShapeDrawer shapeDrawer;
    private boolean interactive = true;
    public FormeFactory shapeFactory;  // Factory qui crée des formes de type Shape
    private DrawingPanel drawingPanel;

    /**
     * Constructeur de la classe ShapeButtonPanel.
     * @param shapeDrawer2 Objet ShapeDrawer pour dessiner les formes
     */
    public ShapeButtonPanel(ShapeDrawer shapeDrawer2) {

        this.shapeFactory =  new RectangleFactory();
        
        this.shapeDrawer = shapeDrawer2;
        setLayout(new FlowLayout());
        
        generateShapesButton = new JButton("Generer des formes");
        generateShapesButton.addActionListener(e -> shapeDrawer.displayRandomShapes());
        add(generateShapesButton);
        
        rectangleButton = new JButton("Rectangle");
        circleButton = new JButton("Cercle");
        triangleButton = new JButton("Triangle");
        termineButton = new JButton("Termine");
        shapeLabel = new JLabel("Selected Shape: Rectangle");

        this.undoButton = new JButton("undo");
        this.redoButton = new JButton("redo");
        undoButton.addActionListener(e -> drawingPanel.undo());
        redoButton.addActionListener(e -> drawingPanel.redo());

        rectangleButton.addActionListener(this);
        circleButton.addActionListener(this);
        triangleButton.addActionListener(this);
        termineButton.addActionListener(this);

        add(rectangleButton, BorderLayout.WEST);
        add(circleButton, BorderLayout.CENTER);
        add(triangleButton, BorderLayout.CENTER);
        add(shapeLabel, BorderLayout.CENTER);
        add(termineButton, BorderLayout.EAST);

        add(this.undoButton);
        add(this.redoButton);
    }

    /**
     * Méthode actionPerformed pour gérer les événements des boutons.
     * @param e Événement ActionEvent
     */
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
        } else if (e.getSource() == triangleButton) {
            nbreTriangle++; // Incrémentation du compteur de triangles
            drawingPanel.setFactory(new TriangleFactory()); // Factory pour créer des triangles
            shapeFactory = new TriangleFactory();
            currentShape = "triangle";
            shapeLabel.setText("Selected Shape: Triangle");

        } else if (e.getSource() == termineButton) {
            // Implémentation de la logique pour finaliser les dessins
            finalizeDrawings();
        }
    }

    /**
     * Méthode pour finaliser les dessins.
     */
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

    public int getNbreTriangle()
    {
        return nbreTriangle;
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
