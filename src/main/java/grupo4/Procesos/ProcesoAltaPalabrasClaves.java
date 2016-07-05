package grupo4.Procesos;

import java.io.IOException;
import java.util.List;

import DTOexterno.LocalComercialExterno;
import grupo4.ComponentesExternos.ComponenteLocalComercial;
import grupo4.ComponentesExternos.LocalComercialAdapter;
import grupo4.Repositorios.RepositorioDePois;

public class ProcesoAltaPalabrasClaves implements Proceso{
private LocalComercialAdapter adapter;
private RepositorioDePois repo;
public ProcesoAltaPalabrasClaves(LocalComercialAdapter adapter,RepositorioDePois repositorio){
	this.adapter=adapter;
	this.repo=repositorio;
}
	
	public void ejecutar() {
		try {
			List<LocalComercialExterno> lista= adapter.obtenerLocalesExternos();
			lista.stream().forEach(localExterno->repo.cambiarPalabrasClaves(localExterno.getNombre(), localExterno.getPalabrasClaves()));
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
