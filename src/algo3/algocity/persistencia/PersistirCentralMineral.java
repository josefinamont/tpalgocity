package algo3.algocity.persistencia;

import algo3.algocity.modelo.centrales.CentralMineral;

import com.google.gson.Gson;

public class PersistirCentralMineral {
	
	Gson gson = new Gson();
	String representacionJSON; 
		
	public void serializar(CentralMineral centralMineralAserializar){
			
		representacionJSON = gson.toJson(centralMineralAserializar);
	}
		
	public CentralMineral deserializar(String objetoSerializado) {
			
		CentralMineral central = gson.fromJson(objetoSerializado,CentralMineral.class);
				
		 return central;
	}
}
