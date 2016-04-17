package grupo4;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;

public class Pruebas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LocalTime fechainicio= LocalTime.of(11, 55);
		LocalTime fechainicio2= LocalTime.of(11, 54);
		LocalDateTime fecha= LocalDateTime.of(2016, 4, 18, 0, 1);
		System.out.println(fecha.getDayOfWeek().getValue());
		if(fechainicio2.getMinute()<fechainicio.getMinute()){
			System.out.println("Eureka(?");
			System.out.println(fechainicio);
			System.out.println(fechainicio2);
		}
		else{
		System.out.println(fechainicio);
		}
	}

}
