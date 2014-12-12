package algo3.algocity.vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import algo3.algocity.controlador.Controlador;

public class VistaFoto extends JPanel{

public VistaFoto(Controlador controlador){
		
		this.setLayout(null);
	}
	
	protected  void paintComponent(Graphics g){
		
		super.paintComponents(g);
		Image image = new ImageIcon("imagenes/fondoInicio.png").getImage();
		g.drawImage(image,0,0,null);
	}

}
