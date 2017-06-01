
/**
 * Escreva a descrição da classe Utilizadores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Collections;
import java.util.Date;
public class Utilizador
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private Date nascimento;
    private Posicao posicao;
    private List <Viagem> viagens;
    
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
        this.viagens = Collections.emptyList();  
        		//-->talvez seja melhor passar a map.
    }
    
    
    
    public Utilizador(String email, String nome, String pass, String morada, String data_de_nascimento, Posicao posicao, List<Viagem> viagens){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.nascimento = new Date(nascimento.getTime());
        this.posicao = new Posicao(posicao);
        for(Viagem v: viagens){
            this.viagens.add(v.clone());
        }
    }
    
    public Utilizador(Utilizador u){
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.morada = u.getMorada();
        this.nascimento = new Date(nascimento.getTime());
        this.posicao = new Posicao(u.getPosicao());
        for(Viagem v: u.getViagens()){
            this.viagens.add(v.clone());
        }
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
    public List<Viagem> getViagens(){
        return this.viagens;
    }
    
    
    public void setPosicao(Posicao p){
        posicao = p;
    }
    
    public void registarViagem(Viagem v){
    	this.viagens.add(v.clone());
    }
    
    //Clone, equals, toString
}
