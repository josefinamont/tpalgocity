package algo3.algocity.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.modelo.mapa.Coordenada;
import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.mapa.Mapa;
import algo3.algocity.vista.EstadoVistaMapa;
import algo3.algocity.vista.VistaInformacionConstruccion;

public class ClickSobreElMapaListener implements MouseListener {

	private Controlador controlador;
	private Mapa mapa;
	
	public ClickSobreElMapaListener(Controlador controlador,Mapa mapa){
		
		this.controlador = controlador;
		this.mapa = mapa;
	}
	
	@SuppressWarnings("static-access")
	public void mouseClicked(MouseEvent args) {
		
		
		Coordenada posicionDeClick = obtenerCoordenadaDondeSeClickeo(args);
		
		if(args.getButton() == MouseEvent.BUTTON3){
			
		 	Hectarea hectarea = mapa.obtenerUnSectorDelMapa(posicionDeClick);
			
			if(hectarea.obtenerMegaConstruccion() != null){
				
				VistaInformacionConstruccion vista = new VistaInformacionConstruccion(hectarea.obtenerMegaConstruccion());
			}
			
		}
		
		
		if(args.getButton() == MouseEvent.BUTTON1){

		try {
				controlador.construirEnElMapa(posicionDeClick);
				controlador.obtenerVista().recibirNotificacion(controlador);
				} catch (NoSePuedeEdificarEnEsaZonaException e) {
					 controlador.notificarUsuario("No se puede edificar en esa zona.");
				}
	}
}
	private void notificarUsuario(String mensaje) {
		controlador.notificarUsuario(mensaje);
	}

	public void mousePressed(MouseEvent e) {
	
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	private Coordenada obtenerCoordenadaDondeSeClickeo(MouseEvent event) {
		return new Coordenada(event.getY() / EstadoVistaMapa.ANCHO_DE_COLUMNA,
				event.getX() / EstadoVistaMapa.ANCHO_DE_FILA);
	}
}
