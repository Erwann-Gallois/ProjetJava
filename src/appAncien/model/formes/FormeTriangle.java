package appAncien.model.formes;

import java.io.Serializable;

public class FormeTriangle extends AbstractForme implements Serializable{
    /**
     * Constructeur de la forme Triangle
     * @param width hauteur
     * @param length largeur
     */
    public FormeTriangle(int width, int length){

        super("(Triangle,"+width+","+length);

        
        // if(length <= 2 && width <= 2 ){
        //     throw new ExceptionInInitializerError("Impossible de créer la forme avec ces paramètres (Valeurs trop petites)");
        // }

        Boolean[][] formeTriangle = new Boolean[width][length];

        for(int i=0;i<width;i++){
            for(int j=0 ; j<length;j++){
                if(i==0 || i == width-1){
                    formeTriangle[i][j]= true;
                }else if(j==0 || j==length-1){
                    formeTriangle[i][j]= true;
                }else{
                    formeTriangle[i][j]= false;
                }
            }
        } 
       
        AbstractForme.ensConfig.put("(Triangle,"+width+","+length+")", formeTriangle);

    }
}
