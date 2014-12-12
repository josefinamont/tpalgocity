package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.centrales.*;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.*;

public class EdificioTest {
	
	Residencial unResidencial;
	Comercial unComercial;
	Industrial unIndustrial;
	CentralEolica unaCentralEolica;
	CentralNuclear unaCentralNuclear;
	CentralMineral unaCentralMineral;
	PozoDeAgua unPozoDeAgua;
	TuberiaDeAgua tuberia1,tuberia2,tuberia3;
	LineaDeTension unaLineaDeTension;
	LineaDeTension lineaDeTension1,lineaDeTension2,lineaDeTension3;
	
	@Before
	public void setUp() {
		
		unResidencial = new Residencial();
		unComercial = new Comercial();
		unIndustrial = new Industrial();
		unaCentralEolica = new CentralEolica();
		unaCentralNuclear = new CentralNuclear();
		unaCentralMineral = new CentralMineral();
		unaLineaDeTension = new LineaDeTension();
		lineaDeTension1 = new LineaDeTension();
		lineaDeTension2 = new LineaDeTension();
		lineaDeTension3 = new LineaDeTension();
		unPozoDeAgua = new PozoDeAgua();
		tuberia1 = new TuberiaDeAgua();
		tuberia2 = new TuberiaDeAgua();
		tuberia3 = new TuberiaDeAgua();
		
		unaCentralEolica.conectarAEntubable(unPozoDeAgua);
		unaCentralNuclear.conectarAEntubable(unPozoDeAgua);
		unaCentralMineral.conectarAEntubable(unPozoDeAgua);
	}
	
	@Test
	public void unResidencialTieneUnaCapacidadMaxima() {
		
		assertEquals(100,unResidencial.capacidadMaxima());
	}
	
	@Test
	public void unIndustrialTieneUnMaximoDePersonasAEmplearPorHA() {
		
		assertEquals(25,unIndustrial.maximoDePersonasAEmplearPorHA());
	}
	
	@Test
	public void todosLosEdificiosTienenUnConsumoElectrico() {
		
		assertEquals(1,unResidencial.consumoElectricoEnMW());
		assertEquals(2,unComercial.consumoElectricoEnMW());
		assertEquals(5,unIndustrial.consumoElectricoEnMW());
	}
	
	@Test
	public void todosLosEdificiosTienenCosto() {
		
		assertEquals(5,unResidencial.costo());
		assertEquals(5,unComercial.costo());
		assertEquals(10,unIndustrial.costo());
	}
	
	@Test
	public void unEdificioRecienCreadoNoTieneAgua(){
		
		assertFalse(unResidencial.tieneAgua());
		assertFalse(unComercial.tieneAgua());
		assertFalse(unIndustrial.tieneAgua());
	}
	
	@Test
	public void unEdificioRecienCreadoNoTieneElectricidad(){
		
		assertFalse(unResidencial.tieneElectricidad());
		assertFalse(unComercial.tieneElectricidad());
		assertFalse(unIndustrial.tieneElectricidad());
	}
	
	@Test
   	public void unEdificioConectadoAUnaCentralTieneElectricidad(){
		
		unIndustrial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		lineaDeTension1.conectarASiguienteElectrificable(unaLineaDeTension);
		unaLineaDeTension.conectarASiguienteElectrificable(unaCentralEolica);
		
		assertTrue(unIndustrial.tieneElectricidad());
		
		unComercial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		lineaDeTension1.conectarASiguienteElectrificable(unaLineaDeTension);
		unaLineaDeTension.conectarASiguienteElectrificable(unaCentralNuclear);
		
		assertTrue(unComercial.tieneElectricidad());
		
		unResidencial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		lineaDeTension1.conectarASiguienteElectrificable(unaLineaDeTension);
		unaLineaDeTension.conectarASiguienteElectrificable(unaCentralMineral);
		
		assertTrue(unResidencial.tieneElectricidad());
		
	}
	
	@Test
   	public void unEdificioConectadoAUnPozoTieneAgua(){
		
		unIndustrial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		tuberia1.conectarAEntubable(unPozoDeAgua);
		
		assertTrue(unIndustrial.tieneAgua());
		
		unComercial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		tuberia1.conectarAEntubable(unPozoDeAgua);
		
		
		assertTrue(unComercial.tieneAgua());
		
		unResidencial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		tuberia1.conectarAEntubable(unPozoDeAgua);
		
		assertTrue(unResidencial.tieneAgua());
		
	}

	@Test
   	public void unEdificioConectadoSoloATuberiasNoConectadasAUnPozoNoTieneAgua(){
		
		unResidencial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		
		assertFalse(unResidencial.tieneAgua());
		
		unComercial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		
		assertFalse(unComercial.tieneAgua());
		
		unIndustrial.conectarAEntubable(tuberia3);
		tuberia3.conectarAEntubable(tuberia2);
		tuberia2.conectarAEntubable(tuberia1);
		
		assertFalse(unIndustrial.tieneAgua());
		
	}
	
	@Test
   	public void unEdificioConectadoSoloALineasDeTensionNoConectadasAUnaCentralNoTieneElectricidad(){
		
		unResidencial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		
		assertFalse(unResidencial.tieneElectricidad());
		
		unComercial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		
		assertFalse(unComercial.tieneElectricidad());
		
		unIndustrial.conectarASiguienteElectrificable(lineaDeTension3);
		lineaDeTension3.conectarASiguienteElectrificable(lineaDeTension2);
		lineaDeTension2.conectarASiguienteElectrificable(lineaDeTension1);
		
		assertFalse(unIndustrial.tieneElectricidad());
		
	}
	
}