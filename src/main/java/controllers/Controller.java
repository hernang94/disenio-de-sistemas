package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import grupo4.Acciones.Usuario;
import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeUsuarios;
import grupo4.Usuarios.Users;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
//#FFBF00;
public class Controller {
	static Users u1;
	public  ModelAndView registro(Request req, Response res){
		return new ModelAndView(null, "registro.hbs");
	}
	public  ModelAndView logeo(Request req, Response res){
		return new ModelAndView(null, "login.hbs");
	}
	public  Void Ingreso(Request req, Response res){
		u1= RepositorioCuentas.getInstancia().buscarUsuario(req.queryParams("usuario").toString(), req.queryParams("contrasenia").toString());
		
		if (u1!=null){
			if(u1.getDecriminatorValue().equalsIgnoreCase("ADMINISTRADOR"))
			res.redirect("/administrador/principal");
			else{
				res.redirect("/terminal/principal");
			}
		}
		res.redirect("/logueoIncorrecto");
		return null;
	}
	public ModelAndView mostrarPantallaParaListarPois(Request req, Response res){
		return new ModelAndView(null,"Administrador/formListarPois.hbs");
	}
	public  ModelAndView mostrarPrincipalAdmin(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user", u1);
		return new ModelAndView(model, "Administrador/principalAdmin.hbs");
	}
	public  ModelAndView mostrarLogueoIncorrecto(Request req, Response res){
		return new ModelAndView(null, "logueoIncorrecto.hbs");
	}
	public Void logout(Request req, Response res){
		u1=null;
		res.redirect("/");
		return null;
	}
	public  ModelAndView mostrarPrincipalTerminal(Request req, Response res){
		Map<String, Users> model= new HashMap<String, Users>();
		model.put("user", u1);
		return new ModelAndView(model, "Terminal/principalTerminal.hbs");
	}	
	public ModelAndView listarPois(Request req, Response res){
		List<Poi> pois=evaluarRetorno(req.queryParams("nombrePoi").toString(), req.queryParams("Tipos").toString());
		Map<String, List<Poi>> model=new HashMap<>();
		model.put("pois", pois);
		return new ModelAndView(model, "Administrador/listadoPoisAdmin.hbs");
	}
	
	public ModelAndView mostrarPantallaParaListarTerminales(Request req, Response res){
		return new ModelAndView(null,"Administrador/formListarTerminales.hbs");
	}
	public ModelAndView listarTerminales(Request req, Response res){
		List<Usuario> terminales;
		if(req.queryParams("comunas").equalsIgnoreCase("Todas")){
			terminales=RepositorioDeUsuarios.getInstancia().getListaDeUsuarios();
		}
		else{
			terminales=RepositorioDeUsuarios.getInstancia().getListaDeUsuariosXComuna(Integer.parseInt(req.queryParams("comunas").toString()));
		}
		Map<String, List<Usuario>> model=new HashMap<>();
		model.put("terminales", terminales);
		return new ModelAndView(model, "Administrador/listadoTerminales.hbs");
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
	
private List<Poi> evaluarRetorno(String nombre,String tipo){
	/*if(nombre.isEmpty()&&tipo.isEmpty()){
		return null;
	}*/
	if(tipo.equalsIgnoreCase("Todos")){
		return RepositorioDePois.getInstancia().busquedaPorNombre(nombre);
	}
	if(nombre.isEmpty()){
		return RepositorioDePois.getInstancia().busquedaPorTipo(tipo);
	}
		return RepositorioDePois.getInstancia().busquedaPorNombre(nombre).stream().filter(poi->poi.getTipo().equalsIgnoreCase(tipo)).collect(Collectors.toList());
}
}
