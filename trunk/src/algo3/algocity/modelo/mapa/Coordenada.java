package algo3.algocity.modelo.mapa;

public class Coordenada {

	private int posicionY; 
	private int posicionX;	
	
	public Coordenada() {}
	
	public Coordenada(int posicionY,int posicionX) {
		
		this.posicionY = posicionY;
		this.posicionX = posicionX;
	}

	public int obtenerPosicionX() {
		
		return this.posicionX;
	}
	
	public int obtenerPosicionY() {
		
		return this.posicionY;
	}

	public int calcularDistanciaEnHaA(Coordenada unaCoordenada) {
		
		int primerTermino,segundoTermino;
		int distanciaEnCuadraditos,distanciaEnHa;
		
		primerTermino = (int) Math.pow((this.posicionX - unaCoordenada.obtenerPosicionX()),2);
		segundoTermino = (int) Math.pow((this.posicionY - unaCoordenada.obtenerPosicionY()),2);
		distanciaEnCuadraditos = (int) Math.sqrt(primerTermino + segundoTermino);
		distanciaEnHa = (int) ((distanciaEnCuadraditos));
				
		return distanciaEnHa;
	}

	public void modificarPosicionY(int fila) {
		
		this.posicionY = fila;
	}

	public void modificarPosicionX(int columna) {
		
		this.posicionX = columna;
	}
}
