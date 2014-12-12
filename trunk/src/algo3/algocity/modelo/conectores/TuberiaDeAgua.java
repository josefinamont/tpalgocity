package algo3.algocity.modelo.conectores;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.catastrofes.Catastrofe;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.catastrofes.Terremoto;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Dañable;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class TuberiaDeAgua extends MiniConstruccion implements Entubable,Dañable  {

	private transient Entubable siguienteEntubable;
	
	public TuberiaDeAgua(){
		
		this.costo = 5;
		this.siguienteEntubable = null; 
		this.porcentajeDeVida = 100;
	}

    public void conectarAEntubable(Entubable unConectable){
		
		this.siguienteEntubable = unConectable;
	}

    public boolean estaConectadoAUnSuministroDeAgua(){
		
    	if (this.siguienteEntubable == null){ return false; }
    	
		return this.siguienteEntubable.estaConectadoAUnSuministroDeAgua();
	}

    public Entubable obtenerSiguienteEntubable(){
    	
    	return this.siguienteEntubable;
    }
    
    public boolean conectarseA(Electrificable unElectrificable){
    	
    	return false;
    }
    
    public boolean conectarseA(Entubable unEntubable){
    	
    	return true;
    }
    
    public boolean conectarseA(Enrutable unEnrutable){
		 
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
    
    public void aplicarDañoRecibido(Catastrofe catastrofe){
    	//no hace nada
    }
    
    public void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirTuberiaDeAgua(this,lugarDeConstruccion);
	}

    public TuberiaDeAgua generarNuevaInstancia(){
 	   
 	   return new TuberiaDeAgua();
    }
    
	@Override
	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
		 
		
	}

	@Override
	public void dibujarseSuperficie(Graphics g,Coordenada coordenadadimensionada) {
		 
		
	}

	@Override
	public void dibujarseSubterranea(Graphics g,Coordenada coordenadadimensionada) {
		
		java.awt.Image image = new ImageIcon("imagenes/tuberia.png").getImage();
		g.drawImage(image,coordenadadimensionada.obtenerPosicionX(),coordenadadimensionada.obtenerPosicionY(),null);
	}

	@Override
	public void aplicarDañoRecibido(Godzilla godzilla) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aplicarDañoRecibido(Terremoto terremoto) {
		// TODO Auto-generated method stub
		
	}
}
