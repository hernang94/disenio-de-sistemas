package grupo4.Acciones;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import grupo4.Repositorios.ResultadoDeBusqueda;

@Entity
@Table(name = "Observers")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class ObserverDeBusqueda {
	@Id
	@GeneratedValue
	private int idObserver;
	protected IdObserver id;

	public abstract void realizarAccion(ResultadoDeBusqueda resultado);

	public abstract IdObserver getId();
	
	public abstract String getTipo();
}
