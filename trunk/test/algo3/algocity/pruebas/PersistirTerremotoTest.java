package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.Terremoto;
import algo3.algocity.persistencia.PersistirTerremoto;

public class PersistirTerremotoTest {

	PersistirTerremoto persistidor;
	Terremoto terremoto;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirTerremoto();
		terremoto = new Terremoto();
	}

	@Test
	public void persistiendoUnTerremoto(){
		
		persistidor.serializar(terremoto);
		assertEquals("{}",persistidor.obtenerSerializacion());
		
		Terremoto nuevoTerremoto = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		//assertEquals(terremoto.porcentajeDeVida(),nuevoTerremoto.porcentajeDeVida());
	}


}
