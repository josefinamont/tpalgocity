package algo3.algocity.modelo.menu;

public class Poblacion {

	private int numeroDeCiudadanos;

	public Poblacion() {
		
		this.numeroDeCiudadanos = 0;
	}
	
	public void modificarNumeroDeCiudadanos(int numeroDeCiudadanos) {
		
		this.numeroDeCiudadanos += numeroDeCiudadanos;
	}

	public void setNumeroDeCiudadanos(int numeroDeCiudadanos) {
		
		this.numeroDeCiudadanos = numeroDeCiudadanos;
	}

	public int obtenerNumeroDeCuidadanos(){
		
		return this.numeroDeCiudadanos;
	}
	
	public int obtenerDineroAPagarAlJugador() {
		
		return this.numeroDeCiudadanos*10;
	}

}
