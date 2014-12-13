package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class ConstruirTuberiaDeAguaListener extends ControladorListener {

	public ConstruirTuberiaDeAguaListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MiniConstruccion tuberiaDeAgua = new TuberiaDeAgua();
		controlador.notificarUsuario("Construir tuberia. Precio: $" + tuberiaDeAgua.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= tuberiaDeAgua.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(tuberiaDeAgua);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}


