package grupo4.Procesos;


public class AdministradorDeProcesos {
	private static AdministradorDeProcesos instancia = new AdministradorDeProcesos();
	private AdministradorDeProcesos() {

	}
	
	public static AdministradorDeProcesos getInstancia() {
		return instancia;
	}

	
}

