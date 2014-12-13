package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirCentralNuclearListener extends ControladorListener {

	public ConstruirCentralNuclearListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion centralNuclear = new CentralNuclear();
		controlador.notificarUsuario("Construir central mineral. Precio: $" + centralNuclear.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= centralNuclear.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(centralNuclear);
			controlador.obtenerVista().recibirNotificacion(controlador);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}

}
