package algo3.algocity.persistencia;

import algo3.algocity.modelo.construibles.EstacionDeBomberos;

import com.google.gson.Gson;

public class PersistirEstacionDeBomberos {

	Gson gson = new Gson();
	String representacionJSON; 
		
	public void serializar(EstacionDeBomberos estacionAserializar){
			
		representacionJSON = gson.toJson(estacionAserializar);
	}
		
	public EstacionDeBomberos deserializar(String objetoSerializado) {
			
		EstacionDeBomberos bomberos = gson.fromJson(objetoSerializado,EstacionDeBomberos.class);
				
		 return bomberos;
	}

	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
}
