package app.model.formes;

public class FormeCercle extends AbstractForme {

    /**
     * Constructeur de la forme cercle.
     * @param width hauteur
     * @param length largeur
     */
    public FormeCercle(int width, int length){

        super("(Cercle,"+width+","+length);
        if(length%2==0 || width%2==0){
           throw new ExceptionInInitializerError("Impossible de créer la forme avec ces paramètres (Valeurs trop petites)");
        }

        Boolean[][] formeCercle = new Boolean[width][length];

        for(int i=0;i<width;i++){
            for(int j=0 ; j<length;j++){
                if(i==0 || i == width-1){
                    formeCercle[i][j]= true;
                }else if( j==0 || j==length-1){
                    formeCercle[i][j] = true;
                }else{
                    formeCercle[i][j]= false;
                }
            }
        } 
       
        AbstractForme.ensConfig.put("(Cercle,"+width+","+length+")", formeCercle);

    }
}
