package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

public class Salir extends ControladorListener {

 public Salir(Controlador controlador) {
		
	super(controlador);
 }

 public void actionPerformed(ActionEvent e) {
		
	System.exit(0);
 }
}

