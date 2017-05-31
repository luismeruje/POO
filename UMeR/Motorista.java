
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.Date;
public class Motorista extends Utilizador
{
    private Viatura viatura;
    private int pontuacaoHorario;
    private int classificacao;
    private boolean disponivel;
    private int kms;
    //WARNING: só deixar pôr-se disponível se tiver algum carro adicionado.
    public Motorista(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao){
        super(email,nome,pass,morada,nascimento,posicao);
        pontuacaoHorario = -1;
        classificacao = -1;
        disponivel = false;
        kms = 0;
    }
}
