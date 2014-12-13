package algo3.algocity.persistencia;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import algo3.algocity.gestorDeArchivo.GestorArchivo;
import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.centrales.CentralElectrica;
import algo3.algocity.modelo.centrales.CentralEolica;
import algo3.algocity.modelo.centrales.CentralMineral;
import algo3.algocity.modelo.centrales.CentralNuclear;
import algo3.algocity.modelo.conectores.LineaDeTension;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.conectores.TuberiaDeAgua;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.edificios.Comercial;
import algo3.algocity.modelo.edificios.Edificio;
import algo3.algocity.modelo.edificios.Industrial;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.interfaces.Electrificable;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.Entubable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.MiniConstruccion;

public class Serializador {

	Fachada fachada;
	ArrayList<String> lineasDeTensionSerializadas;
	ArrayList<String> rutasSerializadas;
	ArrayList<String> tuberiasDeAguaSerializadas;
	ArrayList<String> bomberosSerializados;
	ArrayList<String> pozosSerializados;
	ArrayList<String> jugadorSerializado;
	ArrayList<String> partidaSerializada;
	GestorArchivo gestorArchivo;
	ArrayList<String> residencialesSerializados;
	ArrayList<String> comercialesSerializados;
	ArrayList<String> industrialesSerializados;
	ArrayList<String> centralesEolicasSerializadas;
	ArrayList<String> centralesMineralesSerializadas;
	ArrayList<String> centralesNuclearesSerializadas;
	ArrayList<String> nombresDeJugadoresASerializar;
	ArrayList<String> nombresSerializados;
	
	public Serializador(Fachada fachada,ArrayList<String> nombresDeJugadores){
		
		this.fachada = fachada;
		this.gestorArchivo = new GestorArchivo();
		this.gestorArchivo.crearCarpeta(this.fachada.obtenerJugador().obtenerNombre());
		this.lineasDeTensionSerializadas = new ArrayList<String>();
		this.rutasSerializadas = new ArrayList<String>();
		this.bomberosSerializados = new ArrayList<String>();
		this.pozosSerializados = new ArrayList<String>();
		this.jugadorSerializado = new ArrayList<String>();
		this.partidaSerializada = new ArrayList<String>();
		this.residencialesSerializados = new ArrayList<String>();
		this.comercialesSerializados = new ArrayList<String>();
		this.industrialesSerializados = new ArrayList<String>();
		this.centralesEolicasSerializadas = new ArrayList<String>();
		this.centralesMineralesSerializadas = new ArrayList<String>();
		this.centralesNuclearesSerializadas = new ArrayList<String>();
		this.tuberiasDeAguaSerializadas = new ArrayList<String>();
		this.nombresDeJugadoresASerializar = nombresDeJugadores;
		this.nombresSerializados = new ArrayList<String>();
	}
	
	public void serializarJugador() throws FileNotFoundException{
		
			PersistirJugador persistidor = new PersistirJugador();
			persistidor.serializar(fachada.obtenerJugador());
			this.jugadorSerializado.add(persistidor.obtenerSerializacion());
			this.gestorArchivo.guardar(this.jugadorSerializado,this.fachada.obtenerJugador().obtenerNombre(),"jugador");
	}
	
	public void serializarPartida() throws FileNotFoundException{
		
		PersistirPartida persistidor = new PersistirPartida();
		persistidor.serializar(fachada.obtenerJugador().obtenerPartida());
		this.partidaSerializada.add(persistidor.obtenerSerializacion());
		this.gestorArchivo.guardar(this.partidaSerializada,this.fachada.obtenerJugador().obtenerNombre(),"partida");
	}
	
