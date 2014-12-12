package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class IndustrialTest {

	Industrial unaFabrica;
	Residencial unBarrio;
	Mapa unMapa;
	PozoDeAgua unPozo;
	CentralEolica unaCentral;
	Ruta ruta;
	
	@Before
	public void setUp() throws Exception {
	
		unMapa = new Mapa();	
		unPozo = new PozoDeAgua();
		unaCentral = new CentralEolica();
		unaFabrica = new Industrial();
		unBarrio = new Residencial();
		ruta = new Ruta();
		
		unaCentral.conectarAEntubable(unPozo);
		unMapa.construirMegaConstruccion(unBarrio,new Coordenada(1,1));
		unMapa.construirRuta(ruta,new Coordenada(1,2));
		unMapa.construirMegaConstruccion(unaFabrica, new Coordenada(1,3));
		unBarrio.conectarASiguienteElectrificable(unaCentral);
		unBarrio.conectarAEntubable(unPozo);
		unBarrio.irATrabajarA(unaFabrica);
	}

	@Test
	public void cuandoSeCreaNoDebeTenerEmpleados() {
		
		assertEquals(0,unaFabrica.obtenerCantidadDeEmpleados());
	}
	
	@Test
	public void alSerUnIndustrialTieneUnaCantidadMaximaDeEmpleos(){
		
		assertEquals(25,unaFabrica.maximoDePersonasAEmplearPorHA());
	}

	@Test
	public void alEstarConectadoConUnResidencialAumentaSuCantidadDeEmpleados(){
		
		unBarrio.crecimientoPoblacional();
		assertEquals(4,unaFabrica.obtenerCantidadDeEmpleados());
		
	}
	
	@Test 
	public void laPoblacionSigueCreciendoYLaCantidadDeEmpleados(){
		
		//Si el residencial tiene agua y luz aumenta su poblacion
		
		unBarrio.crecimientoPoblacional();
		unBarrio.crecimientoPoblacional();
		
		assertEquals(8, unaFabrica.obtenerCantidadDeEmpleados());
		assertEquals(0, unBarrio.obtenerPoblacionNoLaboral().obtenerNumeroDeCuidadanos());
		assertEquals(17, unaFabrica.obtenerCantidadDeVacantes());
		
	}
}
