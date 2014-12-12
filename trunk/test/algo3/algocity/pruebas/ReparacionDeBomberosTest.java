package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.CaminarEnLineaRecta;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.centrales.*;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.*;
import algo3.algocity.modelo.edificios.*;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Partida;

public class ReparacionDeBomberosTest {

	EstacionDeBomberos bomberos1,bomberos2;
	Godzilla godzilla;
	Residencial unResidencial;
	Comercial unComercial;
	Industrial unIndustrial;
	CentralEolica unaCentralEolica;
	CentralMineral unaCentralMineral;
	CentralNuclear unaCentralNuclear;
	PozoDeAgua unPozoDeAgua;
	Partida unaPartida;
	Ruta ruta,ruta1;
	
	@Before
	public void setUp() throws Exception {
		
		unaPartida = new Partida();
		bomberos1 = new EstacionDeBomberos();
		bomberos2 = new EstacionDeBomberos();
		unPozoDeAgua = new PozoDeAgua();
		godzilla = new Godzilla(new CaminarEnLineaRecta(new Coordenada(4,4),unaPartida.obtenerMapa().obtenerAncho(),unaPartida.obtenerMapa().obtenerAltura()));
		unResidencial = new Residencial();
		unComercial = new Comercial();
		unIndustrial = new Industrial();
		unaCentralEolica = new CentralEolica();
		unaCentralMineral = new CentralMineral();
		unaCentralNuclear = new CentralNuclear();
		
		unaCentralEolica.conectarAEntubable(unPozoDeAgua);
		unaCentralNuclear.conectarAEntubable(unPozoDeAgua);
		unaCentralMineral.conectarAEntubable(unPozoDeAgua);
		
		unaPartida.construir(bomberos1,new Coordenada(1,2));
		unaPartida.construir(unResidencial,new Coordenada(13,12));
		unaPartida.construir(unComercial,new Coordenada(13,11));
		unaPartida.construir(unIndustrial,new Coordenada(13,13));
		unaPartida.construir(unaCentralEolica,new Coordenada(5,5));
		unaPartida.construir(unaCentralMineral,new Coordenada(1,1));
		unaPartida.construir(unaCentralNuclear,new Coordenada(1,4));
		
		bomberos1.conectarASiguienteElectrificable(unaCentralNuclear);
		bomberos1.conectarAEntubable(unPozoDeAgua);
	
		//Pasa la catastrofe godzilla y destruye todo a su paso
		godzilla.destruirEstructura(unResidencial);
		godzilla.destruirEstructura(unComercial);
		godzilla.destruirEstructura(unIndustrial);
		godzilla.destruirEstructura(unaCentralEolica);
		godzilla.destruirEstructura(unaCentralMineral);
		godzilla.destruirEstructura(unaCentralNuclear);
	
	}

