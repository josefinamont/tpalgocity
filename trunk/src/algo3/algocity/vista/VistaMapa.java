package algo3.algocity.vista;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import algo3.algocity.modelo.mapa.Mapa;

@SuppressWarnings("serial")
class VistaMapa extends JPanel {	
	
	private EstadoVistaMapa estadoVista;
	private Mapa mapa;
	
    public VistaMapa(EstadoVistaMapa estadoVista, Mapa mapa) {
    
    	this.estadoVista = estadoVista;
    	this.mapa = mapa;
    }

    public void paintComponent(Graphics g) {
    	
    	g.setColor(Color.BLACK);
        g.fillRect(0, 0, 750, 750);
        estadoVista.dibujar(g, mapa);
    }

	public void cambiarEstado(EstadoVistaMapa estadoVista) {
		
		this.estadoVista = estadoVista;
	}
}