package appAncien.model;

import java.awt.Color;
import java.util.Arrays;

import appAncien.model.formes.AbstractForme;
import appAncien.model.pieces.PuzzlePiece;
import appAncien.model.player.Player;
import util.AbstractModeleEcoutable;

public class PuzzleGrid extends AbstractModeleEcoutable {
    
   
    private PuzzlePiece[][] grid;
    private int width, length;
    private boolean gui;
    private Player play;
    

    /**
     * Constructeur d'un grille de jeu.
     * @param width hauteur de la grille 
     * @param length largeur de la grilel
     * @param gui booleen pour savoir si on affiche ou non en application
     */
    public PuzzleGrid( int width, int length, boolean gui) {

        this.width = width;
        this.length = length;
        this.gui = gui;

        //initialisation du tableau à false
        this.grid = new PuzzlePiece[width][length];
        for(int i = 0; i < this.grid.length; i++) {
            Arrays.fill(this.grid[i], null);
        }
    }

    /**
     * Accesseur de la hauteur de la grille.
     * @return hauteur
     */
    public int getWidth() {

        return this.width;
    }

    /**
     * Accesseur de la largeur de la grille.
     * @return largeur 
     */
    public int getLength() {
        
        return this.length;
    }

    /**
     * Setter du player de la grille
     * @param play objet Player
     */
    public void setPlayer(Player play){
        this.play = play;
    }

    

    /**
     * La méthode isValid permet de savoir si une piece peut etre posee sur la grille (si elle n'a pas de collisions ou des cases en dehors de la grille)
     * @param piece piece de puzzle
     * @param x milieu x
     * @param y milieu y
     * @return true si peut etre posee false sinon
     */
    public boolean isValid(PuzzlePiece piece, int x, int y) {

        //Check zone de la case seulement maybe choper le milieu d'un pièce (ajouter en param de création de pièce)
        int[] topCoord = getTopLeftIndex(piece, x, y);
        int[] botCoord = getBottomRightIndex(piece, x, y);

        //On regarde si les index ne sortent pas de la grille 
        if(isOutsideGrid(topCoord[0], topCoord[1], botCoord[0], botCoord[1])) {
            System.out.println("Outside the grid");
            return false;
        }
        //On regarde si il n'y a pas de collisions avec d'autres pièces dans la grille sur la zone de la pièce à poser
        if(isFreeToPlace(piece, topCoord[0], topCoord[1])) {
            return true;
        }
        System.out.println("Not free place "+topCoord[0]+" "+topCoord[1]);
        return false;
    }

    /**
     * Cette methode permet de savoir si la piece va etre en dehors de la grille ou non.
     * @param topX x haut gauche
     * @param topY y haut gauche
     * @param botX x bas droite
     * @param botY y bas droite
     * @return true si en dehors flase sinon
     */
    private boolean isOutsideGrid(int topX, int topY, int botX, int botY) {

        if(topX < 0 || topY < 0 || botX >= this.width || botY >= this.length) {
            return true;
        }
        return false;
    }

