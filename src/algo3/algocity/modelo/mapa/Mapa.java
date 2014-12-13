package algo3.algocity.modelo.mapa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import algo3.algocity.modelo.catastrofes.*;
import algo3.algocity.modelo.conectores.Ruta;
import algo3.algocity.modelo.construibles.EstacionDeBomberos;
import algo3.algocity.modelo.construibles.PozoDeAgua;
import algo3.algocity.modelo.excepciones.*;
import algo3.algocity.modelo.interfaces.*;

public class Mapa {

	private transient int ancho;
	private transient int altura;
	private transient ArrayList<ArrayList<Hectarea>> mapa = new ArrayList<ArrayList<Hectarea>>();
	private transient ArrayList<MiniConstruccion> miniConstrucciones = new ArrayList<MiniConstruccion>(); 
	private transient ArrayList<MegaConstruccion> megaConstrucciones = new ArrayList<MegaConstruccion>(); 
	private transient ArrayList<EstacionDeBomberos> bomberos = new ArrayList<EstacionDeBomberos>();
	private transient ArrayList<PozoDeAgua> pozos = new ArrayList<PozoDeAgua>();
	private Coordenada coordenadaDeLaCatastrofe;
	private transient Catastrofe catastrofe;
	private boolean estadoMovimientoCatastrofe;
	
	public Mapa(){
		
		this.altura = 14;
		this.ancho = 23;
		this.crearMapa();
		this.agregarAguaAlMapa();
		this.catastrofe = null;
		this.estadoMovimientoCatastrofe = true;
	}
		
	public void definirCatastrofe(Catastrofe unaCatastrofe){
		
		this.catastrofe = unaCatastrofe;
	}
	
	public int obtenerAncho(){
		
		return this.ancho;
	}
	
	public int obtenerAltura(){
		
		return this.altura; 
	}
	
	public ArrayList<MegaConstruccion> obtenerMegaConstrucciones() {
		
		return this.megaConstrucciones;
	}
	
	public ArrayList<MiniConstruccion> obtenerMiniConstrucciones() {
		
		return this.miniConstrucciones;
	}
	
	private void crearMapa(){
		
		int fila = 0;
		int columna = 0;
		
		while( fila < this.altura ){
          			
			ArrayList<Hectarea> filasMapa = new ArrayList<Hectarea>(); 
	        while(columna < this.ancho){
	        	
	        	agregarHectarea(filasMapa,fila,columna);
	            columna++; 
	        }	
		   
	        this.mapa.add(filasMapa);
		    fila++;
		    columna = 0;	
		}
	} 
	
	private void agregarHectarea(ArrayList<Hectarea> sectorDelMapa,int fila,int columna){
		
		Tierra porcionDeTierra = new Tierra();
		Coordenada coordenada = new Coordenada(fila,columna);
		Hectarea unaHectarea = new Hectarea(porcionDeTierra,coordenada);
    	sectorDelMapa.add(unaHectarea);
	}

	 public Hectarea obtenerUnSectorDelMapa(Coordenada coordenada){
		 
		 int fila = coordenada.obtenerPosicionY();
		 int columna = coordenada.obtenerPosicionX();
		 
		 return this.mapa.get(fila).get(columna);
		 //return this.mapa.get(columna).get(fila);
	}
		
