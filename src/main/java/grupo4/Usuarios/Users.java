package grupo4.Usuarios;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import grupo4.Acciones.Usuario;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO")
public abstract class Users {
	@Id
	@GeneratedValue
	private int id_User;
	private String usuario;
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	private String contrasenia;
	@OneToOne
	Usuario terminal;
	protected Users(){}
	
	public Users(String usuario, String contrasenia) {
		super();
		this.usuario = usuario;
		this.contrasenia = contrasenia;
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
	public String getDecriminatorValue() {
	    return this.getClass().getAnnotation(DiscriminatorValue.class).value();
	}
	public Usuario getTerminal() {
		return terminal;
	}
}
