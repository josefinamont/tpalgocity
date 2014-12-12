package algo3.algocity.modelo.interfaces;

import java.awt.Graphics;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.mapa.Mapa;
import algo3.algocity.modelo.mapa.Terreno;
import algo3.algocity.modelo.mapa.Tierra;

public abstract class Construccion {
	
	protected int porcentajeDeVida;
	protected Terreno tipoDeTerreno;
	protected int radioDeAbastecimientoEnHa;
	protected Hectarea hectareaALaQuePertenece;
	protected int costo;

	public Construccion(){
		
		this.tipoDeTerreno = new Tierra();
		this.hectareaALaQuePertenece = null;
	}
	
	public String obtenerTipoDeSuelo() {
		
		return this.tipoDeTerreno.obtenerTipoDeSuelo();
	}
	
	public int radioDeAccionEnHa() {
		
		return this.radioDeAbastecimientoEnHa;
	}

	public void establecerHectareaALaQuePertenece(Hectarea unaHectarea) {
		
		this.hectareaALaQuePertenece = unaHectarea;
	}

	public Hectarea obtenerHectareaALaQuePertenece() {
		
		return this.hectareaALaQuePertenece;
	}

	public boolean tienePorcentajeDeVida(int porcentajeDeVida){

		return (this.porcentajeDeVida == porcentajeDeVida);
	}
	
	public int porcentajeDeVida(){
		
		return this.porcentajeDeVida;
	}
	
	public int costo(){
		
		return this.costo;
	}
	
	public abstract boolean conectarseA(Construccion unaConstruccion);
	
	public abstract boolean conectarseA(Electrificable unElectrificable);
	    
	public abstract boolean conectarseA(Entubable unEntubable);
	
	public abstract boolean conectarseA(Enrutable unEnrutable);
	
	public abstract void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException;
	
	public abstract Construccion generarNuevaInstancia();
	
	public abstract void dibujarse(Graphics g,Coordenada coordenadaDimensionada);
	
	public abstract boolean tieneElectricidad();
	
	public abstract boolean tieneAgua();
		
	
}
