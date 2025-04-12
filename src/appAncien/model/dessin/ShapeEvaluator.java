package appAncien.model.dessin;

import java.awt.Shape;

import appAncien.model.formes.Forme;

public interface ShapeEvaluator {

    double compareShapes(Shape sp, Shape f);
    
}
