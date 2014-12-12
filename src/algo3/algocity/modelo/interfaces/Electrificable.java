package algo3.algocity.modelo.interfaces;

public interface Electrificable{

	public boolean estaConectadoAUnSuministroElectrico();
	public void conectarASiguienteElectrificable(Electrificable unElectrificable);
	public Electrificable obtenerSiguienteElectrificable();
}
