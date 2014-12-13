package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class ConstruirRutaListener extends ControladorListener {

	public ConstruirRutaListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MiniConstruccion ruta = new Ruta();
		controlador.notificarUsuario("Construir ruta. Precio: $" + ruta.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= ruta.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(ruta);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}
