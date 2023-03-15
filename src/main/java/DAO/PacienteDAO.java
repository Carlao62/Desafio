package DAO;
import Model.cadPaciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class PacienteDAO implements Persistencia<cadPaciente> {
    // atributo estático
    private static PacienteDAO dao = null;
    // construtor
    public PacienteDAO() {
    }
    // create
    @Override
    public int insert(cadPaciente c) {
        int               id  = 0;
        Connection        con = null;
        PreparedStatement pst = null;
        ResultSet         rs  = null;
        String            sql = "INSERT INTO pacientes (nomePacientes, idadePacientes, CPFPaciente, profissaoPaciente) VALUES (?, ?, ?, ?)";
        try {
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getIdade());
            pst.setString(3, c.getCPF());
            pst.setString(4, c.getProfissao());
            pst.execute();
            rs = pst.getGeneratedKeys();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            con.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            id = 0;
        }
        return id;
    }
    //Read
    public List<cadPaciente> read(){
        int               id  = 0;
        Connection        con = null;
        PreparedStatement pst = null;
        ResultSet         rs  = null;
        String            sql = "select nomePacientes,CPFPaciente, DATE_FORMAT(FROM_DAYS(DATEDIFF(current_date, idadePacientes )),'%Y') + 0 AS idade\n" +
"FROM pacientes where statusPaciente = 1; ";
        List lista = new ArrayList<cadPaciente>();
        try {
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            rs = pst.executeQuery();
            while (rs.next()){
                String nome    = rs.getString("nomePacientes");
                String idade   = rs.getString("idade");
                String cpf     = rs.getString("CPFPaciente");
                
                lista.add(new cadPaciente(nome,idade,cpf));
            }           
            con.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();            
        }
        return lista;
    }
    
    // delete
    public void delete(String id) {
        Connection        con = null;
        PreparedStatement pst = null;
        String            sql = "UPDATE pacientes SET statusPaciente = '0' WHERE CPFPaciente = ?";
        try {
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.execute();
            con.close();
        }
        catch(SQLException ex) {
            throw new RuntimeException("Erro no Delete");
        }
    }
// findByCodigo
    public cadPaciente findByCodigo(String id) {
        Connection        con = null;
        PreparedStatement pst = null;
        ResultSet         rs  = null;
        cadPaciente       p   = null;
        String            sql = "SELECT * FROM pacientes WHERE CPFPaciente = ?";
        try {
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                String    nome            = rs.getString("nomePacientes");
                String profissao          = rs.getString("profissaoPaciente");
                p = new cadPaciente(nome, profissao);
            }
            con.close();
        }
        catch(SQLException ex) {
            throw new RuntimeException("Erro no Select");
        }
        return p;
    }    
    // update
    public void update(String id , String pro) {        
        Connection        con = null;
        PreparedStatement pst = null;
        String            sql = "UPDATE pacientes SET profissaoPaciente = ? WHERE CPFPaciente = ?";
        try {
            con = ConnectionFactory.getConnection();
            pst = con.prepareStatement(sql);
            pst.setString(1, pro);
            pst.setString(2, id);
            pst.execute();
            con.close();
        }
        catch(SQLException ex) {
            throw new RuntimeException("Erro no Update");
        }
    }
}