package grupo4.Acciones;

import java.time.LocalDate;

public class FechaCantReporte {
	private LocalDate fecha;
	private int cantidad;

	public FechaCantReporte(LocalDate fecha, int cantidad) {
		this.fecha = fecha;
		this.cantidad = cantidad;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

}
