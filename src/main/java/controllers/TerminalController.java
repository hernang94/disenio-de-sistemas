package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import grupo4.POIs.Banco;
import grupo4.POIs.CGP;
import grupo4.POIs.LocalComercial;
import grupo4.POIs.Parada;
import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Server.Router;
import grupo4.Usuarios.Users;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {
	public  ModelAndView mostrarPrincipalTerminal(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user", Router.getUser());
		return new ModelAndView(model, "Terminal/principalTerminal.hbs");
	}	
	public ModelAndView mostrarBusquedaPois(Request req, Response res){
		return new ModelAndView(null, "Terminal/buscarPois.hbs");
	}
	public ModelAndView buscarPoisTerminal(Request req, Response res){
		List<Poi> pois=RepositorioDePois.getInstancia().busquedaLibre(req.queryParams("criterio").toString());
		Map<String, List<Poi>> model=new HashMap<>();
		model.put("pois", pois);
		return new ModelAndView(model, "Terminal/mostrarResultadosBusqueda.hbs");
	}
	public ModelAndView mostrarDetallePoi(Request req, Response res){
		int idPoi=Integer.parseInt(req.params("id"));
		Poi poi=RepositorioDePois.getInstancia().obtenerPoi(idPoi);
		
		return evaluarRetorno(poi);
	}
	private ModelAndView evaluarRetorno(Poi poi){
		if(poi.getDecriminatorValue().equalsIgnoreCase("banco")){
			Map<String, Poi> model= new HashMap<String, Poi>();
			model.put("poi",(Banco)poi);
			return  new ModelAndView(model, "Pois/mostrarPoiBanco.hbs");
		}
		if(poi.getDecriminatorValue().equalsIgnoreCase("cgp")){
			Map<String, Poi> model= new HashMap<String, Poi>();
			model.put("poi",(CGP)poi);
			return  new ModelAndView(model, "Pois/mostrarPoiCGP.hbs");
		}
		if(poi.getDecriminatorValue().equalsIgnoreCase("parada")){
			Map<String, Poi> model= new HashMap<String, Poi>();
			model.put("poi",(Parada)poi);
			return  new ModelAndView(model, "Pois/mostrarPoiParada.hbs");
		}
			Map<String, Poi> model= new HashMap<String, Poi>();
			model.put("poi",(LocalComercial)poi);
			return  new ModelAndView(model, "Pois/mostrarPoiLocalComercial.hbs");
			
	}
}
