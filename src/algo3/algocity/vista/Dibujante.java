package algo3.algocity.vista;

import java.awt.Graphics;

import algo3.algocity.modelo.catastrofes.Catastrofe;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;

public class Dibujante {

	
	public static final int ANCHO_DE__CONSTRUCCION = 50;
	
	public void dibujarMega(Graphics g,MegaConstruccion megaConstruccion){
		
		Coordenada coordenada = megaConstruccion.obtenerHectareaALaQuePertenece().obtenerCoordenada();
		
		Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		
		 megaConstruccion.dibujarse(g,coordDimensionada);
	}	

	public void dibujarMiniSuperficiales(Graphics g,MiniConstruccion miniConstruccion){
		
		Coordenada coordenada = miniConstruccion.obtenerHectareaALaQuePertenece().obtenerCoordenada();
		Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		
		miniConstruccion.dibujarseSuperficie(g,coordDimensionada);
	}
	
	public void dibujarEstacionesDeBomberos(Graphics g,MegaConstruccion megaConstruccion ){
		
		  Coordenada coordenada = megaConstruccion.obtenerHectareaALaQuePertenece().obtenerCoordenada();
		  Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		  
		  megaConstruccion.dibujarse(g,coordDimensionada);
	}
	
	public void dibujarPozoDeAgua(Graphics g,MegaConstruccion megaConstruccion ){
		
		  Coordenada coordenada = megaConstruccion.obtenerHectareaALaQuePertenece().obtenerCoordenada();
		  Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		  
		  megaConstruccion.dibujarse(g,coordDimensionada);
	}
	
	public void dibujarTuberiasDeAgua(Graphics g,MiniConstruccion miniConstruccion){
		
		   Coordenada coordenada = miniConstruccion.obtenerHectareaALaQuePertenece().obtenerCoordenada();
		   Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		   
		   miniConstruccion.dibujarseSubterranea(g,coordDimensionada);
	}
	
	public void dibujarCatastrofe(Graphics g,Catastrofe catastrofe,Coordenada coordenada){
		
		Coordenada coordDimensionada = new Coordenada(coordenada.obtenerPosicionY() * ANCHO_DE__CONSTRUCCION, coordenada.obtenerPosicionX() * ANCHO_DE__CONSTRUCCION);
		
		catastrofe.dibujarCatastrofe(g, coordDimensionada);
	}
}
