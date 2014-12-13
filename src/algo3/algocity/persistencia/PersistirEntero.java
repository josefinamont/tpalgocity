package algo3.algocity.persistencia;

import com.google.gson.Gson;

public class PersistirEntero {
	

	String representacionJSON;
	Gson gson = new Gson();
	
	public PersistirEntero(){

		this.representacionJSON = null;
	}
	
	public void serializar(int numero){
		
		this.representacionJSON = gson.toJson(numero);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public int deserializar(String numeroSerializado) {
		
		 int numeroDeserializado = gson.fromJson(numeroSerializado,int.class);
		
		 return numeroDeserializado;
	}

}
