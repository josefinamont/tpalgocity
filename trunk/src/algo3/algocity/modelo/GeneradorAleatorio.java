package algo3.algocity.modelo;

import java.util.Random;

public class GeneradorAleatorio {

	private Random generadorAleatorio;
	private int numeroActual;
	 
	public GeneradorAleatorio(){
		 
		this.generadorAleatorio = new Random();
		this.numeroActual = this.generadorAleatorio.nextInt(100);
	}
	 
	public int generarNumero(int hasta){
		 
		this.numeroActual = generadorAleatorio.nextInt(hasta); 
		return this.numeroActual;
	}

	public int numeroActual(){
		 
		 return this.numeroActual;
	}

}
