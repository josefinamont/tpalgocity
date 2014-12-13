package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirResidencialListener extends ControladorListener{

	public ConstruirResidencialListener(Controlador controlador) {

		super(controlador);
	}	
	
	public void actionPerformed(ActionEvent arg0) {
		
		MegaConstruccion residencia = new Residencial();
		controlador.notificarUsuario("Construir zona residencial. Precio: $" + residencia.costo() + ".");
		if (controlador.obtenerFachada().obtenerJugador().dinero() >= residencia.costo()) {
			controlador.definirSiguienteConstruccionAConstruir(residencia);
			controlador.obtenerVista().cambiarAVistaSuperficie();
		} else controlador.notificarUsuario("No tiene dinero suficiente para construir lo requerido");
	}	
}

