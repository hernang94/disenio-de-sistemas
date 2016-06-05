package grupo4;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTerminales {

	private List<RepositorioDePois> listaDeTerminales= new ArrayList<>();
	
	public void agregarTerminal(RepositorioDePois unTerminal){
		this.listaDeTerminales.add(unTerminal);
	}
	
	public void reportePorTerminal(){
		System.out.println("Usuario\t\tCantidad Resultados Totales\n");
		listaDeTerminales.stream().forEach(terminal->reportarTotales(terminal));
	}

	private void reportarTotales(RepositorioDePois terminal) {
		System.out.println(terminal.getNombre()+"\t\t"+terminal.cantidadTotalDeResultados());
	}
	public void reportarParcialesTerminal(String nombreTerminal){
		RepositorioDePois terminalAux=listaDeTerminales.stream().filter(terminal->terminal.getNombre().equalsIgnoreCase(nombreTerminal)).findFirst().get();
		System.out.println("Usuario: "+terminalAux.getNombre()+"\n");
		terminalAux.ReportarBusquedasPorTipo();
	}
	
}
