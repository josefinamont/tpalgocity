package algo3.algocity.vista;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.controlador.CustomKeyListenerjugadorExistente;
import algo3.algocity.controlador.CustomKeyListenerjugadorNuevo;
import algo3.algocity.controlador.VolverABienvenida;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.catastrofes.Terremoto;

public class Vista {
	
	private JFrame ventana = new JFrame("AlgoCity");
	private VistaMapa vistaMapa;
	private JLabel cantidadDeDinero;
	private JLabel indicesJugador;
	private JLabel areaDeNotificaciones;
	Fachada fachada;
	JPanel panelBotonesBienvenida = new JPanel();
	JPanel panelIngresoPorTeclado = new JPanel();
	String nombreJugador;
	JPanel panelActual;
	Timer timer;
	
	 public Vista(){
		 
		 ventana.setBounds(0, 0, 1355, 750); 
		 ventana.setVisible(true);
		 ventana.getContentPane().setLayout(null);
		 ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }	
	 
	 public JFrame obtenerVentana(){
		 
		 return ventana;
	 }
	 
	 public void setPanelVistaMapaConBotones(Controlador controlador){
		
		 timer = new Timer();
		 timer.schedule(controlador.obtenerFachada().obtenerJugador().obtenerPartida(controlador),3000,3000);
		 
		 ventana.remove(panelActual);
		 panelActual = new VistaFoto(controlador);
		 ventana.setContentPane(panelActual);
		 construirBotoneraConstrucciones(controlador,timer);
		 construirGrillaDeTerreno(controlador);		
		 construirPanelDinero(controlador);	
		 contruirPanelNotificaciones();
		 construirPanelIndicesJugador();
		 
		 ventana.validate();
		 ventana.repaint();
	 }
	 
	 public void setPanelBienvenida(Controlador controlador){
		
		 panelActual = new VistaPanelBienvenida(controlador);
		 ventana.setContentPane(panelActual);
		 ventana.validate();
		 ventana.repaint();
	 }
	 
	 public void volverAPanelBienvenida(Controlador controlador){
			
		 panelActual = new VistaPanelBienvenida(controlador);
		 ventana.setContentPane(panelActual);
		 ventana.validate();
		 ventana.repaint();
	 }
	 
	 public void setPanelJugadorNuevoExistente(Controlador controlador){
		 
		 ventana.remove(panelActual);
		 panelActual = new VistaPanelJugadorNuevoOExistente(controlador);
		 ventana.setContentPane(panelActual);
		 ventana.validate();
		 ventana.repaint(); 
	 }
	 
	 private void contruirPanelNotificaciones() {
		
		JPanel panelNotificaciones = new JPanel();
		panelNotificaciones.setBounds(900, 0, 350, 30);
		panelNotificaciones.setBackground(Color.black);
		ventana.getContentPane().add(panelNotificaciones);
			
		areaDeNotificaciones = new JLabel("Area de notificaciones");
		areaDeNotificaciones.setForeground(Color.white);
		panelNotificaciones.add(areaDeNotificaciones);
	 }
	
	 private void construirPanelDinero(Controlador controlador) {
		 
		JPanel panelDinero = new JPanel();
		panelDinero.setBounds(600, 0, 300, 30);
		panelDinero.setBackground(Color.black);
		
		ventana.getContentPane().add(panelDinero); 
		JLabel labelDinero = new JLabel("Dinero: ");
		labelDinero.setForeground(Color.WHITE);
		
		panelDinero.add(labelDinero);
		cantidadDeDinero = new JLabel("$" + controlador.obtenerFachada().obtenerJugador().dinero());
		cantidadDeDinero.setForeground(Color.WHITE);
		panelDinero.add(cantidadDeDinero);
	 }
	 
	 private void  construirPanelIndicesJugador(){
		 
		 JPanel panelIndicesJugador = new JPanel();
		 panelIndicesJugador.setBounds(0,0,600,30);
		 panelIndicesJugador.setBackground(Color.black);
		 ventana.getContentPane().add(panelIndicesJugador);
		 indicesJugador = new JLabel();
		 indicesJugador.setForeground(Color.white);
		 Font normal = new Font("Times", Font.PLAIN, 12);
		 indicesJugador.setFont(normal);
		 panelIndicesJugador.add(indicesJugador);
	}
	 
	 public void definirIndicesJugador(int turno,int poblacion,int felicidad,Controlador controlador){
		
		 indicesJugador.setText("Jugador: " + controlador.obtenerFachada().obtenerJugador().obtenerNombre() + "  Día: " + turno + "   " + "Poblacion: " + poblacion);
	 }
	 
