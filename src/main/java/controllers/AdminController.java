package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import grupo4.Acciones.IdObserver;
import grupo4.Acciones.ObserverAlmacenador;
import grupo4.Acciones.ObserverDeBusqueda;
import grupo4.Acciones.ObserverNotificador;
import grupo4.Acciones.ObserverReporterParcial;
import grupo4.Acciones.ObserverReporterPorFecha;
import grupo4.Acciones.ObserverReporterTotal;
import grupo4.Acciones.Usuario;
import grupo4.ComponentesExternos.EmailSender;
import grupo4.POIs.Banco;
import grupo4.POIs.Poi;
import grupo4.Repositorios.RepositorioCuentas;
import grupo4.Repositorios.RepositorioDePois;
import grupo4.Repositorios.RepositorioDeUsuarios;
import grupo4.Server.Router;
import grupo4.Usuarios.Users;
import grupo4.Usuarios.UsuarioTerminal;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

//#FFBF00;
public class AdminController implements WithGlobalEntityManager {
	public ModelAndView mostrarPantallaParaListarPois(Request req, Response res) {
		return new ModelAndView(null, "Administrador/formListarPois.hbs");
	}

	public ModelAndView mostrarPrincipalAdmin(Request req, Response res) {
		Map<String, Users> model = new HashMap<String, Users>();
		model.put("user", Router.getUser());
		return new ModelAndView(model, "Administrador/principalAdmin.hbs");
	}

	public ModelAndView listarPois(Request req, Response res) {
		entityManager().clear();
		List<Poi> pois = evaluarRetorno(req.queryParams("nombrePoi").toString(), req.queryParams("Tipos").toString());
		Map<String, List<Poi>> model = new HashMap<>();
		model.put("pois", pois);
		return new ModelAndView(model, "Administrador/listadoPoisAdmin.hbs");
	}

	public ModelAndView mostrarPantallaParaListarTerminales(Request req, Response res) {
		return new ModelAndView(null, "Administrador/formListarTerminales.hbs");
	}

	public ModelAndView listarTerminales(Request req, Response res) {
		List<Usuario> terminales;
		entityManager().clear();
		if (req.queryParams("comunas").equalsIgnoreCase("Todas")) {
			terminales = RepositorioDeUsuarios.getInstancia().getListaDeUsuarios();
		} else {
			terminales = RepositorioDeUsuarios.getInstancia()
					.getListaDeUsuariosXComuna(Integer.parseInt(req.queryParams("comunas").toString()));
		}
		Map<String, List<Usuario>> model = new HashMap<>();
		model.put("terminales", terminales);
		return new ModelAndView(model, "Administrador/listadoTerminales.hbs");
	}

	public ModelAndView eliminarPoi(Request req, Response res) {
		int id = Integer.parseInt(req.params("id"));
		Poi poi = RepositorioDePois.getInstancia().obtenerPoi(id);
		System.out.println(id);
		try {
			entityManager().getTransaction().begin();
			RepositorioDePois.getInstancia().bajaPoi(poi.getId());
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/eliminarExito.hbs");
		} catch (Exception e) {
			Map<String, String> model = new HashMap<>();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/eliminarError.hbs");
		}
	}

	public ModelAndView mostarEditarPoi(Request req, Response res) {
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Poi poi = RepositorioDePois.getInstancia().obtenerPoi(id);
		Map<String, Poi> model = new HashMap<>();
		model.put("poi", poi);
		return new ModelAndView(model, "Administrador/formEditarPoi.hbs");
	}

	public ModelAndView editarPoi(Request req, Response res) {
		int id = Integer.parseInt(req.params("id"));
		try {
			entityManager().getTransaction().begin();
			RepositorioDePois.getInstancia().actualizarPoi(id, req.queryParams("nombre").toString(),
					req.queryParams("direccion").toString(), Double.parseDouble(req.queryParams("latitud").toString()),
					Double.parseDouble(req.queryParams("longitud").toString()));
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/editarExito.hbs");
		} catch (Exception e) {
			Map<String, String> model = new HashMap<>();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/editarError.hbs");
		}
	}

