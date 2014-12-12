package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;
import algo3.algocity.modelo.menu.Partida;

public class PartidaTest {

	Partida unaPartida;
	Residencial residencial1,residencial2,residencial3,residencial4;
	Comercial unComercial;
	CentralEolica unaCentralEolica;
	PozoDeAgua unPozo;
	Industrial fabrica1,fabrica2;
	Mapa unMapa;
	Ruta ruta1,ruta2;
	
	@Before
	public void setUp() throws Exception {
		
		unMapa = new Mapa();
		unaCentralEolica = new CentralEolica();
		unPozo = new PozoDeAgua();
		unaPartida = new Partida();
		ruta1 = new Ruta();
		ruta2 = new Ruta();
		
		fabrica1 = new Industrial();
		fabrica2 = new Industrial();
		residencial1 = new Residencial();
		residencial2 = new Residencial();
		residencial3 = new Residencial();
		residencial4 = new Residencial();

		unaCentralEolica.conectarAEntubable(unPozo);
		
		unaPartida.construir(residencial1, new Coordenada(1,1));
		unaPartida.construir(residencial2, new Coordenada(6,6));
		unaPartida.construir(residencial3, new Coordenada(1,2));
		unaPartida.construir(residencial4, new Coordenada(6,5));
		unaPartida.construir(fabrica1, new Coordenada(7,7));
		unaPartida.construir(fabrica2, new Coordenada(13,20));
		
		residencial1.conectarASiguienteElectrificable(unaCentralEolica);
		residencial1.conectarAEntubable(unPozo);
		
		residencial2.conectarASiguienteElectrificable(unaCentralEolica);
		residencial2.conectarAEntubable(unPozo);
	}

	@Test
	public void cadaHabitanteLePaga10PesosAlJugador() {
		unaPartida.setearPoblacion(150);
		assertEquals(1500,unaPartida.obtenerDineroAPagarAlJugadorPorCiudadano());
	}
	
	@Test
	public void seObtieneLaPoblacionTotalDeLaCiudad() {
		
		unaPartida.pasaUnTurno();
		
		assertTrue(unaPartida.tienePoblacionActual(4));
		
		residencial3.conectarASiguienteElectrificable(unaCentralEolica);
		residencial3.conectarAEntubable(unPozo);
	
		unaPartida.pasaUnTurno();
		
		assertTrue(unaPartida.tienePoblacionActual(10));
	}
	
	@Test
	public void seCalculaIndiceDeFelcidadGlobalDeLaCuidad(){
		
		residencial1.conectarAEnrutable(fabrica1);
		residencial1.irATrabajarA(fabrica1);
		residencial2.conectarAEnrutable(fabrica2);
		residencial2.irATrabajarA(fabrica2);
		unaPartida.pasaUnTurno();
			
		assertEquals(71,unaPartida.obtenerIndiceDeFelicidadGlobal());
		
	}
	
	@Test
	public void seCalculaIndiceDeFelcidadGlobalDeLaCuidadConMasResidencialesQueNoTienenPoblacionConEmpleo(){
		
		residencial3.conectarASiguienteElectrificable(unaCentralEolica);
		residencial3.conectarAEntubable(unPozo);
		
		residencial4.conectarASiguienteElectrificable(unaCentralEolica);
		residencial4.conectarAEntubable(unPozo);
		
		residencial1.conectarAEnrutable(fabrica1);
		residencial1.irATrabajarA(fabrica1);
		residencial2.conectarAEnrutable(fabrica2);
		residencial2.irATrabajarA(fabrica2);
		
		unaPartida.pasaUnTurno();
				
		assertEquals(47,unaPartida.obtenerIndiceDeFelicidadGlobal());
		
	}
	
}
