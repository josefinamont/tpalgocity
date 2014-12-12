package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.modelo.menu.Jugador;


 public class NuevoJuegoListener extends ControladorListener {

 private Jugador jugador;
	
 public NuevoJuegoListener(Controlador controlador, Jugador jugador) {

	super(controlador);
	this.jugador = jugador;
 }
 
 public void actionPerformed(ActionEvent e) {
		
	controlador.obtenerVista().cambiarAVistaSuperficie();
 }
}
