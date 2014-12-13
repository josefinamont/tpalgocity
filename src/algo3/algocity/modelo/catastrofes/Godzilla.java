package algo3.algocity.modelo.catastrofes;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.interfaces.Da�able;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class Godzilla implements Catastrofe {
	
	private transient int tama�oEnHa;
	private int[] patronDeMovimiento;
	
	public Godzilla(Strategy estrategia) {
		
		this.tama�oEnHa = 1;
		patronDeMovimiento = estrategia.caminar();
	}

	public Godzilla() {

	}

	public void destruirEstructura(Da�able unDa�able) {
		
		unDa�able.aplicarDa�oRecibido(this);
	}

	public int tama�oEnHa() {
		
		return this.tama�oEnHa;
	}

	public void provocarDa�osEn(Mapa unMapa) {
		
		Coordenada coordenada = unMapa.obtenerPosicionDeLaCatastrofe();
		int fila = coordenada.obtenerPosicionY();
		int columna = coordenada.obtenerPosicionX();
		MegaConstruccion megaConstruccion = null;
		ArrayList<MiniConstruccion> miniConstrucciones = null;
		
		
		if(fila < unMapa.obtenerAltura()&& fila >= 0 &&(columna < unMapa.obtenerAncho()&& columna >= 0 )){
				
				megaConstruccion = unMapa.obtenerUnSectorDelMapa(new Coordenada(fila,columna)).obtenerMegaConstruccion();
				miniConstrucciones = unMapa.obtenerUnSectorDelMapa(new Coordenada(fila,columna)).obtenerMiniConstruccion();
				this.godzillaDa�aConstruccionesASuPaso(megaConstruccion,miniConstrucciones,unMapa);
			
				if (unMapa.estadoDeMovimientoDelPasoDeCatastrofeEs()){
					fila += this.patronDeMovimiento[0];
					columna += this.patronDeMovimiento[1];
					unMapa.modificarEstadoDeMovimientoDelPasoDeCatastrofe(false);
				}
				else{
						fila += this.patronDeMovimiento[2];
						columna += this.patronDeMovimiento[3];
						unMapa.modificarEstadoDeMovimientoDelPasoDeCatastrofe(true);
					}
		   
				if(fila== unMapa.obtenerAltura()||fila < 0|| columna < 0||columna==unMapa.obtenerAncho()){
					unMapa.modificarPosicionDeLaCatastrofe(null);  
					unMapa.definirCatastrofe(null);
				} else {unMapa.modificarPosicionDeLaCatastrofe(new Coordenada(fila,columna));} 
		}	
	}
	
	private void godzillaDa�aConstruccionesASuPaso(MegaConstruccion megaConstruccion,ArrayList<MiniConstruccion> miniConstrucciones, Mapa mapa){
		
		if(megaConstruccion != null && mapa.verificarSiMegaConstruccionSeEncuentraEnElMapa(megaConstruccion)){
			this.destruirEstructura((Da�able) megaConstruccion);
		}
		else{
			if(miniConstrucciones != null)
				for(MiniConstruccion construccionActual : miniConstrucciones)
					this.destruirEstructura((Da�able) construccionActual);
		}
	}

	public void destruirPorTurno(Mapa unMapa) {
		
		this.provocarDa�osEn(unMapa);
	}

	public void dibujarCatastrofe(Graphics g,Coordenada coordDimensionada) {
		
		Image image = new ImageIcon("imagenes/godzillaverde.png").getImage();
		g.drawImage(image,coordDimensionada.obtenerPosicionX(),coordDimensionada.obtenerPosicionY(),null);
	}
	
}