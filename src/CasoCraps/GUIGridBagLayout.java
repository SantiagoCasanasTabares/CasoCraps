package CasoCraps;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GUIGridBagLayout extends JFrame {
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
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados, panelResultados;
    private ImageIcon imageDado;
    private JTextArea mensajeSalidas, resultadosDados;
    private Escucha escucha;
    private ModelCraps modelCraps;
    private JFrame movimientoVentana;




    public GUIGridBagLayout(){
        initGUI();

        this.setTitle("Juego craps");
        this.setUndecorated(true);
        this.setBackground(new Color(255,255,255,0));
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
        movimientoVentana=this;


        //Create Listener Object or Control Object
        escucha = new Escucha();
        modelCraps = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Mesa de juego craps", Color.BLACK);
        headerProject.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerProject.addMouseListener(escucha);
        headerProject.addMouseMotionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=0;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject, constraints); //Change this line if you change JFrame Container's Layout

        //boton de ayuda
        ayuda = new JButton("ayuda");
        ayuda.setCursor(new Cursor(Cursor.HAND_CURSOR));
        ayuda.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        add(ayuda, constraints);

        //boton de salir
        salir = new JButton("salir");
        salir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        salir.addActionListener(escucha);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        add(salir, constraints);


        //dados
        imageDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imageDado);
        dado2 = new JLabel(imageDado);


        //Panel de los dados

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300, 180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus dados"));

        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(panelDados, constraints);



        //panel de los resultados
        resultadosDados = new JTextArea(4, 31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Debes lanzar los dados");
        resultadosDados.setBackground(null);
        resultadosDados.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        add(resultadosDados, constraints);

        //boton de lanzar
        lanzar = new JButton("lanzar");
        lanzar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lanzar.addActionListener(escucha);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(lanzar, constraints);

        //mensajes de juego
        mensajeSalidas = new JTextArea(4, 31);
        mensajeSalidas.setText("Usa el botón (?) para ver las reglas del juego");
        mensajeSalidas.setBorder(BorderFactory.createTitledBorder("Mensajes"));
        mensajeSalidas.setBackground(null);
        mensajeSalidas.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        add(mensajeSalidas, constraints);



    }


    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }

    private class Escucha extends MouseAdapter implements ActionListener  {

        private int x, y;


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==lanzar){
                modelCraps.calcularTiro();
                int[] caras = modelCraps.getCaras();
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
                dado1.setIcon(imageDado);
                imageDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
                dado2.setIcon(imageDado);
                modelCraps.determinarJuego();
                resultadosDados.setText(modelCraps.getEstadoToString()[0]);
                mensajeSalidas.setText(modelCraps.getEstadoToString()[1]);
            }else{
                if(e.getSource()==ayuda){
                    JOptionPane.showMessageDialog(null, MENSAJE_INICIO);
                }else{
                    System.exit(0);
                }
            }


        }

        public void mousePressed(MouseEvent e) {
            x = e.getX();
            y = e.getY();

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            movimientoVentana.setLocation(movimientoVentana.getLocation().x + e.getX() - x, movimientoVentana.getLocation().y + e.getY() - y);


        }
    }

}





