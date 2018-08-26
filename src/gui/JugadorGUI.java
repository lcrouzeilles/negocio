package gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import controlador.Sistema;

public class JugadorGUI extends JFrame {

	private JButton btnApostar, btnRetirarse, btnCobrarCredito, btnSeleccionarMaquina, btnCambiarMaquina,
			btnRegistrarJugador;
	private JLabel lblCredito, lblIdMaquina, lblPrecioJugada, lblIdJugador;
	private JTextField txtCredito, txtIdMaquina, txtPrecioJugada, txtIdJugador;
	private static JLabel[] frutas;
	protected int contador;
	private static int id, credito, idJugador;
	private static final int iteraciones = 10;

	public JugadorGUI() {
		configurarGUI();
		eventos();
	}

	public void configurarGUI() {
		Container c = this.getContentPane();
		c.setLayout(null);

		lblCredito = new JLabel("Credito: ");
		txtCredito = new JTextField();
		lblIdMaquina = new JLabel("Maquina N°: ");
		lblPrecioJugada = new JLabel("Precio de jugada: ");
		txtPrecioJugada = new JTextField();
		txtIdMaquina = new JTextField();
		lblIdJugador = new JLabel("Jugador N°: ");
		txtIdJugador = new JTextField();
		btnSeleccionarMaquina = new JButton("Seleccionar máquina");
		btnCambiarMaquina = new JButton("Cambiar de máquina");
		btnApostar = new JButton("Apostar");
		btnRetirarse = new JButton("Retirarse");
		btnCobrarCredito = new JButton("Cobrar credito");
		btnRegistrarJugador = new JButton("Registrar jugador");

		lblIdMaquina.setBounds(2, 185, 70, 30);
		txtIdMaquina.setBounds(80, 188, 40, 25);
		lblPrecioJugada.setBounds(130, 185, 110, 30);
		txtPrecioJugada.setBounds(240, 188, 40, 25);
		lblCredito.setBounds(2, 215, 50, 30);
		txtCredito.setBounds(80, 218, 40, 25);
		lblIdJugador.setBounds(130, 215, 70, 30);
		txtIdJugador.setBounds(240, 218, 40, 25);

		btnRegistrarJugador.setBounds(0, 246, 175, 25);
		btnSeleccionarMaquina.setBounds(312, 246, 175, 25);
		btnCambiarMaquina.setBounds(625, 246, 175, 25);
		btnApostar.setBounds(0, 271, 175, 25);
		btnRetirarse.setBounds(312, 271, 175, 25);
		btnCobrarCredito.setBounds(625, 271, 175, 25);

		c.add(lblIdJugador);
		c.add(txtIdJugador);
		c.add(lblCredito);
		c.add(txtCredito);
		c.add(lblIdMaquina);
		c.add(txtIdMaquina);
		c.add(lblPrecioJugada);
		c.add(txtPrecioJugada);
		c.add(btnRegistrarJugador);
		c.add(btnSeleccionarMaquina);
		c.add(btnCambiarMaquina);
		c.add(btnApostar);
		c.add(btnRetirarse);
		c.add(btnCobrarCredito);

		txtIdMaquina.setEnabled(false);
		btnCambiarMaquina.setEnabled(false);
		btnSeleccionarMaquina.setEnabled(false);
		txtPrecioJugada.setEnabled(false);
		btnApostar.setEnabled(false);
		btnRetirarse.setEnabled(false);
		btnCobrarCredito.setEnabled(false);
		this.setVisible(true);
		this.setSize(800, 325);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

	private void eventos() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Sistema controlador = Sistema.getSingletonInstance();

		btnRegistrarJugador.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					credito = Integer.parseInt(txtCredito.getText());
					idJugador = Integer.parseInt(txtIdJugador.getText());
					btnRegistrarJugador.setEnabled(false);
					txtIdMaquina.setEnabled(true);
					btnSeleccionarMaquina.setEnabled(true);
					txtCredito.setEnabled(false);
					txtIdJugador.setEnabled(false);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Valor de crédito o ID inválidos");
				}catch(NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Valor de crédito o ID inválidos");
				}

			}

		});

		btnSeleccionarMaquina.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					id = Integer.parseInt(txtIdMaquina.getText());
					if (!controlador.existeMaquina(id)) {
						JOptionPane.showMessageDialog(null, "La máquina seleccionada es incorrecta.");
					} else {
						frutas = new JLabel[controlador.obtenerNroCasilleros(id)];
						int j;
						for (j = 0; j < controlador.obtenerNroCasilleros(id); j++) {
							frutas[j] = new JLabel();
							getContentPane().add(frutas[j]);
							frutas[j].setBounds(25 + (150 * j), 30, 150, 150);
							frutas[j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
							frutas[j].setIcon(new javax.swing.ImageIcon(getClass().getResource("blanco.jpg")));
						}
						txtPrecioJugada.setText("" + controlador.getPrecioJugada(id));
						btnApostar.setEnabled(true);
						btnRetirarse.setEnabled(true);
						btnCambiarMaquina.setEnabled(true);
						btnSeleccionarMaquina.setEnabled(false);
						txtIdMaquina.setEnabled(false);
						controlador.registrarJugador(credito, id, idJugador);
					}
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null, "Máquina inexistente");
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Número de máquina inválido");
				}
			}

		});
		btnCambiarMaquina.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int j;
				for (j = 0; j < controlador.obtenerNroCasilleros(id); j++) {
					getContentPane().remove(getContentPane().findComponentAt(25 + (150 * j), 30));
					getContentPane().validate();
					getContentPane().repaint();
				}

				btnApostar.setEnabled(false);
				btnRetirarse.setEnabled(false);
				btnSeleccionarMaquina.setEnabled(true);
				txtIdMaquina.setEnabled(true);
				credito = Integer.parseInt(txtCredito.getText());
				controlador.quitarJugador(id);
			}

		});

		btnApostar.addActionListener(new ActionListener() {

			Timer timer = new Timer(200, new ActionListener() {
				Random r = new Random();

				public void actionPerformed(ActionEvent e) {					
					btnCobrarCredito.setEnabled(false);
					String[] nombres = { "banana.jpg", "frutilla.jpg", "manzana.jpg", "sandia.jpg", "uva.jpg" };
					for (int j = 0; j < controlador.obtenerNroCasilleros(id); j++)
						frutas[j].setIcon(new javax.swing.ImageIcon(getClass().getResource(nombres[r.nextInt(5)])));
					contador++;
					if (contador == iteraciones) {
						timer.stop();
						boolean flag = controlador.realizarJugada(id, idJugador);
						credito=controlador.getCreditoJugador(id, idJugador);
						txtCredito.setText(Integer.toString(credito));
						ArrayList<String> comb = controlador.getCombinacion();
						for (int j = 0; j < frutas.length; j++) {
							frutas[j].setIcon(new javax.swing.ImageIcon(getClass().getResource(comb.get(j) + ".jpg")));
						}						
						
						if (flag) {
							JOptionPane.showMessageDialog(null, "¡Felicidades! Ha obtenido un premio.\n Si desea, puede cobrarlo.");
							btnCobrarCredito.setEnabled(true);
							flag = false;
						}						
						btnApostar.setEnabled(true);
						btnRetirarse.setEnabled(true);
						btnSeleccionarMaquina.setEnabled(true);
						btnCambiarMaquina.setEnabled(true);
					}

				}
			});

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!timer.isRunning()) {
					contador = 0;
					timer.start();
					btnApostar.setEnabled(false);
					btnRetirarse.setEnabled(false);
					btnSeleccionarMaquina.setEnabled(false);
					btnCambiarMaquina.setEnabled(false);
				}
			}

		});
		btnRetirarse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnCobrarCredito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controlador.asignarPremio(credito, id);
				txtCredito.setText(Integer.toString(credito));
				btnCobrarCredito.setEnabled(false);
			}
		});
	}
}
