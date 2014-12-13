package algo3.algocity.persistencia;

import algo3.algocity.modelo.construibles.PozoDeAgua;

public class PersistirPozoDeAgua extends Persistir {

	public void serializar(PozoDeAgua pozoAserializar){
		
		representacionJSON = gson.toJson(pozoAserializar);
	}
	
	public PozoDeAgua deserializar(String objetoSerializado) {
		
		PozoDeAgua pozo = gson.fromJson(objetoSerializado,PozoDeAgua.class);
			
		 return pozo;
	}

}
