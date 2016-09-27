package grupo4.PoiDTOs;

import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Transient;

import grupo4.HerramientasExternas.Punto;

public abstract class PoiDTO {
	protected String nombre;
	@Id
	private int idPoi;
	@Property("Latitud")
	private double x;
	@Property("Longitud")
	private double y;
	@Embedded
	private List<String> palabrasClaves;
	
	public PoiDTO(String nombre, List<String> palabrasClaves,Punto ubicacion) {
		this.nombre = nombre;
		this.palabrasClaves = palabrasClaves;
		this.setX(ubicacion.latitude());
		this.setY(ubicacion.longitude());
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIdPoi() {
		return idPoi;
	}

	public void setIdPoi(int idPoi) {
		this.idPoi = idPoi;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public List<String> getPalabrasClaves() {
		return palabrasClaves;
	}

	public void setPalabrasClaves(List<String> palabrasClaves) {
		this.palabrasClaves = palabrasClaves;
	}
	
	
}
