package algo3.algocity.persistencia;

import algo3.algocity.modelo.catastrofes.Godzilla;

public class PersistirGodzilla extends Persistir {
	
	public PersistirGodzilla(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(Godzilla godzilla){
		
		this.representacionJSON = gson.toJson(godzilla);
	}
	
	public Godzilla deserializar(String objetoSerializado) {
		
		Godzilla godzilla = gson.fromJson(objetoSerializado,Godzilla.class);
			
		 return godzilla;
	}

}
