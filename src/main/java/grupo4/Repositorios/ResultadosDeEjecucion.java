package grupo4.Repositorios;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Resultados de Ejecucion")
public class ResultadosDeEjecucion {
	@Id	@GeneratedValue
	private long id;
	
	private int cantidadDeElementosAfectados;
	//No convierto a nada la fecha porque solo la manejo desde aca y no necesito verla desde la BDD
	private LocalDateTime fecha;
	@Column(name="Descripcion")
	private String resultado;

	public ResultadosDeEjecucion(int cantidadDeElementosAfectados, LocalDateTime fecha, String resultado) {
		super();
		this.cantidadDeElementosAfectados = cantidadDeElementosAfectados;
		this.fecha = fecha;
		this.resultado = resultado;
	}

	public int getCantidadDeElementosAfectados() {
		return cantidadDeElementosAfectados;
	}

	public LocalDateTime getDescripcion() {
		return fecha;
	}

	public String getResultado() {
		return resultado;
	}

}