	public void serializarMiniConstrucciones() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerMiniConstrucciones().isEmpty()){
			for (MiniConstruccion miniConstruccion: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerMiniConstrucciones()){
				
				Electrificable unElectrificable = null;
				if (miniConstruccion.conectarseA(unElectrificable)) { 
					PersistirLineaDeTension persistidor = new PersistirLineaDeTension();
					persistidor.serializar((LineaDeTension) miniConstruccion);
					this.lineasDeTensionSerializadas.add(persistidor.obtenerSerializacion());
					this.gestorArchivo.guardar(this.lineasDeTensionSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"lineas de tension");
				}
				
				Entubable unEntubable = null;
				if (miniConstruccion.conectarseA(unEntubable)) { 
					PersistirTuberiaDeAgua persistidor = new PersistirTuberiaDeAgua();
					persistidor.serializar((TuberiaDeAgua) miniConstruccion);
					this.tuberiasDeAguaSerializadas.add(persistidor.obtenerSerializacion());
					this.gestorArchivo.guardar(this.tuberiasDeAguaSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"tuberias de agua");
				}
				
				Enrutable unEnrutable = null;
				if (miniConstruccion.conectarseA(unEnrutable)) { 
					PersistirRuta persistidor = new PersistirRuta();
					persistidor.serializar((Ruta) miniConstruccion);
					this.rutasSerializadas.add(persistidor.obtenerSerializacion());
					this.gestorArchivo.guardar(this.rutasSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"rutas");
				}
			}
		}
	}
	public void serializarBomberos() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerEstacionDeBomberos().isEmpty()){
			for (EstacionDeBomberos bomberos: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerEstacionDeBomberos()){
				
				PersistirEstacionDeBomberos persistidor = new PersistirEstacionDeBomberos();
				persistidor.serializar(bomberos);
				this.bomberosSerializados.add(persistidor.obtenerSerializacion());
			}
			this.gestorArchivo.guardar(this.bomberosSerializados,this.fachada.obtenerJugador().obtenerNombre(),"bomberos");
		}
	}
	
	public void serializarPozosDeAgua() throws FileNotFoundException{
		
		if (!this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerPozos().isEmpty()) {
			for (PozoDeAgua pozo: this.fachada.obtenerJugador().obtenerPartida().obtenerMapa().obtenerPozos()){
				
				PersistirPozoDeAgua persistidor = new PersistirPozoDeAgua();
				persistidor.serializar(pozo);
				this.pozosSerializados.add(persistidor.obtenerSerializacion());
			}
			this.gestorArchivo.guardar(this.pozosSerializados,this.fachada.obtenerJugador().obtenerNombre(),"pozos");
		}
	}
	
	
	public void serializarMegaConstrucciones() throws FileNotFoundException{
		
		  for(MegaConstruccion construccion: this.fachada.obtenerMegaConstrucciones()){

		     if(construccion.esUnaConstruccionEnergetica()){ this.serializarCentralesElectricas(construccion);} 
		     else{ this.serializarEdificios(construccion);}
		  }
	}

	private void serializarEdificios(MegaConstruccion construccionASerializar) throws FileNotFoundException {
		
		if(construccionASerializar.tienePoblacion()){
			
			PersistirResidencial persistidor = new PersistirResidencial();
			persistidor.serializar((Residencial) construccionASerializar);
			this.residencialesSerializados.add(persistidor.obtenerSerializacion());
			this.gestorArchivo.guardar(this.residencialesSerializados,this.fachada.obtenerJugador().obtenerNombre(),"residenciales");
			
		}else{
			if(((Edificio) construccionASerializar).tieneEmpleo()){
				
				PersistirIndustrial persistidor = new PersistirIndustrial();
				persistidor.serializar((Industrial) construccionASerializar);
				this.industrialesSerializados.add(persistidor.obtenerSerializazion());
				this.gestorArchivo.guardar(this.industrialesSerializados,this.fachada.obtenerJugador().obtenerNombre(),"industriales");
			}else{
			
				PersistirComercial persistidor =  new PersistirComercial();
				persistidor.serializar((Comercial) construccionASerializar);
				this.comercialesSerializados.add(persistidor.obtenerSerializacion());
				this.gestorArchivo.guardar(this.comercialesSerializados,this.fachada.obtenerJugador().obtenerNombre(),"comerciales");
			}
		}
		
	}

	private void serializarCentralesElectricas(MegaConstruccion construccionASerializar) throws FileNotFoundException {
		
		if(((CentralElectrica) construccionASerializar).tieneAbastecimientoEnMW(100)){
			
			PersistirCentralEolica persistidor = new PersistirCentralEolica();
			persistidor.serializar((CentralEolica) construccionASerializar);
			this.centralesEolicasSerializadas.add(persistidor.obtenerSerializazion());
			this.gestorArchivo.guardar(this.centralesEolicasSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"centrales eolicas");
		}	
		if(((CentralElectrica) construccionASerializar).tieneAbastecimientoEnMW(400)){
		
			PersistirCentralMineral persistidor = new PersistirCentralMineral();
			persistidor.serializar((CentralMineral) construccionASerializar);
			this.centralesMineralesSerializadas.add(persistidor.obtenerSerializazion());
			this.gestorArchivo.guardar(this.centralesMineralesSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"centrales minerales");
		}
		if(((CentralElectrica) construccionASerializar).tieneAbastecimientoEnMW(1000)){
			
			PersistirCentralNuclear persistidor = new PersistirCentralNuclear();
			persistidor.serializar((CentralNuclear) construccionASerializar);
			this.centralesNuclearesSerializadas.add(persistidor.obtenerSerializazion());
			this.gestorArchivo.guardar(this.centralesNuclearesSerializadas,this.fachada.obtenerJugador().obtenerNombre(),"centrales nucleares");
		}
		
	}
	
	public void serializarListaDeJugadores() throws FileNotFoundException{
		
		PersistirListaDeJugadores persistidor = new PersistirListaDeJugadores();
		for (String nombreASerializar: this.nombresDeJugadoresASerializar){
			persistidor.serializar(nombreASerializar);
			this.nombresSerializados.add(persistidor.obtenerSerializacion());
			this.gestorArchivo.guardar(this.nombresSerializados,"lista de jugadores","jugadores");
		}
	}

	public void serializarTodo() throws FileNotFoundException {
		
		this.serializarBomberos();
		this.serializarJugador();
		this.serializarMegaConstrucciones();
		this.serializarMiniConstrucciones();
		this.serializarPartida();
		this.serializarPozosDeAgua();
		this.serializarListaDeJugadores();
	}
}
