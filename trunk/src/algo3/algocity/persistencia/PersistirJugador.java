package algo3.algocity.persistencia;

import algo3.algocity.modelo.menu.Jugador;

import com.google.gson.Gson;

public class PersistirJugador {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Jugador jugadorAserializar){
		
		representacionJSON = gson.toJson(jugadorAserializar);
	}
	
	public Jugador deserializar(String objetoSerializado) {
		
		Jugador jugador = gson.fromJson(objetoSerializado,Jugador.class);
			
		 return jugador;
	}

}
