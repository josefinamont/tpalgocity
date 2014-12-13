package algo3.algocity.persistencia;

import algo3.algocity.modelo.catastrofes.Godzilla;

import com.google.gson.Gson;

public class PersistirGodzilla {
	
	String representacionJSON;
	Gson gson = new Gson();
	
	public PersistirGodzilla(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(Godzilla godzilla){
		
		this.representacionJSON = gson.toJson(godzilla);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public Godzilla deserializar(String godzillaSerializado) {
		
		 Godzilla godzillaNuevo = gson.fromJson(godzillaSerializado,Godzilla.class);
		
		 return godzillaNuevo;
	}

}
