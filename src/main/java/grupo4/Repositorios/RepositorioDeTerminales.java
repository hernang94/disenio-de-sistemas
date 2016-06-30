package grupo4.Repositorios;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTerminales {

	private List<RepositorioDePois> listaDeTerminales;
	private PrintWriter writer;

	public RepositorioDeTerminales(PrintWriter writer) {
		super();
		listaDeTerminales = new ArrayList<>();
		this.writer = writer;
	}

	public void agregarTerminal(RepositorioDePois unTerminal) {
		listaDeTerminales.add(unTerminal);
	}

	public void reporteParcialporTerminal() {
		listaDeTerminales.stream().forEach(terminal -> reportarFormateado(terminal));
	}

	private void reportarFormateado(RepositorioDePois terminal) {
		writer.println("Usuario: " + terminal.getNombre());
		terminal.reporteParcial(writer);
	}

	public void reporteTotalporTerminal() {
		writer.println("Usuario\t\tCantidad Resultados Totales");
		listaDeTerminales.stream().forEach(terminal -> reportarTotalFormateado(terminal));
	}

	private void reportarTotalFormateado(RepositorioDePois terminal) {
		writer.println(terminal.getNombre() + "\t\t");
		terminal.reporteTotal(writer);
	}
}
