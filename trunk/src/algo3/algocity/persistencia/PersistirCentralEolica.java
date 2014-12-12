package algo3.algocity.persistencia;

import algo3.algocity.modelo.centrales.CentralEolica;

import com.google.gson.Gson;

public class PersistirCentralEolica {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(CentralEolica centralEolicaAserializar){
		
		representacionJSON = gson.toJson(centralEolicaAserializar);
	}
	
	public CentralEolica deserializar(String objetoSerializado) {
		
		 CentralEolica central = gson.fromJson(objetoSerializado,CentralEolica.class);
			
		 return central;
	}

}
