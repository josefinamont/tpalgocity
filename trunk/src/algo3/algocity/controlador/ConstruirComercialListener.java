package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

public class ConstruirComercialListener extends ControladorListener {

	public ConstruirComercialListener(Controlador controlador) {
	
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		MegaConstruccion comercio = new Comercial();
		controlador.notificarUsuario("Construir zona comercial. Precio: $" + comercio.costo() + ".");
		controlador.definirSiguienteConstruccionAConstruir(comercio);
		controlador.obtenerVista().cambiarAVistaSuperficie();
	}
}
