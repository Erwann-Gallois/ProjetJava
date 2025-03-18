package app.model.player;

import java.util.ArrayList;

import app.model.PuzzleGrid;
import app.model.pieces.PuzzlePiece;


public interface Player{

    /**
     * Accesseur a la liste des pieces du joueur
     * @return liste des pieces
     */
    public ArrayList<PuzzlePiece> getPuzzleList();

    /**
     * Accesseur de la grilel du joueur
     * @return grille
     */
    public PuzzleGrid getPuzzleGrid();

    /**
     * Tour en console
     */
    public void consolTurn();

    /**
     * Tour en gui
     */
    public void guiTurn();

    /**
     * Afficher etat
     */
    public void afficheState();
}
