package algo3.algocity.modelo.catastrofes;

import algo3.algocity.modelo.mapa.Coordenada;

public class CaminarEnZigZagYDiagonalmente implements Strategy {

	private Coordenada coordenada;
	private int[] patronCaminar = new int [4];
	private int ancho;
	private int altura;
	
	public CaminarEnZigZagYDiagonalmente(Coordenada unaCoordenada,int ancho, int altura){
		
		this.ancho = ancho;
		this.altura = altura;
		this.coordenada = unaCoordenada;
	}
	
	public int[] caminar() {
		
		int fila = this.coordenada.obtenerPosicionY();
		int columna = this.coordenada.obtenerPosicionX();
		
	  	if (fila < this.altura/2){
	  			
	  		this.caminarDiagonalmenteParaAbajo(fila,columna);
	  	}
	  	
	  	else{
	  	
	  		this.caminarDiagonalmenteParaArriba(fila,columna);
	  	}
	    
	    return patronCaminar;
	}

	private void caminarDiagonalmenteParaAbajo(int fila, int columna) {
		
		patronCaminar[0] = 0;//fila1
		patronCaminar[2] = 1;//fila2
		if(columna < this.ancho/2){
				
			patronCaminar[1] = 1;
			patronCaminar[3] = 0;
		}
		else{
			patronCaminar[1] = -1;
			patronCaminar[3] = 0;	
		}
	}

	private void caminarDiagonalmenteParaArriba(int fila, int columna) {
		
		patronCaminar[0] = 0;//fila1
		patronCaminar[2] = -1;//fila2
		if(columna < this.ancho/2){
				
			patronCaminar[1] = 1;
  			patronCaminar[3] = 0;	
		}
		else{ 
			patronCaminar[1] = -1;
			patronCaminar[3] = 0;	
		}	
	}

}
