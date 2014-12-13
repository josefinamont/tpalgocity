package algo3.algocity.controlador;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.Timer;

import algo3.algocity.persistencia.Serializador;

public class GuardarPartidaListener extends ControladorListener {
	
	Timer timer;
	
	public GuardarPartidaListener(Controlador controlador,Timer timer) {
		
		super(controlador);
		this.timer = timer;
	}

	public void actionPerformed(ActionEvent e) {
		
		//controlador.obtenerFachada();
		
		//Jugador jugador = controlador.obtenerFachada().obtenerJugador();
		//Partida partida = controlador.obtenerFachada().obtenerJugador().obtenerPartida();
		timer.cancel();
		Serializador serializador = new Serializador(controlador.obtenerFachada(),controlador.obtenerNombres());
		try {
			//timer.cancel();
			serializador.serializarTodo();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		controlador.obtenerVista().volverAPanelBienvenida(controlador);
	}
}
