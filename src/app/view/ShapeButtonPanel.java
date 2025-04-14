package app.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*; // Importation de Shape

import app.model.dessin.ShapeDrawer;
import app.model.dessin.factory.*;

/**
 * Classe ShapeButtonPanel
 * Représente le panneau de boutons pour sélectionner les formes à dessiner.
 * Elle gère les événements des boutons et l'affichage des formes sélectionnées.
 */
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

        System.out.println("#######################");
        System.out.println(shapeDrawer.getOriginalShapes());
        System.out.println(drawingPanel.getShapes());
        
        Double res = shapeDrawer.evaluateDrawing(shapeDrawer.getOriginalShapes(), drawingPanel.getShapes());
        
        // Pour l'instant, juste un message de fin
        JOptionPane.showMessageDialog(this, "Les dessins sont terminés, vous avez obtenue un score de "+res);
    }

    
    /**
     * Méthode pour obtenir le nom de la forme actuelle.
     * @return Le nom de la forme actuelle
     */
    public String getCurrentShape() {
        return currentShape;
    }

    /**
     * Accesseur du nombre de rectangle.
     * @return Le nombre de rectangles
     */
    public int getNbreRectangle() {
        return nbreRectangle;
    }

    /**
     * Accesseur du nombre de cercles.
     * @return Le nombre de cercles
     */
    public int getNbreCircle() {
        return nbreCircle;
    }

    /**
     * Accesseur du nombre de triangles.
     * @return Le nombre de triangles
     */
    public int getNbreTriangle()
    {
        return nbreTriangle;
    }

    /**
     * Setter pour le panneau de dessin.
     * @param panel Le panneau de dessin
     */
    public void setDrawingPanel(DrawingPanel panel){
        drawingPanel = panel;
    }

    /**
     * Setter pour le mode interactif.
     * @param interactive Le mode interactif
     */
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    
        // Désactive tous les boutons
        for (Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(interactive);
            }
        }
    }

    /**
     * Accesseur pour les formes dessinées.
     * @return Liste des formes dessinées
     */
    public ArrayList<Shape> getShapesDrawn() {  // Liste des formes dessinées sous forme de Shape
        return shapesDrawn;
    }

    /**
     * Setter du mode bouton pour aléatoire
     * @param b true si le bouton doit être activé, false sinon
     */
    public void setRandomShapeButtonEnabled(boolean b) {
        generateShapesButton.setEnabled(b);
    }

    /**
     * Accesseur pour la factory de forme.
     * @return La factory de forme
     */
    public FormeFactory getFactory() {
        return shapeFactory;
    }
}
