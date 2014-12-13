package algo3.algocity.modelo.conectores;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.*;
import algo3.algocity.modelo.mapa.*;

public class LineaDeTension extends MiniConstruccion implements Dañable,Electrificable {

	private transient Electrificable siguienteElectrificable,anteriorElectrificable;	
	private transient Terreno superficieDeConstruccion;
	
	public LineaDeTension(){
		
		this.costo = 5;
		this.porcentajeDeVida = 100;
	    this.superficieDeConstruccion = new Tierra();
	    this.siguienteElectrificable = null;
	    this.anteriorElectrificable = null;
	}
	
	public int costo() {
		
		return this.costo;
	}
	
	public boolean tienePorcentajeDeVida(int porcentaje) {
		
		return this.porcentajeDeVida == porcentaje;
	}
	
	public String obtenerTipoDeSuelo(){
		
		return this.superficieDeConstruccion.obtenerTipoDeSuelo();
	}
	
	public void aplicarDañoRecibido(Godzilla godzilla) {
		
		if (this.porcentajeDeVida > 0) { this.porcentajeDeVida = 0; } else this.porcentajeDeVida = 0;
	}
	
	public void aplicarDañoRecibido(Terremoto terremoto){
		
		int distanciaEnKMAlTerremoto = (int) this.obtenerHectareaALaQuePertenece().calcularDistanciaEnHaA(terremoto.obtenerHectareaALaQuePertenece());
		int dañoProvocadoPorElTerremoto = (int) (100 - distanciaEnKMAlTerremoto*1.5);
		if (dañoProvocadoPorElTerremoto > 0 && this.porcentajeDeVida != 0){ this.porcentajeDeVida -= dañoProvocadoPorElTerremoto; }
	}
	
	public void conectarASiguienteElectrificable(Electrificable unElectrificable){
		
		this.siguienteElectrificable = unElectrificable;
	}

	public void conectarAAnteriorElectrificable(Electrificable unElectrificable){
		
		this.anteriorElectrificable = unElectrificable;
	}
	
	public boolean estaConectadoAUnSuministroElectrico(){
		
    	if (this.siguienteElectrificable == null){ return false; }
    	
    	return this.siguienteElectrificable.estaConectadoAUnSuministroElectrico();
	}
    
   public Electrificable obtenerSiguienteElectrificable(){
    	
    	return this.siguienteElectrificable;
    }
   
   public Electrificable obtenerAnteriorElectrificable(){
   	
   	return this.anteriorElectrificable;
   }

   public boolean conectarseA(Electrificable unElectrificable){
   	
   	  return true;
   }
   
   public boolean conectarseA(Enrutable unEnrutable){
		 
		 return false;
	 }
   
   public boolean conectarseA(Entubable unEntubable){
   	
   	return false;
   }
   
   public boolean conectarseA(Construccion unaConstruccion){
   	
   	Electrificable unElectrificable = null;
   	
   	if(unaConstruccion.conectarseA(unElectrificable)){
   		return this.conectarseA(unElectrificable);
   	}else{
   		Entubable unEntubable = null;
			if(unaConstruccion.conectarseA(unEntubable)){
				return this.conectarseA(unEntubable);
			}
   	}
   return false;	
   }
   
   public void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirLineaDeTension(this,lugarDeConstruccion);
   }
   
   public LineaDeTension generarNuevaInstancia(){
	   
	   return new LineaDeTension();
   }
   

 public void dibujarseSuperficie(Graphics g,Coordenada coordenadaDimensionada) {
		
	if(!this.tienePorcentajeDeVida(0)){	
		
		java.awt.Image image = new ImageIcon("imagenes/torreelectrica.png").getImage();
		g.drawImage(image,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		
		if(!this.tienePorcentajeDeVida(100)){
			
			java.awt.Image image1 = new ImageIcon("imagenes/dañado.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
	}
	}

	@Override
	public void dibujarseSubterranea(Graphics g,Coordenada coordenadadimensionada) {
		 
		
	}

	@Override
	public void dibujarse(Graphics g, Coordenada coordenadaDimensionada) {
		// TODO Auto-generated method stub
		
	}
	
	

}
   
