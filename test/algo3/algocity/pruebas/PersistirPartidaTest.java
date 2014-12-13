package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.CaminarEnDiagonal;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Partida;
import algo3.algocity.persistencia.PersistirPartida;

public class PersistirPartidaTest {
	
	PersistirPartida persistidor;
	Partida partida;

	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirPartida();
		partida = new Partida();
		partida.pasaUnTurno();
		partida.obtenerMapa().modificarPosicionDeLaCatastrofe(new Coordenada(6,6));
		partida.obtenerMapa().ubicarCatastrofeEn( new Godzilla(new CaminarEnDiagonal(new Coordenada(6,6),14,23)), new Coordenada(6,6) );
		
	}

	@Test
	public void persistiendoUnaPartida(){
		
		persistidor.serializar(partida);
		//assertEquals("{\"porcentajeDeVida\":20}",persistidor.obtenerSerializacion());
		
		Partida partidaNueva = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		assertEquals(partida.obtenerTurnos(),partidaNueva.obtenerTurnos());
		//assertEquals(partida.obtenerMapa().obtenerPosicionDeLaCatastrofe().obtenerPosicionX(),partidaNueva.obtenerMapa().obtenerPosicionDeLaCatastrofe().obtenerPosicionX());
	}


}
