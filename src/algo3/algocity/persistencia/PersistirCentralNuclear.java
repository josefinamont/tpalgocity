package algo3.algocity.persistencia;

import algo3.algocity.modelo.centrales.CentralNuclear;

import com.google.gson.Gson;

public class PersistirCentralNuclear {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(CentralNuclear centralNuclearAserializar){
		
		representacionJSON = gson.toJson(centralNuclearAserializar);
	}
	
	public CentralNuclear deserializar(String objetoSerializado) {
		
		 CentralNuclear central = gson.fromJson(objetoSerializado,CentralNuclear.class);
			
		 return central;
	}


}
