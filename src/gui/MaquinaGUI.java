package gui;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Sistema;

public class MaquinaGUI extends JFrame {
	private JLabel lblCasilleros, lblId, lblPrecioJugada, lblRecaudacionInicial, lblRecaudacionMinima;
	private JTextField txtCasilleros, txtId, txtPrecioJugada, txtRecaudacionInicial, txtRecaudacionMinima;
	private JButton btnCrearMaquina, btnAgregarPremios;

	public MaquinaGUI() {
		configurarGUI();
		eventos();
	}

	public void configurarGUI() {
		Container c = this.getContentPane();
		GridLayout g = new GridLayout(6, 4);
		c.setLayout(g);
		lblCasilleros = new JLabel("N° de casilleros:");
		lblId = new JLabel("Maquina N°:");
		lblPrecioJugada = new JLabel("Precio de la jugada:");
		lblRecaudacionInicial = new JLabel("Recaudacion inicial:");
		lblRecaudacionMinima = new JLabel("Recaudacion minima:");
		txtCasilleros = new JTextField();
		txtId = new JTextField();
		txtPrecioJugada = new JTextField();
		txtRecaudacionInicial = new JTextField();
		txtRecaudacionMinima = new JTextField();
		btnCrearMaquina = new JButton("Crear maquina");
		btnAgregarPremios = new JButton("Agregar premios");
		c.add(lblCasilleros);
		c.add(txtCasilleros);
		c.add(lblId);
		c.add(txtId);
		c.add(lblPrecioJugada);
		c.add(txtPrecioJugada);
		c.add(lblRecaudacionInicial);
		c.add(txtRecaudacionInicial);
		c.add(lblRecaudacionMinima);
		c.add(txtRecaudacionMinima);
		c.add(btnCrearMaquina);
		c.add(btnAgregarPremios);
		btnAgregarPremios.setEnabled(false);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.pack();

	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		btnCrearMaquina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Sistema controlador = Sistema.getSingletonInstance();
				try {
					int casilleros = Integer.parseInt(txtCasilleros.getText());
					int id = Integer.parseInt(txtId.getText());
					int precioJugada = Integer.parseInt(txtPrecioJugada.getText());
					int recaudacionInicial = Integer.parseInt(txtRecaudacionInicial.getText());
					int recaudacionMinima = Integer.parseInt(txtRecaudacionMinima.getText());

					if (casilleros >= 3 && casilleros <= 5 && id > 0 && precioJugada >= 10 && recaudacionInicial >= 150
							&& recaudacionMinima <= 40) {
						controlador.inicializarMaquina(recaudacionInicial, recaudacionMinima, casilleros, precioJugada,
								id);
						txtCasilleros.setText(null);
						txtId.setText(null);
						txtPrecioJugada.setText(null);
						txtRecaudacionInicial.setText(null);
						txtRecaudacionMinima.setText(null);
						btnAgregarPremios.setEnabled(true);
					} else {
						if (casilleros < 3 || casilleros > 5)
							JOptionPane.showMessageDialog(null,
									"Valor de casilleros inválido (debe seleccionar un valor entre 3 y 5)");
						if (id < 0)
							JOptionPane.showMessageDialog(null,
									"Identificador de máquina inválido (debe seleccionar un valor positivo)");
						if (precioJugada < 10)
							JOptionPane.showMessageDialog(null,
									"Precio de jugada inválido (debe seleccionar un valor mayor a 10)");
						if (recaudacionInicial <= 150)
							JOptionPane.showMessageDialog(null,
									"Valor de recaudación inicial inválido (debe seleccionar un valor mayor a 150)");
						if (recaudacionMinima > 40)
							JOptionPane.showMessageDialog(null,
									"Valor de recaudación mínima inválido (debe seleccionar un valor menor a 40)");
					}
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null, "No puede crear una máquina con valores nulos");
				}
			}
		});
		btnAgregarPremios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PremiosGUI p = new PremiosGUI();
				setVisible(false);
				dispose();
			}

		});
	}
}
