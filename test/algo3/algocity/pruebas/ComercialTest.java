package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class ComercialTest {

	Comercial unComercial;
	Residencial residencial1,residencial2,residencial3,residencial4;
	CentralEolica unaCentralEolica;
	PozoDeAgua unPozo;
	Industrial unaFabrica;
	Mapa unMapa;
	Ruta ruta,ruta1,ruta2;
	
	@Before
	public void setUp() throws Exception {
		
		unMapa = new Mapa();
		ruta = new Ruta();
		ruta1 = new Ruta();
		ruta2 = new Ruta();
		unComercial = new Comercial();
		unaFabrica = new Industrial();
		unPozo = new PozoDeAgua();
		unaCentralEolica = new CentralEolica();
		residencial1 = new Residencial();
		residencial2 = new Residencial();
		residencial3 = new Residencial();
		residencial4 = new Residencial();
		
		unMapa.construirMegaConstruccion(residencial1, new Coordenada(1,1));
		unMapa.construirMegaConstruccion(unaFabrica, new Coordenada(5,5));
		residencial2.modificarCantidadDePoblacion(40);
		residencial3.modificarCantidadDePoblacion(100);
		residencial4.modificarCantidadDePoblacion(100);
		unaCentralEolica.conectarAEntubable(unPozo);
		residencial1.conectarASiguienteElectrificable(unaCentralEolica);
		residencial1.conectarAEntubable(unPozo);
		
		residencial1.conectarAEnrutable(unaFabrica);
		residencial1.irATrabajarA(unaFabrica);
		
		residencial2.conectarAEnrutable(unComercial);
		residencial3.conectarAEnrutable(unComercial);
		residencial4.conectarAEnrutable(unComercial);
	}

	@Test
	public void unComercialTieneUnaCapacidad() {
		
		assertEquals(300,unComercial.capacidadMaximaDePersonas());
	}
	
	@Test
	public void unComercialDebeTenerUnContadorDeCantidadDeClientes() {
		
		residencial2.irDeCompras(unComercial);
		residencial3.irDeCompras(unComercial);
		assertEquals(140,unComercial.obtenerCantidadDeClientesActual());
	}

	@Test
	public void unComercialNoAgregaMasResidencialesSiYaTiene3Residenciales() {
		
		residencial1.irDeCompras(unComercial);
		residencial2.irDeCompras(unComercial);
		residencial3.irDeCompras(unComercial);
		residencial4.irDeCompras(unComercial);
		
		residencial1.crecimientoPoblacional();

		assertEquals(240,unComercial.obtenerCantidadDeClientesActual());
	    assertEquals(10,residencial1.actualizarPoblacion());
	}
	
	@Test
	public void unComercialAumentaElCrecimientoPoblacionDeUnaResidenciaVinculada(){
		
		residencial1.conectarAEnrutable(unComercial);
		residencial1.irDeCompras(unComercial);
		residencial1.crecimientoPoblacional();
		
		assertEquals(14,residencial1.actualizarPoblacion());
		
	}
}
