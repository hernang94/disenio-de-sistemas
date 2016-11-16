package grupo4.Usuarios;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import grupo4.Acciones.Usuario;
@Entity
@DiscriminatorValue(value="USUARIO_TERMINAL")
public class UsuarioTerminal extends Users{
	@OneToOne
	private Usuario terminal;
	@SuppressWarnings("unused")
	private UsuarioTerminal(){
		super();
	}
	public UsuarioTerminal(String usuario, String contrasenia,Usuario terminal) {
		super(usuario, contrasenia);
		this.terminal=terminal ;
	}
	public Usuario getTerminal() {
		return terminal;
	}

}
