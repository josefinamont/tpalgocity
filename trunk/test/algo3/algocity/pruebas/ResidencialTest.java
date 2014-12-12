package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.IndiceDeFelicidad.EstadosDeFelicidad;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class ResidencialTest {

	Residencial residencial1,residencial2,residencial3,residencial4;
	Comercial unComercial;
	CentralEolica unaCentralEolica;
	PozoDeAgua unPozo;
	Industrial fabrica1,fabrica2;
	Mapa unMapa;
	Ruta ruta1;
	
	@Before
	public void setUp() throws Exception {
	    
		unMapa = new Mapa();
		unaCentralEolica = new CentralEolica();
		unPozo = new PozoDeAgua();
		unComercial = new Comercial();
		fabrica1 = new Industrial();
		fabrica2 = new Industrial();
		residencial1 = new Residencial();
		residencial2 = new Residencial();
		residencial3 = new Residencial();
		residencial4 = new Residencial();
		ruta1 = new Ruta();
		
		unMapa.construirMegaConstruccion(residencial1, new Coordenada(1,1));
		unMapa.construirMegaConstruccion(fabrica1, new Coordenada(7,6));
		unMapa.construirMegaConstruccion(residencial2, new Coordenada(6,6));
		unMapa.construirMegaConstruccion(fabrica2, new Coordenada(13,20));
		
		unaCentralEolica.conectarAEntubable(unPozo);
		
		residencial1.conectarASiguienteElectrificable(unaCentralEolica);
		residencial1.conectarAEntubable(unPozo);	
		
	}
	
	@Test
	public void empiezaLaVidaEnUnResidencialSiSeTieneAguaYLuz(){
		
		residencial1.crecimientoPoblacional();
	    assertEquals(4,residencial1.actualizarPoblacion());
	}

	@Test
	public void seConectaUnResidencialConUnComercialSinEstarConectadoAUnIndustrialNoSeModificaLaPoblacion(){
		
		residencial1.irDeCompras(unComercial);
		residencial1.crecimientoPoblacional();
		assertEquals(4,residencial1.actualizarPoblacion());
	}
	
	@Test
	public void incremetaAUnMasLaPoblacionCuandoTienenTrabajoYSeEncuentraAUnaDistanciaOptima(){
		
		residencial1.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(fabrica1);
		
		residencial1.irATrabajarA(fabrica1);
		residencial1.crecimientoPoblacional();
		assertEquals(10,residencial1.actualizarPoblacion());
	}
	
	@Test
	public void noIncrementaTantoLaPoblacionSiElLugarDeTrabajoQuedaMuyLejos(){
		   
		residencial1.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(fabrica2);
		
		residencial1.irATrabajarA(fabrica2);
		residencial1.crecimientoPoblacional();
		assertEquals(6,residencial1.actualizarPoblacion());
		/*si el lugar de trabajo queda muy lejos de donde viven el indice de felicidad de la poblacion
		de esa Residencia es "muy bajo".*/
		assertTrue(residencial1.obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.MUYBAJO));
	}
	
	@Test
	public void noAumentaLaPoblacionDeResidencial4SiNoPuedenIrAUnComercialQueEstaLleno(){
		
		residencial2.irDeCompras(unComercial);
		residencial3.irDeCompras(unComercial);
		residencial4.irDeCompras(unComercial);
		residencial1.irDeCompras(unComercial);
		residencial1.crecimientoPoblacional();
		assertEquals(4,residencial1.actualizarPoblacion());
	}
  
	
}
