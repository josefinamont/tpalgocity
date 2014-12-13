package algo3.algocity.modelo.construibles;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.mapa.Agua;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class PozoDeAgua extends MegaConstruccion implements Entubable {

	private transient Entubable siguienteEntubable;
	
	public PozoDeAgua() {
		
		this.costo = 250;
	    this.tipoDeTerreno = new Agua();
	    this.siguienteEntubable = null;
	    this.porcentajeDeVida = 100;
	}

	public void conectarAEntubable(Entubable unEntubable){
		
		this.siguienteEntubable = unEntubable;
	}

    public boolean estaConectadoAUnSuministroDeAgua(){
		
    	return true;
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
    
    public boolean tienePoblacion() {
    	
		return false;
	}
    
    public PozoDeAgua generarNuevaInstancia(){
 	   
 	   return new PozoDeAgua();
    }

	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
	
		java.awt.Image image = new ImageIcon("imagenes/pozo.png").getImage();
		g.drawImage(image,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
	}

	public boolean tieneElectricidad() {
		 
		return true;
	}

	public boolean tieneAgua() {
		 
		return true;
	}
	
	public void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirPozoDeAgua(this,lugarDeConstruccion);
	}
	
	public boolean esUnaConstruccionEnergetica(){
		
		return false;
	}

}

