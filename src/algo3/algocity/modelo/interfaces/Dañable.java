package algo3.algocity.modelo.interfaces;

import algo3.algocity.modelo.catastrofes.Godzilla;
import algo3.algocity.modelo.catastrofes.Terremoto;

public interface Daņable {

	public void aplicarDaņoRecibido(Godzilla godzilla);
	public void aplicarDaņoRecibido(Terremoto terremoto);
}
