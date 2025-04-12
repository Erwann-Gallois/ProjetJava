package appAncien.model.dessin;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
// import java.util.Map;
import java.util.Map;

import javax.swing.*;

public class DrawingPanel extends JPanel implements ShapeProvider {
    private Point startPoint;
    private Point endPoint;
    private Map<String, Shape> shapes = new HashMap<String, Shape>();
    // private ArrayList<Shape> shapes = new ArrayList<>();
    private ShapeButtonPanel shapeButtonPanel;

    public DrawingPanel() {
        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                endPoint = e.getPoint();
                Shape shape = createShape(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
                repaint();
                if (shapeButtonPanel.getCurrentShape().equals("rectangle")) {
                    shapes.put("rectangle" + shapeButtonPanel.getNbreRectangle(), shape);
                } else if (shapeButtonPanel.getCurrentShape().equals("circle")) {
                    shapes.put("circle" + shapeButtonPanel.getNbreCircle(), shape);
                } else if (shapeButtonPanel.getCurrentShape().equals("triangle")){
                    shapes.put("triangle" + shapeButtonPanel.getNbreTriangle(), shape);
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

    private Shape createShape(int x1, int y1, int x2, int y2) {
        if (shapeButtonPanel.getCurrentShape().equals("rectangle")) {
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            return new Rectangle(x, y, width, height);

        } else if (shapeButtonPanel.getCurrentShape().equals("circle")) {
            System.out.println("Circle");
            int x = Math.min(x1, x2);
            int y = Math.min(y1, y2);
            int width = Math.abs(x1 - x2);
            int height = Math.abs(y1 - y2);
            int diameter = Math.max(width, height);
            return new Ellipse2D.Double(x, y, diameter, diameter);
        } else if (shapeButtonPanel.getCurrentShape().equals("triangle")) {
            int sideLength = Math.abs(x2 - x1);
            int height = (int) (Math.sqrt(3) / 2 * sideLength);
            int[] xPoints = {x1, x1 + sideLength / 2, x1 - sideLength / 2};
            int[] yPoints = {y1 - height / 2, y1 + height, y1 + height};
            return new Polygon(xPoints, yPoints, 3);
        }
        return null;
    }

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
            Shape shape = createShape(startPoint.x, startPoint.y, endPoint.x, endPoint.y);
            g2d.setColor(Color.lightGray);
            g2d.draw(shape);
        }
    }

    // Method to get all drawn shapes
    public Map<String, Shape> getShapes() {
        return shapes;
    }

    // Method to set the shape button panel
    public void setShapeButtonPanel(ShapeButtonPanel newshapeButtonPanel) {
        this.shapeButtonPanel = newshapeButtonPanel;
    }
}
