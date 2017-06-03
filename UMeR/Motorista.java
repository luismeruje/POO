
/**
 * Escreva a descrição da classe Motorista aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Motorista extends Utilizador
{
    //TODO: Adicionar lista de viaturas
    private List<Integer> viaturasCodigos;
    private int pontuacaoHorario;
    private int classificacao;
    private boolean disponivel;
    private int kms;
    private int nrViagensRealizadas;
    private int viaturaEmUso;
    //WARNING: só deixar pôr-se disponível se tiver algum carro adicionado.
    //WARNING: quando não tiver nenhum carro devolve uma viatura "simbólica" de código -1
    public Motorista(){
        super();
        pontuacaoHorario = -1;
        classificacao = -1;
        disponivel = false;
        kms = -1;
        nrViagensRealizadas = -1;
        viaturasCodigos = new ArrayList<Integer>();
        viaturaEmUso = -1;
    }
    
    public Motorista(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao){
        super(email,nome,pass,morada,nascimento,posicao);
        viaturasCodigos = new ArrayList<Integer>();
        pontuacaoHorario = 0;
        classificacao = 0;
        disponivel = false;
        kms = 0;
        nrViagensRealizadas = 0;
        viaturaEmUso = -1;
    }
    
    public Motorista(Motorista m){
        super(m.getEmail(),m.getNome(),m.getPassword(),m.getMorada(),m.getNascimento(),m.getPosicao());
        viaturasCodigos = new ArrayList<Integer>(m.getViaturasCodigos());
        pontuacaoHorario = m.getPontuacaoHorario();
        classificacao = m.getClassificacao();
        disponivel = m.getDisponivel();
        kms = m.getKms();
        nrViagensRealizadas = m.getNrViagensRealizadas();
    }
    
    public List<Integer> getViaturasCodigos(){
        return new ArrayList<Integer> (viaturasCodigos);
    }
    //TODO: passar a clone e meter métodos para alterarkms de viatura e outros, como viagem..
    public Integer getViaturaEmUso()throws ViaturaNaoDisponivelException{
        if(viaturaEmUso != -1){
            return viaturaEmUso;
        }
        throw new ViaturaNaoDisponivelException("ERRO: Não existe nenhuma viatura selecionada.");
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
    public void setDisponibilidade(boolean disp){
        this.disponivel = disp;
    }
    public void addViatura(Integer codigo){
        viaturasCodigos.add(codigo);
    }
    //TODO: kms deve incluir também deslocações do taxi até ao cliente?
    //TODO: clientes pagam a deslocação do táxi até eles?
    
    
    public int getKms(){
        return kms;
    }
    
    public int getNrViagensRealizadas(){
        return nrViagensRealizadas;
    }
    
    public Motorista clone(){
        return new Motorista(this);
    }
    
    public void setViaturaEmUso(Integer cod)throws ViaturaNaoDisponivelException{
        Iterator<Integer> it = viaturasCodigos.iterator();
        int codigo;
        boolean concluido = false;
        while(it.hasNext() && !concluido){
            codigo = it.next();
            if (codigo == cod){
                viaturaEmUso = cod;
                concluido = true;
            }
        }
        if(concluido == false){
            throw new ViaturaNaoDisponivelException("ERRO: Codigo da viatura não pertence ao motorista");
        }
    }
}
