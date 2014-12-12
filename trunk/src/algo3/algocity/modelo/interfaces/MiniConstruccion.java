package algo3.algocity.modelo.interfaces;

import java.awt.Graphics;

import algo3.algocity.modelo.mapa.Coordenada;

public abstract class MiniConstruccion extends Construccion {
	
	public boolean tieneAgua(){
		
		return true;
	}
	
	public boolean tieneElectricidad(){
		
		return true;
	}
	
	public abstract void dibujarseSuperficie(Graphics g,Coordenada coordenadadimensionada);
	
	public abstract void dibujarseSubterranea(Graphics g,Coordenada coordenadadimensionada);
}
