package algo3.algocity.controlador;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

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
            
		 if(textField.getText() == null){

           	controlador.obtenerVista().setPanelVistaMapaConBotones(controlador);
           	controlador.agregarJugadorAFachadas(textField.getText());
          }
          else{
            	
           	statusLabel.setText("Entered text: "+ textField.getText());
           }
        }
 }

 public void keyReleased(KeyEvent e) {            
      
  }    
} 


	
