package algo3.algocity.modelo.menu;

import java.util.TimerTask;

import algo3.algocity.controlador.Controlador;
import algo3.algocity.modelo.IndiceDeFelicidad.EstadosDeFelicidad;
import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.interfaces.Enrutable;
import algo3.algocity.modelo.interfaces.MegaConstruccion;
import algo3.algocity.modelo.interfaces.Reparable;
import algo3.algocity.modelo.mapa.*;

public class Partida extends TimerTask {

	private Mapa mapa;
	private Poblacion poblacionGlobal;
	private int turnos;
	private transient FabricaDeCatastrofes fabricaDeCatastrofes;
	private transient Controlador controlador;
	private int indiceDeFelicidadGlobal;
	
	public Partida(){
		
		this.mapa = new Mapa();
		this.poblacionGlobal = new Poblacion();
		this.turnos = 0;
		this.fabricaDeCatastrofes = new FabricaDeCatastrofes();
		this.controlador = null;
		this.indiceDeFelicidadGlobal = 0;
	}
	
	public Mapa obtenerMapa(){
		
		return this.mapa;
	}
	
	public boolean vaPorElTurno(int numeroDeTurno){
		
		return this.turnos == numeroDeTurno;
	}
	
	public Poblacion obtenerPoblacion(){
		
		return this.poblacionGlobal;
	}
	
	public void pasaUnTurno() {
		
		this.turnos++;
		this.actualizarPoblacion();
		this.verificarSiLaPoblacionDebePagarleAlJugador();
		this.actualizarDatosDeLaPartida();
		this.verificarSiHayUnaCatastrofe();
		this.verificarSiDebeCrearseUnaCatastrofe();
		this.mapa.eliminarTodasLasMegaConstruccionesQueTenganVidaCero();
		this.mapa.eliminarTodasLasMiniConstruccionesQueTenganVidaCero();
		this.decirleALosBomberosQueReparen();
		this.calcularIndiceDeFelicidadGlobal();
	}
	
	private void actualizarPoblacion() {
		
		int poblacionActual = 0;
		
		for (MegaConstruccion megaConstruccion: this.mapa.obtenerMegaConstrucciones()){
				if(megaConstruccion.tienePoblacion())
				poblacionActual += ((Residencial) megaConstruccion).actualizarPoblacion();//solo van a responder correctamente los residenciales
			}
		
		this.poblacionGlobal.setNumeroDeCiudadanos(poblacionActual);

	}

	public boolean tienePoblacionActual(int numero) {
		
		return (this.poblacionGlobal.obtenerNumeroDeCuidadanos() == numero);
	}

	private void verificarSiLaPoblacionDebePagarleAlJugador(){
		
		if ( ((this.turnos % 30) == 0 && this.poblacionGlobal.obtenerNumeroDeCuidadanos() != 0) ) { 
			controlador.pagarAlJugador(); 
			controlador.notificarUsuario("La población actual le pagó: " + this.obtenerDineroAPagarAlJugadorPorCiudadano());
			controlador.obtenerVista().recibirNotificacion(controlador);
		}
	}
	
	private void verificarSiHayUnaCatastrofe(){
		
		if (controlador != null) {
			if (mapa.hayUnaCatastrofeEnElMapa()){ 
				controlador.refrescarVentana();
				controlador.notificarUsuario("¡Una catástrofe está invadiendo la ciudad!");
				this.mapa.decirleALaCatastrofeQueDestruyaPorTurno();
				controlador.refrescarVentana();
			} else { 
				controlador.notificarUsuario(" ");
				controlador.refrescarVentana();
				}
		}
	}

	private void verificarSiDebeCrearseUnaCatastrofe(){
		
		if ((this.turnos % 150) == 0) { this.crearCatastrofe(); }
	}
	
	private void decirleALosBomberosQueReparen() {
		
		for (MegaConstruccion unaConstruccion: this.mapa.obtenerMegaConstrucciones()){
			
			for (EstacionDeBomberos bomberos: this.mapa.obtenerEstacionDeBomberos())
				if (bomberos.tieneAgua() && bomberos.tieneElectricidad() && bomberos.puedeIrA((Enrutable) unaConstruccion)){
					bomberos.repararDaños((Reparable) unaConstruccion);
					
			}
		}
		
	}
	
    private void actualizarDatosDeLaPartida(){
		
    	if (controlador != null){
    		controlador.obtenerVista().definirIndicesJugador(this.turnos,this.poblacionGlobal.obtenerNumeroDeCuidadanos(),this.indiceDeFelicidadGlobal,controlador);
    	}
    }

	private void crearCatastrofe() {
		
		this.fabricaDeCatastrofes.crearCatastrofe(mapa);
	}

	public void setearPoblacion(int numeroDeCiudadanos) {
	
		this.poblacionGlobal.modificarNumeroDeCiudadanos(numeroDeCiudadanos);
	}

	public int obtenerDineroAPagarAlJugadorPorCiudadano() {
		
		return this.poblacionGlobal.obtenerDineroAPagarAlJugador();	
	}
	
	public void construir(Construccion construccion,Coordenada coordenadaDeConstruccion) throws NoSePuedeEdificarEnEsaZonaException {
		
		construccion.construirmeEn(this.mapa,coordenadaDeConstruccion);		
	}
	
	public void crearCatastrofe(Godzilla godzilla,Coordenada coordenada) {
		
		this.mapa.crearCatastrofe(godzilla);;
	}
	
	public void crearCatastrofe(Terremoto terremoto,Coordenada coordenada) {
		
		this.mapa.crearCatastrofe(terremoto,coordenada);
	}
	
	public void run() {
		
		this.pasaUnTurno();
	}

	public int obtenerTurnos() {
		
		return this.turnos;
	}

	public void definirControlador(Controlador controlador) {
		
		this.controlador = controlador;
	}
	
	public void calcularIndiceDeFelicidadGlobal(){
		
		for(MegaConstruccion construccion: this.mapa.obtenerMegaConstrucciones()){
			
			if(construccion.tienePoblacion()){
			  if(((Residencial) construccion).obtenerIndiceDeFelicidadPromedio() != null){
				
				  if(((Residencial) construccion).obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.ALTO))
					this.indiceDeFelicidadGlobal += ((Residencial) construccion).obtenerPoblacion()*100/this.poblacionGlobal.obtenerNumeroDeCuidadanos();
		
				  else{
					if(((Residencial) construccion).obtenerIndiceDeFelicidadPromedio().equals(EstadosDeFelicidad.BAJO)){
						this.indiceDeFelicidadGlobal += ((Residencial) construccion).obtenerPoblacion()*50/this.poblacionGlobal.obtenerNumeroDeCuidadanos();	
				
					}else{
						this.indiceDeFelicidadGlobal += ((Residencial) construccion).obtenerPoblacion()*25/this.poblacionGlobal.obtenerNumeroDeCuidadanos();	
					
					 }
				}
			  }
			}
		}
	}
	
	public int obtenerIndiceDeFelicidadGlobal() {
		
		return this.indiceDeFelicidadGlobal;
	}

	public void definirMapa(Mapa unMapa) {
		
		this.mapa = unMapa;
		
	}

	public void definirTurnos(int turnos) {
		
		this.turnos = turnos;
		
	}

	public void definirIndiceDeFelicidad(int indice) {
		
		this.indiceDeFelicidadGlobal = indice;
		
	}
	
}
