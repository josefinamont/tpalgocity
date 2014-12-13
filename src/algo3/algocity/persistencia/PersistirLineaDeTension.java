package algo3.algocity.persistencia;

import algo3.algocity.modelo.conectores.LineaDeTension;

import com.google.gson.Gson;

public class PersistirLineaDeTension {

	String representacionJSON;
	Gson gson = new Gson();
	
	public PersistirLineaDeTension(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(LineaDeTension linea){
		
		this.representacionJSON = gson.toJson(linea);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public LineaDeTension deserializar(String lineaSerializada) {
		
		LineaDeTension lineaNueva = gson.fromJson(lineaSerializada,LineaDeTension.class);
		
		 return lineaNueva;
	}
	
}
