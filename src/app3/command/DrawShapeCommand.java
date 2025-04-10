package app3.command;

import java.awt.Shape;
import java.util.Map;

public class DrawShapeCommand implements OperationCommand {
    private Map<String, Shape> shapeMap;
    private String key;
    private Shape shape;

    public DrawShapeCommand(Map<String, Shape> shapeMap, String key, Shape shape) {
        this.shapeMap = shapeMap;
        this.key = key;
        this.shape = shape;
    }

    @Override
    public void operate() {
        shapeMap.put(key, shape);
    }

    @Override
    public void compensate() {
        shapeMap.remove(key);
    }
}
