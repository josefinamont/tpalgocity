package algo3.algocity.modelo.interfaces;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public abstract class MegaConstruccion extends Construccion {

	public abstract boolean tienePoblacion();
	
	public void construirmeEn(Mapa unMapa, Coordenada lugarDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException{
		
		unMapa.construirMegaConstruccion(this,lugarDeConstruccion);
	}
}
