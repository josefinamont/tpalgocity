package algo3.algocity.persistencia;

import algo3.algocity.modelo.menu.Partida;

import com.google.gson.Gson;

public class PersistirPartida {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Partida partidaASerializar){
		
		representacionJSON = gson.toJson(partidaASerializar);
	}
	
	public Partida deserializar(String objetoSerializado) {
		
		Partida partida = gson.fromJson(objetoSerializado,Partida.class);
			
		 return partida;
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
}
