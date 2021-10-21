package pantallas;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dominio.AppVideo;
import dominio.RepositorioUsuarios;

public class PanelRegistro extends JPanel {

	//Atributos
	private JTextField campoNombre;
	private JTextField campoApellidos;
	private JTextField campoEmail;
	private JTextField campoUsuario;
	private JPasswordField campoContrase�a;
	private JPasswordField campoRepContrase�a;
	private RepositorioUsuarios repositoPrueba = new RepositorioUsuarios();
	private JTextField campoFNacimiento;
	private AppVideo controlador;
	
	public PanelRegistro(AppVideo controlador) {
		this.controlador = controlador;
		//panelRegistro = new JPanel();
		this.setBackground(Color.GRAY);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JPanel panelCampos = new JPanel();
		panelCampos.setBackground(Color.GRAY);
		this.add(panelCampos);
		GridBagLayout gbl_panelCampos = new GridBagLayout();
		gbl_panelCampos.columnWidths = new int[] {0, 50, 0, 0};
		gbl_panelCampos.rowHeights = new int[] {25, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelCampos.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		gbl_panelCampos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		panelCampos.setLayout(gbl_panelCampos);
		
		JLabel etiquetaNombre = new JLabel("*Nombre:");
		etiquetaNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
		etiquetaNombre.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaNombre = new GridBagConstraints();
		gbc_etiquetaNombre.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaNombre.anchor = GridBagConstraints.NORTHEAST;
		gbc_etiquetaNombre.gridx = 0;
		gbc_etiquetaNombre.gridy = 0;
		panelCampos.add(etiquetaNombre, gbc_etiquetaNombre);
		
		campoNombre = new JTextField();
		GridBagConstraints gbc_campoNombre = new GridBagConstraints();
		gbc_campoNombre.gridwidth = 3;
		gbc_campoNombre.insets = new Insets(0, 0, 5, 0);
		gbc_campoNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoNombre.gridx = 1;
		gbc_campoNombre.gridy = 0;
		panelCampos.add(campoNombre, gbc_campoNombre);
		campoNombre.setColumns(10);
		
		JLabel etiquetaApellidos = new JLabel("Apellidos:");
		etiquetaApellidos.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaApellidos = new GridBagConstraints();
		gbc_etiquetaApellidos.anchor = GridBagConstraints.EAST;
		gbc_etiquetaApellidos.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaApellidos.gridx = 0;
		gbc_etiquetaApellidos.gridy = 1;
		panelCampos.add(etiquetaApellidos, gbc_etiquetaApellidos);
		
		campoApellidos = new JTextField();
		campoApellidos.setText("");
		GridBagConstraints gbc_campoApellidos = new GridBagConstraints();
		gbc_campoApellidos.gridwidth = 3;
		gbc_campoApellidos.insets = new Insets(0, 0, 5, 0);
		gbc_campoApellidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoApellidos.gridx = 1;
		gbc_campoApellidos.gridy = 1;
		panelCampos.add(campoApellidos, gbc_campoApellidos);
		campoApellidos.setColumns(10);
		
		JLabel etiquetaFNacimiento = new JLabel("*Fecha de nacimiento:");
		etiquetaFNacimiento.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaFNacimiento = new GridBagConstraints();
		gbc_etiquetaFNacimiento.anchor = GridBagConstraints.EAST;
		gbc_etiquetaFNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaFNacimiento.gridx = 0;
		gbc_etiquetaFNacimiento.gridy = 2;
		panelCampos.add(etiquetaFNacimiento, gbc_etiquetaFNacimiento);
		
		campoFNacimiento = new JTextField();
		GridBagConstraints gbc_campoFNacimiento = new GridBagConstraints();
		gbc_campoFNacimiento.gridwidth = 2;
		gbc_campoFNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_campoFNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoFNacimiento.gridx = 1;
		gbc_campoFNacimiento.gridy = 2;
		panelCampos.add(campoFNacimiento, gbc_campoFNacimiento);
		campoFNacimiento.setColumns(10);
		
		JLabel etiquetaEmail = new JLabel("email:");
		etiquetaEmail.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaEmail = new GridBagConstraints();
		gbc_etiquetaEmail.anchor = GridBagConstraints.EAST;
		gbc_etiquetaEmail.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaEmail.gridx = 0;
		gbc_etiquetaEmail.gridy = 3;
		panelCampos.add(etiquetaEmail, gbc_etiquetaEmail);
		
		campoEmail = new JTextField();
		GridBagConstraints gbc_campoEmail = new GridBagConstraints();
		gbc_campoEmail.gridwidth = 3;
		gbc_campoEmail.insets = new Insets(0, 0, 5, 0);
		gbc_campoEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoEmail.gridx = 1;
		gbc_campoEmail.gridy = 3;
		panelCampos.add(campoEmail, gbc_campoEmail);
		campoEmail.setColumns(10);
		
		JLabel etiquetaUsuario = new JLabel("*Usuario:");
		etiquetaUsuario.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaUsuario = new GridBagConstraints();
		gbc_etiquetaUsuario.anchor = GridBagConstraints.EAST;
		gbc_etiquetaUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaUsuario.gridx = 0;
		gbc_etiquetaUsuario.gridy = 5;
		panelCampos.add(etiquetaUsuario, gbc_etiquetaUsuario);
		
		campoUsuario = new JTextField();
		GridBagConstraints gbc_campoUsuario = new GridBagConstraints();
		gbc_campoUsuario.gridwidth = 2;
		gbc_campoUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_campoUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoUsuario.gridx = 1;
		gbc_campoUsuario.gridy = 5;
		panelCampos.add(campoUsuario, gbc_campoUsuario);
		campoUsuario.setColumns(10);
		
		JLabel etiquetaContrase�a = new JLabel("*Contrase\u00F1a:");
		etiquetaContrase�a.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaContrase�a = new GridBagConstraints();
		gbc_etiquetaContrase�a.anchor = GridBagConstraints.EAST;
		gbc_etiquetaContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaContrase�a.gridx = 0;
		gbc_etiquetaContrase�a.gridy = 6;
		panelCampos.add(etiquetaContrase�a, gbc_etiquetaContrase�a);
		
		campoContrase�a = new JPasswordField();
		GridBagConstraints gbc_campoContrase�a = new GridBagConstraints();
		gbc_campoContrase�a.gridwidth = 2;
		gbc_campoContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_campoContrase�a.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoContrase�a.gridx = 1;
		gbc_campoContrase�a.gridy = 6;
		panelCampos.add(campoContrase�a, gbc_campoContrase�a);
		
		JLabel etiquetaRepContrase�a = new JLabel("*Repetir contrase\u00F1a:");
		etiquetaRepContrase�a.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaRepContrase�a = new GridBagConstraints();
		gbc_etiquetaRepContrase�a.anchor = GridBagConstraints.EAST;
		gbc_etiquetaRepContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaRepContrase�a.gridx = 0;
		gbc_etiquetaRepContrase�a.gridy = 7;
		panelCampos.add(etiquetaRepContrase�a, gbc_etiquetaRepContrase�a);
		
		campoRepContrase�a = new JPasswordField();
		GridBagConstraints gbc_campoRepContrase�a = new GridBagConstraints();
		gbc_campoRepContrase�a.gridwidth = 2;
		gbc_campoRepContrase�a.insets = new Insets(0, 0, 5, 5);
		gbc_campoRepContrase�a.fill = GridBagConstraints.HORIZONTAL;
		gbc_campoRepContrase�a.gridx = 1;
		gbc_campoRepContrase�a.gridy = 7;
		panelCampos.add(campoRepContrase�a, gbc_campoRepContrase�a);
		
		JButton botonRegistrar = new JButton("Registrar");
		
		GridBagConstraints gbc_botonRegistrar = new GridBagConstraints();
		gbc_botonRegistrar.insets = new Insets(0, 0, 5, 5);
		gbc_botonRegistrar.anchor = GridBagConstraints.WEST;
		gbc_botonRegistrar.gridx = 1;
		gbc_botonRegistrar.gridy = 8;
		panelCampos.add(botonRegistrar, gbc_botonRegistrar);
		
		JButton botonCancelar = new JButton("Cancelar");
		
		GridBagConstraints gbc_botonCancelar = new GridBagConstraints();
		gbc_botonCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_botonCancelar.anchor = GridBagConstraints.SOUTHWEST;
		gbc_botonCancelar.gridx = 3;
		gbc_botonCancelar.gridy = 8;
		panelCampos.add(botonCancelar, gbc_botonCancelar);
		
		JLabel etiquetaCamposObligatorios = new JLabel("* Campos obligatorios");
		etiquetaCamposObligatorios.setForeground(Color.WHITE);
		GridBagConstraints gbc_etiquetaCamposObligatorios = new GridBagConstraints();
		gbc_etiquetaCamposObligatorios.gridwidth = 2;
		gbc_etiquetaCamposObligatorios.insets = new Insets(0, 0, 5, 5);
		gbc_etiquetaCamposObligatorios.gridx = 1;
		gbc_etiquetaCamposObligatorios.gridy = 10;
		panelCampos.add(etiquetaCamposObligatorios, gbc_etiquetaCamposObligatorios);
		
		botonRegistrar.addActionListener(ev -> {
				if (controlador.registrarUser(campoNombre.getText(), campoFNacimiento.getText(), campoUsuario.getText(), String.valueOf(campoContrase�a.getPassword()), String.valueOf(campoRepContrase�a.getPassword()))) {
					JOptionPane.showMessageDialog((Component) ev.getSource(), "Registro exitodo");
				}
				else {
					JOptionPane.showMessageDialog((Component) ev.getSource(), "Registro fallido");
				}
		});
		
		botonCancelar.addActionListener(ev -> {
				campoNombre.setText("");
				campoApellidos.setText("");
				campoFNacimiento.setText("");
				campoEmail.setText("");
				campoUsuario.setText("");
				campoContrase�a.setText("");
				campoRepContrase�a.setText("");
		});
	}

	}

