package grupo4.Procesos;

public class DecoratorReintentar implements Accion {
	private int cantidadReintentos;
	private Accion decorado;

	public DecoratorReintentar(int cantidadReintentos, Accion decorado) {
		super();
		this.cantidadReintentos = cantidadReintentos;
		this.decorado = decorado;
	}

	public void ejecutar() {//lo hago void y en el catch mi fijo lo del reintento
		int nroIntento = 0;
		try{
			nroIntento++;
			decorado.ejecutar();
		}
		catch(Exception e){
			
		}
	}
}
