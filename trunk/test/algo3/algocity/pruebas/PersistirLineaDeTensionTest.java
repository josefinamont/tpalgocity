package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.persistencia.PersistirLineaDeTension;

public class PersistirLineaDeTensionTest {

	PersistirLineaDeTension persistidor;
	LineaDeTension lineaDeTension;
	Godzilla godzilla;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirLineaDeTension();
		lineaDeTension = new LineaDeTension();
		godzilla = new Godzilla();
		
		lineaDeTension.aplicarDañoRecibido(godzilla);
	}

	@Test
	public void persistiendoUnaRuta(){
		
		persistidor.serializar(lineaDeTension);
		assertEquals("{\"porcentajeDeVida\":0}",persistidor.obtenerSerializacion());
		
		LineaDeTension lineaNueva = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		assertEquals(lineaDeTension.porcentajeDeVida(),lineaNueva.porcentajeDeVida());
	}

}
