package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import controlador.Sistema;
import negocio.Premio;

@SuppressWarnings("serial")
public class PremiosGUI extends JFrame {
	private JLabel lblValorPremio, lblIdMaquina;
	private static JLabel[] frutas; 
	private JTextField txtValorPremio, txtIdMaquina;
	private JButton btnFrutilla, btnSandia, btnBanana, btnManzana, btnUva;
	private JButton btnSeleccionarMaquina, btnCambiarMaquina, btnAgregarPremio, btnFinalizar, btnDeshacer;
	private String nombres[] = {"frutilla.jpg","sandia.jpg","banana.jpg","manzana.jpg","uva.jpg"};
	private static int id;
	private int contador;
	public PremiosGUI() {
		configurar();
		eventos();
	}

	public void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		lblValorPremio = new JLabel("Valor del premio:");
		txtValorPremio = new JTextField();
		lblIdMaquina = new JLabel("Máquina N°: ");
		txtIdMaquina = new JTextField();
		btnSeleccionarMaquina = new JButton("Seleccionar maquina");
		btnAgregarPremio = new JButton("Agregar premio");
		btnCambiarMaquina = new JButton("Cambiar de maquina");
		btnFinalizar = new JButton("Finalizar");
		btnFrutilla = new JButton("Frutilla");
		btnSandia = new JButton("Sandia");
		btnBanana = new JButton("Banana");
		btnManzana = new JButton("Manzana");
		btnUva = new JButton("Uva");
		btnDeshacer = new JButton("Deshacer");
		
		
		
		btnFrutilla.setBounds(0, 5, 100, 25);
		btnSandia.setBounds(162, 5, 100, 25);
		btnBanana.setBounds(325, 5, 100, 25);
		btnManzana.setBounds(487, 5, 100, 25);
		btnUva.setBounds(650, 5, 100, 25);
		
		lblIdMaquina.setBounds(5, 200, 100, 30);
		txtIdMaquina.setBounds(75, 203, 50, 25);
		lblValorPremio.setBounds(155, 200, 100, 30);
		txtValorPremio.setBounds(255, 203, 50, 25);
		
		btnSeleccionarMaquina.setBounds(0, 246, 175, 25);
		btnCambiarMaquina.setBounds(175, 246, 175, 25);
		btnAgregarPremio.setBounds(350, 246, 150, 25);
		btnDeshacer.setBounds(500, 246, 100, 25);
		btnFinalizar.setBounds(600, 246, 150, 25);
		
		c.add(btnFrutilla);
		c.add(btnSandia);
		c.add(btnBanana);
		c.add(btnManzana);
		c.add(btnUva);
		c.add(lblIdMaquina);
		c.add(txtIdMaquina);
		c.add(lblValorPremio);
		c.add(txtValorPremio);
		c.add(btnSeleccionarMaquina);
		c.add(btnCambiarMaquina);
		c.add(btnAgregarPremio);
		c.add(btnDeshacer);
		c.add(btnFinalizar);

		btnAgregarPremio.setEnabled(false);
		btnFinalizar.setEnabled(false);
		btnCambiarMaquina.setEnabled(false);
		btnFrutilla.setEnabled(false);
		btnBanana.setEnabled(false);
		btnSandia.setEnabled(false);
		btnManzana.setEnabled(false);
		btnUva.setEnabled(false);
		txtValorPremio.setEnabled(false);
		btnDeshacer.setEnabled(false);

		this.setVisible(true);
		this.setSize(750, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ArrayList<String> frutas = new ArrayList<String>();
		Sistema controlador = Sistema.getSingletonInstance();
		contador = 0;
		btnSeleccionarMaquina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					id = Integer.parseInt(txtIdMaquina.getText());
					if (!controlador.existeMaquina(id)) {
						JOptionPane.showMessageDialog(null, "La máquina seleccionada es incorrecta.");
					}else {
						btnAgregarPremio.setEnabled(true);
						btnFinalizar.setEnabled(true);
						btnCambiarMaquina.setEnabled(true);
						btnFrutilla.setEnabled(true);
						btnBanana.setEnabled(true);
						btnSandia.setEnabled(true);
						btnManzana.setEnabled(true);
						btnUva.setEnabled(true);
						txtValorPremio.setEnabled(true);
						btnDeshacer.setEnabled(true);
						btnSeleccionarMaquina.setEnabled(false);
						txtIdMaquina.setEnabled(false);
						
						//frutas = new JLabel[controlador.obtenerNroCasilleros(id)];
					}
				}

				catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Error");
				}
			}
		});

		btnCambiarMaquina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnAgregarPremio.setEnabled(false);
				btnFinalizar.setEnabled(false);
				btnCambiarMaquina.setEnabled(false);
				btnFrutilla.setEnabled(false);
				btnBanana.setEnabled(false);
				btnSandia.setEnabled(false);
				btnManzana.setEnabled(false);
				btnUva.setEnabled(false);
				txtValorPremio.setText(null);
				txtValorPremio.setEnabled(false);
				btnDeshacer.setEnabled(false);
				btnSeleccionarMaquina.setEnabled(true);
				txtIdMaquina.setEnabled(true);
				frutas.removeAll(frutas);
				
			}

		});
		btnAgregarPremio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
				if (frutas.size() == controlador.obtenerNroCasilleros(id)) {
					int valorPremio = Integer.parseInt(txtValorPremio.getText());					
					controlador.agregarPremio(valorPremio, id, frutas);
					for (int j = 0; j < controlador.obtenerNroCasilleros(id); j++) {
						getContentPane().remove(getContentPane().findComponentAt((150 * j), 30));
						getContentPane().validate();
						getContentPane().repaint();
					}
					contador = 0;
					frutas.removeAll(frutas);// inicializo de nuevo las frutas para agregar otro premio
					txtValorPremio.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "La cantidad de frutas del premio debe ser igual a "+ controlador.obtenerNroCasilleros(id));
				}
				}
				catch(NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "El valor del premio no puede ser cero");
				}
				}
		});
		btnDeshacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(contador > 0) {					
					contador--;
					int aux = contador;
					frutas.remove(contador);
					getContentPane().remove(getContentPane().findComponentAt((150 * (aux)), 30));
					getContentPane().validate();
					getContentPane().repaint();					
				}				
			}			
		});
		btnFrutilla.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (frutas.size() < controlador.obtenerNroCasilleros(id)) {					
					contador++;
					int aux = contador;
					frutas.add("frutilla");
					JLabel fruta = new JLabel();
					getContentPane().add(fruta);
					fruta.setBounds((150 * (aux-1)), 30, 150, 150);
					fruta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					fruta.setIcon(new javax.swing.ImageIcon(getClass().getResource(frutas.get(aux-1) + ".jpg")));
					
				} else {
					JOptionPane.showMessageDialog(null, "Ha excedido la cantidad de frutas para el premio");
				}
			}
		});

		btnSandia.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (frutas.size() < controlador.obtenerNroCasilleros(id)) {
					contador++;
					int aux = contador;
					frutas.add("sandia");
					JLabel fruta = new JLabel();
					getContentPane().add(fruta);
					fruta.setBounds((150 * (aux-1)), 30, 150, 150);
					fruta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					fruta.setIcon(new javax.swing.ImageIcon(getClass().getResource(frutas.get(aux-1) + ".jpg")));
				} else {
					JOptionPane.showMessageDialog(null, "Ha excedido la cantidad de frutas para el premio");
				}
			}
		});

		btnBanana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			
				if (frutas.size() < controlador.obtenerNroCasilleros(id)) {
					contador++;
					int aux = contador;
					frutas.add("banana");
					JLabel fruta = new JLabel();
					getContentPane().add(fruta);
					fruta.setBounds((150 * (aux-1)), 30, 150, 150);
					fruta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					fruta.setIcon(new javax.swing.ImageIcon(getClass().getResource(frutas.get(aux-1) + ".jpg")));
				} else {
					JOptionPane.showMessageDialog(null, "Ha excedido la cantidad de frutas para el premio");
				}
			}
		});

		btnManzana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frutas.size() < controlador.obtenerNroCasilleros(id)) {
					contador++;
					int aux = contador;
					frutas.add("manzana");
					JLabel fruta = new JLabel();
					getContentPane().add(fruta);
					fruta.setBounds((150 * (aux-1)), 30, 150, 150);
					fruta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					fruta.setIcon(new javax.swing.ImageIcon(getClass().getResource(frutas.get(aux-1) + ".jpg")));
				} else {
					JOptionPane.showMessageDialog(null, "Ha excedido la cantidad de frutas para el premio");
				}
			}
		});

		btnUva.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (frutas.size() < controlador.obtenerNroCasilleros(id)) {
					contador++;
					int aux = contador;
					frutas.add("uva");
					JLabel fruta = new JLabel();
					getContentPane().add(fruta);
					fruta.setBounds((150 * (aux-1)), 30, 150, 150);
					fruta.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					fruta.setIcon(new javax.swing.ImageIcon(getClass().getResource(frutas.get(aux-1) + ".jpg")));
				} else {
					JOptionPane.showMessageDialog(null, "Ha excedido la cantidad de frutas para el premio");
				}
			}
		});

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				JugadorGUI j = new JugadorGUI();
				setVisible(false);
				dispose();
			}
		});
	}
}
