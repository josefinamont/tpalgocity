package algo3.algocity.persistencia;


import algo3.algocity.modelo.conectores.TuberiaDeAgua;

import com.google.gson.Gson;

public class PersistirTuberiaDeAgua {

	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(TuberiaDeAgua partidaASerializar){
		
		representacionJSON = gson.toJson(partidaASerializar);
	}
	
	public TuberiaDeAgua deserializar(String objetoSerializado) {
		
		TuberiaDeAgua tuberia = gson.fromJson(objetoSerializado,TuberiaDeAgua.class);
			
		 return tuberia;
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
}
