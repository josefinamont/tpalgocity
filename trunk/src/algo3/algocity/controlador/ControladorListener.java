package algo3.algocity.controlador;

import java.awt.event.ActionListener;

public abstract class ControladorListener implements ActionListener{

	protected Controlador controlador;
	
	public ControladorListener(Controlador controlador){
		
		this.controlador = controlador;
	}
}
