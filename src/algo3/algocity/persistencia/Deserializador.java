package algo3.algocity.persistencia;

import java.util.ArrayList;

import algo3.algocity.gestorDeArchivo.GestorArchivo;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.centrales.CentralMineral;
import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.menu.Jugador;
import algo3.algocity.modelo.menu.Partida;

public class Deserializador {

	String nombreDelJugador;
	GestorArchivo gestorDeArchivo;
	Fachada fachada;
	
	public Deserializador(String nombreDelJugador){
		
		this.nombreDelJugador = nombreDelJugador;
		this.gestorDeArchivo = new GestorArchivo();
		this.fachada = new Fachada();
	}
	
	public Deserializador(){
		
		this.gestorDeArchivo = new GestorArchivo();
		this.fachada = new Fachada();
	}
	
	public void deserializarJugador(){
		
		ArrayList<String> jugador = new ArrayList<String>();
		jugador = gestorDeArchivo.levantar(nombreDelJugador,"jugador");
		if (jugador != null) {
			PersistirJugador persistidor = new PersistirJugador();
			Jugador jugadorDeserializado = null;
			for (String jugadorActual: jugador){
				jugadorDeserializado = persistidor.deserializar(jugadorActual);		
			}
			this.fachada.definirJugador(jugadorDeserializado);
		}
	}
	
	public void deserializarPartida(){
		
		ArrayList<String> partida = new ArrayList<String>();
		partida = gestorDeArchivo.levantar(nombreDelJugador,"partida");
		if (partida != null) {
			PersistirPartida persistidor = new PersistirPartida();
			Partida partidaDeserealizada = null;
			for (String partidaActual: partida){
				partidaDeserealizada = persistidor.deserializar(partidaActual);		
			}
			this.fachada.obtenerJugador().definirPartida(partidaDeserealizada);
		}
	}
	
