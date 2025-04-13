package app.model.dessin.factory;

import util.AbstractModeleEcoutable;

/**
 * Classe abstraite représentant une fabrique de formes.
 * Elle étend la classe AbstractModeleEcoutable et implémente l'interface FormeFactory.
 * Cette classe sert de base pour créer des formes spécifiques.
 */
public abstract class AbstractFormeFactory extends AbstractModeleEcoutable implements FormeFactory{

    private String pieceName;
    private Integer X;
    private Integer Y;
    private Integer w;
    private Integer h;

    /**
     * Constructeur de la classe AbstractFormeFactory.
     * Initialise les attributs de la classe.
     *
     * @param type Le type de la forme à créer.
     */
    public AbstractFormeFactory(String type) {
        this.pieceName = type;
        this.X = null;
        this.Y = null;
        this.w = null;
        this.h = null;
    }

}
