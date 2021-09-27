package proyectoJava.Utilities;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utilities {
    public static String agregarComillas(String dato){
        if(dato.isBlank()){
            return null;
        }else{
            return"\""+dato+"\"";
        }        
    }
    public static LocalDateTime obtenerFecha(){
        return LocalDateTime.now();
    }
    public static String formatearFecha(LocalDateTime fecha){
        DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return fecha.format(formateoFecha);
    }
    public static String getInicioDia(LocalDateTime fecha){
        DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd 00:00:00");
        return fecha.format(formateoFecha);
    }
    public static String getFinDia(LocalDateTime fecha){
        DateTimeFormatter formateoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd 23:59:59");
        return fecha.format(formateoFecha);
    }
}