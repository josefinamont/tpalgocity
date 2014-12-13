package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.persistencia.PersistirResidencial;

public class PersistirResidencialTest {

	PersistirResidencial persistidor;
	Residencial residencial;
	Godzilla godzilla;
	
	@Before
	public void setUp() throws Exception {
		
		persistidor = new PersistirResidencial();
		residencial = new Residencial();
		godzilla = new Godzilla();
		
		residencial.aplicarDañoRecibido(godzilla);
	}

	@Test
	public void persistiendoUnResidencial(){
		
		persistidor.serializar(residencial);
		assertEquals("{\"poblacionActual\":{\"numeroDeCiudadanos\":0},\"cantidadDePoblacionNoLaboral\":{\"numeroDeCiudadanos\":0},\"indice\":{},\"porcentajeDeVida\":0}",persistidor.obtenerSerializacion());
		
		Residencial residencialNuevo = persistidor.deserializar(persistidor.obtenerSerializacion());
		
		assertEquals(residencial.porcentajeDeVida(),residencialNuevo.porcentajeDeVida());
	}

}
