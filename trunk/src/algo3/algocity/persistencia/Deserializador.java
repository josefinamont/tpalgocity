package algo3.algocity.persistencia;

import java.util.ArrayList;

import algo3.algocity.gestorDeArchivo.GestorArchivo;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.menu.Jugador;

public class Deserializador {

	String nombreDelJugador;
	GestorArchivo gestorDeArchivo;
	Fachada fachada;
	
	public Deserializador(String nombreDelJugador){
		
		this.nombreDelJugador = nombreDelJugador;
		this.gestorDeArchivo = new GestorArchivo();
		this.fachada = new Fachada();
	}
	
	public void deserializarJugador(){
		
		ArrayList<String> jugador = new ArrayList<String>();
		jugador = gestorDeArchivo.levantar(nombreDelJugador,"jugador");
		PersistirJugador persistidor = new PersistirJugador();
		Jugador jugadorDeserializado = null;
		for (String jugadorActual: jugador){
			jugadorDeserializado = persistidor.deserializar(jugadorActual);		
		}
		this.fachada.definirJugador(jugadorDeserializado);
	}
	
	public void deserializarResidenciales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> residenciales = new ArrayList<String>();
		residenciales = gestorDeArchivo.levantar(nombreDelJugador,"residenciales");
		PersistirResidencial persistidor = new PersistirResidencial();
		Residencial residencialDeserializado = null;
		for (String residencial: residenciales){
			residencialDeserializado = persistidor.deserializar(residencial);	
			fachada.jugadorConstruir(residencialDeserializado,residencialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
		}
	}
	
	public void deserializarComerciales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> comerciales = new ArrayList<String>();
		comerciales = gestorDeArchivo.levantar(nombreDelJugador,"comerciales");
		PersistirComercial persistidor = new PersistirComercial();
		Comercial comercialDeserializado = null;
		for (String comercial: comerciales){
			comercialDeserializado = persistidor.deserializar(comercial);	
			fachada.jugadorConstruir(comercialDeserializado,comercialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
		}
	}
	
	public void deserializarIndustriales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> industriales = new ArrayList<String>();
		industriales = gestorDeArchivo.levantar(nombreDelJugador,"industriales");
		PersistirIndustrial persistidor = new PersistirIndustrial();
		Industrial industrialDeserializado = null;
		for (String industrial: industriales){
			industrialDeserializado = persistidor.deserializar(industrial);	
			fachada.jugadorConstruir(industrialDeserializado,industrialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
		}
	}
	
	public void deserializarBomberos() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> bomberos = new ArrayList<String>();
		bomberos = gestorDeArchivo.levantar(nombreDelJugador,"bomberos");
		PersistirEstacionDeBomberos persistidor = new PersistirEstacionDeBomberos();
		EstacionDeBomberos bomberosDeserializados = null;
		for (String estacionDeBomberos: bomberos){
			bomberosDeserializados = persistidor.deserializar(estacionDeBomberos);	
			fachada.jugadorConstruir(bomberosDeserializados,bomberosDeserializados.obtenerHectareaALaQuePertenece().obtenerCoordenada());
		}
	}
	
}
