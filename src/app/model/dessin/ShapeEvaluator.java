package app.model.dessin;

import java.awt.Shape;

import app.model.formes.Forme;

public interface ShapeEvaluator {

    double compareShapes(Forme sp, Forme f);
    
}
