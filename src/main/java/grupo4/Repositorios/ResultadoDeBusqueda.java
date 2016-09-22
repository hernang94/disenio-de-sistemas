package grupo4.Repositorios;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;

@Entity
@Table(name = "Busquedas")
public class ResultadoDeBusqueda {

	@Id
	@GeneratedValue
	private int id;
	@Column(name = "Usuario")
	private String terminalDeLaBusqueda;
	private long tiempoDeBusqueda;
	@Column(name = "Descripcion")
	private String fraseBuscada;
	@Column(name = "Fecha")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate fechaDeBusqueda;
	private int cantidadDeResultados;

	@SuppressWarnings("unused")
	private ResultadoDeBusqueda() {
	}

	public ResultadoDeBusqueda(String terminal, long tiempoDeBusqueda, String fraseBuscada, LocalDate fechaDeBusqueda,
			int cantidadDeResultados) {
		this.terminalDeLaBusqueda = terminal;
		this.tiempoDeBusqueda = tiempoDeBusqueda;
		this.fraseBuscada = fraseBuscada;
		this.fechaDeBusqueda = fechaDeBusqueda;
		this.cantidadDeResultados = cantidadDeResultados;
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

	public boolean esDeTerminal(String unTerminal) {
		return unTerminal.equalsIgnoreCase(terminalDeLaBusqueda);
	}

	public int getId() {
		return id;
	}

}
