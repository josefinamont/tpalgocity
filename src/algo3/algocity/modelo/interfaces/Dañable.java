package algo3.algocity.modelo.interfaces;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.catastrofes.Terremoto;

public interface Dañable {

	public void aplicarDañoRecibido(Godzilla godzilla);
	public void aplicarDañoRecibido(Terremoto terremoto);
}
