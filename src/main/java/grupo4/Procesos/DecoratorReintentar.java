package grupo4.Procesos;

public class DecoratorReintentar implements Accion {
	private int cantidadReintentos;
	private Accion decorado;

	public DecoratorReintentar(int cantidadReintentos, Accion decorado) {
		super();
		this.cantidadReintentos = cantidadReintentos;
		this.decorado = decorado;
	}

	public boolean ejecutar() {//lo hago void y en el catch mi fijo lo del reintento
		boolean status;
		int nroIntento = 0;
		do {
			nroIntento++;
			status = decorado.ejecutar();
		} while (!status && nroIntento < cantidadReintentos);
		return status;
	}
}
