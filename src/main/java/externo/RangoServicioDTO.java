package externo;

public class RangoServicioDTO {
	private int dia;
	private int horaDesde;
	private int minutoDesde;
	private int horaHasta;
	private int minutoHasta;
	
	

	public RangoServicioDTO(int dia, int horaDesde, int minutoDesde, int horaHasta, int minutoHasta) {
		super();
		this.dia = dia;
		this.horaDesde = horaDesde;
		this.minutoDesde = minutoDesde;
		this.horaHasta = horaHasta;
		this.minutoHasta = minutoHasta;
	}

	public int getDia() {
		return dia;
	}

	public int getHoraDesde() {
		return horaDesde;
	}

	public int getMinutoDesde() {
		return minutoDesde;
	}

	public int getHoraHasta() {
		return horaHasta;
	}

	public int getMinutoHasta() {
		return minutoHasta;
	}

}
