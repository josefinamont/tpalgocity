package algo3.algocity.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algo3.algocity.controlador.Controlador;

public class VistaPanelBienvenida extends JPanel  {

	public VistaPanelBienvenida(Controlador controlador){
		
		this.setLayout(null);
		JLabel titulo = new JLabel("Algo City");
		titulo.setFont(new java.awt.Font("Verdana", 0, 100));;
		titulo.setForeground(Color.BLUE);
		this.add(titulo);
		
		JButton botonBienvenidaIniciar = new JButton("Iniciar Partida");
		botonBienvenidaIniciar.addActionListener(controlador.obtenerBienvenidaIniciar());
		this.add(botonBienvenidaIniciar);
		
		JButton botonContinuarPartida = new JButton("Continuar Partida");
		this.add(botonContinuarPartida);
		
		JButton botonVerPuntajes = new JButton("Ver Puntajes");
		this.add(botonVerPuntajes);
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(controlador.obtenerSalirListener());
		this.add(botonSalir);
		
		this.getComponent(0).setBounds(425, 50, 550, 250);
		this.getComponent(1).setBounds(550, 300, 150, 50);
		this.getComponent(2).setBounds(550, 375, 150, 50);
		this.getComponent(3).setBounds(550, 450, 150, 50);
		this.getComponent(4).setBounds(550, 525, 150, 50);
	}
	
	protected  void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Image image = new ImageIcon("imagenes/fondoInicio.png").getImage();
		g.drawImage(image,0,0,null);
	}

}
