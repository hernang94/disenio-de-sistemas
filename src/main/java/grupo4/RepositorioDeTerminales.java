package grupo4;

import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTerminales {
	private List<RepositorioDePois> listaDeTerminales=new ArrayList<>();
	
	public void agregarTerminal(RepositorioDePois unTerminal){
		listaDeTerminales.add(unTerminal);
	}
	public void reporteParcialporTerminal(){
		listaDeTerminales.stream().forEach(terminal->reportarFormateado(terminal));
	}
	private void reportarFormateado(RepositorioDePois terminal) {
		System.out.println("Usuario: "+terminal.getNombre());
		terminal.reporteParcial();
	}
	public void reporteTotalporTerminal(){
		System.out.println("Usuario\t\tCantidad Resultados Totales");
		listaDeTerminales.stream().forEach(terminal->reportarTotalFormateado(terminal));
	}
	private void reportarTotalFormateado(RepositorioDePois terminal) {
		System.out.println(terminal.getNombre()+"\t\t");
		terminal.reporteTotal();
	}
}
