package algo3.algocity.controlador;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.interfaces.Construccion;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.menu.Jugador;
import algo3.algocity.vista.Vista;

	public class Controlador {
		
		Vista vista;
		Fachada fachadaActual;
		ArrayList<Fachada> fachadas = new ArrayList<Fachada>();
		Construccion construccionAConstruir;
		ArrayList<String> nombresDeJugadores;
	
	public Controlador() {

		this.vista = new Vista();
		this.construccionAConstruir = null;
		this.nombresDeJugadores = new ArrayList<String>();
	}
	
	public void definirVista(Vista vista){
	
		this.vista = vista;
	}
	
	public void construirEnElMapa(Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException{
		
		if(this.construccionAConstruir != null)
			this.fachadaActual.jugadorConstruir(this.construccionAConstruir.generarNuevaInstancia(), coordenada);
		 	this.notificarUsuario("Construccion exitosa.");
	}
	
	public void definirPanelVista(JPanel panel){
		
		this.vista.definirPanelActual(panel);
	}
	
	public Fachada obtenerFachada(){
		
		return this.fachadaActual;
	}
	
	public void agregarJugador(String nombreJugador){
		
		this.nombresDeJugadores.add(nombreJugador);
	 	Jugador unJugador = new Jugador(nombreJugador);
	 	fachadaActual = new Fachada(unJugador);
		fachadas.add(fachadaActual);
	}
	
	public Vista obtenerVista(){
		
		return this.vista;
	}
	
	public void definirSiguienteConstruccionAConstruir(Construccion construccion){
		
		this.construccionAConstruir = construccion;
	}
	
	public ActionListener obtenerConstruirResidencialListener() {
		
		return new ConstruirResidencialListener(this);
	}
	
	public ActionListener obtenerConstruirComercialListener() {
		
		return new ConstruirComercialListener(this);
	}
	
	public ActionListener obtenerConstruirIndustrialListener() {
		
		return new ConstruirIndustrialListener(this);
	}
	
	public ActionListener obtenerConstruirRutaListener() {
		
		return new ConstruirRutaListener(this);
	}
	
	public ActionListener obtenerConstruirCentralMineralListener() {
		
		return new ConstruirCentralMineralListener(this);
	}
	
	public ActionListener obtenerConstruirLineaDeTensionListener() {
		
		return new ConstruirLineaDeTensionListener(this);
	}
	
	public ActionListener obtenerConstruirCentralEolicaListener() {
		
		return new ConstruirCentralEolicaListener(this);
	}
	
	public ActionListener obtenerConstruirCentralNuclearListener() {
		
		return new ConstruirCentralNuclearListener(this);
	}
	
	public ActionListener obtenerConstruirPozoDeAguaListener() {
		
		return new ConstruirPozoDeAguaListener(this);
	}
	
	public ActionListener obtenerConstruirTuberiaListener() {
		
		return new ConstruirTuberiaDeAguaListener(this);
	}
	
	public ActionListener obtenerConstruirBomberosListener() {
		
		return new ConstruirBomberosListener(this);
	}
	
	public ActionListener obtenerGuardarPartidaListener(Timer timer) {
		
		return new GuardarPartidaListener(this,timer);
	}
	
	public ActionListener obtenerVistaSuperficieListener(){
		
		return new VistaSuperficieListener(this);
	}
	
	public ActionListener obtenerVistaSubterraneaListener(){
		
		return new VistaSubterraneaListener(this);
	}
	
	public MouseListener obtenerClickSobreElMapaListener() {
		
		return new ClickSobreElMapaListener(this,this.fachadaActual.obtenerJugador().obtenerPartida().obtenerMapa());
	}
	
	public void notificarUsuario(String mensaje){
		
		vista.notificaUsuario(mensaje);
	}

	public ActionListener obtenerBotonComenzar() {

		return new ConstruirPanelMapaConBotonesListener(this);
	}

	public ActionListener obtenerBienvenidaIniciar() {
		
		return new ConstruirPanelNuevoJugadorExistenteListener(this);
	}

	public ActionListener obtenerConstruirElegirJugadorNuevoTecladoListener() {

		return new ConstruirPanelElegirJugadorNuevoListener(this);
	}

	public ActionListener obtenerConstruirJugadorexistenteTecladoListener() {
		 
		return new ConstruirPanelElegirJugadorExistenteTecladoListener(this);
	}

	public ActionListener obtenerVolverABienvenidaListener() {
		
		return new VolverABienvenida(this);
	}

	public ActionListener obtenerVolverListener() {
		
		return new VolverAJugadorNuevoOExistente(this);
	}
	
	public void refrescarVentana(){
		
		this.vista.obtenerVentana().validate();
		this.vista.obtenerVentana().repaint();
	}

	public ActionListener obtenerSalirListener() {
	
		return new Salir(this);
	}

	public void pagarAlJugador() {
		
		fachadaActual.obtenerJugador().cobrarPorCiudadano();
	}
	
	public void iniciar(){
		
		vista.setPanelBienvenida(this);
	}
	
	/*public boolean jugadorEstaEnLaLista(String nombreIngresado) {
		
		for (Fachada fachada: this.fachadas){
			if (nombreIngresado == fachada.obtenerJugador().obtenerNombre()) { return true; }
		}
		return false;
	}*/

	public void definirFachada(Fachada unaFachada) {
		
		this.fachadaActual = unaFachada;
	}

	public void setPanelVistaMapaConBotones() {
		
		this.obtenerVista().setPanelVistaMapaConBotones(this);
	}
	
	public ArrayList<String> obtenerNombres(){
		
		return this.nombresDeJugadores;
	}
	
	public boolean existeEsteJugador(String unNombre){
		
		for(String nombre : this.nombresDeJugadores){
		   if( nombre == unNombre ){ return true; }
		}
		return false;
	}

	public void llenarLabelConNombres(JLabel nombresJugadoresExistentesLabel) {
		
		String dato = null; 
		
		for(String nombre : this.nombresDeJugadores){
		   dato += "    Jugador:" + nombre + '\n';
		}
		
		nombresJugadoresExistentesLabel.setText(dato);
	}

	/*public void agregarJugadorNuevo(String nombreDelJugador) {
		
		this.nombresDeJugadores.add(nombreDelJugador);
	}*/

	public void definirListaDeJugadores(ArrayList<String> listaDeJugadoresDeserealizada) {
	
		this.nombresDeJugadores = listaDeJugadoresDeserealizada;
	}
	
}
