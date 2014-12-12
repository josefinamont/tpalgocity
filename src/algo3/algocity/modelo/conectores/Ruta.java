package algo3.algocity.modelo.conectores;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.*;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class Ruta extends MiniConstruccion implements Dañable,Enrutable {
	
	private transient Entubable siguienteConectable;
	private transient Enrutable siguienteEnrutable;

	public Ruta() {
		
		super();
		this.costo = 10;
		this.porcentajeDeVida = 100;
		this.siguienteEnrutable = null;
	}

	public int costo() {
		
		return this.costo;
	}
	
	public boolean tienePorcentajeDeVida(int porcentajeDeVida) {
		
		return this.porcentajeDeVida == porcentajeDeVida;
	}

	public void aplicarDañoRecibido(Godzilla godzilla){
		
		if (this.porcentajeDeVida > 0) { 
			int daño = (this.porcentajeDeVida*80)/100;
			this.porcentajeDeVida = (this.porcentajeDeVida - daño);
		} else this.porcentajeDeVida = 0;
	}
	
	public void aplicarDañoRecibido(Terremoto terremoto){
		
		int distanciaEnKMAlTerremoto = (int) this.obtenerHectareaALaQuePertenece().calcularDistanciaEnHaA(terremoto.obtenerHectareaALaQuePertenece());
		int dañoProvocadoPorElTerremoto = (int) (100 - distanciaEnKMAlTerremoto*1.5);
		if (dañoProvocadoPorElTerremoto > 0){ this.porcentajeDeVida -= dañoProvocadoPorElTerremoto; }
	}

    public void estaConectadoAUnSuministro(){
		
		this.siguienteConectable.estaConectadoAUnSuministroDeAgua();
	}

    public boolean conectarseA(Electrificable unElectrificable){
    	
    	return false;
    }
    
    public boolean conectarseA(Entubable unEntubable){
    	
    	return false;
    }
    
    public boolean conectarseA(Enrutable unEnrutable){
		 
		 return true;
	 }
    
    public boolean conectarseA(Construccion unaConstruccion){
    	
    	Electrificable unElectrificable = null;
    	
    	if(unaConstruccion.conectarseA(unElectrificable)){
    		return this.conectarseA(unElectrificable);
    	}else{
    		Entubable unEntubable = null;
			if(unaConstruccion.conectarseA(unEntubable)){
				return this.conectarseA(unEntubable);
			} else {
				Enrutable unEnrutable = null;
				if(unaConstruccion.conectarseA(unEnrutable)){
					return this.conectarseA(unEnrutable);
				}
			}
    	}
    	return false;	
    }

	public void construirmeEn(Mapa unMapa,Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException {

		
		unMapa.construirRuta(this,lugarDeConstruccion);
	}

	public Ruta generarNuevaInstancia(){
		
		return new Ruta();
	}
	 
	public void dibujarseSuperficie(Graphics g,Coordenada coordenadadimensionada) {
			 
		if(!this.tienePorcentajeDeVida(0)){
		
			java.awt.Image image = new ImageIcon("imagenes/ruta.png").getImage();
			g.drawImage(image,coordenadadimensionada.obtenerPosicionX(),coordenadadimensionada.obtenerPosicionY(),null);
			
			if(!this.tienePorcentajeDeVida(100)){
				
				java.awt.Image image1 = new ImageIcon("imagenes/dañado.png").getImage();
				g.drawImage(image1,coordenadadimensionada.obtenerPosicionX(),coordenadadimensionada.obtenerPosicionY(),null);
			}
		}
	}

		
		public void dibujarseSubterranea(Graphics g,Coordenada coordenadadimensionada) {
			 
			
		}

		
		public void dibujarse(Graphics g, Coordenada coordenadaDimensionada) {
			// TODO Auto-generated method stub
			
		}

		public boolean puedeIrA(Enrutable unEnrutable) {
			
			if (this.siguienteEnrutable != null){ return this.siguienteEnrutable.puedeIrA(unEnrutable); } else return false;
		}

		public void conectarAEnrutable(Enrutable unEnrutable) {
			
			this.siguienteEnrutable = unEnrutable;
		}

		public boolean estaConectadoA(Ruta ruta) {
			
			return (this.siguienteEnrutable == ruta);
		}

		public Enrutable obtenerSiguienteEnrutable() {
			
			return this.siguienteEnrutable;
		}
		
		public boolean estaConectadoAUnEnrutable(){
			
			return (this.siguienteEnrutable != null);
		}
		
}
