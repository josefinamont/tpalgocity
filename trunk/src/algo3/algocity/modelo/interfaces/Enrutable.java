package algo3.algocity.modelo.interfaces;

public interface Enrutable {
	
	public boolean puedeIrA(Enrutable unEnrutable);
	public void conectarAEnrutable(Enrutable unEnrutable);
	public Enrutable obtenerSiguienteEnrutable();
	public boolean estaConectadoAUnEnrutable();
}
