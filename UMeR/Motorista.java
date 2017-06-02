
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
public class Motorista extends Utilizador
{
    //TODO: Adicionar lista de viaturas
    private List<Viatura> viaturas;
    private int pontuacaoHorario;
    private int classificacao;
    private boolean disponivel;
    private int kms;
    private int nrViagensRealizadas;
    //WARNING: só deixar pôr-se disponível se tiver algum carro adicionado.
    //WARNING: quando não tiver nenhum carro devolve uma viatura "simbólica" de código -1
    public Motorista(){
    	super();
    	pontuacaoHorario = -1;
        classificacao = -1;
        disponivel = false;
        kms = -1;
        nrViagensRealizadas = -1;
    }
    public Motorista(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao){
        super(email,nome,pass,morada,nascimento,posicao);
        viaturas = new ArrayList<Viatura>();
        pontuacaoHorario = 0;
        classificacao = 0;
        disponivel = false;
        kms = 0;
        nrViagensRealizadas = 0;
    }
    public Motorista(Motorista m){
        super(m.getEmail(),m.getNome(),m.getPassword(),m.getMorada(),m.getNascimento(),m.getPosicao());
        viaturas = new ArrayList<Viatura>(m.getViaturas());
        pontuacaoHorario = m.getPontuacaoHorario();
        classificacao = m.getClassificacao();
        disponivel = m.getDisponivel();
        kms = m.getKms();
        nrViagensRealizadas = m.getNrViagensRealizadas();
    }
    
    //TODO:: throws ViaturaInexistenteException.
    public Viatura getViatura(int cod)throws ViaturaNaoDisponivelException{
    	Iterator<Viatura> it = viaturas.iterator();
    	Viatura v;
    	while(it.hasNext()){
    		v = it.next();
    		if (v.getCodigo() == cod) return v;
    	}
    	throw new ViaturaNaoDisponivelException("Codigo da viatura não pertence ao motorista.");
    }
    public List<Viatura> getViaturas(){
    	return new ArrayList<Viatura>(this.viaturas);
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
    public void setViatura(Viatura v){
    	viaturas.add(v);
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
}
