package algo3.algocity.modelo.catastrofes;

import java.awt.Graphics;

import algo3.algocity.modelo.interfaces.Dañable;
import algo3.algocity.modelo.mapa.Coordenada;
//import algo3.algocity.modelo.mapa.Hectarea;
import algo3.algocity.modelo.mapa.Mapa;

public interface Catastrofe {

	public void destruirEstructura(Dañable unDañable);
	public void provocarDañosEn(Mapa mapa);
	public void destruirPorTurno(Mapa mapa);
	public void dibujarCatastrofe(Graphics g,Coordenada coordDimensionada);
}
