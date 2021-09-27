package proyectoJava.BaseDatos;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

import proyectoJava.Negocio.*;
import proyectoJava.Persona.*;
import proyectoJava.Visitas.*;
import proyectoJava.Visitas_persona.*;
import proyectoJava.conector.*;
import proyectoJava.Utilities.*;
import java.time.LocalDateTime;

public class BaseDatos {
    private Statement statement;
    private ResultSet resultado;

    public boolean crearPersona(conector conector, Persona persona) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate(
                    "insert into persona (nombre,apellido,telefono,correo,dni_nie_pasaporte,usuario,contrasena,rol) values ("
                            + Utilities.agregarComillas(persona.getNombre()) + ","
                            + Utilities.agregarComillas(persona.getApellido()) + ","
                            + Utilities.agregarComillas(persona.getTelefono()) + ","
                            + Utilities.agregarComillas(persona.getCorreo()) + ","
                            + Utilities.agregarComillas(persona.getDni_nie_pasaporte()) + ","
                            + Utilities.agregarComillas(persona.getUsuario()) + ","
                            + Utilities.agregarComillas(persona.getPassword()) + ","
                            + Utilities.agregarComillas(persona.getRoll()) + ");");
            conector.desconectar();
            return true;
        } catch (SQLException exception) {
            conector.desconectar();
            return false;
        }
    }

    public boolean crearNegocio(conector conector, Negocio negocio) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate(
                    "insert into negocio (nombre,aforo_maximo,direccion,telefono,correo,usuario,contrasena) values ("
                            + Utilities.agregarComillas(negocio.getNombre()) + "," + negocio.getAforo_maximo() + ","
                            + Utilities.agregarComillas(negocio.getDireccion()) + ","
                            + Utilities.agregarComillas(negocio.getTelefono()) + ","
                            + Utilities.agregarComillas(negocio.getCorreo()) + ","
                            + Utilities.agregarComillas(negocio.getUsuario()) + ","
                            + Utilities.agregarComillas(negocio.getPassword()) + ");");
            conector.desconectar();
            return true;
        } catch (SQLException exception) {
            conector.desconectar();
            return false;
        }
    }

    public boolean crearVisita(conector conector, Visitas visita) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate("insert into visitas (idnegocio,idpersona,fecha,estatus) values ("
                    + visita.getIdnegocio() + "," + visita.getIdpersona() + ","
                    + Utilities.agregarComillas(Utilities.formatearFecha(Utilities.obtenerFecha())) + ","
                    + Utilities.agregarComillas(visita.getEstatus()) + ");");
            conector.desconectar();
            return true;
        } catch (SQLException exception) {
            conector.desconectar();
            return false;
        }
    }

    public boolean modificarNegocio(conector conector, Negocio negocio) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate("update negocio set nombre=" + Utilities.agregarComillas(negocio.getNombre())
                    + ", aforo_maximo=" + negocio.getAforo_maximo() + ", direccion="
                    + Utilities.agregarComillas(negocio.getDireccion()) + ", telefono="
                    + Utilities.agregarComillas(negocio.getTelefono()) + ", correo="
                    + Utilities.agregarComillas(negocio.getCorreo()) + ", usuario="
                    + Utilities.agregarComillas(negocio.getUsuario()) + ", contrasena="
                    + Utilities.agregarComillas(negocio.getPassword()) + " where idnegocio=" + negocio.getId() + ";");
            conector.desconectar();
            return true;
        } catch (Exception e) {
            conector.desconectar();
            return false;
        }
    }

    public boolean modificarPersona(conector conector, Persona persona) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate("update persona set nombre=" + Utilities.agregarComillas(persona.getNombre())
                    + ", apellido=" + Utilities.agregarComillas(persona.getApellido()) + ", telefono="
                    + Utilities.agregarComillas(persona.getTelefono()) + ", correo="
                    + Utilities.agregarComillas(persona.getCorreo()) + ", dni_nie_pasaporte="
                    + Utilities.agregarComillas(persona.getDni_nie_pasaporte()) + ", usuario="
                    + Utilities.agregarComillas(persona.getUsuario()) + ", contrasena="
                    + Utilities.agregarComillas(persona.getPassword()) + ", rol="
                    + Utilities.agregarComillas(persona.getRoll()) + " where idpersona=" + persona.getId() + ";");
            conector.desconectar();
            return true;
        } catch (Exception e) {
            conector.desconectar();
            return false;
        }
    }

    public boolean eliminarNegocio(conector conector, Integer idnegocio) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate("delete from negocio where idnegocio=" + idnegocio + ";");
            conector.desconectar();
            return true;
        } catch (Exception e) {
            conector.desconectar();
            return false;
        }
    }

    public boolean eliminarPersona(conector conector, Integer idpersona) {
        try {
            conector.conectar();
            statement = conector.conect();
            statement.executeUpdate("delete from persona where idpersona=" + idpersona + ";");
            conector.desconectar();
            return true;
        } catch (Exception e) {
            conector.desconectar();
            return false;
        }
    }

    public Persona buscarPersona(conector conector, String correo) {
        try {
            conector.conectar();
            statement = conector.conect();
            resultado = statement
                    .executeQuery("select * from persona where correo=" + Utilities.agregarComillas(correo) + ";");
            Persona individuo = null;
            if (resultado.next()) {
                individuo = new Persona(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                        resultado.getString(8), resultado.getString(9));
            }
            conector.desconectar();
            return individuo;
        } catch (Exception e) {
            conector.desconectar();
            return null;
        }
    }
    
    public ArrayList<Persona> buscarPersonasAlerta(conector conector) {
        try {
            conector.conectar();
            statement = conector.conect();
            resultado = statement
                    .executeQuery("select * from persona;");
            ArrayList<Persona> personas=new ArrayList<Persona>();
            Persona individuo = null;
            if(resultado.next()){
                individuo = new Persona(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
                            resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                            resultado.getString(8), resultado.getString(9));
                personas.add(individuo);
                while (resultado.next()) {
                    individuo = new Persona(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
                            resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                            resultado.getString(8), resultado.getString(9));
                    personas.add(individuo);
                }
                conector.desconectar();
                return personas;
            }else{
                conector.desconectar();
                return null;
            }
        } catch (Exception e) {
            conector.desconectar();
            return null;
        }
    }
    public Negocio buscarNegocio(conector conector, String correo) {
        try {
            conector.conectar();
            statement = conector.conect();
            resultado = statement
                    .executeQuery("select * from negocio where correo=" + Utilities.agregarComillas(correo) + ";");
            Negocio negocio = null;
            if (resultado.next()) {
                negocio = new Negocio(resultado.getInt(1), resultado.getString(2), resultado.getInt(3),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                        resultado.getString(8));
            }
            conector.desconectar();
            return negocio;
        } catch (Exception e) {
            conector.desconectar();
            return null;
        }
    }

    public Negocio validarLogin(conector conector, String usuario, String password) {
        try {
            conector.conectar();
            statement = conector.conect();
            resultado = statement
                    .executeQuery("select * from negocio where usuario=" + Utilities.agregarComillas(usuario)
                            + " and contrasena=" + Utilities.agregarComillas(password) + ";");
            Negocio negocio = null;
            if (resultado.next()) {
                negocio = new Negocio(resultado.getInt(1), resultado.getString(2), resultado.getInt(3),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                        resultado.getString(8));
            }
            conector.desconectar();
            return negocio;
        } catch (Exception e) {
            conector.desconectar();
            return null;
        }
    }

    public ArrayList<Visitas_persona> aforoActual(conector conector, int id) {
        ArrayList<Visitas_persona> listaVisitas2 = new ArrayList<Visitas_persona>();
        try {
            String fecha1,fecha2;
            fecha1=Utilities.getInicioDia(Utilities.obtenerFecha());
            fecha2=Utilities.getFinDia(Utilities.obtenerFecha());
            ArrayList<Visitas_persona> listaVisitas = new ArrayList<Visitas_persona>();
            conector.conectar();
            statement = conector.conect();
            resultado = statement.executeQuery(
                    "select v.*,p.nombre,p.apellido,p.correo,p.rol from visitas as v join persona as p on v.idpersona=p.idpersona where v.fecha between"
                            + Utilities.agregarComillas(fecha1) + " and " + Utilities.agregarComillas(fecha2)
                            + " and v.idnegocio="+id+" order by v.fecha;");
            if (resultado.next() == false) {
                conector.desconectar();
                return listaVisitas2;
            } else {
                Visitas_persona aux = new Visitas_persona();
                aux.Visitas_persona(resultado.getInt(1), resultado.getInt(2), resultado.getString(3),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),resultado.getString(8));
                listaVisitas.add(aux);
                while (resultado.next()) {
                    aux = new Visitas_persona();
                    aux.Visitas_persona(resultado.getInt(1), resultado.getInt(2), resultado.getString(3),
                            resultado.getString(4), resultado.getString(5), resultado.getString(6),
                            resultado.getString(7),resultado.getString(8));
                    listaVisitas.add(aux);
                }
            }
            listaVisitas2 = new ArrayList<Visitas_persona>(listaVisitas);
            for (Visitas_persona visita : listaVisitas) {
                for (Visitas_persona visita2 : listaVisitas) {
                    if (visita != visita2) {
                        if ((visita.getIdpersona() == visita2.getIdpersona()) && ("S".equals(visita2.getEstatus()))) {
                            listaVisitas2.remove(visita);
                            listaVisitas2.remove(visita2);
                        }
                    }
                }
            }
            conector.desconectar();
            return listaVisitas2;
        } catch (SQLException exception) {
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
            conector.desconectar();
            return listaVisitas2;
        }
    }

    public String buscarPersona2(conector conector, String correo) {
        try {
            conector.conectar();
            statement = conector.conect();
            resultado = statement
                    .executeQuery("select * from persona where correo=" + Utilities.agregarComillas(correo) + ";");
            Persona individuo = null;
            if (resultado.next()) {
                individuo = new Persona(resultado.getInt(1), resultado.getString(2), resultado.getString(3),
                        resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7),
                        resultado.getString(8), resultado.getString(9));
            }
            conector.desconectar();
            return individuo.getNombre();
        } catch (Exception e) {
            conector.desconectar();
            return "fallo algo";
        }
    }

    public ArrayList<String> obtenerCorreos(conector conector, String correo) {
        LocalDateTime dia = Utilities.obtenerFecha();
        String fecha = Utilities.formatearFecha(dia);
        String inicioDia = Utilities.getInicioDia(dia);
        String finDia = Utilities.getFinDia(dia);
        ArrayList<String> correos = new ArrayList<String>();
        try {
            conector.conectar();
            statement = conector.conect();
            resultado=statement.executeQuery("select * from visitas as v join persona as p on v.idpersona=p.idpersona where p.correo="+Utilities.agregarComillas(correo)+" and v.fecha between "+Utilities.agregarComillas(inicioDia)+" and "+Utilities.agregarComillas(finDia));
            resultado = statement.executeQuery(
                    "select p.correo from visitas as v join persona as p on v.idpersona=p.idpersona where v.fecha between (select v.fecha from visitas as v join persona as p on v.idpersona=p.idpersona where correo="
                            + Utilities.agregarComillas(correo) + " and v.fecha between "
                            + Utilities.agregarComillas(inicioDia) + " and " + Utilities.agregarComillas(finDia)
                            + " and v.estatus='E') and (select v.fecha from visitas as v join persona as p on v.idpersona=p.idpersona where correo="
                            + Utilities.agregarComillas(correo) + " and v.fecha between "
                            + Utilities.agregarComillas(inicioDia) + " and " + Utilities.agregarComillas(finDia)
                            + " and v.estatus='S') and p.correo != " + Utilities.agregarComillas(correo) + ";");
            if (resultado.next() == false) {
                conector.desconectar();
                return null;
            } else {
                correos.add(resultado.getString(1));
                while (resultado.next()) {
                    if(!correos.contains(resultado.getString(1))){
                        correos.add(resultado.getString(1));
                    }                    
                }
            }
            conector.desconectar();
            return correos;
        } catch (Exception e) {
            conector.desconectar();
            return null;
        }        
    }
}
