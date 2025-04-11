package app2.model.dessin.factory;

import util.AbstractModeleEcoutable;

public abstract class AbstractFormeFactory extends AbstractModeleEcoutable implements FormeFactory{

    private String pieceName;
    private Integer X;
    private Integer Y;
    private Integer w;
    private Integer h;

    public AbstractFormeFactory(String type) {
        this.pieceName = type;
        this.X = null;
        this.Y = null;
        this.w = null;
        this.h = null;
    }

}
