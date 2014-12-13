package algo3.algocity.persistencia;

import algo3.algocity.modelo.centrales.CentralNuclear;

public class PersistirCentralNuclear extends Persistir {
	
	public void serializar(CentralNuclear centralNuclearAserializar){
		
		representacionJSON = gson.toJson(centralNuclearAserializar);
	}
	
	public CentralNuclear deserializar(String objetoSerializado) {
		
		 CentralNuclear central = gson.fromJson(objetoSerializado,CentralNuclear.class);
			
		 return central;
	}

}
