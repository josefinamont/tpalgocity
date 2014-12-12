package algo3.algocity.modelo.centrales;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import algo3.algocity.modelo.mapa.Coordenada;

public class CentralEolica extends CentralElectrica {

	public CentralEolica(){ 

		super();
		this.costo = 1000;
        this.abastecimientoEnMW = 100;
        this.radioDeAbastecimientoEnHa = 4;
    }	
	
	public void repararse() {
		
		if (this.porcentajeDeVida <= 85) {
			int nuevoPorcentaje = this.porcentajeDeVida + 15;
			this.porcentajeDeVida = nuevoPorcentaje;
		} else { this.porcentajeDeVida = 100; }
	}
	
	 public CentralEolica generarNuevaInstancia(){
		   
		   return new CentralEolica();
	   }


	public void dibujarse(Graphics g,Coordenada coordenadaDimensionada) {
		
		if(!this.tienePorcentajeDeVida(0)){
		
			java.awt.Image image = new ImageIcon("imagenes/eolico.png").getImage();
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
