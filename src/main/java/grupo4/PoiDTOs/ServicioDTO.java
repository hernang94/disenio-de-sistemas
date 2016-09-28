package grupo4.PoiDTOs;

import java.time.DayOfWeek;
import java.util.Map;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import grupo4.POIs.Horario;
@Entity
public class ServicioDTO {
	@Id
	private int id;
	private String nombre;
	@Embedded
	private Map<DayOfWeek, Horario> hashHorario;
	
	public ServicioDTO(String nombre, Map<DayOfWeek, Horario> hashHorario) {
		super();
		this.nombre = nombre;
		this.hashHorario = hashHorario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Map<DayOfWeek, Horario> getHashHorario() {
		return hashHorario;
	}

	public void setHashHorario(Map<DayOfWeek, Horario> hashHorario) {
		this.hashHorario = hashHorario;
	}
	
	
}
