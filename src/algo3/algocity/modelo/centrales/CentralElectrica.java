package algo3.algocity.modelo.centrales;

import java.util.ArrayList;

import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Daņable;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.Reparable;
import algo3.algocity.modelo.mapa.Tierra;

public abstract class CentralElectrica extends MegaConstruccion 
                implements Daņable,Reparable,Entubable,Electrificable,Enrutable {
        
        protected transient int abastecimientoEnMW;
        protected transient int radioDeAbastecimientoEnHa;
        protected transient Electrificable siguienteElectrificable,anteriorElectrificable;
        protected transient Entubable siguienteEntubable;
		protected transient Enrutable siguienteEnrutable;
		protected transient ArrayList<Enrutable> enrutables;
        
    public CentralElectrica() {
    	
    	this.porcentajeDeVida = 100;
        this.tipoDeTerreno = new Tierra();
        this.siguienteElectrificable = null;
        this.siguienteEntubable = null;
        this.anteriorElectrificable = null;
        this.siguienteEnrutable = null;
        this.enrutables = new ArrayList<Enrutable>();
    }
       
    public int abasteceEnMW(){
        
    	return this.abastecimientoEnMW;   
    }
    
    public int radioDeAbastecimientoEnHa() {
        
       return this.radioDeAbastecimientoEnHa;
    }
    
    public void aplicarDaņoRecibido(Godzilla godzilla){
    	
    	if (this.porcentajeDeVida > 0) {       
	       int danio = (this.porcentajeDeVida*35)/100;
	       this.porcentajeDeVida = (this.porcentajeDeVida - danio);
    	} else this.porcentajeDeVida = 0;
    }
    
    public void aplicarDaņoRecibido(Terremoto terremoto){
                
        int distanciaEnKMAlTerremoto = (int) this.hectareaALaQuePertenece.calcularDistanciaEnHaA(terremoto.obtenerHectareaALaQuePertenece());
        int daņoProvocadoPorElTerremoto = (int)(100 - distanciaEnKMAlTerremoto*1.5);        
        if (daņoProvocadoPorElTerremoto > 0){ this.porcentajeDeVida -= daņoProvocadoPorElTerremoto; }
    }

    public void conectarAEntubable(Entubable unEntubable){
                
                this.siguienteEntubable = unEntubable;
        }
    
    public void conectarASiguienteElectrificable(Electrificable unElectrificable){
        
        this.siguienteElectrificable = unElectrificable;
    }
    
    public void conectarAAnteriorElectrificable(Electrificable unElectrificable){		
		
		this.anteriorElectrificable = unElectrificable;
	}
    
    public boolean estaConectadoAUnSuministroElectrico(){

    	return this.tieneAgua();
    }

    public boolean tieneAgua() {
    	
    	return this.estaConectadoAUnSuministroDeAgua();
	}
    
    public boolean estaConectadoAUnSuministroDeAgua() {

    	if (this.siguienteEntubable != null) { 
			return this.siguienteEntubable.estaConectadoAUnSuministroDeAgua();
		} else return false;
    }
      
    public Electrificable obtenerSiguienteElectrificable(){
                
        return this.siguienteElectrificable;
    }
   
    public Electrificable obtenerAnteriorElectrificable(){
	   	
	   	return this.anteriorElectrificable;
	}
    
    public Entubable obtenerSiguienteEntubable(){
                
        return this.siguienteEntubable;
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
    
    public boolean tienePoblacion() {
    	
		return false;
	}
    
    public boolean tieneElectricidad(){
    
    	return true;
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
    
	public void conectarAEnrutable(Enrutable unEnrutable){
		
		this.siguienteEnrutable = unEnrutable;
	}
	
	public Enrutable obtenerSiguienteEnrutable(){
		
		return this.siguienteEnrutable;
	}
	
	public boolean estaConectadoAUnEnrutable(){
		
		return (this.siguienteEnrutable != null);
	}
	
	public boolean esUnaConstruccionEnergetica(){
		
		return true;
	}
	
	public boolean tieneAbastecimientoEnMW(int numero){
		
		return (this.abastecimientoEnMW == numero);
	}
	
}  