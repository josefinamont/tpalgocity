package algo3.algocity.vista;

import java.awt.event.ActionEvent;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.controlador.ControladorListener;

public class VistaMapaConBotones extends ControladorListener {
	
	public VistaMapaConBotones(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			controlador.obtenerVista().setPanelVistaMapaConBotones(controlador);
	}

}
