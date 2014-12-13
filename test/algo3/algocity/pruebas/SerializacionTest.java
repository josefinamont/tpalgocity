package algo3.algocity.pruebas;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import algo3.algocity.persistencia.*;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Jugador;
import algo3.algocity.modelo.menu.Poblacion;

public class SerializacionTest {
	
	Fachada unaFachada;
	DeserializadorDePrueba deserializador;
	SerializacionPequeñosObjetos serializador;
	Coordenada unaCoordenada;
	Poblacion poblacion;
	PozoDeAgua unPozo;
	
	@Before
	public void setUp() throws Exception {
		
		 deserializador = new DeserializadorDePrueba();
		 unPozo = new PozoDeAgua();
		 unaFachada = new Fachada(new Jugador());
		 serializador = new SerializacionPequeñosObjetos();
	     unaCoordenada = new Coordenada(1,1);
	     poblacion = new Poblacion();
	     poblacion.setNumeroDeCiudadanos(29);
	}

	@Test
	public void serializandoUnaCoordenada(){
		
		serializador.serializar(unaCoordenada);
		assertEquals("{\"posicionY\":1,\"posicionX\":1}",serializador.obtenerSerializacion());
		
		Coordenada coordenadaNueva = deserializador.deserializarUnaCoordenada(serializador.obtenerSerializacion());
		
		assertEquals(coordenadaNueva.obtenerPosicionX(),unaCoordenada.obtenerPosicionX());
	}
	
	@Test
	public void serializandoUnaPoblacion(){
		
		serializador.serializar(poblacion);
		assertEquals("{\"numeroDeCiudadanos\":29}",serializador.obtenerSerializacion());
		Poblacion poblacionDeserializada = deserializador.deserializarUnaPoblacion(serializador.obtenerSerializacion());
		
		assertEquals(poblacionDeserializada.obtenerNumeroDeCuidadanos(),poblacion.obtenerNumeroDeCuidadanos());
	}

	@Test
	public void atravesDeLaFachadaSerializamosUnaCoordenadaDeUnaParteDelMapa() throws NoSePuedeEdificarEnEsaZonaException{
		
		Industrial unaIndustria = new Industrial();
		Residencial unResidencial = new Residencial();
		unaFachada.jugadorConstruir(unaIndustria, new Coordenada(1,1));
		unaFachada.jugadorConstruir(unResidencial, new Coordenada(1,2));
		
		
		if(unaFachada.obtenerMegaConstrucciones() != null){
			serializador.serializarCoordenadasDeLasMegaConstruccionesDelMapa(unaFachada.obtenerMegaConstrucciones());
		}
		assertEquals("{\"posicionY\":1,\"posicionX\":1}",serializador.obtenerSerializacionDeUnaCoordenadaDeLaPosicion(0));
		assertEquals("{\"posicionY\":1,\"posicionX\":2}",serializador.obtenerSerializacionDeUnaCoordenadaDeLaPosicion(1));
		
		Coordenada coordenadaDeserializada = deserializador.deserializarUnaCoordenada(serializador.obtenerSerializacionDeUnaCoordenadaDeLaPosicion(0));
		
		assertEquals(coordenadaDeserializada.obtenerPosicionX(),unaIndustria.obtenerHectareaALaQuePertenece().obtenerCoordenada().obtenerPosicionX());
		
	}
	
	@Test
	public void serializamosUnPozoDeAgua(){
		
		serializador.serializar(unPozo);
		
		assertEquals("{\"porcentajeDeVida\":100}",serializador.obtenerSerializacion());
		
		PozoDeAgua pozo = deserializador.deserializarPozoDeAgua(serializador.obtenerSerializacion());
		
		assertEquals(pozo.costo(),unPozo.costo());
	}
}
