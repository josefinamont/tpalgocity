package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelElegirJugadorExistenteTecladoListener extends ControladorListener {

	public ConstruirPanelElegirJugadorExistenteTecladoListener(Controlador controlador) {
		
		super(controlador);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			controlador.obtenerVista().definirPanelJugadorExistenteTeclado(controlador);
	}
}
