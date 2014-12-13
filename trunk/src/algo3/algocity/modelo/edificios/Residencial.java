package algo3.algocity.modelo.edificios;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.IndiceDeFelicidad;
import algo3.algocity.modelo.IndiceDeFelicidad.EstadosDeFelicidad;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Poblacion;

public class Residencial extends Edificio {
	
	private transient int capacidadMaxima;
	private Poblacion poblacionActual;
	transient Comercial centroComercialAsociado;
	private Poblacion cantidadDePoblacionNoLaboral;
	private transient ArrayList<IndiceDeFelicidad> indicesDeFelicidad;
	private IndiceDeFelicidad indice;
	private transient ArrayList<Industrial> industriasDondeTrabajan;
	
	public Residencial(){
		
		super();
		this.costo = 5;
		this.capacidadMaxima = 100;
		this.consumoElectricoEnMW = 1;
		this.poblacionActual = new Poblacion();
		this.cantidadDePoblacionNoLaboral = new Poblacion();
		this.centroComercialAsociado = null;
	    this.industriasDondeTrabajan = new ArrayList<Industrial>();
	    this.indice = new IndiceDeFelicidad();
	    this.indicesDeFelicidad = new ArrayList<IndiceDeFelicidad>();
	}
	
	public int capacidadMaxima() {
		
		return this.capacidadMaxima;
	}

	public void aplicarDañoRecibido(Godzilla godzilla){
		if (this.porcentajeDeVida > 0) { this.porcentajeDeVida = 0; } else this.porcentajeDeVida = 0;
	}
	
	public void repararse() {
		
		if (this.porcentajeDeVida <= 90) {
			int nuevoPorcentaje = this.porcentajeDeVida + 10;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}

	public void modificarCantidadDePoblacion(int nuevosClientes){
		
		this.poblacionActual.modificarNumeroDeCiudadanos(nuevosClientes);
	}
	
	public int obtenerPoblacion(){
		
		return this.poblacionActual.obtenerNumeroDeCuidadanos();
	}
	
	public boolean tienePoblacion(){
		
		return true;
	} 
	
	public int actualizarPoblacion(){
		
		if (this.poblacionActual.obtenerNumeroDeCuidadanos() < this.capacidadMaxima) {this.crecimientoPoblacional();}
		return this.poblacionActual.obtenerNumeroDeCuidadanos(); 
	}

	public void irDeCompras(Comercial unComercial) {
		
		if (unComercial.agregarResidencias(this) )//)&& this.puedeIrA(unComercial))
			this.centroComercialAsociado = unComercial; else {
				if (!unComercial.agregarResidencias(this))
				this.enrutables.remove(unComercial);
			}
		
	}

	public void irATrabajarA(Industrial unaIndustria){
		
		IndiceDeFelicidad indiceLocal = new IndiceDeFelicidad();
		
		if ((this.industriasDondeTrabajan.size() < 4)&&(unaIndustria.agregarResidenciaDeEmpleados(this))){
			
			this.industriasDondeTrabajan.add(unaIndustria);
			indiceLocal.definirIndiceDeFelicidadLaboral(unaIndustria.obtenerHectareaALaQuePertenece(),this.obtenerHectareaALaQuePertenece());
			this.indicesDeFelicidad.add(indiceLocal);		
		}
	}
	
	public ArrayList<Industrial> obtenerIndustriasDondeTrabajan(){
		
		return this.industriasDondeTrabajan;
	}
	
	public EstadosDeFelicidad  obtenerIndiceDeFelicidadPromedio(){
		
		return this.indice.obtenerIndice();
	}
	
	public void crecimientoPoblacional() {
		
		if(this.tieneElectricidad()&& this.tieneAgua()){
			 
			this.poblacionActual.modificarNumeroDeCiudadanos(2);
			this.cantidadDePoblacionNoLaboral.modificarNumeroDeCiudadanos(2);
			
			 
			 if(this.industriasDondeTrabajan.size() != 0){ 
				 
				 for(Industrial industriaActual: this.industriasDondeTrabajan){
					 
					this.calcularPromedioDeIndiceDeFelicidad();
					indice.crecimientoPoblacionalPorFelicidad(this.poblacionActual,this.cantidadDePoblacionNoLaboral);
					industriaActual.calcularCantidadDeEmpleadosQueNoSePudieronEmplear(this.cantidadDePoblacionNoLaboral);
				 }
				 
				 if(this.centroComercialAsociado != null){ 
					 
					 this.poblacionActual.modificarNumeroDeCiudadanos(2);
				 	 this.cantidadDePoblacionNoLaboral.modificarNumeroDeCiudadanos(2);
			 	 }
			}
		}
		if(this.poblacionActual.obtenerNumeroDeCuidadanos()>100){ this.poblacionActual.setNumeroDeCiudadanos(100);}
	 }
	
	public void calcularPromedioDeIndiceDeFelicidad(){
		
		int promedio = 0;
		int sumaDeFelicidades = 0;
		int cantidadDeFelicidades = 0;
		
		for(IndiceDeFelicidad indiceActual: this.indicesDeFelicidad){
			cantidadDeFelicidades ++;
			sumaDeFelicidades += indiceActual.obtenerIndice().ordinal();
		}
		promedio = sumaDeFelicidades/cantidadDeFelicidades;
		
		if(promedio == 0){ this.indice.definirEstado(EstadosDeFelicidad.ALTO);}
		else{
		  if(promedio == 1){this.indice.definirEstado(EstadosDeFelicidad.BAJO);}
		  else{this.indice.definirEstado(EstadosDeFelicidad.MUYBAJO);}
		}
	}

	public Poblacion obtenerPoblacionNoLaboral() {
		
		return this.cantidadDePoblacionNoLaboral;
	}
	
	public Residencial generarNuevaInstancia(){
		   
		   return new Residencial();
	   }
	public boolean tieneEmpleo(){
		
		return false;
	}
	 
	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
	
		if(!this.tienePorcentajeDeVida(0)){
		
			java.awt.Image image = new ImageIcon("imagenes/casa.png").getImage();
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
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX()+20,coordenadaDimensionada.obtenerPosicionY()+20,null);
		}
	}
 }		
}