	public void construirMegaConstruccion(MegaConstruccion unaConstruccion,Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException {
		
		     if (unaConstruccion.obtenerTipoDeSuelo() == this.obtenerUnSectorDelMapa(coordenada).obtenerTipoDeSuelo()
		    	&&(this.obtenerUnSectorDelMapa(coordenada).obtenerMegaConstruccion()==null)){
		    	 	
		    	 	this.megaConstrucciones.add(unaConstruccion);	
		    	 	unaConstruccion.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
		    	 	this.obtenerUnSectorDelMapa(coordenada).construirMegaConstruccion(unaConstruccion);
		    	 	//si se construye una megaConstruccion pisa todas las miniConstrucciones pudieran haber(se vacia la lista).
		    	 	this.elimnarMiniConstrucciones(this.obtenerUnSectorDelMapa(coordenada).obtenerMiniConstruccion());
		    	 	this.obtenerUnSectorDelMapa(coordenada).anularMiniConstrucciones();
		    	 	this.buscadorDeConectoresCercanosAMegaConstrucciones(unaConstruccion, coordenada);
		    	 	
		     } else {
		    	 
		    	 NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
		    	 throw excepcion;
		     }
	}

	private void elimnarMiniConstrucciones(ArrayList<MiniConstruccion> miniConstrucciones) {
		
		if(!miniConstrucciones.isEmpty())
			for( MiniConstruccion construccionActual : miniConstrucciones){
				this.miniConstrucciones.remove(construccionActual);
			}
		miniConstrucciones.clear();
	}

	public void construirEstacionDeBomberos(EstacionDeBomberos bomberos,Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException {
	
		if (bomberos.obtenerTipoDeSuelo() == this.obtenerUnSectorDelMapa(coordenada).obtenerTipoDeSuelo()
			&&(this.obtenerUnSectorDelMapa(coordenada).obtenerMegaConstruccion()==null)) {
				
				this.bomberos.add(bomberos);	
				bomberos.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
				this.obtenerUnSectorDelMapa(coordenada).construirMegaConstruccion(bomberos);
				this.buscadorDeConectoresCercanosAMegaConstrucciones(bomberos, coordenada);
	     } else {
	    	 NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
	    	 throw excepcion;
	     }
	}
	
	public void construirPozoDeAgua(PozoDeAgua pozo,Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException {
		
		if (pozo.obtenerTipoDeSuelo() == this.obtenerUnSectorDelMapa(coordenada).obtenerTipoDeSuelo()
			&&(this.obtenerUnSectorDelMapa(coordenada).obtenerMegaConstruccion()==null)){
			
				this.buscadorDeEntubablesContiguos(pozo,coordenada);
				this.pozos.add(pozo);	
				pozo.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
				this.obtenerUnSectorDelMapa(coordenada).construirMegaConstruccion(pozo);
	     } else {
	    	 NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
	    	 throw excepcion;
	     }
	}
	
	public ArrayList<EstacionDeBomberos> obtenerEstacionDeBomberos() {
		
		return this.bomberos;
	}
	
	public boolean verificarSiMegaConstruccionSeEncuentraEnElMapa(Construccion unaConstruccion) {
		
		 Iterator<MegaConstruccion> iterador = this.megaConstrucciones.iterator(); 
		  
	     while(iterador.hasNext()) {
		 
		    Construccion construccionActual = iterador.next();
	     
		    if (construccionActual == unaConstruccion) return true;
		 
	     }
	     return false;
	}
	
	public void construirLineaDeTension(MiniConstruccion unaLineaDeTension, Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException{
		
		boolean hayUnaMiniConstruccion = false;
	    Hectarea hectarea = this.obtenerUnSectorDelMapa(coordenada);
	    
	    for(MiniConstruccion construccion : hectarea.obtenerMiniConstruccion()){
			if(construccion.conectarseA(unaLineaDeTension)){ hayUnaMiniConstruccion=true; }
		}
	    
		if((hectarea.obtenerTipoDeSuelo() == unaLineaDeTension.obtenerTipoDeSuelo())&& hectarea.obtenerMegaConstruccion()== null
			&&(!hayUnaMiniConstruccion)&&(buscadorDeElectrificablesContiguos(unaLineaDeTension,coordenada))){
			
				unaLineaDeTension.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
				this.miniConstrucciones.add(unaLineaDeTension);
				this.obtenerUnSectorDelMapa(coordenada).construirMiniConstruccion(unaLineaDeTension);
		    }
		
		else{ NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
		 	  throw excepcion;
			}
	}
	
	public void construirRuta(MiniConstruccion unaRuta,Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException{
		
		boolean hayUnaMiniConstruccion = false;
	    Hectarea hectarea = this.obtenerUnSectorDelMapa(coordenada);
	    
	    for(MiniConstruccion construccion : hectarea.obtenerMiniConstruccion()){
			if(construccion.conectarseA(unaRuta)) { hayUnaMiniConstruccion = true; }
		}
	    
		if((hectarea.obtenerTipoDeSuelo() == unaRuta.obtenerTipoDeSuelo())&& hectarea.obtenerMegaConstruccion()== null
			&&(!hayUnaMiniConstruccion)&& (buscadorDeEnrutablesContiguos(unaRuta,coordenada))){
			
			unaRuta.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
			this.miniConstrucciones.add(unaRuta);
			this.obtenerUnSectorDelMapa(coordenada).construirMiniConstruccion(unaRuta);
		} else { NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
		 	  throw excepcion;
				}
	}
	
	public void construirTuberiaDeAgua(MiniConstruccion unaTuberia,Coordenada coordenada) throws NoSePuedeEdificarEnEsaZonaException{
		
	    boolean hayUnaMiniConstruccion = false;
	    Hectarea hectarea = this.obtenerUnSectorDelMapa(coordenada);
	    
	    for(MiniConstruccion construccion : hectarea.obtenerMiniConstruccion()){
			if(construccion.conectarseA(unaTuberia)){ hayUnaMiniConstruccion = true; }
		}
	    
	    if((hectarea.obtenerMegaConstruccion()==null )&&!hayUnaMiniConstruccion
				&&(buscadorDeEntubablesContiguos(unaTuberia,coordenada))){
	    	
			unaTuberia.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
			this.miniConstrucciones.add(unaTuberia);
			this.obtenerUnSectorDelMapa(coordenada).construirMiniConstruccion(unaTuberia);
		}
	
		else{  NoSePuedeEdificarEnEsaZonaException excepcion = new NoSePuedeEdificarEnEsaZonaException();
	 	  	   throw excepcion;
			}
	}
	
	public void buscadorDeConectoresCercanosAMegaConstrucciones(Construccion construccion, Coordenada coordenada){
		
		ArrayList<Construccion> conectoresEncontrados; 
		  conectoresEncontrados = buscarConstrucciones(coordenada);
		  if(!conectoresEncontrados.isEmpty()){
			  
			  Enrutable unEnrutable = null;
			  Entubable unEntubable = null;
			  Electrificable unElectrificable = null;
			  
			  for(Construccion conectorActual: conectoresEncontrados){
				  
				  if(conectorActual.conectarseA(unElectrificable)){
					  if(conectorActual.tieneElectricidad())
						  ((Electrificable) construccion).conectarASiguienteElectrificable((Electrificable) conectorActual);
					  else
						  ((Electrificable) conectorActual).conectarASiguienteElectrificable((Electrificable) construccion);
				  }
				  
				   	
				  if(conectorActual.conectarseA(unEntubable))
						if(conectorActual.tieneAgua())
							((Entubable) construccion).conectarAEntubable((Entubable) conectorActual);
						else
							  ((Entubable) conectorActual).conectarAEntubable((Entubable) construccion);
					
				  if(conectorActual.conectarseA(unEnrutable))
						if(((Enrutable) conectorActual).estaConectadoAUnEnrutable())
							((Enrutable) construccion).conectarAEnrutable((Enrutable) conectorActual);
				   }
			  }
	 }
							
	private void encontrarConstrucciones(Hectarea hectarea,ArrayList<Construccion> construccionesEncontradas) {
		
		if(hectarea.obtenerMegaConstruccion()!=null)
				construccionesEncontradas.add(hectarea.obtenerMegaConstruccion());

		else{
			if(!hectarea.obtenerMiniConstruccion().isEmpty())
				for(MiniConstruccion construccionActual : hectarea.obtenerMiniConstruccion())
					construccionesEncontradas.add(construccionActual);
				
			}
	}
	
	public ArrayList<Construccion> buscarConstrucciones(Coordenada coordenada){
		
		int y = coordenada.obtenerPosicionY();
		int x = coordenada.obtenerPosicionX();
		ArrayList<Construccion> construccionesEncontradas =  new ArrayList<Construccion>();
		
		if(y < this.altura-1)
			this.encontrarConstrucciones(this.mapa.get(y+1).get(x),construccionesEncontradas);
		if(y > 0)
			this.encontrarConstrucciones(this.mapa.get(y-1).get(x),construccionesEncontradas);
		if(x < this.ancho-1)	
			this.encontrarConstrucciones(this.mapa.get(y).get(x+1),construccionesEncontradas);
		if(x > 0)	
		this.encontrarConstrucciones(this.mapa.get(y).get(x-1),construccionesEncontradas);
			
	    return construccionesEncontradas;
	}
	
	
	public boolean buscadorDeElectrificablesContiguos(Construccion unElectrificable, Coordenada coordenada){
		  
		  ArrayList<Construccion> conectoresEncontrados; 
		  conectoresEncontrados = buscar(unElectrificable,coordenada);
		  int numeroDeConectoresConSuministro = 0;
		  Construccion conectorConSuministro = null;
		  Construccion conectorSinSuministro = null;
		  
		  if(!conectoresEncontrados.isEmpty()){
			  
			  for(Construccion conectorActual: conectoresEncontrados){
				  if (((Electrificable) conectorActual).estaConectadoAUnSuministroElectrico()){
					
					  numeroDeConectoresConSuministro ++; 
					  conectorConSuministro= conectorActual;
				  }else{ conectorSinSuministro = conectorActual;}
			  }		  
					  
			  if(numeroDeConectoresConSuministro == 1){
				  
				  ((Electrificable) unElectrificable).conectarASiguienteElectrificable((Electrificable) conectorConSuministro);
			  }
			  if(conectorSinSuministro != null){
				  ((Electrificable) conectorSinSuministro).conectarASiguienteElectrificable((Electrificable) unElectrificable);
			  }
			  
			  return true;  
		}
		  return false;
	}
	
	public boolean buscadorDeEnrutablesContiguos(Construccion unEnrutable, Coordenada coordenada){
		  
		  ArrayList<Construccion> conectoresEncontrados; 
		  conectoresEncontrados = buscar(unEnrutable,coordenada);
		  int numeroDeConectoresConectadosAOtroEnrutable = 0;
		  Construccion conectorConectado = null;
		  Construccion conectorDesconectado = null;
		  
		  if (!conectoresEncontrados.isEmpty()){
			  
			  for (Construccion conectorActual: conectoresEncontrados){
				  if (((Enrutable) conectorActual).estaConectadoAUnEnrutable()){
					
					  numeroDeConectoresConectadosAOtroEnrutable++; 
					  conectorConectado= conectorActual;
				  } else { conectorDesconectado = conectorActual;}
			  }		  
					  
			  if (numeroDeConectoresConectadosAOtroEnrutable == 1){
				  
				  ((Enrutable) unEnrutable).conectarAEnrutable((Enrutable) conectorConectado);
			  }
			  if(conectorDesconectado != null){
				  ((Enrutable) conectorDesconectado).conectarAEnrutable((Enrutable) unEnrutable);
			  }
			  
			  return true;  
		}
		  return false;
	}
		
	public boolean buscadorDeEntubablesContiguos(Construccion unEntubable,Coordenada coordenada){
		  
		  ArrayList<Construccion> conectoresEncontrados; 
		  conectoresEncontrados = buscar(unEntubable,coordenada);
		  int numeroDeConectoresConSuministro = 0;
		  Construccion conectorConSuministro = null;
		  Construccion conectorSinSuministro = null;
		  
		  if(!conectoresEncontrados.isEmpty()){
			  
			  for(Construccion conectorActual: conectoresEncontrados){
				  if (((Entubable) conectorActual).estaConectadoAUnSuministroDeAgua()){
					
					  numeroDeConectoresConSuministro ++; 
					  conectorConSuministro= conectorActual;
				  }else{ conectorSinSuministro = conectorActual;}
			  }		  
					  
			  if(numeroDeConectoresConSuministro == 1){
				  
				  ((Entubable) unEntubable).conectarAEntubable((Entubable) conectorConSuministro);
			  }
			  
			  if(conectorSinSuministro != null){
					 
				  ((Entubable) conectorSinSuministro).conectarAEntubable((Entubable) unEntubable);
			  }
			  
			  return true;
		}
		  return false;
	}
			  
	public ArrayList<Construccion> buscar(Construccion unaConstruccion,Coordenada coordenada){
		
		int y = coordenada.obtenerPosicionY();
		int x = coordenada.obtenerPosicionX();
		ArrayList<Construccion> construccionesEncontradas =  new ArrayList<Construccion>();
		
		if(y < this.altura-1)
			this.encontrarConstruccionesConectables(this.mapa.get(y+1).get(x),unaConstruccion,construccionesEncontradas);
		if(y > 0)
			this.encontrarConstruccionesConectables(this.mapa.get(y-1).get(x), unaConstruccion, construccionesEncontradas);
		if(x < this.ancho-1)
			this.encontrarConstruccionesConectables(this.mapa.get(y).get(x+1), unaConstruccion, construccionesEncontradas);
		if(x > 0)	
			this.encontrarConstruccionesConectables(this.mapa.get(y).get(x-1), unaConstruccion, construccionesEncontradas);
			
	    return construccionesEncontradas;
	}
	
	private void encontrarConstruccionesConectables(Hectarea hectarea,Construccion unaConstruccion,
			ArrayList<Construccion> construccionesEncontradas) {
		
		Construccion unaMiniConstruccion = null;
		
		if(hectarea.obtenerMegaConstruccion()!=null){
			if(hectarea.obtenerMegaConstruccion().conectarseA(unaConstruccion)){
				construccionesEncontradas.add(hectarea.obtenerMegaConstruccion());
			}
		} 
		else{
			if(!hectarea.obtenerMiniConstruccion().isEmpty())
				for(MiniConstruccion construccionActual : hectarea.obtenerMiniConstruccion()){
					if(construccionActual.conectarseA(unaConstruccion)){ unaMiniConstruccion = construccionActual; }
				}
			}
		if(unaMiniConstruccion != null){construccionesEncontradas.add(unaMiniConstruccion);}
	}

	private void agregarAguaAlMapa(){
		
		for(int columna = 2;columna < 5; columna++ ){
		
			for(int fila = 2; fila < 5 ; fila++){
				Agua porcionDeAgua = new Agua();
				Coordenada coordenada = new Coordenada(fila,columna);
				Hectarea unaParcela = new Hectarea(porcionDeAgua,coordenada);
				this.mapa.get(fila).set(columna,unaParcela);
			}
		}
		
		for(int columna = 8;columna < 10; columna++ ){
			
			for(int fila = 8; fila < 10 ; fila++){
				Agua porcionDeAgua = new Agua();
				Coordenada coordenada = new Coordenada(fila,columna);
				Hectarea unaParcela = new Hectarea(porcionDeAgua,coordenada);
				this.mapa.get(fila).set(columna,unaParcela);
			}
		}
		
	}

	public void agregarRuta(Ruta unaRuta,Coordenada coordenada) {
		
		unaRuta.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(coordenada));
		
		this.miniConstrucciones.add(unaRuta);
	    
		this.obtenerUnSectorDelMapa(coordenada).construirMiniConstruccion(unaRuta);
	}
	
	public void crearCatastrofe(Godzilla godzilla) {
		
		//sólo para los tests
		
	}
	
	public void crearCatastrofe(Terremoto terremoto,Coordenada coordenada) {
		
		terremoto.establecerHectareaALaQuePertenece(obtenerUnSectorDelMapa(coordenada));
	}
	
	public void eliminarTodasLasMiniConstruccionesQueTenganVidaCero(){
		
		ArrayList<Construccion> construccionesMuertas = new ArrayList<Construccion>();
		
		if(!this.miniConstrucciones.isEmpty())
			for (MiniConstruccion construccionActual: this.miniConstrucciones){
			
				if(construccionActual.porcentajeDeVida() <= 0){
					construccionActual.obtenerHectareaALaQuePertenece().anularMiniConstrucciones();
					construccionesMuertas.add(construccionActual);
				}
			}
		this.eliminarMiniConstrucciones(construccionesMuertas);
	}
	
	public void eliminarTodasLasMegaConstruccionesQueTenganVidaCero(){
		
		ArrayList<Construccion> construccionesMuertas = new ArrayList<Construccion>();
		
		if(!this.megaConstrucciones.isEmpty())
			for (MegaConstruccion construccionActual: this.megaConstrucciones){
			
				if(construccionActual.porcentajeDeVida() <= 0){ 
					construccionActual.obtenerHectareaALaQuePertenece().anularMegaConstruccion();
					construccionesMuertas.add(construccionActual);
				}
			}
		this.eliminarMegaConstrucciones(construccionesMuertas);
	}
	
	private void eliminarMiniConstrucciones(ArrayList<Construccion> construccionesMuertas) {
		
		if(!construccionesMuertas.isEmpty())
			for (Construccion construccionActual: construccionesMuertas)
				this.miniConstrucciones.remove(construccionActual);
		
	}

	private void eliminarMegaConstrucciones(ArrayList<Construccion> construccionesMuertas) {
		
		if(!construccionesMuertas.isEmpty())
			for (Construccion construccionActual: construccionesMuertas)
				this.megaConstrucciones.remove(construccionActual);
		
	}

	public Coordenada obtenerUnBorde() {
		
		Random generadorRandom = new Random();
		int numeroAleatorio = generadorRandom.nextInt(1);
		
		int coordenadaAleatoriaX;
		int coordenadaAleatoriaY;
		
		if (numeroAleatorio == 0) {
			coordenadaAleatoriaX = generadorRandom.nextInt(this.ancho - 1);
			int random = generadorRandom.nextInt(2);
			if (random == 0) { coordenadaAleatoriaY = 0; } else { coordenadaAleatoriaY = this.altura - 1; }
		} else {
			coordenadaAleatoriaY = generadorRandom.nextInt(this.altura - 1);
			int random = generadorRandom.nextInt(2);
			if (random == 0) { coordenadaAleatoriaX = 0; } else { coordenadaAleatoriaX = this.ancho - 1; }
		}
		System.out.println(coordenadaAleatoriaY+" "+coordenadaAleatoriaX);
		Coordenada coordenada = new Coordenada(coordenadaAleatoriaY,coordenadaAleatoriaX);
		
		return coordenada;
	}

	public void ubicarCatastrofeEn(Godzilla godzilla,Coordenada unaCoordenada) {
		
		this.coordenadaDeLaCatastrofe = unaCoordenada;
		this.obtenerUnSectorDelMapa(unaCoordenada);
		this.catastrofe = godzilla;
	}
	
    public void ubicarCatastrofeEn(Terremoto terremoto,Coordenada unaCoordenada) {
		
		this.coordenadaDeLaCatastrofe = unaCoordenada;
		terremoto.establecerHectareaALaQuePertenece(this.obtenerUnSectorDelMapa(unaCoordenada));
		this.catastrofe = terremoto;
	}

	public Coordenada obtenerPosicionDeLaCatastrofe(){
		
		 return this.coordenadaDeLaCatastrofe;
	}
	
	public Coordenada obtenerCoordenadaAleatoria() {
		
		Random generadorRandom = new Random();
		
		int coordenadaAleatoriaX = generadorRandom.nextInt(this.altura - 1);
		int coordenadaAleatoriaY = generadorRandom.nextInt(this.ancho - 1);
		Coordenada coordenada = new Coordenada(coordenadaAleatoriaX,coordenadaAleatoriaY);
		
		return coordenada;
	}
	
	public void modificarPosicionDeLaCatastrofe(Coordenada coordenada) {
		
		this.coordenadaDeLaCatastrofe = coordenada;
    }
	
	public boolean hayUnaCatastrofeEnElMapa(){
		
		return (this.catastrofe != null); 
	}

	public void decirleALaCatastrofeQueDestruyaPorTurno() {
		
		this.catastrofe.destruirPorTurno(this);
	}

	public Catastrofe obtenerCatastrofeActual() {
		
		return this.catastrofe;
	}

	public boolean estadoDeMovimientoDelPasoDeCatastrofeEs() {
		
		return this.estadoMovimientoCatastrofe;
	}

	public void modificarEstadoDeMovimientoDelPasoDeCatastrofe(boolean estado) {
		
		this.estadoMovimientoCatastrofe = estado;
	}

	public ArrayList<PozoDeAgua> obtenerPozos() {
		
		return this.pozos;
	}
	
}