package algo3.algocity.gestorDeArchivo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

	public class GestorArchivo {
		
		ArrayList<String> retorno;
		
	public GestorArchivo() {
			retorno = new ArrayList<String>();
	}
	
	public void crearCarpeta(String nombreCarpeta){
		
		File directorio = new File(nombreCarpeta);
		directorio.mkdir();
	}
	
	public ArrayList<String> levantar(String nombreCarpeta,String nombreArchivo){
		
		ArrayList<String> retorno = new ArrayList<String>();
	    File f = new File(nombreCarpeta+"/" + nombreArchivo+".json");
	    if (f.exists()) {
		    String cadena;
		    Scanner entrada = null;
		    
		    try {
		    	entrada = new Scanner(f);
		        while (entrada.hasNext()) {
		        	cadena = entrada.nextLine();
		            retorno.add(cadena);
		        }
		        entrada.close();
		     } catch (FileNotFoundException e) {
		            System.out.println(e.getMessage());
		        }  
		     return retorno;
	    } else return null;
	}
	
	public void guardar(ArrayList<String> lista,String nombreCarpeta,String nombreArchivo) throws FileNotFoundException{
		
		PrintWriter entrada = null;
		
		entrada = new PrintWriter(nombreCarpeta + "/" + nombreArchivo+".json");
		for(String texto : lista){
			
			entrada.println(texto);
		}
		entrada.flush();
		entrada.close();
	}
	
	public void guardarString(String nuevoNombre,String nombreCarpeta,String nombreArchivo) throws IOException{
		  
		 FileWriter fout = new FileWriter(nombreCarpeta + "/" + nombreArchivo+".json",true);
		 PrintWriter fileout = new PrintWriter(fout,true);
		 fileout.write(nuevoNombre + "\n");
		 //fileout.write(nuevoNombre);
		 fileout.close();
		 }
	
	public void guardarListaString(ArrayList<String> nuevosNombres,String nombreCarpeta,String nombreArchivo) throws IOException{
		  
		 FileWriter fout = new FileWriter(nombreCarpeta + "/" + nombreArchivo+".json",true);
		 PrintWriter fileout = new PrintWriter(fout,true);
		 for(String texto : nuevosNombres){
			fileout.write(texto +"\n");
		 }
		 fileout.close();
	}
	
	public void guardarrString(String nuevoNombre) throws IOException{
		
		File f = new File("lista de jugadores"+"/" + "jugadores"+".json");
		//File TextFile = new File("c:/mostro/EstadoVentanilla.txt"); 
		//FileWriter TextOut = new FileWriter(TextFile, true);
		//TextOut.write("Prueba de Grabación de Archivo_4\r\n");
		//TextOut.close();
		if (f.exists()) {
			FileWriter text = new FileWriter(f,true);
			//PrintWriter fileout = new PrintWriter(f,true);
			text.write(nuevoNombre + "\n");
			text.close();
		}
	}
	
}
