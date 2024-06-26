package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.EventQueue;

public class MenuBienven {

	private JFrame frame;
	
	public JFrame menuPP;
	private JTextField textFieldIngresarArista;
	private JButton btnEnviar;
	private JLabel lblFondo;
	RegArg vistaMapa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuBienven window = new MenuBienven();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuBienven() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		menuPP = new JFrame();
		menuPP.setBounds(600, 600, 668, 571);
		menuPP.setLocationRelativeTo(null);
		menuPP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPP.getContentPane().setLayout(null);

		inicializarTF_IngresarIncrementoPorProvincia();
		inicializarBotonEnviar();
		limitarInputUsuario(textFieldIngresarArista);
		
		asignarFondo();
	}
	JLabel lbl2048 = new JLabel("Ingresar similaridad entre provincias"); // en medio de la pantalla
		lbl2048.setForeground(new Color(255, 255, 255));
		lbl2048.setFont(new Font("Segoe UI Black", Font.PLAIN, 82));
		lbl2048.setBounds(120, 28, 244, 187);
		frame.getContentPane().add(lbl2048);


	private void inicializarTF_IngresarIncrementoPorProvincia() {
		
	}

	private boolean casilleroCompleto() {
		if (!textFieldIngresarArista.getText().isEmpty()) {
			return true;
		}
			return false;
	}
	
	private void inicializarBotonEnviar() {
		btnEnviar = new JButton("ENVIAR");

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (casilleroCompleto()) {
					
					cambiarDeVentana();
				}
				else {
					JOptionPane.showMessageDialog(null, "INGRESE UN PRECIO PARA CONTINUAR");
				}
			}
		});
		btnEnviar.setBounds(432, 464, 105, 30);
		menuPP.getContentPane().add(btnEnviar);
	}
	private void limitarInputUsuario(JTextField tf) {
		tf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {

				if (Calculo.noEsUnNumuero(e.getKeyChar())) {
					e.consume(); // ignorar el evento de teclado
				} else {
					btnEnviar.setEnabled(true);
					if (tf.getText().length() >= 7)
						e.consume();
				}
			}
		});
	}
	
	private void cambiarDeVentana(){
		vistaMapa = new RegArg();
		menuPP.setVisible(false);
		RegArg.ventana.setVisible(true);
		RegArg.ventana.setResizable(false);
	}
	
	private void asignarFondo(){
		lblFondo = new JLabel("");
		lblFondo.setBounds(8, 10, 800, 800);
		menuPP.getContentPane().add(lblFondo);
		
		lblFondo.setIcon(new ImageIcon(MenuBienven.class.getResource("/interfaz/fondoBandera.png")));
	}


}
