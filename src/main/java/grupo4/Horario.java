package grupo4;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
	private int diadesde;
	private int diahasta;
	private LocalTime desde;
	private LocalTime hasta;

	public Horario(String desde, String hasta, int diadesde, int diahasta) { 	// El
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
		this.diadesde = diadesde;
		this.diahasta = diahasta;
	}

	public boolean estaEnHorario(LocalDateTime fecha_consulta) {
		return (fecha_consulta.toLocalTime().isAfter(desde) && fecha_consulta.toLocalTime().isBefore(hasta));

	}

	public boolean estaEnDia(LocalDateTime fecha_consulta) {
		int undia = fecha_consulta.toLocalDate().getDayOfWeek().getValue();
		return (undia >= diadesde && undia <= diahasta);
	}

}
