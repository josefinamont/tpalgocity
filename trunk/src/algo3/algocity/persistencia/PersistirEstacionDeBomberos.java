package algo3.algocity.persistencia;

import algo3.algocity.modelo.construibles.EstacionDeBomberos;

public class PersistirEstacionDeBomberos extends Persistir{

	public void serializar(EstacionDeBomberos estacionAserializar){
			
		representacionJSON = gson.toJson(estacionAserializar);
	}
		
	public EstacionDeBomberos deserializar(String objetoSerializado) {
			
		EstacionDeBomberos bomberos = gson.fromJson(objetoSerializado,EstacionDeBomberos.class);
				
		 return bomberos;
	}
}
