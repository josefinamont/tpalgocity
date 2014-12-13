package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class ConstruirLineaDeTensionListener extends ControladorListener {

	public ConstruirLineaDeTensionListener(Controlador controlador) {

		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MiniConstruccion lineaDeTension = new LineaDeTension();
		controlador.notificarUsuario("Construir una linea de tension. Precio: $" + lineaDeTension.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= lineaDeTension.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(lineaDeTension);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}
