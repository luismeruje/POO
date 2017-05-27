
/**
 * Escreva a descrição da classe Utilizadores aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
public class Utilizador
{
    private String email;
    private String nome;
    private String password;
    private String morada;
    private String dataDeNascimento;
    private Posicao posicao;
    private List <Viagem> viagens;
    
    public Utilizador(String email, String nome, String pass, String morada, String data_de_nascimento, Posicao posicao, List<Viagem> viagens){
        this.email = email;
        this.nome = nome;
        this.password = pass;
        this.morada = morada;
        this.dataDeNascimento = data_de_nascimento;
        this.posicao = new Posicao(posicao);
        this.viagens = viagens; 
    }
}
