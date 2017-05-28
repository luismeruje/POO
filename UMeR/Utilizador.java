
/**
 * Escreva a descrição da classe Utilizadores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Collections;
public class Utilizador
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataDeNascimento;
    private Posicao posicao;
    private List <Viagem> viagens;
    
    public Utilizador(){
        this.email = " ";
        this.nome = " ";
        this.password = " ";
        this.morada = " ";
        this.dataDeNascimento = " ";
        this.posicao = new Posicao();
        this.viagens = Collections.emptyList();
    }
    
    public Utilizador(String email, String nome, String pass, String morada, String data_de_nascimento, Posicao posicao, List<Viagem> viagens){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.dataDeNascimento = data_de_nascimento;
        this.posicao = new Posicao(posicao);
        for(Viagem v: viagens){
            this.viagens.add(v);
        }
    }
    
    public Utilizador(Utilizador u){
        this.email = u.getEmail();
        this.nome = u.getNome();
        this.password = u.getPassword();
        this.morada = u.getMorada();
        this.dataDeNascimento = u.getDDNasc();
        this.posicao = new Posicao(u.getPosicao());
        for(Viagem v: u.getViagens()){
            this.viagens.add(v);
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
    public String getDDNasc(){
        return this.dataDeNascimento;
    }
    public Posicao getPosicao(){
        return this.posicao;
    }
    public List<Viagem> getViagens(){
        return this.viagens;
    }
    
}
