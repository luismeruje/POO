
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Date;
public class Motorista extends Utilizador
{
    //TODO: Adicionar lista de viaturas
    private Viatura viatura;
    private int pontuacaoHorario;
    private int classificacao;
    private boolean disponivel;
    private int kms;
    private int nrViagensRealizadas;
    //WARNING: só deixar pôr-se disponível se tiver algum carro adicionado.
    //WARNING: quando não tiver nenhum carro devolve uma viatura "simbólica" de código -1
    public Motorista(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao){
        super(email,nome,pass,morada,nascimento,posicao);
        viatura = new Viatura();
        pontuacaoHorario = 0;
        classificacao = 0;
        disponivel = false;
        kms = 0;
        nrViagensRealizadas = 0;
    }
    
    public Viatura getViatura(){
        return viatura;
    }
    
    public int getPontuacaoHorario(){
        return pontuacaoHorario;
    }
    
    public int getClassificacao(){
        return classificacao;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setViatura(Viatura v){
    }
    //TODO: kms deve incluir também deslocações do taxi até ao cliente?
    //TODO: clientes pagam a deslocação do táxi até eles?
    public int getKms(){
        return kms;
    }
    
    public int getNrViagensRealizadas(){
        return nrViagensRealizadas;
    }
}
