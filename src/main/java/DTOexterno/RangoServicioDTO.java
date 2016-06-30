package DTOexterno;

import java.time.DayOfWeek;

public class RangoServicioDTO {

	private DayOfWeek dia;
	private int horaDesde;
	private int minutoDesde;
	private int horaHasta;
	private int minutoHasta;

	public RangoServicioDTO(DayOfWeek dia, int horaDesde, int minutoDesde, int horaHasta, int minutoHasta) {
		super();
		this.dia = dia;
		this.horaDesde = horaDesde;
		this.minutoDesde = minutoDesde;
		this.horaHasta = horaHasta;
		this.minutoHasta = minutoHasta;
	}

	public DayOfWeek getDia() {
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
