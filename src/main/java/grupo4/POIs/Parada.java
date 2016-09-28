package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import grupo4.HerramientasExternas.Punto;
import grupo4.PoiDTOs.PoiDTO;

@Entity
public class Parada extends Poi {
	public Parada(String nombre, List<String> palabrasClaves) {
		super(nombre, palabrasClaves);
	}

	public boolean estaCerca(Punto unPunto) {
		return (super.calcularDistancia(unPunto) <= 0.1);
	}

	public boolean estaDisponible(LocalDateTime horaConsulta) {
		return true;
	}

	public boolean encuentraNombre(String criterio) {
		return (criterio.equals(super.getNombre()));
	}

	public PoiDTO instanciaDTO() {
		return new PoiDTO(super.getNombre(),super.getPalabrasClaves(),super.getCoordenadas(),"Parada");
	}
}
