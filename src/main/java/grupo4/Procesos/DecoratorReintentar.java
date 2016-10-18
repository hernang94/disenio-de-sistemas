package grupo4.Procesos;

import grupo4.Repositorios.ResultadosDeEjecucion;

public class DecoratorReintentar implements Accion {
	private int cantidadReintentos;
	private int intentosRealizados;
	private Accion decorado;

	public DecoratorReintentar(int cantidadReintentos, Accion decorado) {
		super();
		this.intentosRealizados = 0;
		this.cantidadReintentos = cantidadReintentos;
		this.decorado = decorado;
	}

	public int getIntentosRealizados() {
		return intentosRealizados;
	}

	public void setIntentosRealizados(int intentosRealizados) {
		this.intentosRealizados = intentosRealizados;
	}

	public int getCantidadReintentos() {
		return cantidadReintentos;
	}

	public ResultadosDeEjecucion ejecutar(){//lo hago void y en el catch mi fijo lo del reintento
		ResultadosDeEjecucion resultado = null;
		try{
			this.setIntentosRealizados(this.getIntentosRealizados()+1);
			resultado=decorado.ejecutar();
		}
		catch(Exception e){
			if(this.getIntentosRealizados()>this.getCantidadReintentos()){
				throw new RuntimeException("Se supero la cantidad de Reintentos y el proceso fallo");
			}
			else{
				this.ejecutar();
			}
		}
		return resultado;
	}
}
