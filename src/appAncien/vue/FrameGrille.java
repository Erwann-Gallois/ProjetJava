package appAncien.vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import appAncien.model.*;
import appAncien.model.player.Player;
import util.EcouteurModele;

public class FrameGrille extends JPanel implements EcouteurModele {
    private Player player;
    private Orchestrator orch;
    private ArrayList<ButtonPanel> listPanel; //Liste de l'ensemble des panels représentants les cases de la grid 
    private PuzzleGrid grid;

    /**
     * La classe VueGrille permet de créer un objet permettant de voir en direct l'évolution d'un grid de bataille navale.
     * 
     * @param player Objet Player représentant le joueur qui possède cette grid de jeu.
     * @param orch Objet Orchestrator représentant la partie en cours
     */
    public FrameGrille(Player player, Orchestrator orch){

        super();
        this.player = player;
        this.orch = orch;
        this.listPanel = new ArrayList<>();
        this.grid = this.player.getPuzzleGrid();

        //Abonnement aux objets Orchestrator et player
        orch.ajoutEcouteur(this);
        this.grid.ajoutEcouteur(this);

        //Organisation du JPanel
        
        setLayout(new GridLayout(this.grid.getWidth()+1,this.grid.getLength()+1,2,2));
        setVisible(true);
        setBackground(Color.black);
        

        //Création de la grid
        
        for(int i = 0; i <= this.grid.getWidth(); i++){
            for(int j = 0; j <= this.grid.getLength(); j++){
                if(i==0){
                    //Partie des chiffres
                    JPanel pan = new JPanel();
                    if(j==0){
                        JLabel lab = new JLabel("X");
                        pan.add(lab);
                    }
                    else{
                        JLabel lab = new JLabel(Integer.toString(j-1));
                        pan.add(lab);
                    }
                    add(pan);
                }
                else{
                    if(j==0){
                        //Partie des lettres
                        JPanel pan = new JPanel();
                        JLabel lab = new JLabel(Integer.toString(i-1));
                        pan.add(lab);
                        add(pan);
                    }
                    else{
                        //Partie des cases de la grid jouable
                        ButtonPanel but = new ButtonPanel(this.orch, i-1, j-1, this.player);
                        but.setBackground(Color.DARK_GRAY);
                        add(but);
                        this.listPanel.add(but);
                        
                    }              
                }
                                
            }
        }

       
    }

    /**
     * Accesseur au player possedant la grille.
     * @return Player
     */
    public Player getPlayer(){
        return this.player;
    }


    @Override
    public void modeleMisAJour(Object m, Object c) {
       if(c instanceof Data){
            Data data = (Data) c;
            if(data.getGrid() == this.grid){
                //Pour modifier une seule grid au lieu des deux en même temps
                if(data.getSituation().equals("Modif")){
                    
                    if(this.player.equals(data.getPlayer())){
                        this.listPanel.get(this.player.getPuzzleGrid().getLength()*data.getX()+data.getY()).setBackground(data.getColor());
                    }
                    
                }
                
            }
           
        }

    }
}
