package grupo4.Repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;
import grupo4.PoiDTOs.PoiDTO;

@Entity
public class ResultadoDeBusqueda {

	@Id
	private int id;
	@Property("Usuario")
	private String terminalDeLaBusqueda;
	private long tiempoDeBusqueda;
	@Property("Descripcion")
	private String fraseBuscada;
	@Property("Fecha")
	//@Converters(value = LocalDateConverter.class)
	private LocalDate fechaDeBusqueda;
	private int cantidadDeResultados;
	private List<PoiDTO> poisObtenidos;

	@SuppressWarnings("unused")
	private ResultadoDeBusqueda() {
	}

	public ResultadoDeBusqueda(String terminal, long tiempoDeBusqueda, String fraseBuscada, LocalDate fechaDeBusqueda,
			int cantidadDeResultados, List<PoiDTO> poisObtenidos) {
		this.terminalDeLaBusqueda = terminal;
		this.tiempoDeBusqueda = tiempoDeBusqueda;
		this.fraseBuscada = fraseBuscada;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.cantidadDeResultados = cantidadDeResultados;
		this.poisObtenidos = poisObtenidos;
	}

	public String getTerminalDeLaBusqueda() {
		return terminalDeLaBusqueda;
	}

	public long getTiempoDeBusqueda() {
		return tiempoDeBusqueda;
	}

	public String getFraseBuscada() {
		return fraseBuscada;
	}

	public LocalDate getFechaDeBusqueda() {
		return fechaDeBusqueda;
	}

	public int getCantidadDeResultados() {
		return cantidadDeResultados;
	}
	
	/*public boolean esDeTerminal(String unTerminal) {
		return unTerminal.equalsIgnoreCase(terminalDeLaBusqueda);
	}*/

	public List<PoiDTO> getPoisObtenidos() {
		return poisObtenidos;
	}

	public void setPoisObtenidos(List<PoiDTO> poisObtenidos) {
		this.poisObtenidos = poisObtenidos;
	}

	public int getId() {
		return id;
	}

}
