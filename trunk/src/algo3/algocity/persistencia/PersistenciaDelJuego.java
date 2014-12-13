package algo3.algocity.persistencia;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import algo3.algocity.gestorDeArchivo.GestorArchivo;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class PersistenciaDelJuego {

	Fachada fachada;
	ArrayList<String> miniConstruccionesSerializadas;
	ArrayList<String> megaConstruccionesSerializadas;
	ArrayList<String> bomberosSerializados;
	ArrayList<String> pozosSerializados;
	ArrayList<String> jugadorSerializado;
	ArrayList<String> partidaSerializada;
	GestorArchivo gestorArchivo;
	
	public PersistenciaDelJuego(Fachada fachada){
		
		this.fachada = fachada;
		this.gestorArchivo = new GestorArchivo();
		this.gestorArchivo.crearCarpeta(this.fachada.obtenerJugador().obtenerNombre());
		this.miniConstruccionesSerializadas = new ArrayList<String>();
		this.megaConstruccionesSerializadas = new ArrayList<String>();
		this.bomberosSerializados = new ArrayList<String>();
		this.pozosSerializados = new ArrayList<String>();
		this.jugadorSerializado = new ArrayList<String>();
		this.partidaSerializada = new ArrayList<String>();
	}
	
	public void persistirJugador() throws FileNotFoundException{
		
			PersistirJugador persistidor = new PersistirJugador();
			persistidor.serializar(fachada.obtenerJugador());
			this.jugadorSerializado.add(persistidor.obtenerSerializacion());
			this.gestorArchivo.guardar(this.jugadorSerializado,this.fachada.obtenerJugador().obtenerNombre(),"jugador");
	}
	
	public void persistirPartida() throws FileNotFoundException{
		
		PersistirPartida persistidor = new PersistirPartida();
		persistidor.serializar(fachada.obtenerJugador().obtenerPartida());
		this.partidaSerializada.add(persistidor.obtenerSerializacion());
		this.gestorArchivo.guardar(this.partidaSerializada,this.fachada.obtenerJugador().obtenerNombre(),"partida");
	}
	
	public void persistirMiniConstruccionesDelMapa() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerMiniConstrucciones().isEmpty()){
			for (MiniConstruccion miniConstruccion: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerMiniConstrucciones()){
				
				Electrificable unElectrificable = null;
				if (miniConstruccion.conectarseA(unElectrificable)) { 
					PersistirLineaDeTension persistidor = new PersistirLineaDeTension();
					persistidor.serializar((LineaDeTension) miniConstruccion);
					this.miniConstruccionesSerializadas.add(persistidor.obtenerSerializacion());
				}
				
				Entubable unEntubable = null;
				if (miniConstruccion.conectarseA(unEntubable)) { 
					PersistirTuberiaDeAgua persistidor = new PersistirTuberiaDeAgua();
					persistidor.serializar((TuberiaDeAgua) miniConstruccion);
					this.miniConstruccionesSerializadas.add(persistidor.obtenerSerializacion());
				}
				
				Enrutable unEnrutable = null;
				if (miniConstruccion.conectarseA(unEnrutable)) { 
					PersistirRuta persistidor = new PersistirRuta();
					persistidor.serializar((Ruta) miniConstruccion);
					this.miniConstruccionesSerializadas.add(persistidor.obtenerSerializacion());
				}
			}
			this.gestorArchivo.guardar(this.miniConstruccionesSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"miniconstrucciones");
		}
	}
	public void persistirBomberos() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerEstacionDeBomberos().isEmpty()){
			for (EstacionDeBomberos bomberos: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerEstacionDeBomberos()){
				
				PersistirEstacionDeBomberos persistidor = new PersistirEstacionDeBomberos();
				persistidor.serializar(bomberos);
				this.bomberosSerializados.add(persistidor.obtenerSerializacion());
			}
			this.gestorArchivo.guardar(this.bomberosSerializados,this.fachada.obtenerJugador().obtenerNombre(),"bomberos");
		}
	}
	
	public void persistirPozosDeAgua() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerPozos().isEmpty()) {
			for (PozoDeAgua pozo: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerPozos()){
				
				PersistirPozoDeAgua persistidor = new PersistirPozoDeAgua();
				persistidor.serializar(pozo);
				this.pozosSerializados.add(persistidor.obtenerSerializacion());
			}
			this.gestorArchivo.guardar(this.pozosSerializados,this.fachada.obtenerJugador().obtenerNombre(),"pozos");
		}
	}

	public void persistirTodo() throws FileNotFoundException {
		
		this.persistirBomberos();
		this.persistirJugador();
		this.persistirMiniConstruccionesDelMapa();
		this.persistirPartida();
		this.persistirPozosDeAgua();
	}
}
