package app2.model.dessin;


import app2.model.formes.Forme;

public interface ShapeEvaluator {
    double compareShapes(Forme original, Forme drawn);
}
