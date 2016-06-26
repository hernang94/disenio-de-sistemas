package DTOexterno;

import java.util.ArrayList;
import java.util.List;

public class BancoExterno {
	
	private String banco;
	private double x;
	private double y;
	private String sucursal;
	private String gerente;
	private List<String> servicios = new ArrayList<>();

	public BancoExterno(String banco, double x, double y, String sucursal, String gerente, List<String> servicios) {
		super();
		this.banco = banco;
		this.x = x;
		this.y = y;
		this.sucursal = sucursal;
		this.gerente = gerente;
		this.servicios = servicios;
	}

	public BancoExterno() {
	}

	public String getBanco() {
		return banco;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public String getSucursal() {
		return sucursal;
	}

	public String getGerente() {
		return gerente;
	}

	public List<String> getServicios() {
		return servicios;
	}
}
