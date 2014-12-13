package algo3.algocity.vista;

import java.awt.Color;
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
import algo3.algocity.controlador.CustomKeyListenerjugadorNuevo;


public class VistaPanelElegirJugadorNuevoTeclado extends JPanel{

	public VistaPanelElegirJugadorNuevoTeclado(final Controlador controlador){
	
		   JLabel ingreseNombre;
		   final JLabel statusLabel;
		   final TextField textField;
		   JLabel titulo = new JLabel("Algo City");
		   titulo.setFont(new java.awt.Font("Verdana", 0, 100));;
		   titulo.setForeground(Color.BLUE);
		   this.add(titulo);
	       this.setLayout(null);
	       ingreseNombre = new JLabel();
	       statusLabel = new JLabel();        
	       statusLabel.setSize(350,100);
	       statusLabel.setAlignmentY(100);
	       statusLabel.setForeground(Color.white);
	       this.setBounds(0,0,750,750);
	       this.add(ingreseNombre);
	       this.add(statusLabel);
	       ingreseNombre.setText("Nuevo jugador:");  
	       ingreseNombre.setFont(new java.awt.Font("Verdana", 0, 20)); 
	       textField = new TextField(40);
	       textField.setText(" ");
	       textField.addKeyListener(new CustomKeyListenerjugadorNuevo(statusLabel, textField,controlador));
	       JButton okButton = new JButton("¡Comenzar!");
	   
	       JButton botonVolverAJugadorNuevoOExistente = new JButton("Volver");
	       botonVolverAJugadorNuevoOExistente.setOpaque(false);
	       botonVolverAJugadorNuevoOExistente.addActionListener(controlador.obtenerVolverListener());
	       
	       this.add(textField);
	       this.add(okButton);   
	       this.add(botonVolverAJugadorNuevoOExistente);
	     
	       okButton.addActionListener(new ActionListener() {

	    	  public void actionPerformed(ActionEvent e) {
	    		  
	    		  if ( (textField.getText().trim().length() != 0) || (!controlador.existeEsteJugador(textField.getText())) ){
	    		 
	    			  //controlador.agregarJugadorAFachadas(textField.getText());
	    			  controlador.agregarJugador(textField.getText());
	    			  controlador.setPanelVistaMapaConBotones();
	    		  }
	    		  if(textField.getText().trim().length() == 0 ){
	    			  
	    			  statusLabel.setText("Ingrese un nombre");
	    		  }
	    	}
	       } 
	       );
	      
	       this.getComponent(0).setBounds(425, 50, 550, 250);
	       this.getComponent(1).setBounds(350, 300, 250, 50);
		   this.getComponent(2).setBounds(550, 375, 150, 50);
		   this.getComponent(3).setBounds(650, 300, 150, 50);
		   this.getComponent(4).setBounds(550, 450, 150, 50);
		   this.getComponent(5).setBounds(550, 525, 150, 50);

	}
	
	protected  void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Image image = new ImageIcon("imagenes/fondoInicio.png").getImage();
		g.drawImage(image,0,0,null);
	}
}
