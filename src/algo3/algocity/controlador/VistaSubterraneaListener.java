package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class VistaSubterraneaListener extends ControladorListener {

 public VistaSubterraneaListener(Controlador controlador) {
		
	 super(controlador);
 }

 public void actionPerformed(ActionEvent e) {
		
	controlador.obtenerVista().cambiarAVistaSubterranea();
 }
}
