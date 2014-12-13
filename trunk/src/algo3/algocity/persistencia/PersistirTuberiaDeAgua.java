package algo3.algocity.persistencia;

import algo3.algocity.modelo.conectores.TuberiaDeAgua;

public class PersistirTuberiaDeAgua extends Persistir{
	
	public void serializar(TuberiaDeAgua partidaASerializar){
		
		representacionJSON = gson.toJson(partidaASerializar);
	}
	
	public TuberiaDeAgua deserializar(String objetoSerializado) {
		
		TuberiaDeAgua tuberia = gson.fromJson(objetoSerializado,TuberiaDeAgua.class);
			
		 return tuberia;
	}

}
