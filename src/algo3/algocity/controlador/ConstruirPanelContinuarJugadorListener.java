package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelContinuarJugadorListener extends ControladorListener {


	public ConstruirPanelContinuarJugadorListener(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		controlador.obtenerVista().cambiarAVistaIngresoPorTecladoJugadorExistente(controlador);
	}

}
