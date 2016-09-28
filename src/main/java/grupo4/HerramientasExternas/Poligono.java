package grupo4.HerramientasExternas;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import org.uqbar.geodds.Polygon;

@Embeddable
public class Poligono extends Polygon{
	
	@ElementCollection
	private List<Punto> puntosPoligono;
	
	public Poligono() {
		super();
	}

	public Poligono(List<Punto> puntosPoligono) {
		super();
		this.puntosPoligono = puntosPoligono;
	}

	public void setPuntosPoligono(List<Punto> puntosPoligono) {
		this.puntosPoligono = puntosPoligono;
		this.actualizarListaPoint();
	}
	
	private void actualizarListaPoint() {
		this.getPuntosPoligono().stream().forEach(unPunto->surface.add(unPunto.puntoAPoint(unPunto)));
	}

	public List<Punto> getPuntosPoligono() {
		return puntosPoligono;
	}

	public boolean isInsideOld(Punto unaCoordenada) {
		return super.isInsideOld(unaCoordenada.puntoAPoint(unaCoordenada));
	}	
	
	public List<String> aString(){
		return surface.stream().map(unPunto->unPunto.toString()).collect(Collectors.toList()); 
	}
}