    /**
     * Cette methode permet de savoir si la future position de la piece va provoquer des collisions ou non.
     * @param piece piece de puzzle
     * @param topX x haut gauche
     * @param topY y haut gauche 
     * @return true si pas de collisions false sinon
     */
    private boolean isFreeToPlace(PuzzlePiece piece, int topX, int topY) {

        Boolean[][] actualPieceForm = AbstractForme.ensConfig.get(piece.getActualName());
       
        for(int i = 0; i < actualPieceForm.length; i++) {
            for(int j = 0; j < actualPieceForm[0].length; j++) {
                if(actualPieceForm[i][j] && this.grid[topX+i][topY+j] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Cette methode va permettre de retirer une piece de la grille.
     * @param piece puzzle piece
     */
    public void removePiece(PuzzlePiece piece) {

        int[] topIndex = getTopLeftIndex(piece, piece.getMidX(), piece.getMidY());
        Boolean[][] actualPieceForm = AbstractForme.ensConfig.get(piece.getActualName());

        for(int i = 0; i < actualPieceForm.length; i++) {
            for(int j = 0; j < actualPieceForm[0].length; j++) {
                if(actualPieceForm[i][j]) {
                   this.grid[topIndex[0]+i][topIndex[1]+j] = null;
                    if(this.gui) {
                        fireChangement(new Data("Modif",topIndex[0]+i, topIndex[1]+j , this, this.play, Color.DARK_GRAY, null));
                    } 
                }
            }
        }
        piece.setMidX(null);
        piece.setMidY(null);
    }

    /**
     * Cette methode va placer une piece sur la grille.
     * @param piece piece de puzzle
     * @param x milieu x
     * @param y milieu y 
     */
    public void placePiece(PuzzlePiece piece, int x, int y) {

        int[] topIndex = getTopLeftIndex(piece, x, y);
        Boolean[][] actualPieceForm = AbstractForme.ensConfig.get(piece.getActualName());
        
        for(int i = 0; i < actualPieceForm.length; i++) {
            for(int j = 0; j < actualPieceForm[0].length; j++) {
                if(actualPieceForm[i][j]){
                    this.grid[topIndex[0]+i][topIndex[1]+j] = piece;
                    if(this.gui) {
                        //La gui est activée et on place un true
                        fireChangement(new Data("Modif", topIndex[0]+i, topIndex[1]+j, this, this.play, piece.getColor(), null));
                    }
                }
            }
        }
        piece.setMidX(x);
        piece.setMidY(y);
        
    }

    /**
     * Cette methode permet d'effectuer une rotation sur une piece placee sur la grille.
     * Elle va la retirer, effectuer la rotation, essayer de la placer si elle peut elle est placee sinon elle revient a son etat d'origine.
     * @param piece piece de puzzle
     * @param newOrientation nouvelle orientation -1 (-90d)/ 1 (90d)
     */
    public void rotatePiece(PuzzlePiece piece, int newOrientation) {

        int midX = piece.getMidX();
        int midY = piece.getMidY();

        removePiece(piece);
        piece.rotate(newOrientation);
        fireChangement(piece);
        if(isValid(piece, midX, midY)) {
            placePiece(piece, midX, midY);
        }
        else{
            piece.rotate(-newOrientation);
            placePiece(piece, midX, midY);
        }
        
    }

    /**
     * Cette methode calcul l'air du plus petit rectangle contenant toutes les pieces
     * @return aire du plus petit rectangle
     */
    public int minArea() {
        
        int minX = -1;
        int minY = -1;
        int maxX = -1;
        int maxY = -1;
        int x = 0;
        int y = 0;

        while((minX == -1 || maxX == -1) && x < this.width) {
            while((minX == -1 || maxX == -1) && y < this.length) {
                if(minX == -1) {
                    if(this.grid[x][y] != null) {
                        minX = x;
                    }
                }
                if(maxX == -1) {
                    if(this.grid[this.width-x-1][y] != null) {
                        maxX = this.width-x-1;
                    }
                }
                y++;
            }
            y = 0;
            x++;
        }
        x = 0;
        while((minY == -1 || maxY == -1) && y < this.length) {
            while((minY == -1 || maxY == -1) && x < this.width) {
                if(minY == -1) {
                    if(this.grid[y][x] != null) {
                        minY = x;
                    }
                }
                if(maxY == -1) {
                    if(this.grid[x][this.length-y-1] != null) {
                        maxY = this.length-y-1;
                    }
                }
                x++;
            }
            x = 0;
            y++;
        }
        if(minX == -1 || minY == -1 || maxX == -1 || maxY == -1) {
            return 0;
        }
        return (maxX-minX+1)*(maxY-minY+1);
    }

    /**
     * Cette methode permet d'obtenir les coordonnees de la case en haut a gauche d'une piece suivant les coordonnees du milieu
     * @param piece piece puzzle
     * @param x milieu x
     * @param y milieu y
     * @return tableau avec comme index 0 x et index 1 y
     */
    private int[] getTopLeftIndex(PuzzlePiece piece, int x, int y) {

        int[] res = new int[2];
        Boolean[][] actualPieceForm = AbstractForme.ensConfig.get(piece.getActualName());
        res[0] = x-actualPieceForm.length/2;
        res[1] = y-actualPieceForm[0].length/2;
        return res;
    }

    /**
     * Cette methode permet d'obtenir les coordonnees de la case en bas droite d'une piece suivant les coordonnees du milieu
     * @param piece piece puzzle
     * @param x milieu x
     * @param y milieu y
     * @return tableau avec comme index 0 x et index 1 y
     */
    private int[] getBottomRightIndex(PuzzlePiece piece, int x, int y) {

        int[] res = new int[2];
        Boolean[][] actualPieceForm = AbstractForme.ensConfig.get(piece.getActualName());
        res[0] = x + actualPieceForm.length/2;
        res[1] = y + actualPieceForm[0].length/2;
        return res;
    }

    @Override
    public String toString() {

        String res = "";
        
        for(int i = 0; i < this.width; i++) {
            for(int j = 0; j < this.length; j++) {
                if(j == 0) {
                    res += "|";
                }
                if(this.grid[i][j] != null) {
                    res += "#|";
                }
                else {
                    res += " |";
                }
            }
            res += "\n";
        }
        return res;
    }


}