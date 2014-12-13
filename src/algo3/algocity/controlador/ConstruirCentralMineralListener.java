package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.centrales.CentralMineral;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirCentralMineralListener extends ControladorListener {

	public ConstruirCentralMineralListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion centralMineral = new CentralMineral();
		controlador.notificarUsuario("Construir central mineral. Precio: $" + centralMineral.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= centralMineral.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(centralMineral);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}
