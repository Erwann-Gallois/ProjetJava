package appAncien.vue;

import java.awt.event.MouseListener;
import javax.swing.JPanel;

import appAncien.model.Orchestrator;
import appAncien.model.pieces.PuzzlePiece;
import appAncien.model.player.Player;
import appAncien.model.player.PlayerHuman;




public class ButtonPanel extends JPanel implements MouseListener {

    private Orchestrator orchestrator;
    private int x;
    private int y;
    private Player player;

    /**
     * La classe ButtonPanel est une classe qui sert de boutton et de moyen d'affichage du statut de la case que l'objet représente. 
     * @param orchestrator Objet orchestrator correspondant à la partie en cours 
     * @param x entier axe x
     * @param y entier axe y
     * @param player Objet Player représentant le joueur qui possède la case
     */
    public ButtonPanel(Orchestrator orchestrator, int x, int y, Player player){
        super();
        
        this.orchestrator = orchestrator;
        this.x = x;
        this.y = y;
        this.player = player;
       
        this.addMouseListener(this);
        
    }



    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
        //Partie pour viser une case
        if(this.player instanceof PlayerHuman){
            if(this.orchestrator.getCurrentPlayer().equals(this.player)){
                if(this.player.getPuzzleList().size()>0){
                    PuzzlePiece p = this.player.getPuzzleList().get((0));
                    if(this.player.getPuzzleGrid().isValid(p, x, y)){

                        this.player.getPuzzleGrid().placePiece(p, x, y);
                        this.player.getPuzzleList().remove(p);
                        this.orchestrator.setStatus();

                    }
                }
                
            }
        }  
    }

    //Obligation de mettre ces fonctions vu que nous utilisons MouseListener
    //Impossibilité d'utiliser MouseAdpater vu que nous utilisons JPanel en classe abstraite
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
       
    }
}
