package algo3.algocity.gestorDeArchivo;

import java.io.File;
import java.io.FileNotFoundException;
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
	        String cadena;
	        Scanner entrada = null;
	        try {
	            entrada = new Scanner(f);
	            while (entrada.hasNext()) {
	                
	            	cadena = entrada.nextLine();
	                retorno.add(cadena);
	            }
	        } catch (FileNotFoundException e) {
	            System.out.println(e.getMessage());
	        } finally{
	            entrada.close();
	        }  
	     return retorno;
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
