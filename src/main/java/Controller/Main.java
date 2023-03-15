package Controller;

import DAO.PacienteDAO;
import Model.cadPaciente;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws SQLException {   
             
        Scanner sc = new Scanner(System.in);  
        
        
        //Inserir
        System.out.println("Informe o nome do paciente: ");
        String n = sc.nextLine();
        System.out.println("Informe a data de nascimento do paciente: ");
        System.out.println("Dia: ");
        String d = sc.nextLine();
        System.out.println("Mês: ");
        String m = sc.nextLine();
        System.out.println("Ano: ");
        String a = sc.nextLine();        
        String i = a+"/"+m+"/"+d;
        System.out.println("Informe o CPF do paciente: ");
        String c = sc.nextLine();
        System.out.println("Informe a profissão do paciente: ");
        String p = sc.nextLine();        
        cadPaciente func = new cadPaciente(n, i , c, p);        
        System.out.println(func); 
        
        
        //Deletar 
        System.out.println("Deletar");
        PacienteDAO dao = new PacienteDAO();
        String cpf = sc.next();
        dao.delete(cpf);
        
        
        //Atualizar
        //PacienteDAO dao = new PacienteDAO();
        System.out.println("Atualizar");
        String cpf2 = sc.next();
        sc.nextLine();
        String pro = sc.nextLine();
        dao.update(cpf2, pro);
        
        
        //Listar
        System.out.println("Listar");
        System.out.println(cadPaciente.Lista());
       
        
    }
    
    
}
