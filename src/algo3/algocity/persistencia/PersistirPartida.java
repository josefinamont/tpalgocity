package algo3.algocity.persistencia;

import algo3.algocity.modelo.menu.Partida;

public class PersistirPartida extends Persistir {
	
	public void serializar(Partida partidaASerializar){
		
		representacionJSON = gson.toJson(partidaASerializar);
	}
	
	public Partida deserializar(String objetoSerializado) {
		
		Partida partida = gson.fromJson(objetoSerializado,Partida.class);
			
		 return partida;
	}

}
