package algo3.algocity.vista;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import algo3.algocity.modelo.edificios.Residencial;
import algo3.algocity.modelo.interfaces.MegaConstruccion;

	@SuppressWarnings("serial")
	public class VistaInformacionConstruccion extends JFrame {

		public VistaInformacionConstruccion(MegaConstruccion construccion){
			JTextPane texto = new JTextPane();
			texto.setText(obtenerTextoDeInformacion(construccion));
			texto.setEditable(false);
			this.add(texto);
			this.pack();
			this.obtenerTextoDeInformacion(construccion);
			this.setVisible(true);
		}
		
		private String obtenerTextoDeInformacion(MegaConstruccion construccion) {
			
			String texto = "";
			if(construccion.tienePoblacion()){
				
				texto += "Habitantes: " +  (((Residencial) construccion).obtenerPoblacion()) + "\n";
			}
			
			texto += "Vida: " + construccion.porcentajeDeVida() + "\n";
	
			return texto;
		}
		
}
