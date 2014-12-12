package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelMapaConBotonesListener extends ControladorListener {
	
	public ConstruirPanelMapaConBotonesListener(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
			controlador.obtenerVista().setPanelVistaMapaConBotones(controlador);
	}

}
