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
	    File f = new File(nombreCarpeta+"/" + nombreArchivo+".txt");
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
		        //} finally {
		            //entrada.close();
		        }  
		    //entrada.close();
		     return retorno;
	    } else return null;
	}
	
	public void guardar(ArrayList<String> lista,String nombreCarpeta,String nombreArchivo) throws FileNotFoundException{
		
		Scanner sca = new Scanner(System.in);
		PrintWriter entrada = null;
		
		entrada = new PrintWriter(nombreCarpeta + "/" + nombreArchivo+".txt");
		for(String texto : lista){
			
			entrada.println(texto);
		}
		entrada.flush();
		entrada.close();
	}
	
	public void guardarString(String nuevoNombre,String nombreCarpeta,String nombreArchivo) throws IOException{
		  
		 FileWriter fout = new FileWriter(nombreCarpeta + "/" + nombreArchivo+".txt",true);
		 PrintWriter fileout = new PrintWriter(fout,true);
		 fileout.println(nuevoNombre + "\n");
		 fileout.close();
		 }
	
	public void guardarListaString(ArrayList<String> nuevoNombre,String nombreCarpeta,String nombreArchivo) throws IOException{
		  
		 FileWriter fout = new FileWriter(nombreCarpeta + "/" + nombreArchivo+".txt",true);
		 PrintWriter fileout = new PrintWriter(fout,true);
		 for(String texto : nuevoNombre){
			fileout.println(nuevoNombre +"\n");
		 }
		 fileout.close();
		 }
	
	public static void main(String[] args) throws IOException {
		
		GestorArchivo arch = new GestorArchivo();

		ArrayList<String> textos = new ArrayList<String>();
		textos.add("uno");
		textos.add("dos");
		textos.add("tres");
		
		String nombreCarpeta= "mouse";
		String nombreTxt = "cris";
		
		arch.crearCarpeta(nombreCarpeta);
		
		arch.guardar(textos,nombreCarpeta,nombreTxt);
		
		//System.out.println(arch.convertir(nombreCarpeta,nombreTxt));
	}
}