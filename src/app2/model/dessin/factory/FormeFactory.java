package app2.model.dessin.factory;

import java.awt.Shape;

public interface FormeFactory{
    Shape createForme(int x1, int y1, int x2, int y2);
}
