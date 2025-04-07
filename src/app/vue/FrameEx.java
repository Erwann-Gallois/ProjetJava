package app.vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameEx extends JFrame{


    /**
     * Constructeur de la page présentant les regles.
     */
    public FrameEx(){
        JPanel panel = new JPanel();
        panel.setBounds(50,50,500,750);
        panel.setBackground(Color.gray);
        panel.setSize(300,300);  

        // bouton de retour
        JButton btnQuit = new JButton("Retour");
        
        btnQuit.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent e){
          JFrame fen = new FrameMenu();
          fen.setTitle("Menu Page");
          fen.setSize(800, 800);
          fen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
          fen.setVisible(true);
          setVisible(false);
          dispose();
        }});
        //zone de texte non-modifiable pour les explications
        JTextArea area=new JTextArea("Cette application est un jeu de mémorisation de formes.\r\n" + "\n" +
            "Le but est de mémoriser et redessiner les formes qui apparaitront à l'écran. \r\n" + "\n"+
            "Cette application est : \r\n"+
                "- Jouable avec différentes formes ( dont Rectangle, Cercle, triangle ) \r\n" +
                "- Jouable en un ou deux joueurs \r\n"+ 
                "- générer avec une frame prédéfinie de formes"+"\n" +
                "On peut utiliser des bouttons pour selectionner la forme à dessiner. ");  
        area.setBounds(125,150, 750,150);
        area.setEditable(false);
        area.setLineWrap(true);

        panel.add(area);
        panel.add(btnQuit);
    
        add(panel);
        setVisible(true);
        
    }
}
