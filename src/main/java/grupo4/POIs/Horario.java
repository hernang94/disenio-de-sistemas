package grupo4.POIs;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

import grupo4.HerramientasExternas.LocalTimeConverter;

@Embeddable
public class Horario {
	@Convert(converter=LocalTimeConverter.class)
	private LocalTime desde;
	@Convert(converter=LocalTimeConverter.class)
	private LocalTime hasta;

	@SuppressWarnings("unused")
	private Horario() {

	}

	public Horario(String desde, String hasta) { // El formato de las hora limites de trabajo son:"HH:MM"
		this.desde = LocalTime.of(Integer.parseInt(desde.substring(0, 2)), Integer.parseInt(desde.substring(3, 5)));
		this.hasta = LocalTime.of(Integer.parseInt(hasta.substring(0, 2)), Integer.parseInt(hasta.substring(3, 5)));
	}

	public boolean estaEnHorario(LocalDateTime fechaConsulta) {
		return (fechaConsulta.toLocalTime().isAfter(desde) && fechaConsulta.toLocalTime().isBefore(hasta));

	}
	public LocalTime getDesde() {
		return desde;
	}

	public LocalTime getHasta() {
		return hasta;
	}

}
