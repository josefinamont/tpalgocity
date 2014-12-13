package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirBomberosListener extends ControladorListener {

	public ConstruirBomberosListener(Controlador controlador) {
		
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion estacionDeBomberos = new EstacionDeBomberos();
		controlador.notificarUsuario("Contruir estacion de bomberos. Precio: $" + estacionDeBomberos.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= estacionDeBomberos.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(estacionDeBomberos);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}
}
