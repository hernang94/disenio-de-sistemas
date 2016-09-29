package grupo4.HerramientasExternas;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

@Embeddable
public class Poligono{
	
	@ElementCollection
	private List<Punto> puntosPoligono;

	@SuppressWarnings("unused")
	private Poligono(){}
	
	public Poligono(List<Punto> puntosPoligono) {
		super();
		this.puntosPoligono = puntosPoligono;
	}

	public List<Punto> getPuntosPoligono() {
		return puntosPoligono;
	}

	public boolean isInsideOld(Punto unaCoordenada) {
		Polygon polygonAsociado = new Polygon(this.getListaDePuntosAsPoint());
		return polygonAsociado.isInsideOld(unaCoordenada.puntoAPoint());
	}	
	
	public boolean isInside(Punto unaCoordenada){
		Polygon polygonAsociado = new Polygon(this.getListaDePuntosAsPoint());
		return polygonAsociado.isInside(unaCoordenada.puntoAPoint());
	}
	
	private List<Point> getListaDePuntosAsPoint() {
		return puntosPoligono.stream().map(unPunto->unPunto.puntoAPoint()).collect(Collectors.toList());
	}

	public List<String> aString(){
		return puntosPoligono.stream().map(unPunto->unPunto.toString()).collect(Collectors.toList()); 
	}
}
