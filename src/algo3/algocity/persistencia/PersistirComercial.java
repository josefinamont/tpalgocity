package algo3.algocity.persistencia;

import com.google.gson.Gson;

import algo3.algocity.modelo.edificios.Comercial;

public class PersistirComercial {
	
	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(Comercial comercialAserializar){
		
		representacionJSON = gson.toJson(comercialAserializar);
	}
	
	public Comercial deserializar(String objetoSerializado) {
		
		 Comercial comercial = gson.fromJson(objetoSerializado,Comercial.class);
			
		 return comercial;
	}

	public String obtenerSerializacion() {
		
		return representacionJSON;
	}

}
