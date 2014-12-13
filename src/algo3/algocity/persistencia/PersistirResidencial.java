package algo3.algocity.persistencia;

import algo3.algocity.modelo.edificios.Residencial;

import com.google.gson.Gson;

public class PersistirResidencial {

	String representacionJSON;
	Gson gson = new Gson();
	
	public PersistirResidencial(){

		this.representacionJSON = null;
	}
	
	public void serializar(Residencial residencial){
		
		this.representacionJSON = gson.toJson(residencial);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public Residencial deserializar(String residencialSerializado) {
		
		 Residencial residencialNuevo = gson.fromJson(residencialSerializado,Residencial.class);
		
		 return residencialNuevo;
	}

}
