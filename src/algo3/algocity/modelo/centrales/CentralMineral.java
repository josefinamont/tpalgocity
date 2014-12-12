package algo3.algocity.modelo.centrales;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.mapa.Coordenada;

public class CentralMineral extends CentralElectrica {

	public CentralMineral() { 
		   
		super();
		this.costo = 3000;
        this.abastecimientoEnMW = 400;
        this.radioDeAbastecimientoEnHa = 10;
    }

	public void repararse() {
		
		if (this.porcentajeDeVida <= 90) {
			int nuevoPorcentaje = this.porcentajeDeVida + 10;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}
	
	 public CentralMineral generarNuevaInstancia(){
		   
		   return new CentralMineral();
	   }

 public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
		 
	if(!this.tienePorcentajeDeVida(0)){
		 
		java.awt.Image image = new ImageIcon("imagenes/mineral.png").getImage();
		g.drawImage(image,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		
		if (!this.tieneAgua()){
			
			java.awt.Image image1 = new ImageIcon("imagenes/sinAgua.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
		if(!this.tienePorcentajeDeVida(100)){
			
			java.awt.Image image1 = new ImageIcon("imagenes/dañado.png").getImage();
			g.drawImage(image1,coordenadaDimensionada.obtenerPosicionX(),coordenadaDimensionada.obtenerPosicionY(),null);
		}
	}
 }
}
