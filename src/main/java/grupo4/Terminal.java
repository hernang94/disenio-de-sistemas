package grupo4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class Terminal {
	List<Poi> lista_de_pois = new ArrayList<>();
	Coordenadas ubicacion_terminal;
public List<Poi> busquedaLibre(String criterio){
	List<Poi> lista_aux = new ArrayList<>();
	lista_aux=lista_de_pois.stream().filter(unPoi->unPoi.coincideCon(criterio)).collect(Collectors.toList());
	return lista_aux;
}
}
