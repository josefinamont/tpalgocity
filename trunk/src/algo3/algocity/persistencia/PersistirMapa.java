package algo3.algocity.persistencia;

import algo3.algocity.modelo.mapa.Mapa;

public class PersistirMapa extends Persistir{
	
	public void serializar(Mapa mapaASerializar){
		
		representacionJSON = gson.toJson(mapaASerializar);
	}
	
	public Mapa deserializar(String objetoSerializado) {
		
		Mapa mapa = gson.fromJson(objetoSerializado,Mapa.class);
			
		 return mapa;
	}
	
}
