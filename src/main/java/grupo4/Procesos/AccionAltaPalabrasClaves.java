package grupo4.Procesos;

import java.time.LocalDateTime;
import java.util.List;

import DTOexterno.LocalComercialExterno;
import grupo4.ComponentesExternos.LocalComercialAdapter;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.ResultadosDeEjecucion;

public class AccionAltaPalabrasClaves implements Accion {
	private LocalComercialAdapter adapter;
	private RepositorioDePois repositorio;

	public AccionAltaPalabrasClaves(LocalComercialAdapter adapter, RepositorioDePois repositorio) {
		this.adapter = adapter;
		this.repositorio = repositorio;
	}

	public ResultadosDeEjecucion ejecutar() {
		List<LocalComercialExterno> lista = adapter.obtenerLocalesExternos();
		lista.stream().forEach(localExterno -> repositorio.cambiarPalabrasClaves(localExterno.getNombre(),
				localExterno.getPalabrasClaves()));
		return new ResultadosDeEjecucion(lista.size(), LocalDateTime.now(), "Alta de Palabras Claves con Exito");
	}
}
