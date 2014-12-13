package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.mapa.Mapa;
import algo3.algocity.modelo.menu.Partida;
import algo3.algocity.persistencia.PersistirMapa;
import algo3.algocity.persistencia.PersistirPartida;

public class PersistirMapaTest {

	PersistirMapa persistidor;
	Mapa mapa;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirMapa();
		mapa = new Mapa();
	}

	@Test
	public void persistiendoUnaPartida(){
		
		persistidor.serializar(mapa);
		//assertEquals("{\"porcentajeDeVida\":20}",persistidor.obtenerSerializacion());
		
		Mapa nuevoMapa = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		//assertEquals(mapa.obtenerPosicionDeLaCatastrofe().obtenerPosicionX(),nuevoMapa.obtenerPosicionDeLaCatastrofe().obtenerPosicionX());
	}
}
