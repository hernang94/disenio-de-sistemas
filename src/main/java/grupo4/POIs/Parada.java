package grupo4.POIs;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.uqbar.geodds.Point;

import grupo4.HerramientasExternas.Punto;
@Entity
public class Parada extends Poi {
	@Id
	@GeneratedValue
	private int id;
	public Parada( String nombre, List<String> palabrasClaves) {
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
}
