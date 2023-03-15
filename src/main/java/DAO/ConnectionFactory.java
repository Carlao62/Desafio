package DAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
public class ConnectionFactory {
    // atributos
    private final static  String sgbd   = "Mysql";
    private final static  String server = "localhost";
    private final static  String porta  = "3306";
    private final static  String user   = "root";
    private final static  String pw     = "1234";
    private final static  String banco  = "desafio";    
    private final static  String strCnx = "jdbc:" + sgbd + "://" + server + ":" + porta + "/" + banco;
    // construtores
    public ConnectionFactory() { }
    // faz conexão
    public static Connection getConnection() throws SQLException {
        //Connection con = null;
        try {
            return DriverManager.getConnection(strCnx, user, pw);
        }
        catch(SQLException e) {
            throw new RuntimeException("Erro na conexão");
        }
    }
}