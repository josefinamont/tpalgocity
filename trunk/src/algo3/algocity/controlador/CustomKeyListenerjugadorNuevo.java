package algo3.algocity.controlador;

import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;

import algo3.algocity.modelo.Fachada;
import algo3.algocity.modelo.menu.Jugador;

public class CustomKeyListenerjugadorNuevo implements KeyListener {

	JLabel statusLabel;
	TextField textField;
	Controlador controlador;
		
 public CustomKeyListenerjugadorNuevo(JLabel lab,TextField textField,Controlador controlador){
	this.statusLabel = lab;
	this.textField = textField;
	this.controlador = controlador;
 }
		
 public void keyTyped(KeyEvent e) {           

 }

		     
 public void keyPressed(KeyEvent e) {
     
	 if(e.getKeyCode() == KeyEvent.VK_ENTER){
     
		  if(textField.getText().trim().length() != 0){

        	  controlador.agregarJugadorAFachadas(textField.getText());
        	  controlador.obtenerVista().setPanelVistaMapaConBotones(controlador);
	      }
	      else{
		            	
              statusLabel.setText("Ingrese un nombre");
          }
     }
 }

 public void keyReleased(KeyEvent e) {            

 }    
} 
		

