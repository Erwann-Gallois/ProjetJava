package app3.model;

import app3.command.CommandHandler;
import app3.command.DrawShapeCommand;
import app3.factory.FormeFactory;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class DrawingPanel extends JPanel implements ShapeProvider {
    private Point startPoint;
    private Point endPoint;
    private Map<String, Shape> shapes = new HashMap<>();
    private ShapeButtonPanel shapeButtonPanel;
    private FormeFactory shapeFactory; 
    private CommandHandler commandHandler = new CommandHandler();

    public DrawingPanel(FormeFactory shape) {
        this.shapeFactory = shape;

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
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
                endPoint = e.getPoint();
                repaint();
            }
        };
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        System.out.println(shapes);

        // Draw all stored shapes
        for (Shape shape : shapes.values()) {
            g2d.setColor(Color.black);
            g2d.draw(shape);
            // System.out.println(shape);
        }

        // Draw the shape being dragged
        if (startPoint != null && endPoint != null && shapeFactory != null) {
            Shape previewShape = shapeFactory.createForme(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            g2d.setColor(Color.lightGray);
            g2d.draw(previewShape);
        }
    }

    @Override
    public Map<String, Shape> getShapes() {
        return shapes;
    }

    public void setShapeButtonPanel(ShapeButtonPanel newShapeButtonPanel) {
        this.shapeButtonPanel = newShapeButtonPanel;
    }

    public void setFactory(FormeFactory shapeFactory) {
        this.shapeFactory = shapeFactory;
    }

    public void undo() {
        commandHandler.undo();
        repaint();
    }
    
    public void redo() {
        commandHandler.redo();
        repaint();
    }
}
