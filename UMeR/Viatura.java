
/**
 * Escreva a descrição da classe Viatura aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.ArrayList;
public class Viatura
{
    private static int totalViaturas;
    
    private int codigo;
    private int kms;
    private int vMedia;
    private int fiabilidade;
    private int precoKm;
    private Posicao posicao;
    private List <Viagem> historico;
    
    //Nota: Corresponde a uma "viatura nula", a ser substituida quando for inserido o primeiro veiculo
    public Viatura(){
        codigo = -1;
        kms = 0;
        vMedia = 0;
        fiabilidade = 0;
        precoKm = 0;
        posicao = new Posicao(0,0);
        historico = new ArrayList <Viagem> ();
    }
    
    public Viatura(int vMedia, int fiabilidade, int precoKm, Posicao posicao){
        this.codigo = totalViaturas;
        totalViaturas++;
        this.kms = 0;
        this.vMedia = vMedia;
        this.fiabilidade = fiabilidade;
        this.precoKm = precoKm;
        this.posicao = posicao;
        this.historico = new ArrayList<Viagem>();
    }
    
    public Viatura(Viatura v){
    	this.codigo = v.getCodigo();
        this.kms = v.getKms();
        this.vMedia = v.getKms();
        this.fiabilidade = v.getFiabilidade();
        this.precoKm = v.getPrecoKm();
        this.posicao = v.getPos();
        this.historico = v.getHistorico();
    }
    /*
     * Gets e Sets
     */
    public int getCodigo(){return this.codigo;}
    public int getKms(){return this.kms;}
    public int getVMedia(){return this.vMedia;}
    public int getFiabilidade(){return this.fiabilidade;}
    public int getPrecoKm(){return this.precoKm;}
    public Posicao getPos(){return new Posicao(this.posicao);}
    public List<Viagem> getHistorico(){return new ArrayList<Viagem>(this.historico);}
    public void setVMedia(int vm){
    	this.vMedia = vm;
    }
    public void setFiabilidade(int f){
    	this.fiabilidade = f;
    }
    public void setPos(Posicao p){
    	this.posicao.move(p.getX(), p.getY());
    }
    
    
    
    /*
     * Metodos da classe
     */
    public void somaKms(int kms){
    	this.kms += kms;
    }
    
    public void registaViagem(Viagem v){
    	this.historico.add(v.clone());
    	somaKms(v.getDist());
    }
    
    /*
     * Overwrites
     */
    
}
