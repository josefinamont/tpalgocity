package algo3.algocity.persistencia;

public class PersistirListaDeJugadores extends Persistir{
	
	public void serializar(String listaAserializar){
		
		representacionJSON = gson.toJson(listaAserializar);
	}
	
	public String deserializar(String objetoSerializado) {
		
		String nombres = gson.fromJson(objetoSerializado,String.class);
			
		 return nombres;
	}

}
