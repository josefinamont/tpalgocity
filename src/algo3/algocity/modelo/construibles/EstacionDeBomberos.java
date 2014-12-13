package algo3.algocity.modelo.construibles;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.Reparable;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class EstacionDeBomberos extends MegaConstruccion implements Entubable,Electrificable,Enrutable {

	private transient int costo;
	private transient Entubable siguienteEntubable;
	private transient Electrificable siguienteElectrificable,anteriorElectrificable;
	private transient Enrutable siguienteEnrutable;
	protected transient ArrayList<Enrutable> enrutables;
	
	public EstacionDeBomberos() {
		
		super();
		this.costo = 1500;
		this.radioDeAbastecimientoEnHa = 7;
		this.siguienteEnrutable = null;
		this.siguienteElectrificable = null;
		this.anteriorElectrificable = null;
		this.enrutables = new ArrayList<Enrutable>();
	}
	
	public int costo() {
		
		return this.costo;
	}

	public void repararDaños(Reparable unReparable) {
		
		if ( this.seEncuentraDentroDelRadioDeAccion(unReparable) ) {//&& (this.tieneAgua() && this.tieneElectricidad() && this.puedeIrA((Enrutable) unReparable)) ){
			unReparable.repararse();
		} 
	}

	private boolean seEncuentraDentroDelRadioDeAccion(Reparable unReparable) {
		
		return this.hectareaALaQuePertenece.seEncuentraDentroDelRadioDeAccion(unReparable.obtenerHectareaALaQuePertenece());
	}

	public boolean estaConectadoAUnSuministroElectrico() {
		
		if(this.siguienteElectrificable != null) { 
    		return this.siguienteElectrificable.estaConectadoAUnSuministroElectrico();
    	} else return false;
	}
	
	public void conectarASiguienteElectrificable(Electrificable unElectrificable) {
		
		this.siguienteElectrificable = unElectrificable;
	}
    
	public void conectarAAnteriorElectrificable(Electrificable unElectrificable){
		
		this.anteriorElectrificable = unElectrificable;
	}
	
	public Electrificable obtenerSiguienteElectrificable() {
		
		return this.siguienteElectrificable;
	}

	public Electrificable obtenerAnteriorElectrificable(){
	   	
	   	return this.anteriorElectrificable;
	}
	
	public boolean estaConectadoAUnSuministroDeAgua() {
		
		if (this.siguienteEntubable != null) { 
			return this.siguienteEntubable.estaConectadoAUnSuministroDeAgua();
		} else return false;
	}

	public void conectarAEntubable(Entubable unEntubable) {
		
		this.siguienteEntubable = unEntubable;
	}

	public Entubable obtenerSiguienteEntubable() {
		
		return this.siguienteEntubable;
	}
	
	public boolean tieneElectricidad(){
		 
		 if (this.siguienteElectrificable == null){ return false; }
	    	
		 return this.estaConectadoAUnSuministroElectrico();
	 }
	 
	 public boolean tieneAgua() {
		 
		 if (this.siguienteEntubable == null){ return false; }
		 
		 return this.estaConectadoAUnSuministroDeAgua();
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
	 
	public void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException{
			
		unMapa.construirEstacionDeBomberos(this, lugarDeConstruccion);
	}
	 
	public boolean tienePoblacion() {
		 
		 return false;
	}
	 
	 public EstacionDeBomberos generarNuevaInstancia(){
		 
		 return new EstacionDeBomberos();
	}
	
	@Override
	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
	
		java.awt.Image image = new ImageIcon("imagenes/bomberos.png").getImage();
		g.drawImage(image,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		
		if (!this.tieneElectricidad()){
			
			java.awt.Image image1 = new ImageIcon("imagenes/sinElectricidad.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
		if (!this.tieneAgua()){
			
			java.awt.Image image1 = new ImageIcon("imagenes/sinAgua.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY()+35,null);
		}
	}

	public boolean puedeIrA(Enrutable unEnrutable) {
		
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

	public void conectarAEnrutable(Enrutable unEnrutable) {
		
		this.siguienteEnrutable = unEnrutable;
	}
	
	public Enrutable obtenerSiguienteEnrutable(){
		
		return this.siguienteEnrutable;
	}
	
	public boolean estaConectadoAUnEnrutable(){
		
		return (this.siguienteEnrutable != null);
	}
	
	public boolean esUnaConstruccionEnergetica(){
		
		return false;
	}

}
