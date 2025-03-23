package app.model.formes;

public class FormeCarre extends AbstractForme{

    /**
     * Constructeur de la forme carré et rectangle.
     * @param width hauteur
     * @param length largeur
     */
    public FormeCarre(int width, int length){

        super("(Carre,"+width+","+length);

        
        // if(length <= 2 && width <= 2 ){
        //     throw new ExceptionInInitializerError("Impossible de créer la forme avec ces paramètres (Valeurs trop petites)");
        // }

        Boolean[][] formeCarre = new Boolean[width][length];

        for(int i=0;i<width;i++){
            for(int j=0 ; j<length;j++){
                if(i==0 || i == width-1){
                    formeCarre[i][j]= true;
                }else if(j==0 || j==length-1){
                    formeCarre[i][j]= true;
                }else{
                    formeCarre[i][j]= false;
                }
            }
        } 
       
        AbstractForme.ensConfig.put("(Carre,"+width+","+length+")", formeCarre);

    }
}
