
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
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class Motorista extends Utilizador implements Serializable
{
    //TODO: Adicionar lista de viagens
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
    
    private Motorista(String email, String nome, String pass, String morada, Date nascimento, Posicao posicao, List<Integer> viaturasCodigos, int pontuacaoHorario, int classificacao, boolean disponivel, int kms, int nrViagensRealizadas, int viaturaEmUso){
        super(email,nome,pass,morada,nascimento,posicao);
        this.viaturasCodigos = viaturasCodigos;
        this.pontuacaoHorario = pontuacaoHorario;
        this.classificacao = classificacao;
        this.disponivel = disponivel;
        this.kms = kms;
        this.nrViagensRealizadas = nrViagensRealizadas;
        this.viaturaEmUso = viaturaEmUso;
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
        if(viaturaEmUso != -1)
            this.disponivel = disp;
        else{
            System.out.println("Por favor selecione/adicione um carro");
            System.out.println("Pressione ENTER para continuar...");
            Scanner input = new Scanner(System.in);
            input.nextLine();
            this.disponivel = false;
        }
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
    
    public ArrayList<Object> escreverFicheiro ()throws FileNotFoundException, IOException{
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(getEmail()); //0
        data.add(getNome()); //1
        data.add(getPassword()); //2
        data.add(getMorada()); //3
        data.add(getNascimento()); //4
        data.add(getPosicao().getX()); //5
        data.add(getPosicao().getY()); //6
        data.add(viaturasCodigos); //7
        data.add(pontuacaoHorario); //8
        data.add(classificacao); //9
        data.add(disponivel); //10
        data.add(kms); //11
        data.add(nrViagensRealizadas); //12
        data.add(viaturaEmUso); //13
        
        return data;
    }
    
    public static Motorista lerFicheiro(Object motorista)throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Object> data = new ArrayList<Object>();
        data = (ArrayList<Object>) motorista;
        
        String email,nome,password,morada;
        int pontuacaoHorario, classificacao, kms, nrViagensRealizadas, viaturaEmUso;
        double x, y;
        boolean disponivel;
        ArrayList<Integer> viaturasCodigos = new ArrayList<Integer>();
        Date nascimento;
        Posicao p;
        
        email = (String) data.get(0);
        nome = (String) data.get(1);
        password = (String) data.get(2);
        morada = (String) data.get(3);
        nascimento = (Date) data.get(4);
        x = (double) data.get(5);
        y = (double) data.get(6);
        viaturasCodigos = (ArrayList <Integer>)data.get(7);
        pontuacaoHorario = (int) data.get(8);
        classificacao = (int) data.get(9);
        disponivel = (boolean) data.get(10);
        kms = (int) data.get(11);
        nrViagensRealizadas = (int) data.get(12);
        viaturaEmUso = (int) data.get(13);
        
        p = new Posicao(x,y);
        return new Motorista(email,nome,password,morada,nascimento,p,viaturasCodigos, pontuacaoHorario, classificacao,disponivel,kms,nrViagensRealizadas,viaturaEmUso);
    }
}
