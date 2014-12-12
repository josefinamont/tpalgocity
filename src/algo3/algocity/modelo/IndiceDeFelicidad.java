package algo3.algocity.modelo;

import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.menu.Poblacion;

public class IndiceDeFelicidad {

	public enum EstadosDeFelicidad {ALTO,BAJO,MUYBAJO};
	
	private EstadosDeFelicidad indice;
	
	public IndiceDeFelicidad(){
			
	}

	public void definirEstado(EstadosDeFelicidad estado) {
		
		this.indice = estado;
	}

	public EstadosDeFelicidad obtenerIndice() {
		
		return this.indice;
	}
	
	public void definirIndiceDeFelicidadLaboral(Hectarea localizacionLaboral, Hectarea localizacionResidencial){
		
		int distancia = (int) localizacionResidencial.calcularDistanciaEnHaA(localizacionLaboral);
		
	    if( distancia >= 5 && distancia  <= 8) { this.indice = EstadosDeFelicidad.ALTO;}
	    	
	    if (distancia < 5) { this.indice = EstadosDeFelicidad.BAJO;}
	    
		if(distancia > 8 ) { this.indice = EstadosDeFelicidad.MUYBAJO;}
	}

	public void crecimientoPoblacionalPorFelicidad(Poblacion poblacionActual,Poblacion cantidadDePoblacionNoLaboral) {
		
		if(this.indice.equals(EstadosDeFelicidad.ALTO)){
			poblacionActual.modificarNumeroDeCiudadanos(3);
			cantidadDePoblacionNoLaboral.modificarNumeroDeCiudadanos(3);
		} 
	 
		if(this.indice.equals(EstadosDeFelicidad.BAJO)){ 
			poblacionActual.modificarNumeroDeCiudadanos(2);
			cantidadDePoblacionNoLaboral.modificarNumeroDeCiudadanos(2);
		}
	 
		if(this.indice.equals(EstadosDeFelicidad.MUYBAJO)){ 
			poblacionActual.modificarNumeroDeCiudadanos(1);
			cantidadDePoblacionNoLaboral.modificarNumeroDeCiudadanos(1);
		}	 
	}
	
	
}
