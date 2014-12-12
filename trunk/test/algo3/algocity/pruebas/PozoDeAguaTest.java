package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.conectores.*;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.*;

public class PozoDeAguaTest {

	private PozoDeAgua unPozoDeAgua;
	private TuberiaDeAgua tuberia1;
	private TuberiaDeAgua tuberia2;
	private TuberiaDeAgua tuberia3;
	private Edificio unaResidencia;
	
	@Before
	public void setUp() {
		
		unPozoDeAgua = new PozoDeAgua();
		tuberia1 = new TuberiaDeAgua();
		tuberia2 = new TuberiaDeAgua();
		tuberia3 = new TuberiaDeAgua();
		unaResidencia = new Residencial();
		
		unaResidencia.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		tuberia1.conectarAEntubable(unPozoDeAgua);
	}
	
	@Test
	public void crearUnPozoDeAguaTieneUnPrecio() {
		
		assertEquals(250,unPozoDeAgua.costo());
	}

	@Test
   	public void unEdificioConectadoAUnPozoDeAgua(){
		
		assertTrue(unaResidencia.tieneAgua());
	}
}
