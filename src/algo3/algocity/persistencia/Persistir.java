package algo3.algocity.persistencia;

import com.google.gson.Gson;

public abstract class Persistir {

	Gson gson = new Gson();
	String representacionJSON;
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}

	
}