	public ModelAndView eliminarTerminal(Request req, Response res){
		entityManager().clear();
		Usuario terminal= RepositorioDeUsuarios.getInstancia().buscarUsuario(Integer.parseInt(req.params("id")));
		Users u1= RepositorioCuentas.getInstancia().buscarUsuario(terminal.getTerminal(), "terminal");
		try{
			entityManager().getTransaction().begin();
			RepositorioCuentas.getInstancia().quitarUsuario(u1);
			RepositorioDeUsuarios.getInstancia().quitarUsuario(terminal);
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/eliminarTerminalExito.hbs");
		}
		catch(Exception e){
			Map<String,String> model = new HashMap<>();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/eliminarTerminalError.hbs");
		}
		
	}
	
	public ModelAndView mostarEditarTerminal(Request req, Response res) {
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		Map<String, Usuario> model = new HashMap<>();
		model.put("terminal", terminal);
		return new ModelAndView(model, "Administrador/formEditarTerminal.hbs");
	}
	
	public ModelAndView editarTerminal(Request req, Response res) {
		int id = Integer.parseInt(req.params("id"));
		String nombreTerminalOld=RepositorioDeUsuarios.getInstancia().buscarUsuario(id).getTerminal();
		int idCuenta=RepositorioCuentas.getInstancia().buscarUsuario(nombreTerminalOld, "terminal").getId();
		try {
			entityManager().getTransaction().begin();
			RepositorioCuentas.getInstancia().actualizarUsuario(idCuenta, req.queryParams("nombre").toString());
			RepositorioDeUsuarios.getInstancia().actualizarUsuario(id, req.queryParams("nombre").toString(),
					Integer.parseInt(req.queryParams("comunas").toString()));
			entityManager().flush();
			entityManager().clear();
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/editarTerminalExito.hbs");
		} catch (Exception e) {
			Map<String, String> model = new HashMap<>();
			e.printStackTrace();
			model.put("error", e.getMessage());
			return new ModelAndView(model, "Administrador/editarTerminalError.hbs");
		}
	}
	public ModelAndView mostrarPrincipalConfigurarAcciones(Request req, Response res) {
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		Map<String, Usuario> model = new HashMap<>();
		model.put("terminal", terminal);
		return new ModelAndView(model, "Administrador/principalConfigurarAcciones.hbs");
	}
	public ModelAndView mostrarAgregarAccion(Request req, Response res) {
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		Map<String, Usuario> model = new HashMap<>();
		pasarStringsAccionesATerminal(terminal);
		model.put("terminal", terminal);
		return new ModelAndView(model, "Administrador/agregarAccion.hbs");
	}
	public ModelAndView mostrarEliminarAccion(Request req, Response res) {
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		Map<String, Usuario> model = new HashMap<>();
		model.put("terminal", terminal);
		return new ModelAndView(model, "Administrador/quitarAccion.hbs");
	}
	public ModelAndView agregarAccion(Request req, Response res){
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		try{
		terminal.agregarObserver(evaluarObserverParaAgregar(req.queryParams("acciones")));
		entityManager().getTransaction().begin();
		RepositorioDeUsuarios.getInstancia().actualizarObjetoUsuario(terminal);
		entityManager().flush();
		entityManager().clear();
		entityManager().getTransaction().commit();
		return new ModelAndView(null, "Administrador/agregarAccionExito.hbs");
		}
		catch(Exception e){
		return new ModelAndView(null, "Administrador/agregarAccionError.hbs");
		}
	}
	
	public ModelAndView eliminarAccion(Request req, Response res){
		entityManager().clear();
		int id = Integer.parseInt(req.params("id"));
		Usuario terminal = RepositorioDeUsuarios.getInstancia().buscarUsuario(id);
		try{
		terminal.quitarObserver(evaluarObserverParaEliminar(req.queryParams("acciones")));
		entityManager().getTransaction().begin();
		RepositorioDeUsuarios.getInstancia().actualizarObjetoUsuario(terminal);
		entityManager().flush();
		entityManager().clear();
		entityManager().getTransaction().commit();
		return new ModelAndView(null, "Administrador/quitarAccionExito.hbs");
		}
		catch(Exception e){
			e.printStackTrace();
		return new ModelAndView(null, "Administrador/quitarAccionError.hbs");
		}
	}
	
