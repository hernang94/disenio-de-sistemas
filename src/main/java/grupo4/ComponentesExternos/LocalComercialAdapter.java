package grupo4.ComponentesExternos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.http.message.BufferedHeader;

import DTOexterno.LocalComercialExterno;
import grupo4.POIs.Poi;

public class LocalComercialAdapter implements Adaptadores{
	private ComponenteLocalComercial componente;

	public LocalComercialAdapter() {

	}

	public List<LocalComercialExterno> obtenerLocalesExternos() {
		List<LocalComercialExterno> listaADevolver;
		try {
			listaADevolver = crearListaDeLocales(componente.obtenerArchivo());
			return listaADevolver;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	private List<LocalComercialExterno> crearListaDeLocales(File obtenerArchivo) throws IOException {
		List<LocalComercialExterno> listaARetornar = new ArrayList<>();
		BufferedReader archivo = new BufferedReader(new FileReader(obtenerArchivo));
		String linea;
		while ((linea = archivo.readLine()) != null) {
			listaARetornar.add(obtenerObjetoLocal(linea));
		}
		return listaARetornar;
	}

	private LocalComercialExterno obtenerObjetoLocal(String contenido) {
		LocalComercialExterno localComercial;
		String nombre = contenido.substring(0, contenido.indexOf(';'));
		String lista = contenido.substring(contenido.indexOf(';') + 1, contenido.length());
		List<String> palabrasClave = Arrays.asList(lista.split(""));
		localComercial = new LocalComercialExterno(nombre, palabrasClave);
		return localComercial;
	}

	public void setComponente(ComponenteLocalComercial componente) {
		this.componente = componente;
	}

	@Override
	public List<Poi> buscarPois(String criterio1) {
		// TODO Auto-generated method stub
		return null;
	}

}
