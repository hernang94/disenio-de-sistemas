package grupo4.PoiDTOs;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import grupo4.HerramientasExternas.Poligono;
import grupo4.HerramientasExternas.Punto;
import grupo4.POIs.Horario;
import grupo4.POIs.Rubro;
import grupo4.PoiDTOs.ServicioDTO;

@Entity
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
	@Embedded
	private Map<DayOfWeek, Horario> hashHorario;
	@Embedded
	private List<ServicioDTO> listaServicios = new ArrayList<>();
	@Embedded
	private Map<DayOfWeek, Horario> hashManana;
	@Embedded
	private Map<DayOfWeek, Horario> hashTarde;
	private Rubro rubro;
	private Poligono comuna;
	private String tipo;
	
	public PoiDTO(String nombre, List<String> palabrasClaves,Punto ubicacion, String tipo) {
		this.nombre = nombre;
		this.palabrasClaves = palabrasClaves;
		this.setX(ubicacion.latitude());
		this.setY(ubicacion.longitude());
		this.tipo=tipo;
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

	public Map<DayOfWeek, Horario> getHashHorario() {
		return hashHorario;
	}

	public void setHashHorario(Map<DayOfWeek, Horario> hashHorario) {
		this.hashHorario = hashHorario;
	}

	public List<ServicioDTO> getListaServicios() {
		return listaServicios;
	}

	public void setListaServicios(List<ServicioDTO> listaServicios) {
		this.listaServicios = listaServicios;
	}

	public Map<DayOfWeek, Horario> getHashManana() {
		return hashManana;
	}

	public void setHashManana(Map<DayOfWeek, Horario> hashManana) {
		this.hashManana = hashManana;
	}

	public Map<DayOfWeek, Horario> getHashTarde() {
		return hashTarde;
	}

	public void setHashTarde(Map<DayOfWeek, Horario> hashTarde) {
		this.hashTarde = hashTarde;
	}

	public Rubro getRubro() {
		return rubro;
	}

	public void setRubro(Rubro rubro) {
		this.rubro = rubro;
	}

	public Poligono getComuna() {
		return comuna;
	}

	public void setComuna(Poligono comuna) {
		this.comuna = comuna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
