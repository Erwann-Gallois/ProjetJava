package app2.view;

import app2.model.dessin.command.CommandHandler;
import app2.model.dessin.command.DrawShapeCommand;
import app2.model.dessin.factory.FormeFactory;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;


public class DrawingPanel extends JPanel {
    private Point startPoint;
    private Point endPoint;
    private HashMap<String, Shape> shapes = new HashMap<>();
    private ShapeButtonPanel shapeButtonPanel;
    private boolean randomShapesMode = false;
    private boolean interactive = true;

    private FormeFactory shapeFactory;
    private CommandHandler commandHandler = new CommandHandler();
    
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
            }
        
            @Override
            public void mouseReleased(MouseEvent e) {
                if (!interactive) return;
                endPoint = e.getPoint();
                // Shape shape = createShape(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                // if (shapeButtonPanel.getCurrentShape().equals("rectangle")) {
                //     shapes.put("rectangle" + shapeButtonPanel.getNbreRectangle(), shape);
                // } else if (shapeButtonPanel.getCurrentShape().equals("circle")) {
                //     shapes.put("circle" + shapeButtonPanel.getNbreCircle(), shape);
                // }
                // repaint();
                shapeFactory = shapeButtonPanel.getFactory();

                if (startPoint != null && endPoint != null && shapeFactory != null) {

                    // System.out.println(shapeFactory);

                    Shape newShape = shapeFactory.createForme(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                    String shapeName = shapeButtonPanel.getCurrentShape();
                    String key = shapeName + shapeButtonPanel.getNbreRectangle();
                    shapes.put(shapeName + shapeButtonPanel.getNbreRectangle(), newShape);
                    System.out.println(shapes);
                    DrawShapeCommand command = new DrawShapeCommand(shapes, key, newShape);
                    commandHandler.handle(command);
                    repaint();
                }
            }
        
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!interactive) return;
                endPoint = e.getPoint();
                repaint();
            }
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void addShape(String id, Shape forme) {
        shapes.put(id, forme);
        repaint();
    }

    public void clearShapes() {
        shapes.clear();
        repaint();
    }

    public void setFactory(FormeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public HashMap<String, Shape> getShapes() {
        return shapes;
    }

    public void setInteractive(boolean interactive) {
        this.interactive = interactive;
    }
    

    public void undo() {
        commandHandler.undo();
        repaint();
    }
    
    public void redo() {
        commandHandler.redo();
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        // super.paintComponent(g);
        // for (Shape shape : shapes.values()) {
        //     if (shape instanceof Rectangle) {
        //         g.fillRect((int) shape.getBounds2D().getX(), (int) shape.getBounds2D().getY(), (int) shape.getBounds2D().getWidth(), (int) shape.getBounds2D().getHeight());
        //         System.out.print("On dessine");
        //     } else if (shape instanceof Ellipse2D.Double) {
        //         g.fillOval((int) shape.getBounds2D().getX(), (int) shape.getBounds2D().getY(), (int) shape.getBounds2D().getWidth(), (int) shape.getBounds2D().getHeight());
        //         System.out.print("On dessine");
        //     }
        // }
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

    // private Shape createShape(int x1, int y1, int x2, int y2) {
    //     if (shapeButtonPanel.getCurrentShape().equals("rectangle")) {
    //         int x = Math.min(x1, x2);
    //         int y = Math.min(y1, y2);
    //         int width = Math.abs(x1 - x2);
    //         int height = Math.abs(y1 - y2);
    //         return new Rectangle(x, y, width, height);

    //     } else if (shapeButtonPanel.getCurrentShape().equals("circle")) {
    //         int x = Math.min(x1, x2);
    //         int y = Math.min(y1, y2);
    //         int width = Math.abs(x1 - x2);
    //         int height = Math.abs(y1 - y2);
    //         int diameter = Math.max(width, height);
    //         return new Ellipse2D.Double(x, y, diameter, diameter);
    //     }
    //     return null;
    // }

    // Method to set the shape button panel
    public void setShapeButtonPanel(ShapeButtonPanel newshapeButtonPanel) {
        this.shapeButtonPanel = newshapeButtonPanel;
    }

    public void setRandomShapesMode(boolean mode) {
        this.randomShapesMode = mode;
    }

}
