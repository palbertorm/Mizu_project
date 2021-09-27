package proyectoJava.gestorDeAforo;

import proyectoJava.BaseDatos.BaseDatos;
import proyectoJava.CorreoElectronico.CorreoElectronico;
import proyectoJava.Negocio.Negocio;
import proyectoJava.Utilities.Utilities;
import proyectoJava.Visitas.Visitas;
import proyectoJava.Visitas_persona.Visitas_persona;
import proyectoJava.conector.conector;
import proyectoJava.Persona.Persona;

import java.util.ArrayList;

import com.google.gson.Gson;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@RestController
public class AforoController {
	@RequestMapping(value = "/buscarPersona", method = RequestMethod.GET)
	public ResponseEntity<String> buscarPersona(@RequestParam String correo) {
		Persona individuo;
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		individuo=bd.buscarPersona(conector, correo);
		Gson gson=new Gson();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<String> (gson.toJson(individuo),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/crearVisita", method = RequestMethod.POST)
	public ResponseEntity<Boolean> crearEntrada(@RequestParam int idnegocio, int idpersona, String estatus) {
		Visitas visita=new Visitas(idnegocio,idpersona,null,estatus);
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<Boolean> (bd.crearVisita(conector, visita),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/crearPersona", method = RequestMethod.POST)
	public ResponseEntity<Boolean> crearPersona(@RequestParam String nombre, String apellido, String telefono,String correo, String dni_nie_pasaporte, String usuario,String contrasena, String rol) {
		Persona persona=new Persona(null, nombre, apellido, telefono, correo, dni_nie_pasaporte, usuario, contrasena, rol);
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<Boolean> (bd.crearPersona(conector, persona),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/crearNegocio", method = RequestMethod.POST)
	public ResponseEntity<Boolean> crearNegocio(@RequestParam String nombre, int aforo_maximo, String direccion, String telefono, String correo, String usuario, String password) {
		Negocio negocio=new Negocio(null,nombre,aforo_maximo,direccion,telefono,correo,usuario,password);
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<Boolean> (bd.crearNegocio(conector, negocio),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/buscarNegocio", method = RequestMethod.GET)
	public ResponseEntity<String> buscarNegocio(@RequestParam String correo) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		Negocio negocio=bd.buscarNegocio(conector, correo);
		Gson gson=new Gson();
		return new ResponseEntity<String> (gson.toJson(negocio),headers,HttpStatus.OK); 
	}
	@RequestMapping(value = "/validarLoging", method = RequestMethod.GET)
	public ResponseEntity<String> validarLonging(@RequestParam String usuario,String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		Negocio negocio=bd.validarLogin(conector, usuario,password);
		Gson gson=new Gson();
		return new ResponseEntity<String> (gson.toJson(negocio),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/Alertar", method = RequestMethod.GET)
	public ResponseEntity<Boolean> obtenerHistorial(@RequestParam String correo) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		ArrayList <String> correos=new ArrayList<String>();
		correos=bd.obtenerCorreos(conector, correo);
		CorreoElectronico ce=new CorreoElectronico();
		for(String aux:correos){
			ce.sendSimpleMessage(aux, "Alerta", "Por medio de nuestra aplicación se detecto un posible contacto entre usted y una persona infectada con el Covid-19. Se le aconseja tomar medidas preventivas.");			
		}
		return new ResponseEntity<Boolean> (headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/llenarTablaAlerta", method = RequestMethod.GET)
	public ResponseEntity<String> llenarTablaAlerta() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		Gson gson=new Gson();
		ArrayList<String> listaPersonas=new ArrayList<String>();
		ArrayList<Persona> personas=bd.buscarPersonasAlerta(conector);
		for(Persona p:personas){
			listaPersonas.add(gson.toJson(p));
		}
		return new ResponseEntity<String> (gson.toJson(listaPersonas),headers,HttpStatus.OK);
	}
	@RequestMapping(value = "/calcularAforo", method = RequestMethod.GET)
	public ResponseEntity<String> calcularAforo(@RequestParam int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		conector conector = new conector();
		BaseDatos bd=new BaseDatos();
		Gson gson=new Gson();
		ArrayList<String> listaPersonas=new ArrayList<String>();
		ArrayList<Visitas_persona> personas=bd.aforoActual(conector,id);
		for(Visitas_persona p:personas){
			listaPersonas.add(gson.toJson(p));
		}
		return new ResponseEntity<String> (gson.toJson(listaPersonas),headers,HttpStatus.OK);
	}
}
