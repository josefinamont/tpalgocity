package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Jugador;

public class FachadaTest {

	Fachada unaFachada;
	LineaDeTension unaLinea;
	CentralEolica unaCentral;
	Coordenada coordenadaLinea;
	Coordenada coordenadaCentral;
	PozoDeAgua unPozo;
	TuberiaDeAgua unaTuberia;
	EstacionDeBomberos bomberos;
	Jugador unJugador;
	
	@Before
	public void setUp() throws Exception {
	
		unJugador = new Jugador();	
		unaFachada = new Fachada(unJugador);
		unaLinea = new LineaDeTension();
		unaCentral = new CentralEolica();
		coordenadaCentral = new Coordenada(1,2);
		coordenadaLinea =  new Coordenada(1,3);
		unPozo = new PozoDeAgua();
		unaTuberia = new TuberiaDeAgua();
		bomberos = new EstacionDeBomberos();
		
		unaCentral.conectarAEntubable(unPozo);
	}

	@Test
	public void interaccionConLaFachadaParaConstruirUnaCentralNuclear() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.jugadorConstruir(unaCentral, coordenadaCentral);		
		assertTrue(null != unaCentral.obtenerHectareaALaQuePertenece());
	}

	
	@Test
	public void interaccionConLaFachadaParaConstruirUnaLineaDeTension() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.jugadorConstruir(unaCentral,coordenadaCentral);
		unaFachada.jugadorConstruir(unaLinea,coordenadaLinea);
		
		assertTrue(null != unaLinea.obtenerHectareaALaQuePertenece());
		assertTrue(unaLinea.estaConectadoAUnSuministroElectrico());
	}
	
	@Test
	public void interaccionConLaFachadaParaConstruirUnPozoDeAgua() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.jugadorConstruir(unPozo, new Coordenada(2,2));
		assertTrue(null != unPozo.obtenerHectareaALaQuePertenece());
	}
	

	@Test
	public void interaccionConLaFachadaParaConstruirUnTuberiaDeAgua() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.jugadorConstruir(unPozo, new Coordenada(3,3));
		unaFachada.jugadorConstruir(unaTuberia,new Coordenada(3,4));
		assertTrue(null != unaTuberia.obtenerHectareaALaQuePertenece());
	}
	
	@Test
	public void interaccionConLaFachadaParaConstruirUnaEstacionDeBomberos() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.jugadorConstruir(bomberos,new Coordenada(5,5));
		assertTrue(null != bomberos.obtenerHectareaALaQuePertenece());
	}
	
	@Test
	public void interaccionConLaFachadaParaPasarUnTurno() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaFachada.pasarUnTurno();
		
		assertTrue(unaFachada.obtenerJugador().obtenerPartida().vaPorElTurno(1));
	}
	

}
