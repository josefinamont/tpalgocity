package algo3.algocity.vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Mapa;

public class EstadoVistaMapaSuperficie implements EstadoVistaMapa {
	
	private Dibujante dibujante = new Dibujante();
	private final int ANCHO_HECTAREA = 50;
	private final int ALTO_HECTAREA = 50;
	
	public void dibujar(Graphics graphics, Mapa mapa) {

		dibujarTerreno(graphics);
		dibujarAgua(graphics, mapa);
		dibujarGrilla(graphics, mapa);
		dibujarMegaConstrucciones(graphics, mapa);
		dibujarMiniConstruccionesSuperficiales(graphics, mapa);
		dibujarEstacionesDeBomberos(graphics, mapa);
		dibujarPozoDeAgua(graphics,mapa);
		dibujarCatastrofe(graphics, mapa);
	}

	private void dibujarCatastrofe(Graphics g,Mapa mapa){
		
		if (mapa.hayUnaCatastrofeEnElMapa()){
			
			dibujante.dibujarCatastrofe(g, mapa.obtenerCatastrofeActual(),mapa.obtenerPosicionDeLaCatastrofe());
		}
	}
	
	private void dibujarPozoDeAgua(Graphics g,Mapa mapa){
		
		for(PozoDeAgua pozo : mapa.obtenerPozos()){
			
			dibujante.dibujarPozoDeAgua(g,pozo);
		}
	}
	
	private void dibujarMegaConstrucciones(Graphics g, Mapa mapa) {
	
		for(MegaConstruccion megaConstruccion : mapa.obtenerMegaConstrucciones()){
			
			dibujante.dibujarMega(g,megaConstruccion);
		}
	}
		
	private void dibujarMiniConstruccionesSuperficiales(Graphics g, Mapa mapa){
		
		for(MiniConstruccion miniConstruccion : mapa.obtenerMiniConstrucciones()){
			
			dibujante.dibujarMiniSuperficiales(g,miniConstruccion);
		}
	}

	private void dibujarEstacionesDeBomberos(Graphics g, Mapa mapa){
	
		for(EstacionDeBomberos estacionDebomberos : mapa.obtenerEstacionDeBomberos()){
		
			dibujante.dibujarEstacionesDeBomberos(g,estacionDebomberos);
		}
	}
	
	private void dibujarGrilla(Graphics g, Mapa mapa) {
	
		g.setColor(Color.BLACK);
		
		for(int columna = 0; columna < mapa.obtenerAltura() * ANCHO_DE_COLUMNA; columna += ANCHO_DE_COLUMNA) {
		
			g.drawLine(0, columna, mapa.obtenerAncho() * ANCHO_DE_FILA, columna);
		}
		
		for(int fila = 0; fila < mapa.obtenerAncho() * ANCHO_DE_FILA; fila += ANCHO_DE_FILA) {
			
			g.drawLine(fila, 0, fila, 750);
		}
	  }

	private void dibujarTerreno(Graphics g) {
		
		for(int columna = 0; columna < 14 * ANCHO_DE_COLUMNA; columna += ANCHO_DE_COLUMNA){
			
			for(int fila = 0; fila < 23 * ANCHO_DE_FILA; fila += ANCHO_DE_FILA){
				
				Image image = new ImageIcon("imagenes/grasstamaño.png").getImage();
				g.drawImage(image,fila,columna,null);
			}
		}
	}
	
	private void dibujarAgua(Graphics g, Mapa m){
	
	for(int columna = 2;columna <=  4; columna++ ){
			
			for(int fila = 2; fila <= 4 ; fila++){
			
				Image image = new ImageIcon("imagenes/Agua3c.png").getImage();
				g.drawImage(image,fila*ANCHO_HECTAREA,columna*ALTO_HECTAREA,null);
			}
		}
		
		for(int columna = 8;columna < 10; columna++ ){
			
			for(int fila = 8; fila < 10 ; fila++){
			
				Image image = new ImageIcon("imagenes/Agua3c.png").getImage();
				g.drawImage(image,fila*ANCHO_HECTAREA,columna*ALTO_HECTAREA,null);
			}
		}
		
	}

	public static void main(String[] args) {
		
		EstadoVistaMapaSuperficie v = new EstadoVistaMapaSuperficie();
	}
}
