package algo3.algocity.modelo;

import java.util.ArrayList;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Jugador;

public class Fachada {

	private Jugador unJugador;
	
	public Fachada(){};
	
	public Fachada(Jugador jugador){
		
		this.unJugador = jugador;
	}

	public void jugadorConstruir(Construccion construccion,Coordenada coordenadaDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException {
		
	  this.unJugador.construir(construccion, coordenadaDeConstruccion);
	}
	
	public void jugadorReconstruir(Construccion construccion,Coordenada coordenadaDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException {
		
		this.unJugador.reconstruir(construccion, coordenadaDeConstruccion);
	}
	
	public Jugador obtenerJugador(){
		
		return this.unJugador;
	}

	public void pasarUnTurno() {
		
		this.unJugador.obtenerPartida().pasaUnTurno();
	}
	
	public ArrayList<MegaConstruccion> obtenerMegaConstrucciones(){
	
		return this.unJugador.obtenerPartida().obtenerMapa().obtenerMegaConstrucciones();
	}

	public void definirJugador(Jugador jugador) {
		
		this.unJugador = jugador;
	}
}
