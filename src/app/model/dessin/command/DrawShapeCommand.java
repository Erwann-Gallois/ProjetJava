package app.model.dessin.command;

import java.awt.Shape;
import java.util.Map;

public class DrawShapeCommand implements OperationCommand {
    private Map<String, Shape> shapeMap;
    private String key;
    private Shape shape;

    /**
     * Constructeur pour la classe DrawShapeCommand.
     *
     * @param shapeMap La map qui contient les formes anciennes.
     * @param key      La clé pour identifier la forme ancienne.
     * @param shape    La forme enlevée.
     */
    public DrawShapeCommand(Map<String, Shape> shapeMap, String key, Shape shape) {
        this.shapeMap = shapeMap;
        this.key = key;
        this.shape = shape;
    }

    /**
     * Exécute la commande en ajoutant la forme à la map.
     */
    @Override
    public void operate() {
        shapeMap.put(key, shape);
    }

    /**
     * Annule la commande en supprimant la forme de la map.
     */
    @Override
    public void compensate() {
        shapeMap.remove(key);
    }
}
