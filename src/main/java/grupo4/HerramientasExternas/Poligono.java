package grupo4.HerramientasExternas;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Convert;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
@Entity
public class Poligono extends Polygon{
	@OneToMany
	@Convert(converter=Punto.class)
	private List<Point> puntos;
	public Poligono() {
		super();
		puntos = new ArrayList<>();
	}

	public Poligono(List<Point> points) {
		super(points);
		points.stream().forEach(p->puntos.add(p));
	}
	public boolean add(Point point){
		super.add(point);
		return puntos.add(point);
	}

}