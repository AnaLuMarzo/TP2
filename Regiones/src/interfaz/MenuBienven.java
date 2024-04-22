package interfaz;

import javax.swing.JFrame;
import javax.swing.JTextField;

import negocio.Calculo;

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
	private JTextField textFieldIngresarPrecioPorMts;
	private JButton btnEnviar;
	private JLabel lblFondo;
	RegArg vistaMapa;
	static Double precioPormetro;
	static Double costoPorPasarProvincia;

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
		menuPP.setBounds(600, 600, 800, 800);
		menuPP.setLocationRelativeTo(null);
		menuPP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPP.getContentPane().setLayout(null);

		inicializarTF_IngresarPrecioPorMetro();
		inicializarTF_IngresarPorcentaje300km();
		inicializarTF_IngresarIncrementoPorProvincia();
		inicializarBotonEnviar();
		limitarInputUsuario(textFieldIngresarPrecioPorMts);
		limitarInputUsuario(textFieldIngresarPorcenjeAumento);
		limitarInputUsuario(textFieldIngresarAumentoProvincia);
		
		asignarFondo();
	}
	
	
	private void inicializarTF_IngresarPrecioPorMetro() {
		textFieldIngresarPrecioPorMts = new JTextField();
		setTp(new TextoTranslucido("PRECIO POR METRO", textFieldIngresarPrecioPorMts));
		textFieldIngresarPrecioPorMts.setBounds(142,470, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarPrecioPorMts);
		textFieldIngresarPrecioPorMts.setColumns(10);
	}
	
	private void inicializarTF_IngresarPorcentaje300km() {
		textFieldIngresarPorcenjeAumento = new JTextField();
		setTp(new TextoTranslucido("COSTO POR SUPERAR 300KM", textFieldIngresarPorcenjeAumento));
		textFieldIngresarPorcenjeAumento.setBounds(408, 520, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarPorcenjeAumento);
		textFieldIngresarPorcenjeAumento.setColumns(10);
	}
	
	private void inicializarTF_IngresarIncrementoPorProvincia() {
		textFieldIngresarAumentoProvincia = new JTextField();
		setTp(new TextoTranslucido("INCREMENTO POR PROVI.", textFieldIngresarAumentoProvincia));
		textFieldIngresarAumentoProvincia.setBounds(747, 520, 178, 68);
		menuPP.getContentPane().add(textFieldIngresarAumentoProvincia);
		textFieldIngresarAumentoProvincia.setColumns(10);
	}

	private boolean estanLos3TexfieldActivados() {
		if (!textFieldIngresarPrecioPorMts.getText().isEmpty() && !textFieldIngresarPorcenjeAumento.getText().isEmpty()
				&& !textFieldIngresarAumentoProvincia.getText().isEmpty()) {
			return true;
		}
			return false;
	}
	
	private void inicializarBotonEnviar() {
		btnEnviar = new JButton("ENVIAR");

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estanLos3TexfieldActivados()) {
					precioPormetro=Double.parseDouble(textFieldIngresarPrecioPorMts.getText() );
					costoPorPasarProvincia=Double.parseDouble(textFieldIngresarAumentoProvincia.getText() );
					porcentajeExtra300km=Double.parseDouble(textFieldIngresarPorcenjeAumento.getText() );
					
					cambiarDeVentana();
				}
				else {
					JOptionPane.showMessageDialog(null, "INGRESE UN PRECIO PARA CONTINUAR");
				}
			}
		});
		btnEnviar.setBounds(443, 488, 105, 30);
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
		vistaMapa = new VistaMapa();
		menuPP.setVisible(false);
		vistaMapa.ventana.setVisible(true);
		vistaMapa.ventana.setResizable(false);
	}
	
	private void asignarFondo(){
		lblFondo = new JLabel("");
		lblFondo.setBounds(8, 10, 800, 800);
		menuPP.getContentPane().add(lblFondo);
		
		lblFondo.setIcon(new ImageIcon(MenuBienven.class.getResource("/interfaz/fondoBandera.png")));
	}


}
