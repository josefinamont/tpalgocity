package algo3.algocity.persistencia;

import algo3.algocity.modelo.menu.Jugador;

public class PersistirJugador extends Persistir {
	
	public void serializar(Jugador jugadorAserializar){
		
		representacionJSON = gson.toJson(jugadorAserializar);
	}
	
	public Jugador deserializar(String objetoSerializado) {
		
		Jugador jugador = gson.fromJson(objetoSerializado,Jugador.class);
			
		 return jugador;
	}

	
}
