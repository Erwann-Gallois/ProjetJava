package appAncien.model;

import java.util.*;
import java.util.concurrent.TimeUnit;

import appAncien.model.formes.*;
import appAncien.model.pieces.PuzzlePiece;
import appAncien.model.pieces.PuzzlePieceCircle;
import appAncien.model.pieces.PuzzlePieceL;
import appAncien.model.pieces.PuzzlePieceSquare;
import appAncien.model.pieces.PuzzlePieceU;
import appAncien.model.player.*;
import util.AbstractModeleEcoutable;

public class Orchestrator extends AbstractModeleEcoutable implements Runnable {
    
    private boolean gui;
    private boolean endPress;
    private int nbTurn = 0;
    private int nbMaxTurn;
    private ArrayList<Player> listPlayer;
    private Player currentPlayer;
    private int status;
    


    /**
    * Constructeur d'un orchestrateur de partie.
     * @param type1 type du joueur 1
     * @param type2 type du joueur 2
     * @param sizeX hauteur de la grille 
     * @param sizeY largeur de la grille 
     * @param nbPiece nombre de pieces
     * @param gui boolean gui
     */
    public Orchestrator(String type1, String type2, int sizeX, int sizeY, int nbPiece, boolean gui) {

        this.gui = gui;
        this.endPress = false;
        this.nbMaxTurn = nbPiece*4;
        ArrayList<ArrayList<PuzzlePiece>> puzzleLists = createPieceEns(nbPiece);
        PuzzleGrid puzzleGrid1 = new PuzzleGrid(sizeX, sizeY, gui);
        PuzzleGrid puzzleGrid2 = new PuzzleGrid(sizeX, sizeY, gui);

        this.listPlayer = new ArrayList<>();
        this.listPlayer.add(createPlayer(type1, puzzleLists.get(0), puzzleGrid1));
        this.listPlayer.add(createPlayer(type2, puzzleLists.get(1), puzzleGrid2));

        this.currentPlayer = this.listPlayer.get(0);
        this.status = -1;


    }

    /**
     * Constructeur d'un orchestrateur de partie.
     * @param type1 type du joueur 1
     * @param type2 type du joueur 2
     * @param sizeX hauteur de la grille 
     * @param sizeY largeur de la grille 
     * @param nbPiece nombre de pieces
     */
    public Orchestrator(String type1, String type2, int sizeX, int sizeY, int nbPiece) {

        this(type1, type2, sizeX, sizeY, nbPiece, false);
    }

    /**
     * Constructeur d'un orchestrateur de partie.
     * @param type1 type du joueur 1
     * @param type2 type du joueur 2
     */
    public Orchestrator(String type1, String type2) {

        this(type1, type2, 20, 20, 5, false);
    }

