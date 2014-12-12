package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.mapa.*;

public class HectareaTest {
	
	Hectarea unaHectarea;
	Agua agua;
	Coordenada unaCoordenada;
	
	@Before
	public void setUp() {
		
	  agua = new Agua();
	  unaCoordenada = new Coordenada(1,1);	
	  unaHectarea = new Hectarea(agua,unaCoordenada);
	}
	
	@Test  
	public void unaHectareaDevuelveSuTipoDeTerreno() {
		
		assertEquals(agua,unaHectarea.obtenerTipoDeTerreno());	
	}

	@Test
	public void seVerificanLasCoordenadasDeUnaParcela(){
   		
		assertEquals(1,unaHectarea.obtenerCoordenada().obtenerPosicionX());	
		assertEquals(1,unaHectarea.obtenerCoordenada().obtenerPosicionY());
	}
	
}
