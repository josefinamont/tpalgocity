package algo3.algocity.modelo.menu;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.mapa.Coordenada;

public class Jugador {

	private int dineroEnPesos;
	private String nombre;
	private transient Partida partida;
	
	public Jugador() {
		
		this.dineroEnPesos = 5000;
		this.nombre = " ";
		this.partida = new Partida();
	}
	
	public Jugador(String nombre) {
		
		this.dineroEnPesos = 5000;
		this.nombre = nombre;
		this.partida = new Partida();
	}
	
	public String obtenerNombre() {
		
		return this.nombre;
	}
	
	public int dinero() {
		
		return this.dineroEnPesos;
	}

	public void cobrarPorCiudadano() {
		
		this.dineroEnPesos += this.partida.obtenerDineroAPagarAlJugadorPorCiudadano();
	}
	
	public void descontarDineroPorConstruir(Construccion construccion){
		
		this.dineroEnPesos = this.dineroEnPesos - construccion.costo();
	}
	
	public void construir(Construccion construccion, Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException {
		
		if (this.dineroEnPesos >= construccion.costo()){
			this.partida.construir(construccion,coordenada);
			this.descontarDineroPorConstruir(construccion);
		}
	}
	
	public void reconstruir(Construccion construccion, Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException{

		this.partida.construir(construccion,coordenada);
	}


	public Partida obtenerPartida() {
		 
		return this.partida;
	}

	public Partida obtenerPartida(Controlador controlador) {
		
		this.partida.definirControlador(controlador);
		return this.partida;
	}

	public void definirPartida(Partida partidaDeserealizada) {
		
		this.partida = partidaDeserealizada;
	}
	
}
