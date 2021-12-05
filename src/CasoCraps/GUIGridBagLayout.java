/*package CasoCraps;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIGridBagLayout extends JFrame {
    private static final String MENSAJE_INICIO = "Bienvenido a craps \n"
            + "Oprime el botón lanzar para iniciar el juego"
            + "\nSi tu tiro de salida es 7 u 11 ganas con Natural"
            + "\nSi tu tiro de salida es 2, 3 u 12 pierdes con Craps"
            + "\nSi sacas cualquier otro valor establecerás el Punto"
            + "\nEstado punto podrás seguir lanzando los dados"
            + "\nGanarás si sacas nuevamente el valor de punto"
            + "\nperderás si sacas 7 antes de esto";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalida, resultadosJuego;
    private Escucha escucha;
    private ModelCraps modelCraps;


    public void GUI() {
        initGUI();

        //Default JFrame configuration
        this.setTitle("The Title app");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void initGUI () {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa de juego craps", Color.BLACK);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=0;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout

        }

    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            modelCraps.calcularTiro();
            int[] caras = modelCraps.getCaras();
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imageDado);
            imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imageDado);
            modelCraps.determinarJuego();
            resultadosDados.setT

        }
    }

    }*/





