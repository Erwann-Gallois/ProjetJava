package app.vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import app.model.Data;
import app.model.Orchestrator;
import app.model.pieces.PuzzlePiece;
import util.EcouteurModele;

public class FrameGame extends JFrame implements EcouteurModele, ActionListener {

    private JPanel currentGrille, otherGrille;
    private JLabel nbRestant;
    private JTextArea p1, p2;
    private JButton toLeft, toRight, toEnd, toExit;
    private FrameGrille vuePlacement1, vuePlacement2;
    private Orchestrator orch;
    private Thread gameThread;
    
    /**
     * Constructeur d'une page représentant une session de jeu
     * @param orch Orchestrator
     */
    public FrameGame(Orchestrator orch){

        // Transformation en JFrame
        super("Partie");
        orch.ajoutEcouteur(this);
        orch.getPlayer1().getPuzzleGrid().ajoutEcouteur(this);
        orch.getPlayer2().getPuzzleGrid().ajoutEcouteur(this);

        this.currentGrille = new JPanel();
        this.otherGrille = new JPanel();

        this.nbRestant = new JLabel();

        this.p1 = new JTextArea();
        this.p2 = new JTextArea();
        
        

        this.orch = orch;

        
        
        //Création des Bouttons
        this.toEnd = new JButton("Fin de partie");
        this.toExit = new JButton("Retour Menu");


        //Ajout de l'action aux bouttons
        this.toEnd.addActionListener(this);
        this.toExit.addActionListener(this);
        this.toEnd.setPreferredSize(new Dimension(100,50));
        this.toExit.setPreferredSize(new Dimension(100,50));

        

        //Caractéristiques de la Frame
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920,1080);
        setResizable(true);
        setVisible(true);

        
        //Création de la vue sur la grille du joueur Humain.
        this.vuePlacement1 = new FrameGrille(orch.getPlayer1(), orch);
        this.vuePlacement2 = new FrameGrille(orch.getPlayer2(), orch);

        
        //PARTIE CREATION DE LA VUE SUR LA PARTIE 
        
        JPanel joueur1 = new JPanel();
        joueur1.setLayout(new BorderLayout());
        joueur1.add(new JLabel("Joueur 1", SwingConstants.CENTER),BorderLayout.NORTH);
        joueur1.add(vuePlacement1, BorderLayout.CENTER);

        JPanel joueur2 = new JPanel();
        joueur2.setLayout(new BorderLayout());
        joueur2.add(new JLabel("Joueur 2", SwingConstants.CENTER), BorderLayout.NORTH);
        joueur2.add(vuePlacement2, BorderLayout.CENTER);

        this.currentGrille = joueur1;
        this.otherGrille = joueur2;


        if(this.orch.getPlayer1().getPuzzleList().size()>2){
            this.p1.setText(this.orch.getPlayer1().getPuzzleList().get(0).toString());
            this.p2.setText(this.orch.getPlayer1().getPuzzleList().get(1).toString());
        }
        else if(this.orch.getPlayer1().getPuzzleList().size() == 1){
            this.p1.setText(this.orch.getPlayer1().getPuzzleList().get(0).toString());
        }

        //PARTIE DU PANEL DES DEUX PIECES DE PUZZLE
        JPanel actualPuzzlePiece = new JPanel();
        actualPuzzlePiece.setLayout(new BorderLayout());
        actualPuzzlePiece.add(new JLabel("Piece actuelle", SwingConstants.CENTER), BorderLayout.NORTH);
        actualPuzzlePiece.add(this.p1, BorderLayout.CENTER);

        JPanel nextPuzzlePiece = new JPanel();
        nextPuzzlePiece.setLayout(new BorderLayout());
        nextPuzzlePiece.add(new JLabel("Piece suivante", SwingConstants.CENTER), BorderLayout.NORTH);
        nextPuzzlePiece.add(this.p2, BorderLayout.CENTER);
        
        JPanel piecesPanel = new JPanel();
        piecesPanel.setLayout(new GridLayout(0,2));
        piecesPanel.add(actualPuzzlePiece);
        piecesPanel.add(nextPuzzlePiece);
        //FIN 


        //PARTIE DES BOUTTONS ET DATA
        
