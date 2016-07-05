package DTOexterno;

import java.util.List;

public class LocalComercialExterno {
private String nombre;
private List<String> palabrasClaves;
public LocalComercialExterno(String nombre, List<String> palabrasClaves) {
	super();
	this.nombre = nombre;
	this.palabrasClaves = palabrasClaves;
}
public String getNombre() {
	return nombre;
}
public List<String> getPalabrasClaves() {
	return palabrasClaves;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public void setPalabrasClaves(List<String> palabrasClaves) {
	this.palabrasClaves = palabrasClaves;
}

}
