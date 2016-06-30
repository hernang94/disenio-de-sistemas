package grupo4.ComponentesExternos;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import grupo4.POIs.Horario;

public class FactoryHorarioBanco {

	private Map<DayOfWeek, Horario> hashMapBanco;
	private Horario horarioBanco;

	public FactoryHorarioBanco() {
		horarioBanco = new Horario("10:00", "15:00");
		hashMapBanco = new HashMap<>();
	}

	public Map<DayOfWeek, Horario> dameHorarioBanco() {
		hashMapBanco.put(DayOfWeek.MONDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.TUESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.WEDNESDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.THURSDAY, horarioBanco);
		hashMapBanco.put(DayOfWeek.FRIDAY, horarioBanco);
		return hashMapBanco;
	}
}
