package algo3.algocity.persistencia;

import algo3.algocity.modelo.conectores.LineaDeTension;

public class PersistirLineaDeTension extends Persistir {
	
	public PersistirLineaDeTension(){
		
		this.representacionJSON = null;
	}
	
	public void serializar(LineaDeTension linea){
		
		this.representacionJSON = gson.toJson(linea);
	}
	
	public LineaDeTension deserializar(String lineaSerializada) {
		
		LineaDeTension lineaNueva = gson.fromJson(lineaSerializada,LineaDeTension.class);
		
		 return lineaNueva;
	}
	
}
