package algo3.algocity.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;






//import algo3.algocity.modelo.Electrificable;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class LineasDeTensionTest {

	CentralEolica unaCentralEolica;
	Industrial unaIndustria,otraIndustria;
	LineaDeTension unaLineaDeTension;
	LineaDeTension lineaDeTension1,lineaDeTension2,lineaDeTension3;
	Mapa unMapa;
	TuberiaDeAgua tuberia,otraTuberia;
	PozoDeAgua unPozo;
	
	@Before
	public void setUp() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaIndustria = new Industrial(); 
		otraIndustria = new Industrial(); 
		unaCentralEolica = new CentralEolica();
		unaLineaDeTension = new LineaDeTension();
		lineaDeTension1 = new LineaDeTension();
		lineaDeTension2 = new LineaDeTension();
		lineaDeTension3 = new LineaDeTension();
		tuberia = new TuberiaDeAgua();
		otraTuberia = new TuberiaDeAgua();
		unMapa = new Mapa();
		unPozo = new PozoDeAgua();
		
		unaCentralEolica.conectarAEntubable(otraTuberia);
		otraTuberia.conectarAEntubable(unPozo);
	}
	
	@Test
	public void unaLineaDeTensionTieneUnCosto(){
		
		
		assertEquals(5,unaLineaDeTension.costo());
	}
	
	@Test
   	public void unEdificioConectadoAUnaCentralTieneElectricidad(){
		
		unaIndustria.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		lineaDeTension1.conectarASiguienteElectrificable(unaLineaDeTension);
		unaLineaDeTension.conectarASiguienteElectrificable(unaCentralEolica);
		
		assertTrue(unaIndustria.tieneElectricidad());
	}
	
	@Test
	public void noSePuedenConstruirLineasDeTensionAisladas() throws NoSePuedeEdificarEnEsaZonaException{
		
		try{ unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(2,2));
		} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
	}
	
	@Test
	public void construirUnaLineaDeTensionEnElMapa() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(2,11));
		unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(2,12));
		assertTrue(null != lineaDeTension1.obtenerHectareaALaQuePertenece());
	}
	
	@Test 
	public void siSeQuiereConstruirDosLineasDeTensionEnElMismoLugarSeLanzaUnaExcepcion() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(6,6));
		unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(6,7));
		
		try{unMapa.construirLineaDeTension(lineaDeTension2, new Coordenada(6,7));
		} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
	}

	@Test
	public void sePuedeConstruirUnaLineaDeTensionEnUnaHectareaQueYaTieneTuberiaDeAgua() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unPozo, new Coordenada(2,2));
		unMapa.construirTuberiaDeAgua(tuberia, new Coordenada(1,2));
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(1,1));
		unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(1,2));
		
		assertTrue(null != lineaDeTension1.obtenerHectareaALaQuePertenece());
	}
	
	@Test
	public void seCreaUnaRedDeLineasDeTensionConectadasAUnaCentralElectricaQueProveeDeElectricidadAUnIndustrial() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(10,15));
		unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(10,16));
		unMapa.construirLineaDeTension(lineaDeTension2, new Coordenada(10,17));
		unMapa.construirLineaDeTension(lineaDeTension3, new Coordenada(10,18));
		unMapa.construirMegaConstruccion(otraIndustria, new Coordenada(10,19));
		
		assertTrue(otraIndustria.estaConectadoAUnSuministroElectrico());
	}
	
	@Test
	public void seCreaUnaRedDeLineasDeTensionConectadasAUnaCentralElectricaQueProveeDeElectricidadAUnIndustrialQueSeConstruyeAlFinal() throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(unaCentralEolica, new Coordenada(13,10));
		unMapa.construirLineaDeTension(lineaDeTension1, new Coordenada(13,11));
		unMapa.construirLineaDeTension(lineaDeTension2, new Coordenada(13,12));
		unMapa.construirLineaDeTension(lineaDeTension3, new Coordenada(12,12));
		unMapa.construirLineaDeTension(unaLineaDeTension, new Coordenada(13,13));
		unMapa.construirMegaConstruccion(otraIndustria, new Coordenada(12,12));
		assertTrue(otraIndustria.estaConectadoAUnSuministroElectrico());
	}
	
}

