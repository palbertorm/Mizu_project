package proyectoJava.Visitas_persona;
public class Visitas_persona {
    private Integer idnegocio;
    private Integer idpersona;
    private String fecha;
    private String estatus;
    private String nombre;
    private String apellido; 
    private String correo;
    private String rol;

    public void Visitas_persona(Integer idnegocioParametro, Integer idpersonaParametro, String fechaParametro, String estatusParametro, String nombreParametro, String apellidoParametro, String correoParametro, String rolParametro){
        idnegocio=idnegocioParametro;
        idpersona=idpersonaParametro;
        fecha=fechaParametro;
        estatus=estatusParametro;
        nombre=nombreParametro;
        apellido=apellidoParametro;
        correo=correoParametro;
        rol=rolParametro;
    }
    public Integer getIdnegocio(){
        return idnegocio;
    }
    public Integer getIdpersona(){
        return idpersona;
    }
    public String getFecha(){
        return fecha;
    }
    public String getEstatus(){
        return estatus;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public String correo(){
        return correo;
    }
}
