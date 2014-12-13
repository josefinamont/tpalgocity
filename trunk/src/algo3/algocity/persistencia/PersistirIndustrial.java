package algo3.algocity.persistencia;

import algo3.algocity.modelo.edificios.Industrial;

public class PersistirIndustrial extends Persistir{
	
	
	public void serializar(Industrial industrialAserializar){
		
		representacionJSON = gson.toJson(industrialAserializar);
	}
	
	public Industrial deserializar(String objetoSerializado) {
		
		 Industrial industrial = gson.fromJson(objetoSerializado,Industrial.class);
			
		 return industrial;
	}

}
