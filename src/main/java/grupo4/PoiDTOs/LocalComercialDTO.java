package grupo4.PoiDTOs;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;

import grupo4.HerramientasExternas.Punto;

@Entity
public class LocalComercialDTO extends PoiDTO {

	public LocalComercialDTO(String nombre, List<String> palabrasClaves, Punto ubicacion) {
		super(nombre, palabrasClaves, ubicacion);
	}

}
