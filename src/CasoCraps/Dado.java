package CasoCraps;

import java.util.Random;

/**
 * Class Dado generate a random value between 1 and 6
 * @author Santiago Casanas tabares 202025301
 * @version V.1.0.0 date 5/12/2021
 */

public class Dado {
    //attributes
    private int cara;

    /**
     *Method that generate a random value to cara
     * @return number between (1 ,6)
     */
    public int getCara() {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }

}
