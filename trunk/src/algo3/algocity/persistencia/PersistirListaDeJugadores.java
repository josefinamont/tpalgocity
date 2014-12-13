package algo3.algocity.persistencia;

import com.google.gson.Gson;

public class PersistirListaDeJugadores {
	
	Gson gson = new Gson();
	String representacionJSON; 
	
	public void serializar(String listaAserializar){
		
		representacionJSON = gson.toJson(listaAserializar);
	}
	
	public String deserializar(String objetoSerializado) {
		
		String nombres = gson.fromJson(objetoSerializado,String.class);
			
		 return nombres;
	}

	public String obtenerSerializacion() {
		
		return representacionJSON;
	}

}
