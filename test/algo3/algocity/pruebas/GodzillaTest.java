package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.CaminarEnLineaRecta;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.centrales.CentralMineral;
import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class GodzillaTest {

	Mapa mapa;
	Godzilla godzilla;
    Residencial unResidencial;
    Comercial unComercial;
    Industrial unIndustrial;
    LineaDeTension unaLineaDeTension;
    Ruta unaRuta;
    CentralEolica unaCentralEolica;
    CentralMineral unaCentralMineral;
    CentralNuclear unaCentralNuclear;
	
	@Before
	public void setUp() throws Exception {
		
		mapa = new Mapa();
        godzilla = new Godzilla(new CaminarEnLineaRecta(new Coordenada(4,0),mapa.obtenerAncho(),mapa.obtenerAltura()));
        unResidencial = new Residencial();
        unComercial = new Comercial();
        unIndustrial = new Industrial();
        unaRuta = new Ruta();
        unaCentralEolica = new CentralEolica();
        unaCentralMineral = new CentralMineral();
        unaCentralNuclear = new CentralNuclear();
        unaLineaDeTension = new LineaDeTension();
        
        mapa.construirMegaConstruccion(unResidencial,new Coordenada(12,5));
        mapa.construirMegaConstruccion(unComercial,new Coordenada(7,8));
        mapa.construirMegaConstruccion(unIndustrial,new Coordenada(13,1));
        mapa.construirMegaConstruccion(unaCentralEolica,new Coordenada(13,14));
        mapa.construirMegaConstruccion(unaCentralMineral,new Coordenada(1,1));
        mapa.construirMegaConstruccion(unaCentralNuclear,new Coordenada(10,10));
        mapa.construirLineaDeTension(unaLineaDeTension,new Coordenada(10,11));
        mapa.agregarRuta(unaRuta,new Coordenada(13,10));
        mapa.crearCatastrofe(godzilla);
	}

	@Test
    public void laCatastrofeGodzillaOcupa1HectareaEnElMapa(){
		
            assertEquals(1,godzilla.tamañoEnHa());
    }
	
    @Test
    public void losEdificiosNoTienenNingunDaño(){
		
		assertTrue(unResidencial.tienePorcentajeDeVida(100));
		assertTrue(unComercial.tienePorcentajeDeVida(100));
		assertTrue(unIndustrial.tienePorcentajeDeVida(100));
	
	}
	
	@Test
    public void laCatastrofeGodzillaDestruyeEdificiosDeDiferenteManera() {
            
        godzilla.destruirEstructura(unResidencial);
        assertTrue(unResidencial.tienePorcentajeDeVida(0));
          
        godzilla.destruirEstructura(unComercial);
        assertTrue(unComercial.tienePorcentajeDeVida(25));
           
        godzilla.destruirEstructura(unIndustrial);
        assertTrue(unIndustrial.tienePorcentajeDeVida(60));
	}
	
	@Test
    public void laCatastrofeGodzillaDestruyePoCompletoUnaLineaDeTensionConNivelDeVidaCompleto() {
           
		assertTrue(unaLineaDeTension.tienePorcentajeDeVida(100));
        godzilla.destruirEstructura(unaLineaDeTension);
        assertTrue(unaLineaDeTension.tienePorcentajeDeVida(0));
	}  
	
	@Test
    public void laCatastrofeGodzillaCausaDañoAUnaRutaConNivelDeVidaCompleto() {

		assertTrue(unaRuta.tienePorcentajeDeVida(100));
        godzilla.destruirEstructura(unaRuta);
        assertTrue(unaRuta.tienePorcentajeDeVida(20));
	} 
	
	@Test
    public void lasCentralesNoTienenNingunDaño(){
		
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(100));
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(100));
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(100));
	}
	
	@Test
    public void laCatastrofeGodzillaCausaElMismoDañoATodasLasCentralesElectricas() {
		
        godzilla.destruirEstructura(unaCentralEolica);
        assertTrue(unaCentralEolica.tienePorcentajeDeVida(65));

        godzilla.destruirEstructura(unaCentralMineral);
        assertTrue(unaCentralMineral.tienePorcentajeDeVida(65));

        godzilla.destruirEstructura(unaCentralNuclear);
        assertTrue(unaCentralNuclear.tienePorcentajeDeVida(65));

	}
	
}
