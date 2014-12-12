package algo3.algocity.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class TuberiaDeAguaTest {
	
	TuberiaDeAgua unaTuberiaDeAgua;
	TuberiaDeAgua tuberia1,tuberia2,tuberia3,tuberia4;
	PozoDeAgua unPozo;
	Mapa unMapa;
	LineaDeTension linea1;
	CentralEolica unaCentralEolica;
	Industrial unaIndustria;
	
	@Before
	public void setUp() {
		
		unaTuberiaDeAgua = new TuberiaDeAgua();
		tuberia1 = new TuberiaDeAgua(); 
	    tuberia2 = new TuberiaDeAgua();
	    tuberia3 = new TuberiaDeAgua();
	    tuberia4 = new TuberiaDeAgua();
	    unPozo = new PozoDeAgua();
	    unMapa = new Mapa();
	    linea1 = new LineaDeTension();
	    unaCentralEolica = new CentralEolica();
	    unaIndustria = new Industrial();
	}
	
	@Test
	public void crearUnaTuberiaTieneUnCosto() {
		
		assertEquals(5,unaTuberiaDeAgua.costo());
	}

	@Test
	public void noSePuedenConstruirTuberiasDeAguaAsiladas() throws NoSePuedeEdificarEnEsaZonaException{
		
		try{ unMapa.construirTuberiaDeAgua(tuberia1, new Coordenada(2,2));
		} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
	}
	
	@Test
	public void construirUnaTuberiaDeAguaEnElMapa() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unPozo, new Coordenada(2,2));
		unMapa.construirTuberiaDeAgua(tuberia1, new Coordenada(2,1));
		
		assertTrue(null != tuberia1.obtenerHectareaALaQuePertenece());
	}

	@Test 
	public void siSeQuiereConstruirDosTuberiasDeAguaEnElMismoLugarSeLanzaUnaExcepcion() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unPozo, new Coordenada(2,2));
		unMapa.construirTuberiaDeAgua(tuberia1, new Coordenada(1,2));
		
		try{unMapa.construirTuberiaDeAgua(tuberia2, new Coordenada(1,2));
		} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
	}

	@Test
	public void sePuedenConstruirTuberiaDeAguaEnUnaHectareaQueYaTieneUnaLineaDeTension() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unPozo, new Coordenada(2,2));
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(1,1));
		unMapa.construirLineaDeTension(linea1, new Coordenada(1,2));
		unMapa.construirTuberiaDeAgua(tuberia1, new Coordenada(1,2));
		
		assertTrue(null != tuberia1.obtenerHectareaALaQuePertenece());
	}
	
	@Test
	public void seCreaUnaRedDeLineasDeTensionConectadasAUnaCentralElectricaQueProveeDeElectricidadAUnIndustrial() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unPozo, new Coordenada(8,9));
		unMapa.construirTuberiaDeAgua(tuberia1,new Coordenada(8,10));
		unMapa.construirTuberiaDeAgua(tuberia2, new Coordenada(8,11));
		unMapa.construirTuberiaDeAgua(tuberia3, new Coordenada(8,12));
		unMapa.construirTuberiaDeAgua(unaTuberiaDeAgua, new Coordenada(8,13));
		
		assertTrue(unaTuberiaDeAgua.estaConectadoAUnSuministroDeAgua());
		
		unMapa.construirMegaConstruccion(unaIndustria, new Coordenada(9,13));
		
		assertTrue(unaIndustria.estaConectadoAUnSuministroDeAgua());
	
	}
	
}
