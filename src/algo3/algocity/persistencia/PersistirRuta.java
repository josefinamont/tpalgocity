package algo3.algocity.persistencia;

import algo3.algocity.modelo.conectores.Ruta;

public class PersistirRuta extends Persistir {

	public PersistirRuta(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(Ruta ruta){
		
		this.representacionJSON = gson.toJson(ruta);
	}
	
	public Ruta deserializar(String rutaSerializada) {
		
		 Ruta rutaNueva = gson.fromJson(rutaSerializada,Ruta.class);
		
		 return rutaNueva;
	}
	
}
