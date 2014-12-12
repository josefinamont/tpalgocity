package algo3.algocity.modelo.centrales;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.mapa.Coordenada;

public class CentralNuclear extends CentralElectrica {
	
	public CentralNuclear(){ 
		   
		super();
		this.costo = 10000;
        this.abastecimientoEnMW = 1000;
        this.radioDeAbastecimientoEnHa = 25;
    }

	public void repararse() {
		
		if (this.porcentajeDeVida <= 97) {
			int nuevoPorcentaje = this.porcentajeDeVida + 3;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}
	
	 public CentralNuclear generarNuevaInstancia(){
		   
		   return new CentralNuclear();
	   }

 public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {

	 if(!this.tienePorcentajeDeVida(0)){
		 
		java.awt.Image image = new ImageIcon("imagenes/nuclear.png").getImage();
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
