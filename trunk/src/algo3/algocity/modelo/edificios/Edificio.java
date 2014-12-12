package algo3.algocity.modelo.edificios;

import java.util.ArrayList;

import algo3.algocity.modelo.catastrofes.Terremoto;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Dañable;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.Reparable;

public abstract class Edificio extends MegaConstruccion implements Dañable,Electrificable,Entubable,Reparable,Enrutable {

	protected int consumoElectricoEnMW;
	protected Entubable siguienteEntubable;
	protected Electrificable siguienteElectrificable;
	private Enrutable siguienteEnrutable;
	protected ArrayList<Enrutable> enrutables;
	
	public Edificio() {
		
		this.porcentajeDeVida = 100;
	    this.siguienteEntubable = null;
	    this.siguienteElectrificable = null;
	    this.siguienteEnrutable = null;
	    this.enrutables = new ArrayList<Enrutable>();
	}

	public int consumoElectricoEnMW() {
		
		return this.consumoElectricoEnMW;
	}
   
	public boolean tieneElectricidad(){
    	
    	return this.estaConectadoAUnSuministroElectrico();
    }
	
    public boolean tieneAgua() {
   
    	return this.estaConectadoAUnSuministroDeAgua();
	}
    
    public boolean estaConectadoAUnSuministroElectrico() {
		
    	if(this.siguienteElectrificable != null) { 
    		return this.siguienteElectrificable.estaConectadoAUnSuministroElectrico();
    	} else return false;
    }

	public boolean estaConectadoAUnSuministroDeAgua() {
		
		if (this.siguienteEntubable != null) { 
			return this.siguienteEntubable.estaConectadoAUnSuministroDeAgua();
		} else return false;
	}
	
    public void conectarASiguienteElectrificable(Electrificable unElectrificable){
    	
		this.siguienteElectrificable = unElectrificable;
	}
    
    public void conectarAEntubable(Entubable unConectable){
		
		this.siguienteEntubable = unConectable;
	}
    
    public Electrificable obtenerSiguienteElectrificable(){
    	
    	return this.siguienteElectrificable;
    }
   
    public Entubable obtenerSiguienteEntubable(){
    	
    	return this.siguienteEntubable;
    }
    
    public void aplicarDañoRecibido(Terremoto terremoto){
		
    	if (this.porcentajeDeVida > 0) {
			int distanciaEnHaAlTerremoto = (int) this.hectareaALaQuePertenece.calcularDistanciaEnHaA(terremoto.obtenerHectareaALaQuePertenece());
			int dañoProvocadoPorElTerremoto = (int)(100 - distanciaEnHaAlTerremoto*1.5);
			if (dañoProvocadoPorElTerremoto > 0){ this.porcentajeDeVida -= dañoProvocadoPorElTerremoto; }
    	} else this.porcentajeDeVida = 0;
	}
    
    public boolean conectarseA(Electrificable unElectrificable){
    	
    	return true;
    }
    
    public boolean conectarseA(Entubable unEntubable){
    	
    	return true;
    }
    
    public boolean conectarseA(Enrutable unEnrutable){
    	
    	return true;
    }
    
    public boolean conectarseA(Construccion unaConstruccion){
    	
    	Electrificable unElectrificable = null;
    	
    	if(unaConstruccion.conectarseA(unElectrificable)){
    		return this.conectarseA(unElectrificable);
    	} else {
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
    
    public boolean tienePoblacion() {
    	
    	return false;
	}
    
    public boolean puedeIrA(Enrutable unEnrutable){
    	
    	if (this.enrutables.contains(unEnrutable)) { return true; }
    	if (this.siguienteEnrutable != null){ 
    		return this.siguienteEnrutable.puedeIrA(unEnrutable); 
    	}  
    	if (this == unEnrutable) {
    		this.enrutables.add(unEnrutable);
    		return true;
    		}  
    	if (unEnrutable.obtenerSiguienteEnrutable() != null) { return unEnrutable.puedeIrA(this); } 
    	return false;
    }
    
    public Enrutable obtenerSiguienteEnrutable(){
    	
    	return this.siguienteEnrutable;
    }
    
	public void conectarAEnrutable(Enrutable unEnrutable){
		
		this.siguienteEnrutable = unEnrutable;
	}
	
	public boolean estaConectadoAUnEnrutable(){
		
		return true;
	}
    
}
   