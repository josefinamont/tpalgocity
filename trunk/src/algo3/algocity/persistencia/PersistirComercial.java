package algo3.algocity.persistencia;

import algo3.algocity.modelo.edificios.Comercial;

public class PersistirComercial extends Persistir {
	
	
	public void serializar(Comercial comercialAserializar){
		
		representacionJSON = gson.toJson(comercialAserializar);
	}
	
	public Comercial deserializar(String objetoSerializado) {
		
		 Comercial comercial = gson.fromJson(objetoSerializado,Comercial.class);
			
		 return comercial;
	}

}
