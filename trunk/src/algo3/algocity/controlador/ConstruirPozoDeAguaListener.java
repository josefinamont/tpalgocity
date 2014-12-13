package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirPozoDeAguaListener extends ControladorListener {

	public ConstruirPozoDeAguaListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion pozo = new PozoDeAgua();
		controlador.notificarUsuario("Construir pozo de agua. Precio: $" + pozo.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= pozo.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(pozo);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}

