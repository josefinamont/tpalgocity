package algo3.algocity.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class VolverAJugadorNuevoOExistente extends ControladorListener {

 public VolverAJugadorNuevoOExistente(Controlador controlador) {
		
	super(controlador);
 }
	
 public void actionPerformed(ActionEvent arg0) {
	
	controlador.obtenerVista().setPanelJugadorNuevoExistente(controlador);
 }
}
