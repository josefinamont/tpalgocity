package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class GuardarPartidaListener extends ControladorListener {

	public GuardarPartidaListener(Controlador controlador) {
		
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		controlador.notificarUsuario("¡Se ha generado una catástrofe!");
	}
}
