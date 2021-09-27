package proyectoJava.Visitas;
public class Visitas {
    private Integer idnegocio;
    private Integer idpersona;
    private String fecha;
    private String estatus;

    public Visitas(Integer idnegocioParametro, Integer idpersonaParametro, String fechaParametro, String estatusParametro){
        idnegocio=idnegocioParametro;
        idpersona=idpersonaParametro;
        fecha=fechaParametro;
        estatus=estatusParametro;
    }
    public Visitas(){
        idnegocio=null;
        idpersona=null;
        fecha=null;
        estatus=null;
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
}
