package grupo4;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Prueba {

	public static void main(String[] args) {
		LocalDateTime inicio= LocalDateTime.of(2016, 4, 4, 15, 45);
		LocalDateTime fin= LocalDateTime.of(2016, 4, 4, 16, 30);
		System.out.println(ChronoUnit.SECONDS.between(inicio, fin));

	}

}
