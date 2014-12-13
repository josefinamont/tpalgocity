package algo3.algocity.controlador;

import java.awt.event.ActionEvent;

import algo3.algocity.persistencia.PersistenciaDelJuego;

public class GuardarPartidaListener extends ControladorListener {
	
	public GuardarPartidaListener(Controlador controlador) {
		
		super(controlador);
	}

	public void actionPerformed(ActionEvent e) {
		
		//controlador.notificarUsuario("¡Se ha generado una catástrofe!");
		//deberia detener el timer
		//controlador.obtenerFachada();
		
		//Jugador jugador = controlador.obtenerFachada().obtenerJugador();
		//Partida partida = controlador.obtenerFachada().obtenerJugador().obtenerPartida();
		
		PersistenciaDelJuego persistencia = new PersistenciaDelJuego(controlador.obtenerFachada());
		
	}
}
