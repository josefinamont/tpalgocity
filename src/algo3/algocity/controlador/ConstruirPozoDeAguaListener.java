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
		controlador.definirSiguienteConstruccionAConstruir(pozo);
		controlador.obtenerVista().cambiarAVistaSuperficie();
	}
}

