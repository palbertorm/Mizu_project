package proyectoJava.conector;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class conector {
    private Connection conect;    
    private String url;
    private String usuario;
    private String password;

    public conector(){
        url="jdbc:mysql://localhost/gestordeaforopandemico?serverTimezone=UTC";
        usuario="root";
        password="Llàstics%2020";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception exception) {
        }     
    }
    public void conectar(){
        try{
            conect = DriverManager.getConnection(url,usuario,password);
                       
        }catch(SQLException exception){
            System.out.println("SQLException: " + exception.getMessage());
            System.out.println("SQLState: " + exception.getSQLState());
            System.out.println("VendorError: " + exception.getErrorCode());
        }        
    }
    public void desconectar(){
        try {
            conect.close();
        } catch (Exception e) {
        }
    }
    public Statement conect(){
        try {
            return conect.createStatement();             
        } catch (Exception e) {
            return null;
        }
               
    }
}