	@Test
	public void siLosBomberosNoTienenAguaNiElectricidadNoReparanNada() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaPartida.construir(bomberos2,new Coordenada(5,6));
		bomberos2.conectarASiguienteElectrificable(null);
		bomberos2.conectarAEntubable(null);
	
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(65));
	}
	
	@Test
	public void vidaDeLasConstruccionesDespuesDeQuePasoGodzilla() {
		
		assertTrue(unResidencial.tienePorcentajeDeVida(0));
		assertTrue(unComercial.tienePorcentajeDeVida(25));
		assertTrue(unIndustrial.tienePorcentajeDeVida(60));
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(65));
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(65));
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(65));
	}
	
	@Test
	public void siNoPasaUnTurnoLosBomberosNoReparanLasConstruccionesQueSeEncuentranEnSuRadioDeAccion() {
		
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(65));
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(65));
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(65));
	}
	
	@Test
	public void cuandoPasaUnTurnoLosBomberosNoReparanConstruccionesQueNoSeEncuentrenEnSuRadioDeAccion() {	
		
		unaPartida.pasaUnTurno();
		
		assertTrue(unResidencial.tienePorcentajeDeVida(0));//no lo repara porque no esta en el radio
		assertTrue(unComercial.tienePorcentajeDeVida(25));//no lo repara porque no esta en el radio
		assertTrue(unIndustrial.tienePorcentajeDeVida(60));//no lo repara porque no esta en el radio
	}
	
	@Test
	public void cuandoPasaUnTurnoLosBomberosReparanUnaCentralEolicaQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralEolica);
		
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(80));//repara 15 de vida por turno
		
	}	
	
	@Test
	public void cuandoPasaUnTurnoLosBomberosReparanUnaCentralMineralQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralMineral);
		
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(75));//repara 10 de vida por turno
		
	}	
	
	@Test
	public void cuandoPasaUnTurnoLosBomberosReparanUnaCentralNuclearQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralNuclear);
		
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(68));//repara 3 de vida por turno	
		
	}	
	
	@Test
	public void cuandoPasaMasDeUnTurnoLosBomberosReparanUnaCentralEolicaQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralEolica);
		
		unaPartida.pasaUnTurno();
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(95));//repara 15 de vida por turno
		
	}	
	
	@Test
	public void cuandoPasaMasDeUnTurnoLosBomberosReparanUnaCentralMineralQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralMineral);
		
		unaPartida.pasaUnTurno();
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(85));//repara 10 de vida por turno
		
	}	
	
	@Test
	public void cuandoPasaMasDeUnTurnoLosBomberosReparanUnaCentralNuclearQueSeEncuentranEnSuRadio() {	
		
		ruta = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralNuclear);
		
		unaPartida.pasaUnTurno();
		unaPartida.pasaUnTurno();
		
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(71));//repara 3 de vida por turno	
		
	}
	
	@Test
	public void unaCentralEolicaSeReparaMasRapidoSiSeEncuentraEnElRadioDeAccionDe2EstacionesDeBomberos() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaPartida.construir(bomberos2, new Coordenada(5,6));//estos bomberos estan cerca de los otros bomberos
		bomberos2.conectarASiguienteElectrificable(unaCentralNuclear);
		bomberos2.conectarAEntubable(unPozoDeAgua);
		
		ruta = new Ruta();
		ruta1 = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralEolica);
		
		bomberos2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralEolica);
	
		unaPartida.pasaUnTurno();	
		assertTrue(unaCentralEolica.tienePorcentajeDeVida(95));//la reparan ambos bomberos

	}
	
	@Test
	public void unaCentralMineralSeReparaMasRapidoSiSeEncuentraEnElRadioDeAccionDe2EstacionesDeBomberos() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaPartida.construir(bomberos2, new Coordenada(5,6));//estos bomberos estan cerca de los otros bomberos
		bomberos2.conectarASiguienteElectrificable(unaCentralNuclear);
		bomberos2.conectarAEntubable(unPozoDeAgua);
		
		ruta = new Ruta();
		ruta1 = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralMineral);
		
		bomberos2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralMineral);
	
		unaPartida.pasaUnTurno();	
		assertTrue(unaCentralMineral.tienePorcentajeDeVida(85));//la reparan ambos bomberos

	}
	
	@Test
	public void unaCentralNuclearSeReparaMasRapidoSiSeEncuentraEnElRadioDeAccionDe2EstacionesDeBomberos() throws NoSePuedeEdificarEnEsaZonaException {
		
		unaPartida.construir(bomberos2, new Coordenada(5,6));//estos bomberos estan cerca de los otros bomberos
		bomberos2.conectarASiguienteElectrificable(unaCentralNuclear);
		bomberos2.conectarAEntubable(unPozoDeAgua);
		
		ruta = new Ruta();
		ruta1 = new Ruta();
		
		bomberos1.conectarAEnrutable(ruta);
		ruta.conectarAEnrutable(unaCentralNuclear);
		
		bomberos2.conectarAEnrutable(ruta1);
		ruta1.conectarAEnrutable(unaCentralNuclear);
		
		unaPartida.pasaUnTurno();	
		
		assertTrue(unaCentralNuclear.tienePorcentajeDeVida(71));//la reparan ambos bomberos

	}
	
}
