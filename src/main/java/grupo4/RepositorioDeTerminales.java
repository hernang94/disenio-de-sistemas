package grupo4;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDeTerminales {

	private List<RepositorioDePois> listaDeTerminales= new ArrayList<>();
	
	public void agregarTerminal(RepositorioDePois unTerminal){
		this.listaDeTerminales.add(unTerminal);
	}
	
	public void reportePorTerminal(){
		PrintWriter writer=crearArchivo("ReportePorTerminal");
		writer.println("Usuario\t\tCantidad Resultados Totales\n");
		listaDeTerminales.stream().forEach(terminal->reportarTotales(terminal,writer));
		writer.close();
	}

	private void reportarTotales(RepositorioDePois terminal,PrintWriter writer) {
		writer.println(terminal.getNombre()+"\t\t"+terminal.cantidadTotalDeResultados());
	}
	public void reportarParcialesTerminal(String nombreTerminal){
		PrintWriter writer=crearArchivo("ReportarParcialesTerminal");
		RepositorioDePois terminalAux=listaDeTerminales.stream().filter(terminal->terminal.getNombre().equalsIgnoreCase(nombreTerminal)).findFirst().get();
		writer.println("Usuario: "+terminalAux.getNombre()+"\n");
		terminalAux.ReportarBusquedasPorTipo(writer);
		writer.close();
	}
	
	private PrintWriter crearArchivo(String nombre){
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(nombre+".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return writer;
	}
}

/*PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8"); 
writer.println("The first line"); 
writer.println("The second line"); 
writer.close();*/