        this.nbRestant.setText("Nombre de pièces restant : "+orch.getPlayer1().getPuzzleList().size());
        


        //Panneau contenant les bouttons de panneau Info
        JPanel buttonPannel = new JPanel();
        buttonPannel.setLayout(new GridLayout(2,2,20,20));
        buttonPannel.add(this.nbRestant);
        buttonPannel.add(this.toExit);
        buttonPannel.add(this.toEnd);



        //Panneau des infos
        JPanel paneRight = new JPanel();
        paneRight.setLayout(new GridLayout(3,0));
        paneRight.add(this.otherGrille);
        paneRight.add(piecesPanel);
        paneRight.add(buttonPannel);

        
        JSplitPane jsPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.currentGrille, paneRight);
        jsPane.setResizeWeight(0.7);
        jsPane.setEnabled(true);

                
        getContentPane().add(this.currentGrille, BorderLayout.CENTER);
        getContentPane().add(paneRight, BorderLayout.EAST);

        //Début de la partie
        this.gameThread = new Thread(this.orch);
        this.gameThread.setDaemon(true);
        

        //PopUp
        JOptionPane.showMessageDialog(this, "Voulez vous commencer la partie ?", "Start Game", JOptionPane.QUESTION_MESSAGE);
        this.gameThread.start();
        
        
    }

    @Override
    public void modeleMisAJour(Object m, Object c) {
        if(m instanceof Orchestrator){
            if(c instanceof Data){
                Data d = (Data) c;
                if(d.getSituation().equals("InterChangements Player")){
                    if(d.getPlayer().equals(this.vuePlacement1.getPlayer())){
                        this.currentGrille.add(this.vuePlacement2, BorderLayout.CENTER);
                        this.otherGrille.add(this.vuePlacement1, BorderLayout.CENTER); 
                    }
                    else{
                        //Interchangement des grilles
                        this.currentGrille.add(this.vuePlacement1, BorderLayout.CENTER);
                        this.otherGrille.add(this.vuePlacement2, BorderLayout.CENTER); 
                    }
                    
                    
                    SwingUtilities.updateComponentTreeUI(this);
                }
                else if(d.getSituation().equals("nbPiece")){
                    //affichage du nombre de pièces restantes
                    this.nbRestant.setText(("Nombre de pièces restant : "+d.getPlayer().getPuzzleList().size()));
                    SwingUtilities.updateComponentTreeUI(this);
                }
                else if(d.getSituation().equals("fin")){
                    //Affichage de la frame de fin
                    JOptionPane.showMessageDialog(this, d.getStatus(), "Game End", JOptionPane.QUESTION_MESSAGE);
                }
                else if(d.getSituation().equals("changePiece")){
                    //Changement des pièces
                    ArrayList<PuzzlePiece> puzzleList = d.getPlayer().getPuzzleList();
                    this.p1.setText("");
                    this.p2.setText("");
                    if(puzzleList.size()>0){
                        if(puzzleList.size() ==1){
                            this.p1.setText(puzzleList.get(0).toString());
                        }
                        else{
                            this.p1.setText(puzzleList.get(0).toString());
                            this.p2.setText(puzzleList.get(1).toString());
                        }
                    }
                }
                
            }
        }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object button = e.getSource();

        //Action pour modifier l'orientation d'un bateau 
        if(button == this.toLeft){
            
            if(this.orch.getCurrentPlayer().getPuzzleList().size()>0){
                this.orch.getCurrentPlayer().getPuzzleList().get(0).rotate(-1);
                this.p1.setText(this.orch.getCurrentPlayer().getPuzzleList().get(0).toString());
            }
        }
        else if(button == this.toRight){
            
            if(this.orch.getCurrentPlayer().getPuzzleList().size()>0){
                this.orch.getCurrentPlayer().getPuzzleList().get(0).rotate(1);
                this.p1.setText(this.orch.getCurrentPlayer().getPuzzleList().get(0).toString());
            }
            
        }
        else if(button == this.toEnd){
            this.orch.setEnd();
            this.orch.setStatus();
            
        }
        else if(button == this.toExit){
            JFrame fen = new FrameMenu();
            fen.setTitle("Menu Page");
            fen.setSize(800, 800);
            fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            fen.setVisible(true);
            setVisible(false);
            dispose(); 
        }
    }
}
