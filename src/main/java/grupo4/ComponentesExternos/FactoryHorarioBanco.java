package grupo4.ComponentesExternos;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import grupo4.POIs.Horario;

public class FactoryHorarioBanco {
	private Map<Integer,Horario> hashMapBanco;
	private Horario horarioBanco;
	private LocalDateTime fechaAux;
	public FactoryHorarioBanco(){
	horarioBanco= new Horario("10:00", "15:00");
	hashMapBanco = new HashMap<>();
	fechaAux= LocalDateTime.now();
	}
	public Map<Integer,Horario> dameHorarioBanco(){
	hashMapBanco.put(fechaAux.getDayOfWeek().MONDAY.getValue(), horarioBanco);
	hashMapBanco.put(fechaAux.getDayOfWeek().TUESDAY.getValue(), horarioBanco);
	hashMapBanco.put(fechaAux.getDayOfWeek().WEDNESDAY.getValue(), horarioBanco);
	hashMapBanco.put(fechaAux.getDayOfWeek().THURSDAY.getValue(), horarioBanco);
	hashMapBanco.put(fechaAux.getDayOfWeek().FRIDAY.getValue(), horarioBanco);
	return hashMapBanco;
	}
}
