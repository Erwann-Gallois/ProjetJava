package app.model.dessin;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import app.model.formes.*;
import java.awt.Shape;

public class ShapeEvaluatorCircle implements ShapeEvaluator{
    public double compareShapes(Forme original, Forme drawn) {
        // Comparaison basique des dimensions et positions des formes
        Ellipse2D originalBounds = new Ellipse2D.Double(original.getMidX() - original.getWidth() / 2, original.getMidY() - original.getHeight() / 2, original.getHeight(), original.getWidth());
        Ellipse2D drawnBounds = new Ellipse2D.Double(drawn.getMidX() - drawn.getWidth() / 2, drawn.getMidY() - drawn.getHeight() / 2, drawn.getHeight(), drawn.getWidth());

        // Tolérance (par exemple, pour les petites erreurs de positionnement)
        final double tolerance = 5.0;

        return 100 - Math.abs(originalBounds.getX() - drawnBounds.getX()) - Math.abs(originalBounds.getY() - drawnBounds.getY()) - Math.abs(originalBounds.getWidth() - drawnBounds.getWidth()) - Math.abs(originalBounds.getHeight() - drawnBounds.getHeight());
    }
}