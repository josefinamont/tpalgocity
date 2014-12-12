package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.modelo.catastrofes.CaminarEnLineaRecta;
import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.centrales.CentralMineral;
import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Partida;

public class BomberosYReparablesTest {

	Godzilla godzilla;
	CentralEolica unaCentralEolica;
	CentralMineral unaCentralMineral;
	CentralNuclear unaCentralNuclear;
	PozoDeAgua unPozoDeAgua;
	Partida unaPartida;
	Ruta ruta1,ruta2,ruta3;
	
	@Before
	public void setUp() throws Exception {
		
		unaPartida = new Partida();
		unPozoDeAgua = new PozoDeAgua();
		godzilla = new Godzilla(new CaminarEnLineaRecta(new Coordenada(4,4),unaPartida.obtenerMapa().obtenerAncho(),unaPartida.obtenerMapa().obtenerAltura()));
		unaCentralEolica = new CentralEolica();
		unaCentralMineral = new CentralMineral();
		unaCentralNuclear = new CentralNuclear();
		ruta1 = new Ruta();
		ruta2 = new Ruta();
		ruta3 = new Ruta();
		
		unaCentralEolica.conectarAEntubable(unPozoDeAgua);
		unaCentralNuclear.conectarAEntubable(unPozoDeAgua);
		unaCentralMineral.conectarAEntubable(unPozoDeAgua);	
	}

	//tests implementando rutas
	
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnComercialNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Comercial comercial = new Comercial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(comercial);//supongo que godzilla pasa por el comercial y lo destruye
			
			unaPartida.construir(comercial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(comercial.tienePorcentajeDeVida(25));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnComercialDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Comercial comercial = new Comercial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(comercial);//supongo que godzilla pasa por el comercial y lo destruye
			
			unaPartida.construir(comercial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			comercial.conectarAEnrutable(ruta3);
			ruta3.conectarAEnrutable(ruta2);
			ruta2.conectarAEnrutable(ruta1);
			ruta1.conectarAEnrutable(bomberos);
			
			unaPartida.pasaUnTurno();
			
			assertTrue(comercial.tienePorcentajeDeVida(32));
		}
		
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnIndustrialNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Industrial industrial = new Industrial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(industrial);//supongo que godzilla pasa por el industrial y lo destruye
			
			unaPartida.construir(industrial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(industrial.tienePorcentajeDeVida(60));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnIndustrialDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Industrial industrial = new Industrial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(industrial);//supongo que godzilla pasa por el industrial y lo destruye
			
			unaPartida.construir(industrial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			industrial.conectarAEnrutable(ruta3);
			ruta3.conectarAEnrutable(ruta2);
			ruta2.conectarAEnrutable(ruta1);
			ruta1.conectarAEnrutable(bomberos);
			unaPartida.pasaUnTurno();
			
			assertTrue(industrial.tienePorcentajeDeVida(63));
		}
		
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnResidencialNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Residencial residencial = new Residencial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(residencial);//supongo que godzilla pasa por el residencial y lo destruye
			
			unaPartida.construir(residencial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(residencial.tienePorcentajeDeVida(0));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnResidencialDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			Residencial residencial = new Residencial();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(residencial);//supongo que godzilla pasa por el residencial y lo destruye
			
			unaPartida.construir(residencial,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			unaPartida.construir(ruta1,new Coordenada(6,7));
			unaPartida.construir(ruta1,new Coordenada(6,8));
			unaPartida.construir(ruta1,new Coordenada(6,9));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(residencial.tienePorcentajeDeVida(0));
		}
		
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnaCentralMineralNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralMineral centralMineral = new CentralMineral();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralMineral);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralMineral,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(centralMineral.tienePorcentajeDeVida(65));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnaCentralMineralDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralMineral centralMineral = new CentralMineral();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralMineral);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralMineral,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			centralMineral.conectarAEnrutable(ruta3);
			ruta3.conectarAEnrutable(ruta2);
			ruta2.conectarAEnrutable(ruta1);
			ruta1.conectarAEnrutable(bomberos);
			unaPartida.pasaUnTurno();
			
			assertTrue(centralMineral.tienePorcentajeDeVida(75));
		}
		
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnaCentralNuclearNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralNuclear centralNuclear = new CentralNuclear();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralNuclear);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralNuclear,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(centralNuclear.tienePorcentajeDeVida(65));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnaCentralNuclearDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralNuclear centralNuclear = new CentralNuclear();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralNuclear);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralNuclear,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			centralNuclear.conectarAEnrutable(ruta3);
			ruta3.conectarAEnrutable(ruta2);
			ruta2.conectarAEnrutable(ruta1);
			ruta1.conectarAEnrutable(bomberos);
			unaPartida.pasaUnTurno();
			
			assertTrue(centralNuclear.tienePorcentajeDeVida(68));
		}
		
		@Test
		public void siLosBomberosNoEstanUnidosPorRutasAUnaCentralEolicaNoDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralEolica centralEolica = new CentralEolica();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralEolica);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralEolica,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			unaPartida.pasaUnTurno();
			
			assertTrue(centralEolica.tienePorcentajeDeVida(65));
		}
		
		@Test
		public void siLosBomberosEstanUnidosPorRutasAUnaCentralEolicaDebenReparar() throws NoSePuedeEdificarEnEsaZonaException{
			
			CentralEolica centralEolica = new CentralEolica();
			EstacionDeBomberos bomberos = new EstacionDeBomberos();
			
			bomberos.conectarASiguienteElectrificable(unaCentralNuclear);
			bomberos.conectarAEntubable(unPozoDeAgua);
			
			godzilla.destruirEstructura(centralEolica);//supongo que godzilla pasa por la central y la destruye
			
			unaPartida.construir(centralEolica,new Coordenada(6,6));
			unaPartida.construir(bomberos,new Coordenada(6,10));
			
			centralEolica.conectarAEnrutable(ruta3);
			ruta3.conectarAEnrutable(ruta2);
			ruta2.conectarAEnrutable(ruta1);
			ruta1.conectarAEnrutable(bomberos);
			unaPartida.pasaUnTurno();
			
			assertTrue(centralEolica.tienePorcentajeDeVida(80));
		}
		
}
