package proyectoJava.Negocio;
public class Negocio {
    private Integer id;
    private String nombre;
    private Integer aforo_maximo;
    private String direccion;
    private String telefono;
    private String correo;
    private String usuario;
    private String password;
    
    public Negocio(Integer idParametro,String nombreParametro, Integer aforo_maximoParametro, String direccionParametro, String telefonoParametro, String correoParametro, String usuarioParametro, String passwordParametro){
        id=idParametro;
        nombre=nombreParametro;
        aforo_maximo=aforo_maximoParametro;
        direccion=direccionParametro;
        telefono=telefonoParametro;
        correo=correoParametro;
        usuario=usuarioParametro;
        password=passwordParametro;
    }
    public Negocio(){
        id=null;
        nombre=null;
        aforo_maximo=null;
        direccion=null;
        telefono=null;
        correo=null;
        usuario=null;
        password=null;
    }    
    public String getNombre(){
        return nombre;
    }
    public Integer getAforo_maximo(){
        return aforo_maximo;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getTelefono(){
        return telefono;
    } 
    public String getCorreo(){
        return correo;
    }
    public String getUsuario(){
        return usuario;
    }
    public String getPassword(){
        return password;
    }
    public Integer getId(){
        return id;
    }
    public void setNombre(String nombreParametro){
        nombre=nombreParametro;
    }
    public void setAforo_maximo(Integer aforo_maximoParametro){
        aforo_maximo=aforo_maximoParametro;
    }
    public void setDireccion(String direccionParametro){
        direccion=direccionParametro;
    }
    public void setTelefono(String telefonoParametro){
        telefono=telefonoParametro;
    }
    public void setCorreo(String correoParametro){
        correo=correoParametro;
    }
    public void setUsuario(String usuarioParametro){
        usuario=usuarioParametro;
    }
    public void setPassword(String passwordParametro){
        password=passwordParametro;
    }
}
