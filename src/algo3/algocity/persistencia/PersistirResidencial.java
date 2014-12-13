package algo3.algocity.persistencia;

import algo3.algocity.modelo.edificios.Residencial;

public class PersistirResidencial extends Persistir {
	
	public PersistirResidencial(){

		this.representacionJSON = null;
	}
	
	public void serializar(Residencial residencial){
		
		this.representacionJSON = gson.toJson(residencial);
	}
	
	public Residencial deserializar(String residencialSerializado) {
		
		 Residencial residencialNuevo = gson.fromJson(residencialSerializado,Residencial.class);
		
		 return residencialNuevo;
	}

}
