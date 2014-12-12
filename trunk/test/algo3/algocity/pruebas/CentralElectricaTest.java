package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import algo3.algocity.modelo.centrales.*;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class CentralElectricaTest {

	Mapa mapa;
	CentralEolica unaCentralEolica;
	CentralMineral unaCentralMineral;
	CentralNuclear unaCentralNuclear;
	Residencial unResidencial;
	Comercial unComercial;
	Industrial unIndustrial;
	
	@Before
	public void setUp() throws NoSePuedeEdificarEnEsaZonaException {
		
		mapa = new Mapa();
		unaCentralEolica = new CentralEolica();
	    unaCentralMineral = new CentralMineral();
	    unaCentralNuclear = new CentralNuclear();
	    unResidencial = new Residencial();
	    unComercial = new Comercial();
	    unIndustrial = new Industrial();
	    
	    mapa.construirMegaConstruccion(unResidencial,new Coordenada(6,6));
	    mapa.construirMegaConstruccion(unComercial,new Coordenada(7,7));
		mapa.construirMegaConstruccion(unIndustrial,new Coordenada(12,12));
		mapa.construirMegaConstruccion(unaCentralEolica,new Coordenada(13,21));
		mapa.construirMegaConstruccion(unaCentralMineral,new Coordenada(12,21));
		mapa.construirMegaConstruccion(unaCentralNuclear,new Coordenada(11,20));
	}
	
	@Test
	public void todasLasCentralesTienenUnCosto(){
		
		assertEquals(1000,unaCentralEolica.costo());
		assertEquals(3000,unaCentralMineral.costo());
		assertEquals(10000,unaCentralNuclear.costo());
	}
	
   @Test
	public void abastecimientoDeLasCentralesEnMW(){
		
		assertEquals(100,unaCentralEolica.abasteceEnMW());
		assertEquals(400,unaCentralMineral.abasteceEnMW());
		assertEquals(1000,unaCentralNuclear.abasteceEnMW());
	}
   
   @Test
   public void radioDeAbastecimientoSinLineasDeTension(){
   
        assertEquals(4,unaCentralEolica.radioDeAbastecimientoEnHa());
        assertEquals(10,unaCentralMineral.radioDeAbastecimientoEnHa());
        assertEquals(25,unaCentralNuclear.radioDeAbastecimientoEnHa());
   }
   
}
