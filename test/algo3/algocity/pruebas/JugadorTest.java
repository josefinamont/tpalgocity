package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Jugador;

public class JugadorTest {

	Jugador unJugador;
	
	@Before
	public void setUp() throws Exception {
		
		unJugador = new Jugador("Juan");
	}

	@Test
	public void unJugadorInicialmenteCuentaCon5000Pesos() {
		
		assertEquals(5000,unJugador.dinero());
	}

	@Test
	public void unJugadorNuevoDebeConstruirseConUnNombre() {
		
		assertEquals("Juan",unJugador.obtenerNombre());
	}
	
	@Test
	public void unJugadorConstruyeYSeleDescuentaElDinero() throws NoSePuedeEdificarEnEsaZonaException {
		
		unJugador.construir(new Residencial(), new Coordenada(13,13));
		assertEquals(4995,unJugador.dinero());
	}
	
	public void seLePaganAlJugadorLosImpuestosDeLaPoblacion(){
		
		unJugador.cobrarPorCiudadano();
		assertEquals(6495,unJugador.dinero());
	}
}
