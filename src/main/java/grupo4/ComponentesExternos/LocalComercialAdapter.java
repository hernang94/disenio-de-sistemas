package grupo4.ComponentesExternos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DTOexterno.LocalComercialExterno;

public class LocalComercialAdapter implements Actualizador {
	private ComponenteLocalComercial componente;

	public List<LocalComercialExterno> obtenerLocalesExternos() {
		List<LocalComercialExterno> listaADevolver;
		try {
			listaADevolver = crearListaDeLocales(componente.obtenerArchivo());
			return listaADevolver;
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

	public List<LocalComercialExterno> crearListaDeLocales(File obtenerArchivo) throws IOException {
		List<LocalComercialExterno> listaARetornar = new ArrayList<>();
		BufferedReader archivo = new BufferedReader(new FileReader(obtenerArchivo));
		String linea;
		while ((linea = archivo.readLine()) != null) {
			listaARetornar.add(obtenerObjetoLocal(linea));
		}
		archivo.close();
		return listaARetornar;
	}

	// Este metodo deberia ser privado pero lo pusimos publico para testearlo
	public LocalComercialExterno obtenerObjetoLocal(String contenido) {
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

}
