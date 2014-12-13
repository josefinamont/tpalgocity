package algo3.algocity.modelo.catastrofes;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.interfaces.Dañable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.mapa.Mapa;

public class Terremoto implements Catastrofe {
	
	private Hectarea hectareaALaQuePertenece;
	private int duracionEnTurnos;
	
	public Terremoto() {
		
		this.hectareaALaQuePertenece = null;
		this.duracionEnTurnos = 0;
	}

	public void destruirEstructura(Dañable unDañable) {

		unDañable.aplicarDañoRecibido(this);
	}

	public void establecerHectareaALaQuePertenece(Hectarea hectarea) {
		
		this.hectareaALaQuePertenece = hectarea;
	}

	public Hectarea obtenerHectareaALaQuePertenece() {

		return this.hectareaALaQuePertenece;
	}

	public void provocarDañosEn(Mapa mapa) {
		
		this.duracionEnTurnos++;
		for (MegaConstruccion megaConstruccionActual: mapa.obtenerMegaConstrucciones()){
			((Dañable) megaConstruccionActual).aplicarDañoRecibido(this);
		}
		
		for (MiniConstruccion miniConstruccionActual: mapa.obtenerMiniConstrucciones()){
			((Dañable) miniConstruccionActual).aplicarDañoRecibido(this);
		}
		
		if (duracionEnTurnos == 2) {
			mapa.modificarPosicionDeLaCatastrofe(null);  
			mapa.definirCatastrofe(null);
			this.duracionEnTurnos = 0;
		}
	}

	public void destruirPorTurno(Mapa mapa) {
		
		this.provocarDañosEn(mapa);	
	}

	public void dibujarCatastrofe(Graphics g, Coordenada coordDimensionada) {
		
		Image image = new ImageIcon("imagenes/terremoto.png").getImage();
		g.drawImage(image,coordDimensionada.obtenerPosicionX()+10,coordDimensionada.obtenerPosicionY()+10,null);
	}
	
	public int duracionEnTurnos() {
		
		return this.duracionEnTurnos;
	}

}
