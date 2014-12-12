package algo3.algocity.pruebas;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.centrales.*;
import algo3.algocity.modelo.conectores.*;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class TerremotoTest {
        
        Mapa mapa;
        Residencial unResidencial, otroResidencial;
        Comercial unComercial;
        Industrial unIndustrial;
        LineaDeTension unaLineaDeTension, otraLinea;
        Ruta unaRuta;
        CentralEolica unaCentralEolica;
        CentralMineral unaCentralMineral,otraCentral;
        CentralNuclear unaCentralNuclear,atucha;
        Terremoto unTerremoto; 
                
        @Before
        public void setUp() throws Exception {
                
                mapa = new Mapa();
                unResidencial = new Residencial();
                otroResidencial = new Residencial();
                unComercial = new Comercial();
                unIndustrial = new Industrial();
                unaRuta = new Ruta();
                unaCentralEolica = new CentralEolica();
                unaCentralMineral = new CentralMineral();
                unaCentralNuclear = new CentralNuclear();
                unaLineaDeTension = new LineaDeTension();
                otraLinea = new LineaDeTension();
                unTerremoto = new Terremoto();
                otraCentral = new CentralMineral();
                atucha = new CentralNuclear();
                
                mapa.construirMegaConstruccion(unResidencial,new Coordenada(12,5));
                mapa.construirMegaConstruccion(unComercial,new Coordenada(7,8));
                mapa.construirMegaConstruccion(unIndustrial,new Coordenada(13,1));
                mapa.construirMegaConstruccion(unaCentralEolica,new Coordenada(13,14));
                mapa.construirMegaConstruccion(unaCentralMineral,new Coordenada(1,1));
                mapa.construirMegaConstruccion(unaCentralNuclear,new Coordenada(10,10));
                mapa.construirMegaConstruccion(atucha,new Coordenada(13,12));
                mapa.construirLineaDeTension(unaLineaDeTension,new Coordenada(10,11));
                mapa.construirMegaConstruccion(otroResidencial, new Coordenada(10,15));
                mapa.agregarRuta(unaRuta,new Coordenada(13,10));
                mapa.crearCatastrofe(unTerremoto,new Coordenada(10,15));
                mapa.construirMegaConstruccion(otraCentral,new Coordenada(1,2));
        }
        
        @Test
        public void siUnEdificioSeEncuentraEnElEpicentroDelTerremotoSeDestruyePorCompleto(){
    		
    		assertTrue(otroResidencial.tienePorcentajeDeVida(100));
    		unTerremoto.destruirEstructura(otroResidencial);
    		assertTrue(otroResidencial.tienePorcentajeDeVida(0));
    	}
        
        @Test
        public void losEdificiosNoTienenNingunDaño(){
    		
    		assertTrue(unResidencial.tienePorcentajeDeVida(100));
    		assertTrue(unComercial.tienePorcentajeDeVida(100));
    		assertTrue(unIndustrial.tienePorcentajeDeVida(100));
    	
    	}
        
        @Test
        public void laCatastrofeTerremotoDisminuyeElDañoEnProporcionALaDistanciaEnQueSeEncuentraUnEdificio(){
                
        	unTerremoto.destruirEstructura(unResidencial);
            unTerremoto.destruirEstructura(unComercial);
            unTerremoto.destruirEstructura(unIndustrial);
            
            assertTrue(unResidencial.tienePorcentajeDeVida(15));
    		assertTrue(unComercial.tienePorcentajeDeVida(11));
    		assertTrue(unIndustrial.tienePorcentajeDeVida(21));
    	}
                

        @Test
        public void laCatastrofeTerremotoDisminuyeElDañoEnProporcionALaDistanciaEnQueSeEncuentraUnaLineaDeTension(){
                
        	assertTrue(unaLineaDeTension.tienePorcentajeDeVida(100));
        	unTerremoto.destruirEstructura(unaLineaDeTension);
            assertTrue(unaLineaDeTension.tienePorcentajeDeVida(6));
        }

        @Test
        public void laCatastrofeTerremotoDisminuyeElDañoEnProporcionALaDistanciaEnQueSeEncuentraUnaRuta(){
                
        	assertTrue(unaRuta.tienePorcentajeDeVida(100));
        	unTerremoto.destruirEstructura(unaRuta);
            assertTrue(unaRuta.tienePorcentajeDeVida(8));
        }
        
        @Test
        public void lasCentralesNoTienenNingunDaño(){
    		
    		assertTrue(unaCentralEolica.tienePorcentajeDeVida(100));
    		assertTrue(unaCentralMineral.tienePorcentajeDeVida(100));
    		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(100));
    	}
 
        @Test
        public void laCatastrofeTerremotoDisminuyeElDañoEnProporcionALaDistanciaEnQueSeEncuentraUnaCentralElectrica(){
                     
        	unTerremoto.destruirEstructura(unaCentralEolica);
        	unTerremoto.destruirEstructura(unaCentralMineral);
        	unTerremoto.destruirEstructura(unaCentralNuclear);
                
        	assertTrue(unaCentralEolica.tienePorcentajeDeVida(5));
            assertTrue(unaCentralMineral.tienePorcentajeDeVida(24));
            assertTrue(unaCentralNuclear.tienePorcentajeDeVida(8));
        }
        
        @Test
        public void seIniciaUnTerremotoEnUnSectorDelMapaYDañaConstrucciones(){
        	
        	Terremoto terremoto = new Terremoto();
        	mapa.crearCatastrofe(terremoto,new Coordenada(12,5));
        	terremoto.provocarDañosEn(mapa);
        	
        	assertTrue(unResidencial.tienePorcentajeDeVida(0));
        	assertTrue(unaCentralMineral.tienePorcentajeDeVida(17));
        	assertTrue(unaLineaDeTension.tienePorcentajeDeVida(9));
        }
}