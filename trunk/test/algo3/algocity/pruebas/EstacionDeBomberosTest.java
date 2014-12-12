package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.mapa.Mapa;

public class EstacionDeBomberosTest {
	
	Mapa mapa;
	PozoDeAgua unPozoDeAgua;
	Residencial unaResidencia;
	EstacionDeBomberos bomberos;
	Hectarea hectarea1,hectarea2,hectarea3;
	CentralNuclear unaCentralNuclear;
	TuberiaDeAgua tuberia1,tuberia2;
	LineaDeTension lineaDeTension1,lineaDeTension2;
	
	@Before
	public void setUp() throws NoSePuedeEdificarEnEsaZonaException {
		
		mapa = new Mapa();
		unaResidencia = new Residencial();
		bomberos = new EstacionDeBomberos();
		unaCentralNuclear = new CentralNuclear();
		unPozoDeAgua = new PozoDeAgua();
		lineaDeTension1 = new LineaDeTension();
		lineaDeTension2 = new LineaDeTension();
		tuberia1 = new TuberiaDeAgua();
		tuberia2 = new TuberiaDeAgua();
		
		mapa.construirEstacionDeBomberos(bomberos,new Coordenada(1,1));
		mapa.construirMegaConstruccion(unaResidencia,new Coordenada(10,11));
		mapa.construirMegaConstruccion(unaCentralNuclear,new Coordenada(12,12));
		
		bomberos.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		lineaDeTension1.conectarASiguienteElectrificable(unaCentralNuclear);
		
		bomberos.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		tuberia1.conectarAEntubable(unPozoDeAgua);
	
	}
	
	@Test
	public void crearUnaEstacionDeBomberosTieneUnCosto() {
		
		assertEquals(1500,bomberos.costo());
	}

	@Test
	public void losBomberosTienenUnRadioDeAccionPredefinido() {
		
		assertEquals(7,bomberos.radioDeAccionEnHa());
	}
	
	@Test
	public void verificarQueUnaMegaConstruccionEstaEnElRadioDeAccionDeLosBomberos() {
		
		assertTrue(bomberos.obtenerHectareaALaQuePertenece().seEncuentraDentroDelRadioDeAccion(unaResidencia.obtenerHectareaALaQuePertenece()));
	}
	
	@Test 
	public void verificarQueUnaMegaConstruccionNoEstaEnElRadioDeAccionDeLosBomberos() {
		
		assertFalse(bomberos.obtenerHectareaALaQuePertenece().seEncuentraDentroDelRadioDeAccion(unaCentralNuclear.obtenerHectareaALaQuePertenece()));
	}
	
}
