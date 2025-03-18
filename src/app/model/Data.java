package app.model;

import java.awt.Color;

import app.model.player.Player;

public class Data {

    private String situation;
    private Integer x;
    private Integer y;
    private PuzzleGrid grid;
    private Player play;
    private Color col;
    private String status;


    /**
     * Constructeur d'un objet Data qui sert à transmettre des informations dans l'application.
     * @param situation string situation de la data
     * @param x entier x
     * @param y entier y
     * @param grid grille 
     * @param play player
     * @param col couleur
     * @param status statu
     */
    public Data(String situation, Integer x, Integer y, PuzzleGrid grid, Player play, Color col, String status){
        this.situation = situation;
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.play = play;
        this.col = col;
        this.status = status;
    }

    /**
     * Accesseur à la situation de la donnée.
     * @return String de al situation
     */
    public String getSituation(){
        return this.situation;
    }

    /**
     * Accesseur à l'entier x
     * @return entier
     */
    public Integer getX(){
        return this.x;
    }

    /**
     * Accesseur à l'entier y
     * @return entier
     */
    public Integer getY(){
        return this.y;
    }

    /**
     * Accesseur à la grille 
     * @return grille
     */
    public PuzzleGrid getGrid(){
        return this.grid;
    }

    /**
     * Accesseur au player
     * @return player
     */
    public Player getPlayer(){
        return this.play;
    }  
    
    /**
     * Accesseur a la couleur
     * @return couleur
     */
    public Color getColor(){
        return this.col;
    }

    /**
     * Accesseur au statu
     * @return string
     */
    public String getStatus(){
        return this.status;
    }
}
