package algo3.algocity.controlador;

import java.awt.event.ActionEvent;
import java.util.Timer;

 public class VolverABienvenida extends ControladorListener {

	 Timer timer;
	 
	 public VolverABienvenida(Controlador controlador) {
			
		super(controlador);
	 }
	 
	 public VolverABienvenida(Controlador controlador, Timer timer) {
		
		 super(controlador);
		 this.timer = timer;
	}

	public void actionPerformed(ActionEvent e) {

		if (this.timer != null) this.timer.cancel();
		controlador.obtenerVista().volverAPanelBienvenida(controlador);
	 }
}
