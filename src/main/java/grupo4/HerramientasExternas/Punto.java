package grupo4.HerramientasExternas;


import javax.persistence.Embeddable;

import org.uqbar.geodds.Point;

@Embeddable
public class Punto{
	private double latitud;
	private double longitud;
	
	@SuppressWarnings("unused")
	private Punto(){}
	
	public Punto(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public double distance(Punto punto) {
		return this.puntoAPoint(this).distance(punto.puntoAPoint(punto));
	}	
	
	public Point puntoAPoint(Punto punto){
		return new Point(punto.getLatitud(),punto.getLongitud());
	}
	
	public boolean intersects(Punto punto1, Punto punto2){
		return this.puntoAPoint(this).intersects(punto1.puntoAPoint(punto1), punto2.puntoAPoint(punto2));
	}
	
	public String toString(){
		return this.puntoAPoint(this).toString();
	}
}
