package Model;

import DAO.PacienteDAO;
import java.util.List;
import java.util.Set;

public class cadPaciente {
    
//Variáveis   
    private int codigo;
    private String idade;
    private String CPF;
    private String nome;
    private String profissao;
    
//Construtor
     
    public cadPaciente(String n, String i, String c) {
        this.setIdade(i);
        this.setCPF(c);
        this.setNome(n);
    }
    public cadPaciente(String n, String i, String c, String p) {
        this.setIdade(i);
        this.setCPF(c);
        this.setNome(n);
        this.setProfissão(p);
        gravar();
    }
    public cadPaciente(String n, String p) {
        this.setNome(n);
        this.setProfissão(p);
    }

    public cadPaciente(int cod, String n, String i, String c,  String p) {
        this.setIdade(i);
        this.setCPF(c);
        this.setNome(n);
        this.setProfissão(p);
        this.setCodigo(cod);
    }
    
    public cadPaciente(String cod){
        //this.setCodigo(c);
        deletar(cod);
    }
    //Gravar
    public void gravar(){
        
        PacienteDAO dao = new PacienteDAO();
        int codigo = dao.insert(this);
        if(codigo > 0){
            setCodigo(codigo);
        }
    }
    //Deletar
    public void deletar(String cdg){
        PacienteDAO dao = new PacienteDAO();
        dao.delete(cdg);
    }
    //Listar
    public static List<cadPaciente> Lista(){
        System.out.println("Aqui estão todos os pacientes cadastrados: ");
        PacienteDAO dao = new PacienteDAO();
        List<cadPaciente> lista = dao.read();        
        for(int i=0;i<lista.size();i++){
            System.out.println("\n----------");
            System.out.println(lista.get(i));
            System.out.println("----------");
        }        
        return null;        
    }
    //Atualizar
    public void atualizar(String cdg2, String pro){
        PacienteDAO dao = new PacienteDAO();           
        dao.update(cdg2, pro);
    }
    
//Getter & Setter    
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissão(String profissao) {
        this.profissao = profissao;
    }
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    @Override
    public String toString(){
        return "Nome     : " + this.getNome() + 
             "\nIdade    : " + this.getIdade() + 
             "\nCPF      : " + this.getCPF(); 
    }
    
    
}
