package algo3.algocity.persistencia;

import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class PersistenciaDelJuego {

	Fachada fachada;
	
	public PersistenciaDelJuego(Fachada fachada){
		
		this.fachada = fachada;
	}
	
	public void persistirJugador(){
		
		PersistirJugador persistidor = new PersistirJugador();
		persistidor.serializar(fachada.obtenerJugador());
	}
	
	public void persistirPartida(){
		
		PersistirPartida persistidor = new PersistirPartida();
		persistidor.serializar(fachada.obtenerJugador().obtenerPartida());
	}
	
	public void persistirMiniConstruccionesDelMapa(){
		
		for (MiniConstruccion miniConstruccion: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerMiniConstrucciones()){
			
			Electrificable unElectrificable = null;
			if (miniConstruccion.conectarseA(unElectrificable)) { 
				PersistirLineaDeTension persistidor = new PersistirLineaDeTension();
				persistidor.serializar((LineaDeTension) miniConstruccion);
			}
			
			Entubable unEntubable = null;
			if (miniConstruccion.conectarseA(unEntubable)) { 
				PersistirTuberiaDeAgua persistidor = new PersistirTuberiaDeAgua();
				persistidor.serializar((TuberiaDeAgua) miniConstruccion);
			}
			
			Enrutable unEnrutable = null;
			if (miniConstruccion.conectarseA(unEnrutable)) { 
				PersistirRuta persistidor = new PersistirRuta();
				persistidor.serializar((Ruta) miniConstruccion);
			}
		}
	}

}
