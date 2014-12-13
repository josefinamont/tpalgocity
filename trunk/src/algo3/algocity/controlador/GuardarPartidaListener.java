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
		
		Serializador persistencia = new Serializador(controlador.obtenerFachada());
		try {
			timer.cancel();
			persistencia.persistirTodo();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		controlador.obtenerVista().volverAPanelBienvenida(controlador);
	}
}
