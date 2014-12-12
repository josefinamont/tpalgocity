package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class ConstruirPanelCrearJugadorListener extends ControladorListener {

	
	
	public ConstruirPanelCrearJugadorListener(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			
	
			controlador.obtenerVista().cambiarAVistaIngresoPorTecladoNuevoJugador(controlador);
	}

}
