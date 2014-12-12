package algo3.algocity.modelo.interfaces;

public interface Entubable {
  
	public boolean estaConectadoAUnSuministroDeAgua();
	public void conectarAEntubable(Entubable unEntubable);
	public Entubable obtenerSiguienteEntubable();
}
