package grupo4.POIs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import grupo4.Acciones.Usuario;

@Entity
public class Users {
	@Id
	@GeneratedValue
	private int id_User;
	private String usuario;
	private String contrasenia;
	@OneToOne
	private Usuario terminal;
	@SuppressWarnings("unused")
	private Users(){}
	
	public Users(String usuario, String contrasenia,Usuario terminal) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.terminal=terminal;
	}
	public int getId() {
		return id_User;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public Usuario getTerminal(){
		return terminal;
	}
}
