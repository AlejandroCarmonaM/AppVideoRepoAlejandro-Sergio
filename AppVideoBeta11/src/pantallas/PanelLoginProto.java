package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import dominio.AppVideo;

public class PanelLoginProto extends JPanel {
	
	private AppVideo appVideo;
	
	public PanelLoginProto(AppVideo appVideo) {
		this.appVideo=appVideo;
		setBackground(Color.GRAY);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_centro_central = new JPanel();
		add(panel_centro_central);
		panel_centro_central.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_centro_central.setBackground(Color.GRAY);
		panel_centro_central.setLayout(new BorderLayout(0, 50));
		
		JPanel panel_cc_sur = new JPanel();
		panel_cc_sur.setBackground(Color.GRAY);
		panel_centro_central.add(panel_cc_sur, BorderLayout.SOUTH);
		panel_cc_sur.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 5));
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		panel_cc_sur.add(rigidArea);
		
		JButton btn_aceptar = new JButton("Aceptar");
		panel_cc_sur.add(btn_aceptar);
		
		Component rigidArea_cc_sur = Box.createRigidArea(new Dimension(0, 8));
		panel_cc_sur.add(rigidArea_cc_sur);
		
		JButton btnCancelar = new JButton("Cancelar");
		panel_cc_sur.add(btnCancelar);
		/*btnCancelar.addActionListener(ev -> {
			creaFramePruebas();
			});*/
		
		Component rigidArea_8 = Box.createRigidArea(new Dimension(0, 50));
		panel_cc_sur.add(rigidArea_8);
		
		JPanel panel_cc_centro = new JPanel();
		panel_cc_centro.setBackground(Color.GRAY);
		panel_centro_central.add(panel_cc_centro, BorderLayout.CENTER);
		panel_cc_centro.setLayout(new BoxLayout(panel_cc_centro, BoxLayout.Y_AXIS));
		
		Component rigidArea_7 = Box.createRigidArea(new Dimension(0, 120));
		panel_cc_centro.add(rigidArea_7);
		
		JPanel panel_login = new JPanel();
		panel_login.setBackground(Color.GRAY);
		panel_cc_centro.add(panel_login);
		
		Component rigidArea_3 = Box.createRigidArea(new Dimension(15, 0));
		panel_login.add(rigidArea_3);
		
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setForeground(Color.WHITE);
		panel_login.add(lblLogin);
		
		JTextField textFieldLogin = new JTextField();
		panel_login.add(textFieldLogin);
		textFieldLogin.setColumns(15);
		
		
		JPanel panel_password = new JPanel();
		panel_password.setBackground(Color.GRAY);
		panel_cc_centro.add(panel_password);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.WHITE);
		panel_password.add(lblPassword);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(15);
		panel_password.add(passwordField);
		
		
		//nota: se obtiene un string del campo contrasena de la siguiente manera:
				//de otra manera, no se obtiene bien
		btn_aceptar.addActionListener(ev -> {
					if(appVideo.login(textFieldLogin.getText(), String.valueOf(passwordField.getPassword())))
					{
						JOptionPane.showMessageDialog(panel_centro_central, "Login aceptado");
						PanelPrueba panel_prueba = new PanelPrueba();
						CreadorPaneles.creaPanel(appVideo.getPanelCentro(), panel_prueba);
						appVideo.setEtiqueta(textFieldLogin.getText());
						this.appVideo.getFrameBase().validate();
					}
					else {
						JOptionPane.showMessageDialog(panel_centro_central, "Login rechazado", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					});
		
		btnCancelar.addActionListener(ev -> {
			textFieldLogin.setText("");
			passwordField.setText("");
			panel_cc_centro.revalidate();
			panel_cc_centro.repaint();
			this.appVideo.getFrameBase().validate();
			});
		
		
	}

	}

