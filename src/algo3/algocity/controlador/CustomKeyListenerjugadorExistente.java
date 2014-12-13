package algo3.algocity.controlador;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import algo3.algocity.modelo.excepciones.NoSePuedeEdificarEnEsaZonaException;
import algo3.algocity.persistencia.Deserializador;

 public class CustomKeyListenerjugadorExistente implements KeyListener {

		JLabel statusLabel;
		TextField textField;
		Controlador controlador;

 public CustomKeyListenerjugadorExistente(JLabel lab,TextField textField,Controlador controlador){
			this.statusLabel = lab;
			this.textField = textField;
			this.controlador = controlador;
 }

 public void keyTyped(KeyEvent e) {           
      
 }

     
 public void keyPressed(KeyEvent e) {
     
	 if(e.getKeyCode() == KeyEvent.VK_ENTER){
            
		 if ( (textField.getText().trim().length() != 0) && (controlador.existeEsteJugador(textField.getText())) ){
			 
			String nombreDelJugador = textField.getText();
			Deserializador deserializador = new Deserializador(nombreDelJugador);
			try {
				controlador.definirFachada(deserializador.deserializarTodo());
			} catch (NoSePuedeEdificarEnEsaZonaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
       	  	 //controlador.agregarJugadorAFachadas(textField.getText());
			controlador.setPanelVistaMapaConBotones();
       	  	 //controlador.jugadorEstaEnLaLista(textField.getText());
	      }
	      else{
		         	
             statusLabel.setText("Ingrese un nombre válido");
         }
        }
 }

 public void keyReleased(KeyEvent e) {            
      
  }    
} 


	

