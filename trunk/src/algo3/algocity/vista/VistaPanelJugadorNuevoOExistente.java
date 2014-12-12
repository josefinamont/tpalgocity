package algo3.algocity.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algo3.algocity.controlador.Controlador;

public class VistaPanelJugadorNuevoOExistente extends JPanel{

	public VistaPanelJugadorNuevoOExistente(Controlador controlador){
		
		this.setLayout(null);
		
		JLabel titulo = new JLabel("Algo City");
		titulo.setFont(new java.awt.Font("Verdana", 0, 100));;
		titulo.setForeground(Color.BLUE);
		this.add(titulo);
		
		JButton botonElegirJugadorNuevoTeclado = new JButton("Jugador Nuevo");
		botonElegirJugadorNuevoTeclado.addActionListener(controlador.obtenerConstruirElegirJugadorNuevoTecladoListener());
		this.add(botonElegirJugadorNuevoTeclado);
	
		JButton botonElegirJugadorExistenteTeclado = new JButton("Jugador Existente");
		botonElegirJugadorExistenteTeclado.addActionListener(controlador.obtenerConstruirJugadorexistenteTecladoListener());
		this.add(botonElegirJugadorExistenteTeclado);
		
		JButton botonVolverABienvenida = new JButton("Volver");
		botonVolverABienvenida.addActionListener(controlador.obtenerVolverABienvenidaListener());
		this.add(botonVolverABienvenida);
		
		this.getComponent(0).setBounds(425, 50, 550, 250);
		this.getComponent(1).setBounds(550, 300, 150, 50);
		this.getComponent(2).setBounds(550, 375, 150, 50);
		this.getComponent(3).setBounds(550, 450, 150, 50);
	}
	
	protected void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Image image = new ImageIcon("imagenes/fondoInicio.png").getImage();
		g.drawImage(image,0,0,null);
	}
}
