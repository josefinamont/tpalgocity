package algo3.algocity.modelo.catastrofes;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.interfaces.Dañable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Mapa;

public class Godzilla implements Catastrofe {
	
	private transient int tamañoEnHa;
	private int[] patronDeMovimiento;
	
	public Godzilla(Strategy estrategia) {
		
		this.tamañoEnHa = 1;
		patronDeMovimiento = estrategia.caminar();
	}

	public Godzilla() {

	}

	public void destruirEstructura(Dañable unDañable) {
		
		unDañable.aplicarDañoRecibido(this);
	}

	public int tamañoEnHa() {
		
		return this.tamañoEnHa;
	}

	public void provocarDañosEn(Mapa unMapa) {
		
		Coordenada coordenada = unMapa.obtenerPosicionDeLaCatastrofe();
		int fila = coordenada.obtenerPosicionY();
		int columna = coordenada.obtenerPosicionX();
		MegaConstruccion megaConstruccion = null;
		ArrayList<MiniConstruccion> miniConstrucciones = null;
		
		
		if(fila < unMapa.obtenerAltura()&& fila >= 0 &&(columna < unMapa.obtenerAncho()&& columna >= 0 )){
				
				megaConstruccion = unMapa.obtenerUnSectorDelMapa(new Coordenada(fila,columna)).obtenerMegaConstruccion();
				miniConstrucciones = unMapa.obtenerUnSectorDelMapa(new Coordenada(fila,columna)).obtenerMiniConstruccion();
				this.godzillaDañaConstruccionesASuPaso(megaConstruccion,miniConstrucciones,unMapa);
			
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
	
	private void godzillaDañaConstruccionesASuPaso(MegaConstruccion megaConstruccion,ArrayList<MiniConstruccion> miniConstrucciones, Mapa mapa){
		
		if(megaConstruccion != null && mapa.verificarSiMegaConstruccionSeEncuentraEnElMapa(megaConstruccion)){
			this.destruirEstructura((Dañable) megaConstruccion);
		}
		else{
			if(miniConstrucciones != null)
				for(MiniConstruccion construccionActual : miniConstrucciones)
					this.destruirEstructura((Dañable) construccionActual);
		}
	}

	public void destruirPorTurno(Mapa unMapa) {
		
		this.provocarDañosEn(unMapa);
	}

	public void dibujarCatastrofe(Graphics g,Coordenada coordDimensionada) {
		
		Image image = new ImageIcon("imagenes/godzillaverde.png").getImage();
		g.drawImage(image,coordDimensionada.obtenerPosicionX(),coordDimensionada.obtenerPosicionY(),null);
	}
	
}