package grupo4.Usuarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue(value="ADMINISTRADOR")
public class UsuarioAdministrador extends Users{

	private UsuarioAdministrador(){
		super();
	}
	public UsuarioAdministrador(String usuario, String contrasenia) {
		super(usuario, contrasenia);
	}

}
