package algo3.algocity.modelo.edificios;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Poblacion;

public class Comercial extends Edificio {
	
	private transient int capacidadMaximaDePersonas;
	private Poblacion cantidadDeClientesActual;
	private transient ArrayList<Residencial> residenciasQueMeVisitan;
	
	public Comercial() {
		
		super();
		this.costo = 5;
		this.consumoElectricoEnMW = 2;
		this.capacidadMaximaDePersonas = 300;
		this.cantidadDeClientesActual = new Poblacion();
		this.residenciasQueMeVisitan = new ArrayList<Residencial>();
	}

	public void aplicarDañoRecibido(Godzilla godzilla){
		
		if (this.porcentajeDeVida > 0) { 
			int danio = (this.porcentajeDeVida*75)/100;
			this.porcentajeDeVida = (this.porcentajeDeVida - danio);
		} else this.porcentajeDeVida = 0;
	}

	public void repararse() {
		
		if (this.porcentajeDeVida <= 93) {
			int nuevoPorcentaje = this.porcentajeDeVida + 7;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}

	public int capacidadMaximaDePersonas() {
		
		return this.capacidadMaximaDePersonas;
	}

	public boolean agregarResidencias(Residencial unaResidencia){
		
		if(hayLugarParaUnaResidencia() && this.puedeIrA(unaResidencia)){
			this.residenciasQueMeVisitan.add(unaResidencia);
			return true;
		}
		return false;
	}
	
	public boolean hayLugarParaUnaResidencia(){
		
		if (this.residenciasQueMeVisitan.size() < 3){return true;}
		
		return false; 
	}
	
	public void modificarCantidadDeClientes(int nuevosClientes){
		
		this.cantidadDeClientesActual.modificarNumeroDeCiudadanos(nuevosClientes);
	}
	
	public int obtenerCantidadDeClientesActual() {
		
		int cantidad = 0;
		
		for(Residencial residenciaActual: this.residenciasQueMeVisitan){
		 
			cantidad += residenciaActual.actualizarPoblacion();
		}
		 
	 return cantidad;
	}

	 public Comercial generarNuevaInstancia(){
		   
		   return new Comercial();
	   }

	
 public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
	
	if(!this.tienePorcentajeDeVida(0)){
		
		java.awt.Image image = new ImageIcon("imagenes/comercial.png").getImage();
		g.drawImage(image,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		
		if (!this.tieneElectricidad()){
			
			java.awt.Image image1 = new ImageIcon("imagenes/sinElectricidad.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
		if (!this.tieneAgua()){
			
			java.awt.Image image1 = new ImageIcon("imagenes/sinAgua.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY()+35,null);
		}
		if(!this.tienePorcentajeDeVida(100)){
			
			java.awt.Image image1 = new ImageIcon("imagenes/dañado.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
	}
 }
}
