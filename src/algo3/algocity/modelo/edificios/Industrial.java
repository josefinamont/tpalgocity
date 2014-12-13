package algo3.algocity.modelo.edificios;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Poblacion;

public class Industrial extends Edificio {
	
	private transient int maximoDePersonasAEmplearPorHA;
	private int cantidadDeEmpleados;
	private int cantidadDeVacantes;
	private transient ArrayList<Residencial> residenciasEmpleados;
	private transient String indiceDeFelicidadQueAportaASusEmpleados;
	
	public Industrial() {
		
		super();
		this.costo = 10;
		this.consumoElectricoEnMW = 5;
		this.maximoDePersonasAEmplearPorHA = 25;
		this.cantidadDeEmpleados = 0;
		this.residenciasEmpleados = new ArrayList<Residencial>();
		this.indiceDeFelicidadQueAportaASusEmpleados = " ";
	    this.cantidadDeVacantes = 25;
	}

	public int maximoDePersonasAEmplearPorHA() {
		
		return this.maximoDePersonasAEmplearPorHA;
	}
	
	public void aplicarDañoRecibido(Godzilla godzilla){
		
		if (this.porcentajeDeVida > 0) {
			int danio = (this.porcentajeDeVida*40)/100;
			this.porcentajeDeVida = (this.porcentajeDeVida - danio);
		}
	}

	public void repararse() {
		
		if (this.porcentajeDeVida <= 97) {
			
			int nuevoPorcentaje = this.porcentajeDeVida + 3;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}

	public boolean agregarResidenciaDeEmpleados(Residencial residencial) {
		
		if ( (this.residenciasEmpleados.size() < 1) ){
			
			this.residenciasEmpleados.add(residencial);
			this.calcularCantidadDeEmpleadosQueNoSePudieronEmplear(residencial.obtenerPoblacionNoLaboral());
			return true;
		}	
		return false;
	}
	
	public int obtenerCantidadDeEmpleados() {
		
		return this.cantidadDeEmpleados;
	}
	
	public void calcularCantidadDeEmpleadosQueNoSePudieronEmplear(Poblacion cantidadDeGente) {
		
		if(this.cantidadDeVacantes > 0 ){
		
			if( this.cantidadDeVacantes <= cantidadDeGente.obtenerNumeroDeCuidadanos()){ 
				
				cantidadDeGente.modificarNumeroDeCiudadanos(-this.cantidadDeVacantes);
				this.cantidadDeVacantes = 0;
				this.cantidadDeEmpleados = this.maximoDePersonasAEmplearPorHA - this.cantidadDeVacantes ;
			}
			else{ 
				this.cantidadDeVacantes -= cantidadDeGente.obtenerNumeroDeCuidadanos(); 
				this.cantidadDeEmpleados = this.maximoDePersonasAEmplearPorHA - this.cantidadDeVacantes ;
			    cantidadDeGente.modificarNumeroDeCiudadanos(-cantidadDeGente.obtenerNumeroDeCuidadanos());	
			}
		}
	}

	public String obtenerIndiceDeFelicidadGenerado() {
		
		return this.indiceDeFelicidadQueAportaASusEmpleados;
	}

	public int obtenerCantidadDeVacantes() {
		
		return this.cantidadDeVacantes; 
	}

	public Industrial generarNuevaInstancia(){
		   
		   return new Industrial();
	   }
	
	public boolean tieneEmpleo(){
		
		return true;
	}

	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
	
		if(!this.tienePorcentajeDeVida(0)){
		
			java.awt.Image image = new ImageIcon("imagenes/industrial.png").getImage();
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
