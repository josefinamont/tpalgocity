package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirCentralEolicaListener extends ControladorListener {

	public ConstruirCentralEolicaListener(Controlador controlador) {
		
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		MegaConstruccion centralEolica = new CentralEolica();
		controlador.notificarUsuario("Construir central eolica. Precio: $" + centralEolica.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= centralEolica.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(centralEolica);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}

}
