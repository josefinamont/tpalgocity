package algo3.algocity.persistencia;

import algo3.algocity.modelo.construibles.PozoDeAgua;

import com.google.gson.Gson;

public class PersistirPozoDeAgua {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(PozoDeAgua pozoAserializar){
		
		representacionJSON = gson.toJson(pozoAserializar);
	}
	
	public PozoDeAgua deserializar(String objetoSerializado) {
		
		PozoDeAgua pozo = gson.fromJson(objetoSerializado,PozoDeAgua.class);
			
		 return pozo;
	}
}
