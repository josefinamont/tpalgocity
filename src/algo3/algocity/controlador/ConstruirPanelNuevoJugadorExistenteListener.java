package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelNuevoJugadorExistenteListener extends ControladorListener {

	public ConstruirPanelNuevoJugadorExistenteListener(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		controlador.obtenerVista().setPanelJugadorNuevoExistente(controlador);
	}

}
