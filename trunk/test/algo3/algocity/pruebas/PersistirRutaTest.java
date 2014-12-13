package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.persistencia.PersistirRuta;

public class PersistirRutaTest {

	PersistirRuta persistidor;
	Ruta ruta;
	Godzilla godzilla;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirRuta();
		ruta = new Ruta();
		godzilla = new Godzilla();
		
		ruta.aplicarDañoRecibido(godzilla);
	}

	@Test
	public void persistiendoUnaRuta(){
		
		persistidor.serializar(ruta);
		assertEquals("{\"porcentajeDeVida\":20}",persistidor.obtenerSerializacion());
		
		Ruta rutaNueva = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		assertEquals(ruta.porcentajeDeVida(),rutaNueva.porcentajeDeVida());
	}

}