	public void deserializarResidenciales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> residenciales = new ArrayList<String>();
		residenciales = gestorDeArchivo.levantar(nombreDelJugador,"residenciales");
		if (residenciales != null) {
			PersistirResidencial persistidor = new PersistirResidencial();
			Residencial residencialDeserializado = null;
			for (String residencial: residenciales){
				residencialDeserializado = persistidor.deserializar(residencial);	
				fachada.jugadorConstruir(residencialDeserializado,residencialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarComerciales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> comerciales = new ArrayList<String>();
		comerciales = gestorDeArchivo.levantar(nombreDelJugador,"comerciales");
		if (comerciales != null) {
			PersistirComercial persistidor = new PersistirComercial();
			Comercial comercialDeserializado = null;
			for (String comercial: comerciales){
				comercialDeserializado = persistidor.deserializar(comercial);	
				fachada.jugadorConstruir(comercialDeserializado,comercialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarIndustriales() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> industriales = new ArrayList<String>();
		industriales = gestorDeArchivo.levantar(nombreDelJugador,"industriales");
		if (industriales != null) {
			PersistirIndustrial persistidor = new PersistirIndustrial();
			Industrial industrialDeserializado = null;
			for (String industrial: industriales){
				industrialDeserializado = persistidor.deserializar(industrial);	
				fachada.jugadorConstruir(industrialDeserializado,industrialDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarBomberos() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> bomberos = new ArrayList<String>();
		bomberos = gestorDeArchivo.levantar(nombreDelJugador,"bomberos");
		if (bomberos != null) {
			PersistirEstacionDeBomberos persistidor = new PersistirEstacionDeBomberos();
			EstacionDeBomberos bomberosDeserializados = null;
			for (String estacionDeBomberos: bomberos){
				bomberosDeserializados = persistidor.deserializar(estacionDeBomberos);	
				fachada.jugadorConstruir(bomberosDeserializados,bomberosDeserializados.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarCentralEolica() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> centrales = new ArrayList<String>();
		centrales = gestorDeArchivo.levantar(nombreDelJugador,"centrales eolicas");
		if (centrales != null) {
			PersistirCentralEolica persistidor = new PersistirCentralEolica();
			CentralEolica centralDeserializada = null;
			for (String centralActual: centrales){
				centralDeserializada = persistidor.deserializar(centralActual);	
				fachada.jugadorConstruir(centralDeserializada,centralDeserializada.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarCentralNuclear() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> centrales = new ArrayList<String>();
		centrales = gestorDeArchivo.levantar(nombreDelJugador,"centrales nucleares");
		if (centrales != null) {
			PersistirCentralNuclear persistidor = new PersistirCentralNuclear();
			CentralNuclear centralDeserializada = null;
			for (String centralActual: centrales){
				centralDeserializada = persistidor.deserializar(centralActual);	
				fachada.jugadorConstruir(centralDeserializada,centralDeserializada.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarCentralMineral() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> centrales = new ArrayList<String>();
		centrales = gestorDeArchivo.levantar(nombreDelJugador,"centrales minerales");
		if (centrales != null) {
			PersistirCentralMineral persistidor = new PersistirCentralMineral();
			CentralMineral centralDeserializada = null;
			for (String centralActual: centrales){
				centralDeserializada = persistidor.deserializar(centralActual);	
				fachada.jugadorConstruir(centralDeserializada,centralDeserializada.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}

	public void deserializarPozos() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> pozos = new ArrayList<String>();
		pozos = gestorDeArchivo.levantar(nombreDelJugador,"pozos");
		if (pozos != null){
			PersistirPozoDeAgua persistidor = new PersistirPozoDeAgua();
			PozoDeAgua pozoDeAguaDeserializado = null;
			for (String pozoDeAgua: pozos){
				pozoDeAguaDeserializado = persistidor.deserializar(pozoDeAgua);	
				fachada.jugadorConstruir(pozoDeAguaDeserializado,pozoDeAguaDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public ArrayList<String> deserializarListaDeJugadores(){
		
		ArrayList<String> listaDeJugadores = new ArrayList<String>();
		ArrayList<String> listaDeserializadaDeJugadores = new ArrayList<String>();
		listaDeJugadores = gestorDeArchivo.levantar("lista de jugadores","jugadores");
		if (listaDeJugadores != null) {
			PersistirListaDeJugadores persistidor = new PersistirListaDeJugadores();
			String nombreDeJugadorDeserializado = null;
			for (String nombreDelJugadorSerializado: listaDeJugadores){
				nombreDeJugadorDeserializado = persistidor.deserializar(nombreDelJugadorSerializado);
				listaDeserializadaDeJugadores.add(nombreDeJugadorDeserializado);
			}
			return listaDeserializadaDeJugadores;
		} else return null;
	}
	
	public void deserializarTuberiasDeAgua() throws NoSePuedeEdificarEnEsaZonaException{
		
		ArrayList<String> tuberias = new ArrayList<String>();
		tuberias = gestorDeArchivo.levantar(nombreDelJugador,"tuberias de agua");
		if (tuberias != null) {
			PersistirTuberiaDeAgua persistidor = new PersistirTuberiaDeAgua();
			TuberiaDeAgua tuberiaDeserializada = null;
			for (String tuberiaDeAgua: tuberias){
				tuberiaDeserializada = persistidor.deserializar(tuberiaDeAgua);	
				fachada.jugadorConstruir(tuberiaDeserializada,tuberiaDeserializada.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			}
		}
	}
	
	public void deserializarRutas() throws NoSePuedeEdificarEnEsaZonaException{
		  
		  ArrayList<String> rutas = new ArrayList<String>();
		  rutas = gestorDeArchivo.levantar(nombreDelJugador,"rutas");
		  if (rutas != null){
			  PersistirRuta persistidor = new PersistirRuta();
			  Ruta rutaDeserializado = null;
			  for (String rutaADeserializar: rutas){
				  rutaDeserializado = persistidor.deserializar(rutaADeserializar); 
				  fachada.jugadorConstruir(rutaDeserializado,rutaDeserializado.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			  }
		  }
	}
	
	public void deserializarLineasDeTension() throws NoSePuedeEdificarEnEsaZonaException{
		  
		  ArrayList<String> lineas = new ArrayList<String>();
		  lineas = gestorDeArchivo.levantar(nombreDelJugador,"lineas de tension");
		  if (lineas != null) {
			  PersistirLineaDeTension persistidor = new PersistirLineaDeTension();
			  LineaDeTension lineaDeserializada = null;
			  for (String lineaADeserializar: lineas){
				  lineaDeserializada = persistidor.deserializar(lineaADeserializar); 
				  fachada.jugadorConstruir(lineaDeserializada,lineaDeserializada.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			  }
		  }
	}
	
	public Fachada deserializarTodo() throws NoSePuedeEdificarEnEsaZonaException{
		
		this.deserializarJugador();
		this.deserializarPartida();
		this.deserializarResidenciales();
		this.deserializarPozos();
		this.deserializarBomberos();
		this.deserializarComerciales();
		this.deserializarIndustriales();
		this.deserializarCentralEolica();
		this.deserializarCentralNuclear();
		this.deserializarCentralMineral();
		this.deserializarTuberiasDeAgua();
		this.deserializarRutas();
		this.deserializarLineasDeTension();
		
		return this.fachada;
	}

}
