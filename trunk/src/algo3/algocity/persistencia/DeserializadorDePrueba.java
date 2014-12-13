package algo3.algocity.persistencia;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Type;

import com.google.gson.Gson;

import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Poblacion;
import algo3.algocity.modelo.mapa.*;

public class DeserializadorDePrueba {
	
	Gson gson = new Gson();
	
	public DeserializadorDePrueba(){
		
	}

	public PozoDeAgua deserializarPozoDeAgua(String objetoSerializado) {
		 
		 PozoDeAgua unPozo; 
		 unPozo = gson.fromJson(objetoSerializado, PozoDeAgua.class);
		
		 return unPozo;
	}

	public Poblacion deserializarUnaPoblacion(String objetoSerializado) {
		
		 Poblacion poblacion = gson.fromJson(objetoSerializado,Poblacion.class);
		
		 return poblacion;
	}

	public Coordenada deserializarUnaCoordenada(String objetoSerializado) {
		
		 Coordenada coordenada = gson.fromJson(objetoSerializado,Coordenada.class);
		
		 return coordenada;
	}

	public Comercial deserializarComercial(String objetoSerializado) {
		
		 Comercial comercial = gson.fromJson(objetoSerializado,Comercial.class);
			
		 return comercial;
	}

	public Mapa deserializarUnMapa(String objetoSerializado) {
		
		Mapa unMapa = gson.fromJson(objetoSerializado,Mapa.class);
		
		return unMapa;
	}
	
}
