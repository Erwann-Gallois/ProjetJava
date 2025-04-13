package app.view;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * La classe Background permet de créer un JPanel avec une image de fond.
 * Elle hérite de JPanel et redéfinit la méthode paintComponent pour dessiner l'image.
 */
public class Background extends JPanel{

    //Type de contenu
    private String type;
    //Image finale
    private Image img;

    /** 
     * La classe background va créer un JPanel avec l'image passée en paramètre.
     * @param type String type d'image
     * @param path String path de l'image
     */
    public Background(String type, String path){
        super();// Initialisation du JPanel
        this.type = type;

        if(this.type.equals("image")){
            try {
                this.img = ImageIO.read(new File(path));
            } catch (IOException e) {
                System.out.println("jsuis la");
            }
        }
        else if(this.type.equals("gif")){
            this.img =  Toolkit.getDefaultToolkit().createImage(path);
        }
        
       
    }

    /**
     * Permet de dessiner l'image 
     * @param g La fenetre ou dessiner l'image
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage( this.img, 0, 0, getWidth(), getHeight(), this); //déssine l'image sur le jpanel
        
    }
}