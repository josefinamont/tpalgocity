package algo3.algocity.persistencia;

import algo3.algocity.modelo.catastrofes.Terremoto;

public class PersistirTerremoto extends Persistir {
	
	public void serializar(Terremoto terremotoASerializar){
		
		representacionJSON = gson.toJson(terremotoASerializar);
	}
	
	public Terremoto deserializar(String objetoSerializado) {
		
		Terremoto terremoto = gson.fromJson(objetoSerializado,Terremoto.class);
			
		 return terremoto;
	}
}
