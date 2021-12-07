package CasoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used for ...
 * @autor Santiago Casanas Tabares 202025301-
 * @version v.1.0.0 date:05/12/2021
 */
public class GUI extends JFrame {
    private static final String MENSAJE_INICIO = "Bienvenido a craps \n"
            + "Oprime el botón lanzar para iniciar el juego"
            + "\n-Si tu tiro de salida es 7 u 11 ganas con Natural."
            + "\n-Si tu tiro de salida es 2, 3 u 12 pierdes con Craps."
            + "\n-Si sacas cualquier otro valor establecerás el Punto."
            + "\nEstado punto podrás seguir lanzando los dados,"
            + "\nGanarás si sacas nuevamente el valor de punto,"
            + "\nperderás si sacas 7 antes de esto.";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalidas, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JSeparator separator;

    /**
     * Constructor of GUI class
     */
    public GUI(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Juego craps");
        //this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa de juego craps", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        //dados
        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);

        //Botones
        lanzar = new JButton("lanzar");
        lanzar.addActionListener(escucha);

        //Panel de los dados
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        //area de resultados
        mensajeSalidas = new JTextArea(7, 31);
        mensajeSalidas.setText(MENSAJE_INICIO);
       // mensajeSalidas.setBorder(BorderFactory.createTitledBorder("Instrucciones de juego"));
        JScrollPane scroll = new JScrollPane(mensajeSalidas);

        //panel de resultados de los dados
        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("Instrucciones de juego"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370, 180));


        this.add(panelResultados, BorderLayout.EAST);

        resultadosDados = new JTextArea(4, 31);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(320, 7));
        separator.setBackground(Color.BLUE);


    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
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

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            panelResultados.add(resultadosDados);
            panelResultados.add(separator);
            panelResultados.add(mensajeSalidas);
            resultadosDados.setText(modelCraps.getEstadoToString()[0]);
            mensajeSalidas.setRows(4);
            mensajeSalidas.setText(modelCraps.getEstadoToString()[1]);

            revalidate();
            repaint();

        }
    }
}
