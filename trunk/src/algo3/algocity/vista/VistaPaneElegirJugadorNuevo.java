package algo3.algocity.vista;

import java.awt.event.ActionEvent;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.controlador.ControladorListener;

public class VistaPaneElegirJugadorNuevo extends ControladorListener {

	public VistaPaneElegirJugadorNuevo(Controlador controlador) {
		
		super(controlador);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		controlador.obtenerVista().definirPanelJugadorNuevoTeclado(controlador);
	}

}
