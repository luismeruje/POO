
/**
 * Escreva a descrição da classe Utilizadores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Utilizador
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date nascimento;
    private Posicao posicao;
    private List <Integer> viagens;
    
    //depois remover;
    public Utilizador(){
    }
    
    public Utilizador(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = new Date(nascimento.getTime());
        this.posicao = new Posicao(posicao);
        this.viagens = new ArrayList<Integer>();  
                //-->talvez seja melhor passar a map.
    }
    
    
    
    public Utilizador(String email, String nome, String pass, String morada, String data_de_nascimento, Posicao posicao, List<Integer> viagens){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = new Date(nascimento.getTime());
        this.posicao = new Posicao(posicao);
        for(Integer v: viagens){
            this.viagens.add(v);
        }
    }
    
    public Utilizador(Utilizador u){
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.morada = u.getMorada();
        this.nascimento = new Date(nascimento.getTime());
        this.posicao = new Posicao(u.getPosicao());
        this.viagens = u.getViagensCodigos();
    }
    
    public String getEmail(){
        return this.email;
    }
    public String getNome(){
        return this.nome;
    }
    public String getPassword(){
        return this.password;
    }
    public String getMorada(){
        return this.morada;
    }
    public Date getNascimento(){
        return new Date(nascimento.getTime());
    }
    public Posicao getPosicao(){
        return this.posicao;
    }
    public List<Integer> getViagensCodigos(){
        return new ArrayList<Integer>(this.viagens);
    }
    
    
    public void setPosicao(Posicao p){
        posicao = p;
    }
    
    public void registarViagem(Integer identificacao){
        this.viagens.add(identificacao);
    }
    
    public ArrayList<Object> escreverFicheiro ()throws FileNotFoundException, IOException{
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(email); //0
        data.add(nome); //1
        data.add(password); //2
        data.add(morada); //3
        data.add(nascimento); //4
        data.add(posicao.getX()); //5
        data.add(posicao.getY()); //6
        data.add(viagens);//7
        
        return data;
    }
    
    public static Utilizador lerFicheiro(Object utilizador)throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Object> data = new ArrayList<Object>();
        data = (ArrayList<Object>) utilizador;
        
        String email,nome,password,morada;
        int kms;
        double x, y;
        Date nascimento;
        Posicao p;
        ArrayList <Integer> viagens;
        
        email = (String) data.get(0);
        nome = (String) data.get(1);
        password = (String) data.get(2);
        morada = (String) data.get(3);
        nascimento = (Date) data.get(4);
        x = (double) data.get(5);
        y = (double) data.get(6);
        viagens = (ArrayList <Integer>) data.get(7);
        
        p = new Posicao(x,y);
        return new Utilizador(email,nome,password,morada,nascimento,p);
    }
    
    public Utilizador clone(){
        return new Utilizador(this);
    }
}