	public ModelAndView mostrarAgregarTerminal(Request req, Response res){
		return new ModelAndView(null,"Administrador/formAgregarTerminal.hbs");
	}
	
	public ModelAndView agregarTerminal(Request req, Response res){
		entityManager().clear();
		Usuario terminal=new Usuario(req.queryParams("nombre").toString(), Integer.parseInt(req.queryParams("comunas").toString()));
		if(RepositorioCuentas.getInstancia().buscarUsuario(req.queryParams("nombre").toString(), "terminal")!=null){
			Map<String,String>model=new HashMap<>();
			model.put("error", "Terminal ya existente, intente con otro nombre");
			return new ModelAndView(model, "Administrador/agregarTerminalError.hbs");
		}
		Users usuario=new UsuarioTerminal(req.queryParams("nombre").toString(), "terminal", terminal);
		try{
			entityManager().getTransaction().begin();
			RepositorioDeUsuarios.getInstancia().agregarUsuario(terminal);
			RepositorioCuentas.getInstancia().agregarUsuario(usuario);
			entityManager().flush();
			entityManager().clear();
			entityManager().getTransaction().commit();
			return new ModelAndView(null, "Administrador/agregarTerminalExito.hbs");
		}
		catch(Exception e){
			Map<String,String>model=new HashMap<>();
			model.put("error", "No se pudo agregar el terminal");
			return new ModelAndView(model, "Administrador/agregarTerminalError.hbs");
		}
	}
	
	private ObserverDeBusqueda evaluarObserverParaAgregar(String queryParams) {
		if (queryParams.equalsIgnoreCase("almacenador")) {
			return new ObserverAlmacenador();
		}
		if(queryParams.equalsIgnoreCase("notificador")){
			return new ObserverNotificador(5, new EmailSender() {			
				@Override
				public void enviarMail(String asunto) {
				}
			});
		}
		if (queryParams.equalsIgnoreCase("Reporte Total")) {
			return new ObserverReporterTotal();
		}
		if (queryParams.equalsIgnoreCase("Reporte Parcial")) {
			return new ObserverReporterParcial();
		}
		return new ObserverReporterPorFecha();
	}

	private IdObserver evaluarObserverParaEliminar(String queryParams) {
		if (queryParams.equalsIgnoreCase("almacenador")) {
			return IdObserver.ALMACENADOR;
		}
		if(queryParams.equalsIgnoreCase("notificador")){
			return IdObserver.NOTIFICADOR;
		}
		if (queryParams.equalsIgnoreCase("Reporte Total")) {
			return IdObserver.REPORTERTOTAL;
		}
		if (queryParams.equalsIgnoreCase("Reporte Parcial")) {
			return IdObserver.REPORTERPARCIAL;
		}
		return IdObserver.REPORTERFECHA;
	}
	

	private List<Poi> evaluarRetorno(String nombre, String tipo) {
		
		if (tipo.equalsIgnoreCase("Todos")) {
			return RepositorioDePois.getInstancia().busquedaPorNombre(nombre);
		}
		if (nombre.isEmpty()) {
			return RepositorioDePois.getInstancia().busquedaPorTipo(tipo);
		}
		return RepositorioDePois.getInstancia().busquedaPorNombre(nombre).stream()
				.filter(poi -> poi.getTipo().equalsIgnoreCase(tipo)).collect(Collectors.toList());
	}
	public void pasarStringsAccionesATerminal(Usuario terminal){
		List<String> listaAux=new ArrayList<>();
		listaAux.add("Notificador");
		listaAux.add("Almacenador");
		listaAux.add("Reporte Total");
		listaAux.add("Reporte Parcial");
		listaAux.add("Reporte por Fecha");
		terminal.setAcciones(listaAux);
	}
}
