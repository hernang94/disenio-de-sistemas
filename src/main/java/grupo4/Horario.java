package grupo4;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Horario {
	private LocalTime desde;
	private LocalTime hasta;

	public Horario(String desde, String hasta) { // El formato de las horas limites de trabajo son:"HH:MM"
		this.desde = LocalTime.of(Integer.parseInt(desde.substring(0, 2)), Integer.parseInt(desde.substring(3, 5)));
		this.hasta = LocalTime.of(Integer.parseInt(hasta.substring(0, 2)), Integer.parseInt(hasta.substring(3, 5)));
	}

	public boolean estaEnHorario(LocalDateTime fechaConsulta) {
		return (fechaConsulta.toLocalTime().isAfter(desde) && fechaConsulta.toLocalTime().isBefore(hasta));

	}
}
