package algo3.algocity.persistencia;

import java.util.ArrayList;

import algo3.algocity.modelo.interfaces.MegaConstruccion;

import com.google.gson.Gson;

public class SerializacionPequeñosObjetos {
	
	ArrayList<String> coordenadasSerializadas;
	String representacionJSON = null;
	Gson gson = new Gson();
	
	public SerializacionPequeñosObjetos(){
		
		coordenadasSerializadas = new ArrayList<String>();
	}
	
	public void serializar(Object unObjeto){
		
		representacionJSON = gson.toJson(unObjeto);
	}
	
	public String obtenerSerializacion() {
		
		return this.representacionJSON;
	}
	
	public void serializarCoordenadasDeLasMegaConstruccionesDelMapa(ArrayList<MegaConstruccion> megaConstrucciones) {
		
		String representacionActual = new String();
		for(MegaConstruccion construccionActual: megaConstrucciones){
	
			representacionActual = gson.toJson(construccionActual.obtenerHectareaALaQuePertenece().obtenerCoordenada());
			coordenadasSerializadas.add(representacionActual);
		}
	}	
	
	public void serializarCoordenadasDeLasEstacionesDeBomberosDelMapa(ArrayList<MegaConstruccion> megaConstrucciones){
	
	
	}
	
	public String obtenerSerializacionDeUnaCoordenadaDeLaPosicion(int posicion) {
			
		
			return this.coordenadasSerializadas.get(posicion);
		}
	
	}


