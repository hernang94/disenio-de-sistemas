package grupo4.HerramientasExternas;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Coordenadas")
public class Punto {
	@Id
	@GeneratedValue
	private int id;
	private double latitud;
	private double longitud;
	
	@SuppressWarnings("unused")
	private Punto(){};
	
	public Punto(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public int getId() {
		return id;
	}
	
	
	
	

}
