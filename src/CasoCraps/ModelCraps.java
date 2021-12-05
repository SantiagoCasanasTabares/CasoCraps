package CasoCraps;

/**
 * ModelCraps apply craps rules
 * estado 1 natural winner
 * estado 2 craps looser
 * estado 3 Stablish punto
 * estado 4 Punto winner
 * estado 5 Punto looser
 * @author Santiago Casnas Tabares 202025301
 * @version V.1.0.0 date 05/12/2021
 */
public class ModelCraps {
    //Attributes
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String estadoToString;
    private int[] caras; //caras es un arreglo de enteros

    /**
     * Class constructor
     */
    public ModelCraps() {
        dado1 = new Dado();
        dado2 = new Dado();
        caras =  new int[2];
        flag = 0;

    }

    /**
     * Establish the tiro value according to each dice
     */
    public void  calcularTiro() {
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0]+caras[1];

    }

    /**
     * Establish game state according to the game rules
     * estado 1 natural winner
     * estado 2 craps looser
     * estado 3 Stablish punto
     */
    public void determinarJuego() {
        if(flag==0){
            if(tiro==7 || tiro==11) {
                estado = 1;
            }else{
                if(tiro==3 || tiro==2 || tiro==12) {
                    estado = 2;
                }else{
                    estado = 3;
                    punto = tiro;
                    flag = 1;

                }
            }
        }else{
            //ronda punto
            rondaPunto();

        }

    }

    /**
     * Establish Game state according to estado attribute value
     * estado 4 Punto winner
     * estado 5 Punto looser
     */
    private void rondaPunto() {
        if(tiro == punto) {
            estado = 4;
            flag = 0;
        }
        if(tiro == 7) {
            estado = 5;
            flag = 0;
        }
    }

    public int getTiro() {
        return tiro;
    }

    public int getPunto() {
        return punto;
    }

    /**
     * Establish message game state according to estado attribute value
     * @return Message for the view class
     */
    public String getEstadoToString() {
        switch (estado) {
            case 1: estadoToString = "Sacaste natural. ¡¡Has ganado!!";
                    break;
            case 2: estadoToString = "Sacaste craps, has perdido :c";
                break;
            case 3: estadoToString = "Estableciste punto en "+punto+ ", ¡¡continua lanzando!!"
                                     +"\n Pero si sacas 7 antes que "+punto+" perderás.";
                break;
            case 4: estadoToString = "Volviste a sacar "+punto+". ¡¡Has ganado!!";
                break;
            case 5: estadoToString = "Sacaste 7 antes que "+punto+", has perdido :c";
                break;


        }
        return estadoToString;
    }

    public int[] getCaras() {
        return caras;
    }
}
