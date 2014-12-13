package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.persistencia.PersistirGodzilla;

public class PersistirGodzillaTest {

	PersistirGodzilla persistidor;
	Godzilla godzilla;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirGodzilla();
		godzilla = new Godzilla();
	}

	@Test
	public void persistiendoUnObjetoDeTipoGodzilla(){
		
		persistidor.serializar(godzilla);
		assertEquals("{}",persistidor.obtenerSerializacion());
		
		Godzilla godzillaNuevo = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		//assertEquals(godzilla.patronDeMovimiento(),godzillaNuevo.patronDeMovimiento());
	}

}
