package algo3.algocity.modelo.catastrofes;

import java.util.Random;

import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class FabricaDeCatastrofes {

	private Strategy obtenerEstrategiaAleatoria(Coordenada unaCoordenada, int ancho, int altura){
		
		Random random = new Random();
		int numeroAleatorio = random.nextInt(4);
		Strategy estrategia = null;
		
		switch (numeroAleatorio) {
		
			case 0:
	        	estrategia = new CaminarEnLineaRecta(unaCoordenada, ancho, altura);
	        	break;
	
	        case 1: 
	        	estrategia = new CaminarEnDiagonal(unaCoordenada,ancho,altura);
	        	break;

	        case 2: 
	        	estrategia = new CaminarEnZigZagYLineaRecta(unaCoordenada,ancho,altura);
	        	break;
	     
	        case 3: 
	        	estrategia = new CaminarEnZigZagYDiagonalmente(unaCoordenada,ancho,altura);
	        	break;
		}
		
		return estrategia;
	}
	
	public void crearCatastrofe(Mapa mapa) {
		
		Random generadorRandom = new Random();
		int numeroAleatorio = generadorRandom.nextInt(2);
		Coordenada unaCoordenada;
		
		if (numeroAleatorio == 0) {
			unaCoordenada = mapa.obtenerUnBorde();
			mapa.ubicarCatastrofeEn(new Godzilla(this.obtenerEstrategiaAleatoria(unaCoordenada,mapa.obtenerAncho(),mapa.obtenerAltura())),unaCoordenada);
		} else {
			unaCoordenada = mapa.obtenerCoordenadaAleatoria();
			mapa.ubicarCatastrofeEn(new Terremoto(),unaCoordenada);
		}
	}

}
