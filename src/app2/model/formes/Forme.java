package app2.model.formes;

import java.awt.Shape;

public abstract class Forme {
    private double midX, midY, width, height;

    // Constructeur et méthodes getters/setters
    public Forme(double midX, double midY, double width, double height) {
        this.midX = midX;
        this.midY = midY;
        this.width = width;
        this.height = height;
    }

    public double getMidX() {
        return midX;
    }

    public double getMidY() {
        return midY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public abstract double area();

    public abstract Shape transform();
}
