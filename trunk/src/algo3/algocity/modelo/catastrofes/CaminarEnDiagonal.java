package algo3.algocity.modelo.catastrofes;

import algo3.algocity.modelo.mapa.Coordenada;

public class CaminarEnDiagonal implements Strategy {
	
	private Coordenada coordenada;
	private int[] patronCaminar = new int [4];
	private int altura;
	private int ancho;
	
	public CaminarEnDiagonal(Coordenada unaCoordenada,int ancho,int altura) {
		
		this.coordenada = unaCoordenada;
		this.altura = altura;
		this.ancho = ancho;
	}
	
	public int[] caminar() {
		
		int fila = this.coordenada.obtenerPosicionY();
		int columna = this.coordenada.obtenerPosicionX();
		
	  	if (fila < this.altura){
	  			
	  		this.caminarDiagonalmenteParaAbajo(fila,columna);
	  	}
	  	
	  	else{
	  	
	  		this.caminarDiagonalmenteParaArriba(fila,columna);
	  	}
	    
	    return patronCaminar;
	}

	private void caminarDiagonalmenteParaAbajo(int fila, int columna) {
		
		patronCaminar[0] = 1;//fila1
		patronCaminar[2] = 1;//fila2
		if(columna < this.ancho/2){
				
			patronCaminar[1] = 1;
			patronCaminar[3] = 1;
		}
		else{
			patronCaminar[1] = -1;
			patronCaminar[3] = -1;	
		}
	}

	private void caminarDiagonalmenteParaArriba(int fila, int columna) {
		
		patronCaminar[0] = -1;//fila1
		patronCaminar[2] = -1;//fila2
		if(columna < this.ancho/2){
				
			patronCaminar[1] = 1;
  			patronCaminar[3] = 1;	
		}
		else{ 
			patronCaminar[1] = -1;
			patronCaminar[3] = -1;	
		}	
	}

}
