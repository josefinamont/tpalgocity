package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class VistaSuperficieListener extends ControladorListener {

 public VistaSuperficieListener(Controlador controlador) {

	super(controlador);
 }

 public void actionPerformed(ActionEvent e) {
		
	controlador.obtenerVista().cambiarAVistaSuperficie();
 }
}
