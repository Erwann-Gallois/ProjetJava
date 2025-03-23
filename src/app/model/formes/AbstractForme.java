package app.model.formes;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import app.model.formes.AbstractForme;
import util.AbstractModeleEcoutable;



public abstract class AbstractForme extends AbstractModeleEcoutable implements Forme {
    
    public static Map<String, Boolean[][]> ensConfig = new HashMap<>();
    private String pieceName;
    private Integer midX;
    private Integer midY;

    private Color c = new Color((int)(Math.random() * 0x1000000));


    /**
     * Constructeur abstrait d'une forme.
     * @param type base du nom de la forme
     */
    public AbstractForme(String type) {

        this.pieceName = type;
        this.midX = null;
        this.midY = null;
    }

    @Override
    public Integer getMidX() {

        return this.midX;
    }

    @Override
    public Integer getMidY() {

        return this.midY;
    }

    @Override
    public Color getColor(){
        return this.c;
    }

    @Override
    public String getActualName() {

        return "("+this.pieceName+","+getPosition()+")";
    }

    public String getPosition(){
        return "("+getMidX()+","+getMidY()+")";
    }

    @Override
    public void setMidX(Integer x) {

        this.midX = x;
    }

    @Override
    public void setMidY(Integer y) {

        this.midY = y;
    }

    @Override
    public String toString() {

        String res = "";
        Boolean[][] fig = AbstractForme.ensConfig.get(getActualName());

        System.out.println(getActualName());
        for(int i = 0; i < fig.length; i++) {
            for(int j = 0; j < fig[i].length; j++) {
                if(fig[i][j]) {
                    res += "*";
                }
                else {
                    res += " ";
                }
            }
            res += System.lineSeparator();
        }
        return res;
    }

}
