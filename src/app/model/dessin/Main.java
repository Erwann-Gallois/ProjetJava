package app.model.dessin;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.util.ArrayList;

import javax.swing.JButton;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ShapeDrawer frame = new ShapeDrawer();
            frame.setVisible(true);

            // Add a button to print the shapes
            // JButton printShapesButton = new JButton("Print Shapes");
            // printShapesButton.addActionListener(e -> {
            //     ArrayList<Shape> shapes = frame.getShapes();
            //     for (Shape shape : shapes) {
            //         System.out.println("--------------------");
            //         System.out.println("Points: (" + shape.getBounds().x + ", " + shape.getBounds().y + ")");
            //         System.out.println("Width: " + shape.getBounds().width);
            //         System.out.println("Height: " + shape.getBounds().height);
            //         System.out.println("--------------------");
            //     }
            // });
            // frame.add(printShapesButton, BorderLayout.SOUTH);

            // // Print out the shapes
            // for (Shape shape : shapes) {
            //     System.out.println("Points: " + shape.getBounds().x + ", " + shape.getBounds().y);
            //     System.out.println("Width: " + shape.getBounds().width);
            //     System.out.println("Height: " + shape.getBounds().height);
            // }
        });
        

    }
}
