package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.IndiceDeFelicidad;
import algo3.algocity.modelo.IndiceDeFelicidad.EstadosDeFelicidad;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class IndiceDeFelicidadTest {

	IndiceDeFelicidad indiceDeFelicidad;
	EstadosDeFelicidad estado;
	Residencial residencial1;
	Comercial unComercial;
	CentralEolica unaCentralEolica;
	PozoDeAgua unPozo;
	Industrial fabrica1,fabrica2,fabrica3;
	Mapa unMapa;
	Ruta ruta,ruta1;
	
	@Before
	public void setUp() throws Exception {
		
		indiceDeFelicidad= new IndiceDeFelicidad();
		estado = EstadosDeFelicidad.ALTO;
		
		unMapa = new Mapa();
		unaCentralEolica = new CentralEolica();
		unPozo = new PozoDeAgua();
		unComercial = new Comercial();
		fabrica1 = new Industrial();
		residencial1 = new Residencial();
		fabrica2 = new Industrial();
		fabrica3 = new Industrial();
		ruta = new Ruta();
		ruta1 = new Ruta();
		
		unMapa.construirMegaConstruccion(residencial1, new Coordenada(1,1));
		unMapa.construirMegaConstruccion(fabrica1, new Coordenada(7,6));
		unMapa.construirMegaConstruccion(fabrica2, new Coordenada(10,10));
		unMapa.construirMegaConstruccion(fabrica3, new Coordenada(1,4));
		
		unaCentralEolica.conectarAEntubable(unPozo);
		residencial1.conectarASiguienteElectrificable(unaCentralEolica);
		residencial1.conectarAEntubable(unPozo);

	}

	@Test
	public void distintosTiposDeIndicesDeFelicidad() {
		
		indiceDeFelicidad.definirEstado(estado);
		
		assertEquals(estado,indiceDeFelicidad.obtenerIndice());
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadAltoCuandoEstaConectadoAUnIndustrialADistanciaOptima(){
		
		residencial1.conectarAEnrutable(fabrica1);
		residencial1.irATrabajarA(fabrica1);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.ALTO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadMuyBajoCuandoEstaConectadoAUnIndustrialAUnaDistanciaLejana(){
		
		residencial1.conectarAEnrutable(fabrica2);
		residencial1.irATrabajarA(fabrica2);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.MUYBAJO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadBajoCuandoEstaConectadoAUnIndustrialAUnaDistanciaMuyCercana(){
		
		residencial1.conectarAEnrutable(fabrica3);
		residencial1.irATrabajarA(fabrica3);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.BAJO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadPromedioBajoCuandoEstaConectadoAVariasIndustriasUnaLejanaYOtraADistanciaOptima(){
		
		residencial1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(fabrica1);
		
		residencial1.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(fabrica2);
		
		residencial1.irATrabajarA(fabrica1);
		residencial1.irATrabajarA(fabrica2);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.BAJO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadPromedioAltoCuandoEstaConectadoAVariasIndustriasUnaMuyCercanaYOtraADistanciaOptima(){
		
		residencial1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(fabrica1);
		
		residencial1.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(fabrica3);
		
		residencial1.irATrabajarA(fabrica1);
		residencial1.irATrabajarA(fabrica3);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.ALTO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadPromedioBajoCuandoEstaConectadoAVariasIndustriasUnaLejanaYOtraADistanciaMuyCercana(){
		
		residencial1.conectarAEnrutable(fabrica2);
		residencial1.conectarAEnrutable(fabrica3);
		residencial1.irATrabajarA(fabrica2);
		residencial1.irATrabajarA(fabrica3);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.BAJO));
	}
	
	@Test
	public void unResidencialTieneUnEstadoDeFelicidadPromedioBajoCuandoEstaConectadoAVariasIndustriasUnaLejanaOtraADistanciaOptimaYUnaMuyCercana(){
		
		residencial1.conectarAEnrutable(fabrica1);
		residencial1.conectarAEnrutable(fabrica2);
		residencial1.conectarAEnrutable(fabrica3);
		residencial1.irATrabajarA(fabrica1);
		residencial1.irATrabajarA(fabrica2);
		residencial1.irATrabajarA(fabrica3);
		residencial1.crecimientoPoblacional();
		
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.BAJO));
	}
	
}
