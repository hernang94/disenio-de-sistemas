package grupo4;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
	private int diaDesde;
	private int diaHasta;
	private LocalTime desde;
	private LocalTime hasta;

	public Horario(String desde, String hasta, int diaDesde, int diaHasta) { 	// El
																				// formato
																				// de
																				// las
																				// horas
																				// limites
																				// de
																				// trabajo
																				// son:
																				// "HH:MM"
		this.desde = LocalTime.of(Integer.parseInt(desde.substring(0, 2)), Integer.parseInt(desde.substring(3, 5)));
		this.hasta = LocalTime.of(Integer.parseInt(hasta.substring(0, 2)), Integer.parseInt(hasta.substring(3, 5)));
		this.diaDesde = diaDesde;
		this.diaHasta = diaHasta;
	}

	public boolean estaEnHorario(LocalDateTime fechaConsulta) {
		return (fechaConsulta.toLocalTime().isAfter(desde) && fechaConsulta.toLocalTime().isBefore(hasta));

	}

	public boolean estaEnDia(LocalDateTime fechaConsulta) {
		int unDia = fechaConsulta.toLocalDate().getDayOfWeek().getValue();
		return (unDia >= diaDesde && unDia <= diaHasta);
	}

}
