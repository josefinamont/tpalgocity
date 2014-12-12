package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelElegirJugadorNuevoListener extends ControladorListener {

	public ConstruirPanelElegirJugadorNuevoListener(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		controlador.obtenerVista().definirPanelJugadorNuevoTeclado(controlador);
	}

}
