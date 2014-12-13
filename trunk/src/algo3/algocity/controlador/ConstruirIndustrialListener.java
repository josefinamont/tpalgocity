package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirIndustrialListener extends ControladorListener{

	public ConstruirIndustrialListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion industria = new Industrial();
		controlador.notificarUsuario("Construir zona industrial. Precio: $" + industria.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= industria.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(industria);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}
