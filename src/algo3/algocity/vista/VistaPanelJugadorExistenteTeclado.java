package algo3.algocity.vista;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.controlador.CustomKeyListenerjugadorExistente;

public class VistaPanelJugadorExistenteTeclado extends JPanel{
	
	public VistaPanelJugadorExistenteTeclado(Controlador controlador){
		
		   JLabel ingreseNombreDeJugadorExistente;
		   JLabel statusLabel;
		   TextField textField;

	       ingreseNombreDeJugadorExistente = new JLabel();
	       statusLabel = new JLabel();        
	       statusLabel.setSize(350,100);
 
	       this.setBounds(0,0,750,750);
	       this.setLayout(new FlowLayout());
	       
	       JButton botonVolverAJugadorNuevoOExistente = new JButton("Volver");
	       botonVolverAJugadorNuevoOExistente.addActionListener(controlador.obtenerVolverListener());
		   this.add(botonVolverAJugadorNuevoOExistente);
		   botonVolverAJugadorNuevoOExistente.setBackground(Color.green);
		   this.add(botonVolverAJugadorNuevoOExistente);	
	       
	       this.add(ingreseNombreDeJugadorExistente);
	       this.add(statusLabel);
	       ingreseNombreDeJugadorExistente.setText("Ingrese Nombre Del Nuevo Jugador");      
	       textField  = new TextField(20);
	       textField.setText("");
	       textField.addKeyListener(new CustomKeyListenerjugadorExistente(statusLabel, textField,controlador));
	       Button okButton = new Button("Comenzar");
	   

	       this.add(textField);
	       this.add(okButton);    
	     
	       okButton.addActionListener(new ActionListener() {

	  		public void actionPerformed(ActionEvent e) {
	              
	  		}
	});
	}
	
	protected  void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Image image = new ImageIcon("imagenes/fondoInicio.png").getImage();
		g.drawImage(image,0,0,null);
	}
}
