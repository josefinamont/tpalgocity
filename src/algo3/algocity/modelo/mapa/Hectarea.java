package algo3.algocity.modelo.mapa;

import java.util.ArrayList;

import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class Hectarea {
	
	private transient Terreno tipoDeTerreno;
	private Coordenada coordenada;
	private transient ArrayList<MiniConstruccion> miniConstrucciones = new ArrayList<MiniConstruccion>();
	private transient MegaConstruccion megaConstruccion;
		
	public Hectarea(Terreno tipoDeTerreno,Coordenada nuevaCoordenada) {
		
		this.megaConstruccion = null;
		this.tipoDeTerreno = tipoDeTerreno;
		this.coordenada = nuevaCoordenada;
	}

	public String obtenerTipoDeSuelo() {
	
		return this.tipoDeTerreno.obtenerTipoDeSuelo();
	}

	public Terreno obtenerTipoDeTerreno(){
		
		return this.tipoDeTerreno;
	}
	
	public Coordenada obtenerCoordenada() {
		
		return this.coordenada;
	}

	public double calcularDistanciaEnHaA(Hectarea unaHectarea) {
		
		return this.coordenada.calcularDistanciaEnHaA(unaHectarea.obtenerCoordenada());
	}

	public void construirMiniConstruccion(MiniConstruccion unaMiniConstruccion){
	  
	  this.miniConstrucciones.add(unaMiniConstruccion);
    }

	public void construirMegaConstruccion(MegaConstruccion nuevaMegaConstruccion){
		
		this.megaConstruccion = nuevaMegaConstruccion;
	}
	
	public ArrayList<MiniConstruccion> obtenerMiniConstruccion() {
		
		return this.miniConstrucciones;
	}
	
	public MegaConstruccion obtenerMegaConstruccion(){
		
	   return this.megaConstruccion;	
	}
	
	public boolean seEncuentraDentroDelRadioDeAccion(Hectarea unaHectarea) {
		
		return (this.coordenada.calcularDistanciaEnHaA(unaHectarea.obtenerCoordenada()) < 15);
	}

	public void anularMiniConstrucciones() {
		
		this.miniConstrucciones = null;
		
	}
	
	public void anularMegaConstruccion(){
		
		this.megaConstruccion = null;
	}
	}
	

