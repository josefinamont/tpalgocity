package algo3.algocity.modelo.catastrofes;

import algo3.algocity.modelo.mapa.Coordenada;

public class CaminarEnLineaRecta implements Strategy {

	private Coordenada coordenada;
	private int[] patronCaminar = new int [4];
	private int ancho;
	private int altura;
	
	public CaminarEnLineaRecta(Coordenada unaCoordenada, int ancho, int altura) {
		
		this.ancho = ancho;
		this.altura = altura;
		this.coordenada = unaCoordenada;
	}

	public int[] caminar() {
		
		int fila = this.coordenada.obtenerPosicionY();
		int columna = this.coordenada.obtenerPosicionX();
	    
		//caminar Horizontalemente
		if (fila !=0 && fila != this.altura-1){
	
			if(columna == 0){ 
				patronCaminar[1] = 1;
				patronCaminar[3] = 1;
			}
			else{
				patronCaminar[1] = -1;
				patronCaminar[3] = -1;	
			}
		}
		
		else{
			//caminar Verticalmente
			if(fila == 0){ 
				patronCaminar[0] = 1;
				patronCaminar[2] = 1;	
			}
			else{ 
				patronCaminar[0] = -1;
				patronCaminar[2] = -1;	
			}	
		}
		
	return patronCaminar;
	}	
		
}
