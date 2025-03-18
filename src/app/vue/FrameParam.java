package app.vue;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.model.Orchestrator;
import util.JTextFieldWithOnlyNumbers;

import java.awt.*;
import java.awt.event.*;

public class FrameParam extends JFrame implements ActionListener{

    //Field .
    JTextFieldWithOnlyNumbers height, length, nbPiece;
        
    //Checkbox .
    JCheckBox jcj, jcIAr, jcIA;

    //Bouttons pour envoyer les paramètres ou retour au menu.
    JButton send, retour;

    
    Thread optiThread;

    /**
     * Constructeur d'un objet pour créer la page de paramètres de création.
     */
    public FrameParam(){
        
        super("Page param");

        //Création des JButtons
        this.send = new JButton("Envoyer");
        this.retour = new JButton("Retour menu");

        //Ajout des actions.
        this.send.addActionListener(this);
        this.retour.addActionListener(this);




        //---------------  PARTIE PANEL DES PARAMETRES

        //Création des JTextFieldWithOnlyNumbers avec en paramètre le String du jlabel pour les paramètres du jeu
        this.height = new JTextFieldWithOnlyNumbers("Hauteur de la grille :");
        this.length = new JTextFieldWithOnlyNumbers("Largeur de la grille : ");
        this.nbPiece = new JTextFieldWithOnlyNumbers("Nombre de pièces : ");
        
        
        //Création du Panel qui va contenir le choix du type de jeu
        JPanel optiType = new JPanel();
        JLabel optiTypeName = new JLabel("Choix du type de jeu : ");
        
        //Création des CheckBox 
        this.jcIAr = new JCheckBox("jcIA random");
        this.jcIA = new JCheckBox("JcIA"); 
        this.jcj = new JCheckBox("JcJ");

        //Ajout des actions aux CheckBox
        this.jcIAr.addActionListener(this);
        this.jcIA.addActionListener(this);
        this.jcj.addActionListener(this);

        //Organisation du Panel des CheckBox
        optiType.setLayout(new GridLayout(0,1));
        optiType.add(optiTypeName);
        optiType.add(jcIAr);
        optiType.add(jcIA);
        optiType.add(jcj);


        //Panel principal des paramètres
        JPanel paramEntree = new JPanel();
        paramEntree.setLayout(new BoxLayout(paramEntree, BoxLayout.Y_AXIS));
        paramEntree.setBorder(new EmptyBorder(new Insets(50,20,20,20)));
        paramEntree.add(this.height);
        paramEntree.add(this.length);
        paramEntree.add(this.nbPiece);
       
        paramEntree.add(optiType);
        paramEntree.add(this.send);


        //------------ FIN PARTIE PANEL PARAMETRES

        

    
        //------------ PARTIE ORGA DE LA PAGE

        
        add(paramEntree, BorderLayout.CENTER);
        
        
    }



    @Override
    public void actionPerformed(ActionEvent a){
        Object objectSelec = a.getSource();

        //Bloc permettant d'avoir une seule JCheckBox de valide.
        if(objectSelec == this.jcj){
            if (this.jcj.isSelected() == true){
                this.jcIAr.setSelected(false);
                this.jcIA.setSelected(false);
            }
        }
        else if(objectSelec == this.jcIAr){
            if (this.jcIAr.isSelected() == true){
                this.jcj.setSelected(false);
                this.jcIA.setSelected(false);
            }
        }
        else if(objectSelec == this.jcIA){
            if(this.jcIA.isSelected()){
                this.jcj.setSelected(false);
                this.jcIAr.setSelected(false);
            }
        }

        
        //Bloc pour l'envoi des paramètres et la création de la partie
        else if(objectSelec == this.send){

            Boolean checkIfComplete = true; // Booléen pour savoir si tous les champs sont remplis

            if(this.height.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            if(this.length.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            if(this.nbPiece.getTextField().getText().equals("")){
                checkIfComplete = false;
            }
            
            Integer res = null;

            if(this.jcj.isSelected()){
                res = 0;
            }
            else if(this.jcIAr.isSelected()){
                res = 1;
            }
            else if(this.jcIA.isSelected()){
                res = 2;
            }
            else{
                checkIfComplete = false;
            }
            
            //Tout est complet l'algo est créé 
            if(checkIfComplete){
                //CREER ORCHESTRATOR
                Orchestrator orch = null;
                if(res == 0){
                    
                    orch = new Orchestrator("human", "human", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);
                    
                }
                else if(res == 1){
                    
                    orch = new Orchestrator("human", "iar", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);
                }
                else if(res == 2){

                    orch = new Orchestrator("human", "ia", Integer.parseInt(this.height.getTextField().getText()), Integer.parseInt(this.length.getTextField().getText()), Integer.parseInt(this.nbPiece.getTextField().getText()), true);

                }

                if(orch != null){
                    JFrame fen = new FrameGame(orch);
                    fen.setTitle("Tetris");
                    fen.setSize(1920, 1080);
                    fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    fen.setVisible(true);
                    //On ferme cette fenetre derière
                    dispose();
                }
                
            }
            // Tout n'est pas bon. Création d'une frame erreur.
            else{
                JFrame error = new JFrame();
                JLabel message = new JLabel("Veuillez rentrer tous les champs");
               
                error.add(new Background("gif", "images/gyro.gif"),BorderLayout.CENTER);
                error.add(message, BorderLayout.SOUTH);
                error.setSize(200,200);
                error.setVisible(true);
                error.setLocationRelativeTo(null);
                error.setResizable(false);
            }
        }
        
        
       
    }
}
