package grupo4.POIs;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class PersistentClass {
	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return id;
	}
	
}
