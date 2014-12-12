package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;
import algo3.algocity.modelo.centrales.*;

public class RutaTest {
	
	Ruta unaRuta;
	Ruta ruta1,ruta2,ruta3;
	Residencial unResidencial;
	Industrial unIndustrial;
	Comercial unComercial;
	CentralEolica unaCentralEolica;
	CentralMineral unaCentralMineral;
	CentralNuclear unaCentralNuclear;
	EstacionDeBomberos bomberos;
	
	@Before
	public void setUp() throws Exception {
		
		unaRuta = new Ruta();
		ruta1 = new Ruta();
		ruta2 = new Ruta();
		ruta3 = new Ruta();
		unResidencial = new Residencial();
		unIndustrial = new Industrial();
		unComercial = new Comercial();
		unaCentralEolica = new CentralEolica();
		unaCentralMineral = new CentralMineral();
		unaCentralNuclear = new CentralNuclear();
		bomberos = new EstacionDeBomberos();
	}

	@Test
	public void crearUnaRutaTieneCosto() {
		
		assertEquals(10,unaRuta.costo());
	}
	
	@Test
	public void alCrearUnaNuevaRutaEstaTieneLaVidaCompleta() {
		
		assertTrue(unaRuta.tienePorcentajeDeVida(100));
	}
	
	@Test
	public void dosRutasConectadasEntreSi(){
		
		ruta3.conectarAEnrutable(ruta2);
		
		assertTrue(ruta3.estaConectadoA(ruta2));
	}
	
	@Test
	public void dosRutasNoConectadasEntreSi(){
		
		assertFalse(ruta2.estaConectadoA(ruta1));
	}
	
	@Test
	public void edificiosNoConectadosMedianteRutas(){
		
		assertFalse(unIndustrial.puedeIrA(unResidencial));
		assertFalse(unResidencial.puedeIrA(unComercial));
		assertFalse(unComercial.puedeIrA(unIndustrial));
	}
	
	@Test
	public void unComercialYUnResidencialConectadosMedianteRutas(){
	
		unComercial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unResidencial);
		
		assertTrue(unComercial.puedeIrA(unResidencial));	
	}
	
	@Test
	public void unResidencialYUnIndustrialConectadosMedianteRutas(){
		
		unResidencial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unComercial);
		
		assertTrue(unResidencial.puedeIrA(unComercial));
	}
	
	@Test
	public void unIndustrialYUnResidencialConectadosMedianteRutas(){
		
		unIndustrial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unResidencial);
		
		assertTrue(unIndustrial.puedeIrA(unResidencial));
	}
	
	@Test
	public void bomberosConectadosAUnIndustrial(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unIndustrial);
		
		assertTrue(bomberos.puedeIrA(unIndustrial));
	} 
	
	@Test
	public void bomberosConectadosAUnComercial(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unComercial);
		
		assertTrue(bomberos.puedeIrA(unComercial));
	} 
	
	@Test
	public void bomberosConectadosAUnResidencial(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unResidencial);
		
		assertTrue(bomberos.puedeIrA(unResidencial));
	} 
	
	@Test
	public void bomberosConectadosAUnaCentralEolica(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralEolica);
		
		assertTrue(bomberos.puedeIrA(unaCentralEolica));
	} 
	
	@Test
	public void bomberosConectadosAUnaCentralMineral(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralMineral);
		
		assertTrue(bomberos.puedeIrA(unaCentralMineral));
	} 
	
	@Test
	public void bomberosConectadosAUnaCentralNuclear(){
		
		bomberos.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralNuclear);
		
		assertTrue(bomberos.puedeIrA(unaCentralNuclear));
	}
	
	@Test
	public void rutasQueConectanUnResidencialYUnComercialDebenPermitirLaDobleConexion(){
		
		unComercial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unResidencial);
		
		assertTrue(unComercial.puedeIrA(unResidencial));	
		assertTrue(unResidencial.puedeIrA(unComercial));
	}
	
	@Test
	public void rutasQueConectanUnIndustrialYUnComercialDebenPermitirLaDobleConexion(){
		
		unComercial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unIndustrial);
		
		assertTrue(unComercial.puedeIrA(unIndustrial));	
		assertTrue(unIndustrial.puedeIrA(unComercial));
	}
	
	@Test
	public void rutasQueConectanUnResidencialYUnIndustrialDebenPermitirLaDobleConexion(){
		
		unResidencial.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unIndustrial);
		
		assertTrue(unResidencial.puedeIrA(unIndustrial));	
		assertTrue(unIndustrial.puedeIrA(unResidencial));
	}
	
	@Test
	public void rutasQueConectanUnaCentralEolicaConLosBomberosDebenPermitirLaDobleConexion(){
		
		unaCentralEolica.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(bomberos);
		
		assertTrue(unaCentralEolica.puedeIrA(bomberos));	
		assertTrue(bomberos.puedeIrA(unaCentralEolica));
	}
	
	@Test
	public void rutasQueConectanUnaCentralMineralConLosBomberosDebenPermitirLaDobleConexion(){
		
		unaCentralMineral.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(bomberos);
		
		assertTrue(unaCentralMineral.puedeIrA(bomberos));	
		assertTrue(bomberos.puedeIrA(unaCentralMineral));
	}
	
	@Test
	public void rutasQueConectanUnaCentralNuclearConLosBomberosDebenPermitirLaDobleConexion(){
		
		unaCentralNuclear.conectarAEnrutable(ruta3);
		ruta3.conectarAEnrutable(ruta2);
		ruta2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(bomberos);
		
		assertTrue(unaCentralNuclear.puedeIrA(bomberos));	
		assertTrue(bomberos.puedeIrA(unaCentralNuclear));
	}
	
	@Test
	public void seCreaUnaRedDeRutasQueConectanBomberosYUnResidencial() throws NoSePuedeEdificarEnEsaZonaException{
		
		Mapa unMapa = new Mapa();
		unMapa.construirMegaConstruccion(unResidencial, new Coordenada(10,15));
		unMapa.construirRuta(ruta1, new Coordenada(10,16));
		unMapa.construirRuta(ruta2, new Coordenada(10,17));
		unMapa.construirRuta(ruta3, new Coordenada(10,18));
		unMapa.construirMegaConstruccion(bomberos, new Coordenada(10,19));
		
		assertTrue(bomberos.puedeIrA(unResidencial));
	}
	
}
