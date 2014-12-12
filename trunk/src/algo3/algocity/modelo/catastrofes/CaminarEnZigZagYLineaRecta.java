package algo3.algocity.modelo.catastrofes;

import algo3.algocity.modelo.mapa.Coordenada;

public class CaminarEnZigZagYLineaRecta implements Strategy {

	private Coordenada coordenada;
	private int[] patronCaminar = new int [4];
	private int altura;
	private int ancho;
	
	public CaminarEnZigZagYLineaRecta(Coordenada unaCoordenada, int ancho, int altura) {
		
		this.ancho = ancho;
		this.altura = altura;
		this.coordenada = unaCoordenada;
	}
	
	public int[] caminar() {
		
		int fila = this.coordenada.obtenerPosicionY();
		int columna = this.coordenada.obtenerPosicionX();
		
		patronCaminar[0] = 1;//fila1
	    patronCaminar[1] = 1;//columna1
		patronCaminar[2] = 1;//fila2
	    patronCaminar[3] = 1;//columna2
		
		if (fila !=0 && fila != altura-1){ 
			this.caminarHorizontalmente(fila, columna);
		}
		else{
			this.caminarVerticalmente(fila, columna);
			}
		
		return patronCaminar;
	}	

	public void caminarHorizontalmente(int fila, int columna){
		
		//con esto logramos que vaya de la fila 1 = 1 hasta la fila2 = -1, el efecto zigzag
		
		patronCaminar[2] = -1;
	    
		if(columna != 0){ 
			
			  patronCaminar[1] = -1; 
		      patronCaminar[3] = -1;
		}
	}
  
	public void caminarVerticalmente(int fila, int columna){
			
		if(fila == 0 ){
			
			if( columna != ancho -1) { patronCaminar[3] = -1;}
			
			else{ patronCaminar[1] = -1;}	
		}
		 
		if(fila == altura-1){
			
			patronCaminar[0] = -1;//fila1
			patronCaminar[2] = -1;//fila2
		    patronCaminar[3] = -1;//columna2
		    
		    if( columna != ancho-1) { patronCaminar[1] = 1;}
			else{ 
				patronCaminar[1] = -1; 
				patronCaminar[3] = 1;
			}
		}
	}
	
}
