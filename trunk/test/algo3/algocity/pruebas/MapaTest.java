package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.mapa.*;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;

public class MapaTest {
    
	Mapa unMapa;
	Tierra porcionDeTierra;
	Coordenada unaCoordenada;
	Residencial unResidencial;
	Hectarea unaHectarea;
	LineaDeTension linea1,linea2,linea3,linea4,linea5;
	CentralEolica unaCentral;
	TuberiaDeAgua tuberia1,tuberia2,tuberia3,tuberia4;
	PozoDeAgua unPozo;
	
	@Before
    public void setUp() throws NoSePuedeEdificarEnEsaZonaException {
		
		porcionDeTierra = new Tierra();
        unMapa = new Mapa();
        unaCoordenada = new Coordenada(10,10);
        unaHectarea = new Hectarea(porcionDeTierra,unaCoordenada);
	    unResidencial = new Residencial();
	    unMapa.construirMegaConstruccion(unResidencial,new Coordenada(12,12));
	    linea1 = new LineaDeTension();
	    linea2 = new LineaDeTension();
	    linea3 = new LineaDeTension();
	    linea4 = new LineaDeTension();
	    linea5 = new LineaDeTension();
	    unaCentral = new CentralEolica();
	    tuberia1 = new TuberiaDeAgua(); 
	    tuberia2 = new TuberiaDeAgua();
	    tuberia3 = new TuberiaDeAgua();
	    tuberia4 = new TuberiaDeAgua();
	    unPozo = new PozoDeAgua();
	    
	    unMapa.construirMegaConstruccion(unaCentral,new Coordenada(6,6));
	    unMapa.construirLineaDeTension(linea1,new Coordenada(6,7));
	    unMapa.construirLineaDeTension(linea2,new Coordenada(6,8));
	    unMapa.construirLineaDeTension(linea3,new Coordenada(6,9));
	    unMapa.construirLineaDeTension(linea4,new Coordenada(6,10));
	   
	    unMapa.construirMegaConstruccion(unPozo,new Coordenada(2,2));
	    unMapa.construirTuberiaDeAgua(tuberia1,new Coordenada(2,3));
	    unMapa.construirTuberiaDeAgua(tuberia2,new Coordenada(2,4));
	    unMapa.construirTuberiaDeAgua(tuberia3,new Coordenada(2,5));
	    unMapa.construirTuberiaDeAgua(tuberia4,new Coordenada(2,6));
	}
	
    @Test
	public void dimensionesDelMapa(){
        
    	assertEquals(14,unMapa.obtenerAltura());
        assertEquals(23,unMapa.obtenerAncho());
    }
    
    @Test
    public void verificoQueHayTierraEnUnSectorDelMapa(){
       	
        assertEquals(porcionDeTierra.obtenerTipoDeSuelo(),unMapa.obtenerUnSectorDelMapa(new Coordenada(1,2)).obtenerTipoDeSuelo());
    }
   
    @Test
    public void verificarUnaposicionDelMapa(){
    	
    	assertEquals(unaHectarea.obtenerCoordenada().obtenerPosicionX(),unMapa.obtenerUnSectorDelMapa(new Coordenada(10,10)).obtenerCoordenada().obtenerPosicionX());
    }
 
    @Test
    public void verificarQueSeCreoUnaMegaConstruccionEnUnSectorDelMapa(){
    	
    	assertTrue(unMapa.verificarSiMegaConstruccionSeEncuentraEnElMapa(unResidencial));
    }
    
    @Test
    public void siSeQuiereCrearUnaEstructuraEnUnaHectareaConDiferenteSuperficieSeLanzaUnaExcepcion(){
    	
    	try { unMapa.construirMegaConstruccion(unResidencial,new Coordenada(2,2));
    	} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
    }
    
    @Test
    public void siSeQuiereCrearUnaLineaDeTensionSobreUnaParcelaQueYaTieneUnaConstruccionSeLanzaUnaExcepcion(){
    	
    	try { unMapa.construirLineaDeTension(new LineaDeTension(),new Coordenada(4,6));
    	} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
    }

    @Test
    public void siSeQuiereCrearUnaTuberiaDeAguaSobreUnaParcelaQueYaTieneUnaConstruccionSeLanzaUnaExcepcion(){
    	
    	try { unMapa.construirTuberiaDeAgua(new TuberiaDeAgua(),new Coordenada(3,6));
    	} catch(NoSePuedeEdificarEnEsaZonaException excepcion) { }
    }
}


