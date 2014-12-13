package algo3.algocity.controlador;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import algo3.algocity.persistencia.PersistenciaDelJuego;

public class GuardarPartidaListener extends ControladorListener {
	
	public GuardarPartidaListener(Controlador controlador) {
		
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		//controlador.obtenerFachada();
		
		//Jugador jugador = controlador.obtenerFachada().obtenerJugador();
		//Partida partida = controlador.obtenerFachada().obtenerJugador().obtenerPartida();
		
		PersistenciaDelJuego persistencia = new PersistenciaDelJuego(controlador.obtenerFachada());
		try {
			persistencia.persistirTodo();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