	 private void construirGrillaDeTerreno(Controlador controlador) {
		
		vistaMapa = new VistaMapa(new EstadoVistaMapaSuperficie(), controlador.obtenerFachada().obtenerJugador().obtenerPartida().obtenerMapa());
		vistaMapa.addMouseListener(controlador.obtenerClickSobreElMapaListener());
		vistaMapa.setBounds(200, 30, 1355, 750);
		vistaMapa.setOpaque(true);
		ventana.getContentPane().add(vistaMapa);
		ventana.setVisible(true);
	}
	 
	 private void construirBotoneraConstrucciones(Controlador controlador,Timer timer) {
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBounds(0, 30, 200, 600);
		panelBotones.setBackground(Color.lightGray);
		panelBotones.setLayout(new GridLayout(20, 1));
		ventana.getContentPane().add(panelBotones);
		
		JLabel labelConstruir = new JLabel("Construir:");
		labelConstruir.setForeground(Color.lightGray);
		panelBotones.add(labelConstruir);
		
		JButton botonConstruirZonaResidencial = new JButton("Zona Residencial");
		botonConstruirZonaResidencial.addActionListener(controlador.obtenerConstruirResidencialListener());
		panelBotones.add(botonConstruirZonaResidencial);
			
		JButton botonConstruirZonaComercial = new JButton("Zona Comercial");
		botonConstruirZonaComercial.addActionListener(controlador.obtenerConstruirComercialListener());
		panelBotones.add(botonConstruirZonaComercial);
		
		JButton botonConstruirZonaIndustrial = new JButton("Zona Industrial");
		botonConstruirZonaIndustrial.addActionListener(controlador.obtenerConstruirIndustrialListener());
		panelBotones.add(botonConstruirZonaIndustrial);
		
		JButton botonConstruirCalle = new JButton("Ruta");
		botonConstruirCalle.addActionListener(controlador.obtenerConstruirRutaListener());
		panelBotones.add(botonConstruirCalle);
			
		JButton botonConstruirPosteLineaDeTension = new JButton("Linea De Tension");
		botonConstruirPosteLineaDeTension.addActionListener(controlador.obtenerConstruirLineaDeTensionListener());
		panelBotones.add(botonConstruirPosteLineaDeTension);
		
		JButton botonConstruirCentralMineral = new JButton("Central Mineral");
		botonConstruirCentralMineral.addActionListener(controlador.obtenerConstruirCentralMineralListener());
		panelBotones.add(botonConstruirCentralMineral);
			
		JButton botonConstruirCentralEolica = new JButton("Central Eolica");
		botonConstruirCentralEolica.addActionListener(controlador.obtenerConstruirCentralEolicaListener());
		panelBotones.add(botonConstruirCentralEolica);
		
		JButton botonConstruirCentralNuclear = new JButton("Central Nuclear");
		botonConstruirCentralNuclear.addActionListener(controlador.obtenerConstruirCentralNuclearListener());
		panelBotones.add(botonConstruirCentralNuclear);
			
		JButton botonConstruirPozoDeAgua = new JButton("Pozo De Agua");
		botonConstruirPozoDeAgua.addActionListener(controlador.obtenerConstruirPozoDeAguaListener());
		panelBotones.add(botonConstruirPozoDeAgua);
			
		JButton botonConstruirTuberia = new JButton("Tuberia De Agua");
		botonConstruirTuberia.addActionListener(controlador.obtenerConstruirTuberiaListener());
		panelBotones.add(botonConstruirTuberia);
		
		JButton botonConstruirBomberos = new JButton("Bomberos");
		botonConstruirBomberos.addActionListener(controlador.obtenerConstruirBomberosListener());
		panelBotones.add(botonConstruirBomberos);
		
		JLabel labelControles = new JLabel("Controles:");
		labelControles.setForeground(Color.WHITE);
		panelBotones.add(labelControles);
	
		JButton botonGuardarPartida = new JButton("Guardar partida");
		botonGuardarPartida.addActionListener(controlador.obtenerGuardarPartidaListener(timer));
		panelBotones.add(botonGuardarPartida);
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new VolverABienvenida(controlador,timer));
		panelBotones.add(botonVolver);
	 }
		
	 public void recibirNotificacion(Controlador controlador) {
			
		cantidadDeDinero.setText("$" + controlador.obtenerFachada().obtenerJugador().dinero());
		ventana.validate();
		ventana.repaint();
	 }
		
	 public void recibirNotificacionDeTerremoto(Terremoto terremoto){
			
		areaDeNotificaciones.setText("Hubo un terremoto en la ciudad");
		ventana.validate();
		ventana.repaint();
	 }
		
	 public JLabel getAreaDeNotificaciones(){
			
	 	return areaDeNotificaciones;
	 }
		
	 public JLabel getCantidadDeDinero(){
			
	 	return cantidadDeDinero;
	 }
		
	 public void cambiarAVistaSuperficie(){
			
		vistaMapa.cambiarEstado(new EstadoVistaMapaSuperficie());
		ventana.validate();
		ventana.repaint();
	 }
		
	 public void cambiarAVistaSubterranea(){
		
		if(vistaMapa != null)	
		vistaMapa.cambiarEstado(new EstadoVistaMapaSubterranea());
		ventana.validate();
		ventana.repaint();
	 }
		
	 public void notificaUsuario(String mensaje){
			
		areaDeNotificaciones.setText(mensaje);
		areaDeNotificaciones.setVisible(true);
	 }
		
	public void cambiarAVistaIngresoPorTecladoNuevoJugador(final Controlador controlador) {
		
		JLabel headerLabel;
		JLabel statusLabel;
		final TextField textField;
		ventana.setLayout(new GridLayout(3, 1));
	    ventana.remove(panelBotonesBienvenida);
	    headerLabel = new JLabel();
	    statusLabel = new JLabel();        
	    statusLabel.setSize(350,100);
	
	    panelIngresoPorTeclado.setBounds(0,0,750,750);
	    panelIngresoPorTeclado.setLayout(new FlowLayout());
	
	    ventana.add(headerLabel);
	    ventana.add(panelIngresoPorTeclado);
	    ventana.add(statusLabel);
	    headerLabel.setText("Ingrese Nombre Del Jugador");      
	    textField  = new TextField(20);
	
	    textField.addKeyListener(new CustomKeyListenerjugadorNuevo(statusLabel, textField,controlador));
	    Button okButton = new Button("Comenzar");
	   
	    panelIngresoPorTeclado.add(textField);
	    panelIngresoPorTeclado.add(okButton);    
	    ventana.setVisible(true);  
	     
	    okButton.addActionListener(new ActionListener() {
	     	 
	    	public void actionPerformed(ActionEvent e) {
	    	
	       	  controlador.agregarJugador(textField.getText());
	   		  controlador.obtenerVista().setPanelVistaMapaConBotones(controlador);
	   		}
	   });
	
	      ventana.validate();
		  ventana.repaint();   
	 }		
	
	 public void cambiarAVistaIngresoPorTecladoJugadorExistente(Controlador controlador) {
		
		
		   JLabel headerLabel;
		   JLabel statusLabel;
		   TextField textField;
		  
		   ventana.setLayout(new GridLayout(3, 1));
	       
		   ventana.remove(panelBotonesBienvenida);
	       headerLabel = new JLabel();
	     
	       statusLabel = new JLabel();        
	       statusLabel.setSize(350,100);
	
	       panelIngresoPorTeclado.setBounds(0,0,750,750);
	       panelIngresoPorTeclado.setLayout(new FlowLayout());
	
	      ventana.add(headerLabel);
	      ventana.add(panelIngresoPorTeclado);
	      ventana.add(statusLabel);
	      headerLabel.setText("Ingrese Nombre Del Jugador");      
	      textField  = new TextField(20);
	
	      textField.addKeyListener(new CustomKeyListenerjugadorExistente(statusLabel, textField,controlador));
	      Button botonComenzar = new Button("Comenzar");
	  
	      panelIngresoPorTeclado.add(textField);
	      panelIngresoPorTeclado.add(botonComenzar);    
	      ventana.setVisible(true);  
	     
	      botonComenzar.addActionListener(controlador.obtenerBotonComenzar());  
	 }
	
	public void definirPanelActual(JPanel panel) {
		
		ventana.remove(panelActual);
		this.panelActual = panel;
		
	}
	
	public void definirPanelJugadorNuevoTeclado(Controlador controlador) {
	
		 ventana.remove(panelActual);
		 panelActual = new VistaPanelElegirJugadorNuevoTeclado(controlador);
		 ventana.setContentPane(panelActual);
		 ventana.validate();
		 ventana.repaint(); 
	}
	
	public void definirPanelJugadorExistenteTeclado(Controlador controlador) {
	
		 ventana.remove(panelActual);
		 panelActual = new VistaPanelJugadorExistenteTeclado(controlador);
		 ventana.setContentPane(panelActual);
		 ventana.validate();
		 ventana.repaint(); 
	}
	
	public JPanel obtenerPanelActual() {
		
		return panelActual;
	}

}