    /**
     * Accesseur du player courrant
     * @return player
     */
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }

    /**
     * Accesseur du player 1
     * @return player
     */
    public Player getPlayer1(){
        return this.listPlayer.get(0);
    }

    /**
     * Accesseur du second Player
     * @return player
     */
    public Player getPlayer2(){
        return this.listPlayer.get(1);
    }

    /**
     * Setter de enPress
     */
    public void setEnd() {
        this.endPress = true;
    }

    /**
     * Setter du statu
     */
    public void setStatus(){
        this.status = 1;
    }

    /**
     * Cette methode permet de creer un ensemble de n pieces de puzzle.
     * @param nbPiece nombre de pieces de puzzle
     * @return liste des pieces de puzzle
     */
    public ArrayList<ArrayList<PuzzlePiece>> createPieceEns(int nbPiece) {

        ArrayList<ArrayList<PuzzlePiece>> res = new ArrayList<>();
    //     ArrayList<PuzzlePiece> l1 = new ArrayList<>();
    //     ArrayList<PuzzlePiece> l2 = new ArrayList<>();
    //     Random rand = new Random();

    //     for(int i = 0; i<nbPiece; i++){
    //         int randomChoice = rand.nextInt(5);
    //         switch(randomChoice) {
    //             case 0:
    //                 l1.add(new PuzzlePieceCircle(5, 3));
    //                 l2.add(new PuzzlePieceCircle(5, 3));
    //                 break;

    //             case 1:
    //                 l1.add(new PuzzlePieceL(5, 3));
    //                 l2.add(new PuzzlePieceL(5, 3));
    //                 break;

    //             case 2:
    //                 l1.add(new PuzzlePieceSquare(3, 1));
    //                 l2.add(new PuzzlePieceSquare(3,1));
    //                 break;

    //             case 3:
    //                 l1.add(new FormeCarre(3, 5));
    //                 l2.add(new FormeCarre(3, 5));
    //                 break;

    //             case 4:
    //                 l1.add(new PuzzlePieceU(3, 3));
    //                 l2.add( new PuzzlePieceU(3, 3));
    //                 break;
                    
    //             default:
    //                 System.out.println("Erreur de truc");
    //         }
    //     }
    //     res.add(l1);
    //     res.add(l2);
        return res;
    }

    /**
     * Cette methode permet de creer les differents type de Player.
     * @param type type de player
     * @param list liste des pieces de puzzle du player
     * @param puzzleGrid grilel du player
     * @return player cree
     */
    public Player createPlayer(String type, ArrayList<PuzzlePiece> list, PuzzleGrid puzzleGrid) {
        
        switch(type) {
            case "human":
                return new PlayerHuman(list, puzzleGrid, this);
            case "ia":
                return new PlayerIA(list, puzzleGrid, this, false);
            case "iar":
                return new PlayerIA(list, puzzleGrid, this, true);
            default:
                return null;
        }
    }

  

    /**
     * Execution d'un tour en mode gui.
     */
    public void guiMode() {

        while(isOver() == false) {
            
            for(Player p : this.listPlayer) {
                this.currentPlayer = p;
                fireChangement(new Data("changePiece", null, null,null, p,null,null));
                fireChangement(new Data("nbPieces", null, null, null, p, null, null));
                if(this.currentPlayer instanceof PlayerHuman){
                    while(this.status == -1){
                        try {
                            TimeUnit.MICROSECONDS.sleep(1);
                            
                        } catch (InterruptedException e) {
                            
                            e.printStackTrace();
                        }
                    }
                }
                else{
                    p.guiTurn();
                }
                if(this.endPress){
                    break;
                }
                fireChangement(new Data("InterChangements Player", null, null, null, currentPlayer, null, null));
                this.status = -1;
                
            }

            this.nbTurn += 1;
        }
        
        fireChangement(new Data("fin", null, null, null, null, null, this.getWinners()));
    }

    /**
     * Execution d'un tour en mode console.
     */
    public void consolMode() {

        System.out.println("Début de la partie");
        while(isOver() == false) {
            for(Player p : this.listPlayer) {
                this.currentPlayer = p;
                p.consolTurn();
                p.afficheState();
            }
            this.nbTurn += 1;
        }
        System.out.println(getWinners());
    }
    
    /**
     * isOver() permet de savoir si une partie est finie ou non.
     * @return true si finie false sinon
     */
    public boolean isOver() {

       return endPress || (this.nbTurn > this.nbMaxTurn || this.currentPlayer.getPuzzleList().isEmpty());
    }

    /**
     * Cette methode retourne l'etat des gagnant en fin de partie.
     * @return string des gagnants
     */
    public String getWinners() {

        Map<Player, Integer> calcRes = new HashMap<>();
        ArrayList<Player> winner = new ArrayList<>();
        

        for (Player player : this.listPlayer) {
            calcRes.put(player, player.getPuzzleGrid().minArea());
        }
        
        Integer valMin = Collections.min(calcRes.values());

        for(Map.Entry<Player, Integer> entry : calcRes.entrySet()) {
            if(valMin.equals(entry.getValue())) {
                winner.add(entry.getKey());
            }
        }
        if(winner.size()>1){
            return "Égalité entre plusieurs joueurs avec comme score "+valMin;
        }
        else{
            return "Le gagnant est "+winner.get(0).toString()+" avec comme score "+valMin;
        }
    }

    @Override
    public void run() {
        if(this.gui) {
            guiMode();
        }
        else {
            consolMode();
        }
    }
}
