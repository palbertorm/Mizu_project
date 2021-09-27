package proyectoJava.Persona;
public class Persona {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private String dni_nie_pasaporte;
    private String usuario;
    private String pasword;
    private String roll;

    public Persona(Integer idparametro, String nombreParametro, String apellidoParametro, String telefonoParametro,
            String correoParametro, String dni_nie_pasaporteParametro, String usuarioParametro,
            String contrasenaParametro, String rolParametro) {
        id = idparametro;
        nombre = nombreParametro;
        apellido = apellidoParametro;
        telefono = telefonoParametro;
        correo = correoParametro;
        dni_nie_pasaporte = dni_nie_pasaporteParametro;
        usuario = usuarioParametro;
        pasword = contrasenaParametro;
        roll = rolParametro;
    }

    public Persona() {
        id=null;
        nombre=null;
        apellido=null;
        telefono=null;
        correo=null;
        dni_nie_pasaporte=null;
        usuario=null;
        pasword=null;
        roll=null;
    }
    public Integer getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String getTelefono(){
        return telefono;
    }
    public String getCorreo(){
        return correo;
    }
    public String getDni_nie_pasaporte(){
        return dni_nie_pasaporte;
    }
    public String getUsuario(){
        return usuario;
    }
    public String getPassword(){
        return pasword;
    }
    public String getRoll(){
        return roll;
    }
}
