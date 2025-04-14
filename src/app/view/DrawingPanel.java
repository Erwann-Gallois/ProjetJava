package app.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import app.model.dessin.command.CommandHandler;
import app.model.dessin.command.DrawShapeCommand;
import app.model.dessin.factory.FormeFactory;


/**
 * Classe DrawingPanel
 * Représente le panneau de dessin.
 * Elle permet de dessiner des formes géométriques.
 */
public class DrawingPanel extends JPanel {
    private Point startPoint;
    private Point endPoint;
    private HashMap<String, Shape> shapes = new HashMap<>();
    private ShapeButtonPanel shapeButtonPanel;
    private boolean randomShapesMode = false;
    private boolean interactive = true;
    private boolean isMoveMode = false;
    private String selectedShapeKey = null;
    private Point lastMousePosition = null;

    private FormeFactory shapeFactory;
    private CommandHandler commandHandler = new CommandHandler();
    
    /**
     * Constructeur de la classe DrawingPanel.
     * @param shapeButtonPanel ShapeButtonPanel
     * @see ShapeButtonPanel
     */
    public DrawingPanel(ShapeButtonPanel shapeButtonPanel) {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        this.shapeButtonPanel = shapeButtonPanel;
        this.shapeFactory = shapeButtonPanel.getFactory();
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!interactive) return;
        
                startPoint = e.getPoint();
        
                if (isMoveMode) {
                    for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
                        if (entry.getValue().contains(startPoint)) {
                            selectedShapeKey = entry.getKey();
                            lastMousePosition = startPoint;
                            break;
                        }
                    }
                }
            }
        
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!interactive) return;
        
                endPoint = e.getPoint();
        
                if (isMoveMode && selectedShapeKey != null && lastMousePosition != null) {
                    Shape selectedShape = shapes.get(selectedShapeKey);
                    if (selectedShape != null) {
                        int dx = e.getX() - lastMousePosition.x;
                        int dy = e.getY() - lastMousePosition.y;
        
                        Shape movedShape = moveShape(selectedShape, dx, dy);
                        shapes.put(selectedShapeKey, movedShape);
        
                        lastMousePosition = e.getPoint();
                        repaint();
                    }
                } else {
                    repaint();
                }
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!interactive) return;
        
                endPoint = e.getPoint();
        
                if (isMoveMode && selectedShapeKey != null && lastMousePosition != null) {
                    selectedShapeKey = null;
                    lastMousePosition = null;
                    repaint();
                    return;
                }
        
                if (startPoint != null && endPoint != null && shapeFactory != null) {
                    Shape newShape = shapeFactory.createForme(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    String shapeName = shapeButtonPanel.getCurrentShape();
                    String key = shapeName + shapeButtonPanel.getNbreRectangle();
                    shapes.put(key, newShape);
        
                    DrawShapeCommand command = new DrawShapeCommand(shapes, key, newShape);
                    commandHandler.handle(command);
                    repaint();
                }
        
                startPoint = null;
                endPoint = null;
            }
        };
        
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    /**
     * Ajoute une forme à la liste des formes.
     * @param id String
     * @param forme Shape
     * @see Shape
     * @see java.awt.Shape
     */
    public void addShape(String id, Shape forme) {
        shapes.put(id, forme);
        repaint();
    }

    /**
     * Supprime l'ensemble des formes de la liste.
     */
    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public void setMoveMode(boolean isMoveMode) {
        this.isMoveMode = isMoveMode;
    }

    // Déplacer une forme
    private Shape moveShape(Shape shape, int dx, int dy) {
        AffineTransform transform = AffineTransform.getTranslateInstance(dx, dy);
        return transform.createTransformedShape(shape);
    }

    /**
     * Setter la factory de forme.
     * @param shapeFactory FormeFactory
     * @see FormeFactory
     */
    public void setFactory(FormeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    /**
     * Accesseur de la liste des formes dessinées
     * @return Map avec le nom et la forme dessinées
     */
    public HashMap<String, Shape> getShapes() {
        return shapes;
    }

    /**
     * Setter pour le mode interactif
     * @param interactive boolean
     */
    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    
    /**
     * Méthode pour annuler la dernière action.
     */
    public void undo() {
        commandHandler.undo();
        repaint();
    }

    public boolean isMoveMode() {
        return isMoveMode;
    }
    
    /**
     * Méthode pour rétablir la dernière action annulée.
     */
    public void redo() {
        commandHandler.redo();
        repaint();
    }
    
    /**
     * Méthode pour peindre le composant.
     * @param g Le contexte graphique
     * @see javax.swing.JComponent#paintComponent(Graphics)
     * @see java.awt.Graphics
     * @see java.awt.Graphics2D
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw all shapes
        for (Shape shape : shapes.values()) {
            g2d.setColor(Color.black);
            g2d.draw(shape);
        }

        // Draw the current shape being dragged
        if (startPoint != null && endPoint != null) {
            // Shape shape = createShape(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            Shape shape = shapeFactory.createForme(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            g2d.setColor(Color.lightGray);
            g2d.draw(shape);
        }
    }

    /**
     * Setter pour le panneau de boutons de forme.
     * @param newshapeButtonPanel ShapeButtonPanel
     * @see ShapeButtonPanel
     */
    public void setShapeButtonPanel(ShapeButtonPanel newshapeButtonPanel) {
        this.shapeButtonPanel = newshapeButtonPanel;
    }

    /**
     * Setter pour le mode aléatoire.
     * @param mode boolean
     */
    public void setRandomShapesMode(boolean mode) {
        this.randomShapesMode = mode;
    }

}
