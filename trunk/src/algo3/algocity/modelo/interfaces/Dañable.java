package algo3.algocity.modelo.interfaces;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.catastrofes.Terremoto;

public interface Da�able {

	public void aplicarDa�oRecibido(Godzilla godzilla);
	public void aplicarDa�oRecibido(Terremoto terremoto);
}
