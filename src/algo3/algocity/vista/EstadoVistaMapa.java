package algo3.algocity.vista;

import java.awt.Graphics;

import algo3.algocity.modelo.mapa.Mapa;

public interface EstadoVistaMapa {

	public static final int ANCHO_DE_COLUMNA = 50;
	public static final int ANCHO_DE_FILA    = 50;
	
	void dibujar(Graphics g, Mapa mapa);
}
