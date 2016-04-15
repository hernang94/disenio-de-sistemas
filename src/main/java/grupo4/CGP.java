package grupo4;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends Poi{
	Polygon comuna= new Polygon();
	public boolean estaCerca(Point unaCoordenada){
		return comuna.isInside(unaCoordenada);
	}
}
