
/**
 * Escreva a descrição da classe Viatura aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Viatura
{
    private static int totalViaturas = 0;
    
    private String motoristaEmail;
    private int identificacao;
    private float kms;
    private float vMedia;
    private int fiabilidade;
    private float precoKm;
    private Posicao posicao;
    private List <Integer> historico;
    private int Lugares;
    
    //Nota: Corresponde a uma "viatura nula", a ser substituida quando for inserido o primeiro veiculo
    public Viatura(){
        identificacao = -1;
        kms = 0;
        vMedia = 0;
        fiabilidade = 0;
        precoKm = 0;
        posicao = new Posicao(0,0);
        historico = new ArrayList <Integer> ();
        motoristaEmail = "";
    }
    
  
    public Viatura(float vMedia, int fiabilidade, float precoKm, Posicao posicao, String email){
        this.identificacao = totalViaturas;
        totalViaturas++;
        this.kms = 0;
        this.vMedia = vMedia;
        this.fiabilidade = fiabilidade;
        this.precoKm = precoKm;
        this.posicao = posicao;
        this.historico = new ArrayList<Integer>();
        this.motoristaEmail = email;
    }
    
    public Viatura(Viatura v){
    	this.identificacao = v.getIdentificacao();
        this.kms = v.getKms();
        this.vMedia = v.getKms();
        this.fiabilidade = v.getFiabilidade();
        this.precoKm = v.getPrecoKm();
        this.posicao = v.getPos();
        this.historico = v.getHistorico();
        this.motoristaEmail = v.getMotoristaEmail();
    }
    /*
     * Gets e Sets
     */
    public int getIdentificacao(){return this.identificacao;}
    public float getKms(){return this.kms;}
    public float getVMedia(){return this.vMedia;}
    public int getFiabilidade(){return this.fiabilidade;}
    public float getPrecoKm(){return this.precoKm;}
    public Posicao getPos(){return new Posicao(this.posicao);}
    public List<Integer> getHistorico(){return new ArrayList<Integer>(this.historico);}
    public String getMotoristaEmail(){return this.motoristaEmail;}
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
    
    public void registarViagem(Viagem v){
    	this.historico.add(v.getIdentificacao());
    	somaKms(  (int) v.getDist()  );
    }
    
    public double proximidade(Posicao p){
    	return p.distancia(this.posicao);
    }
    
    /*
     * Overwrites
     */
    
    public Viatura clone(){
    	return new Viatura(this);
    }
    
    public String toString2(){
    	String s = new String("Codigo: " + this.identificacao
    						  + "\nTotal de quilómetros da viatura: " + this.kms
    						  + "\nPreço por quilómetro: " + this.precoKm
    						  + "\nPosição:"  +  this.posicao.toString()
    						  + "\nFiabilidade: " + this.fiabilidade + "\n");
    	return s;
    }
    
    public boolean equals(Object o){
    	if(o == this) return true;
    	if( o == null || o.getClass() != this.getClass()) return false;
    	Viatura v = (Viatura) o;
    	if(this.historico.size() != v.getHistorico().size()) return false;
    	Iterator<Integer> it = v.getHistorico().iterator();
    	while(it.hasNext()){
    		if(!this.historico.contains(it.next())) return false;
    	}
    	return (this.identificacao == v.getIdentificacao() &&
    			this.fiabilidade == v.getFiabilidade() &&
    			this.kms == v.getKms() &&
    			this.precoKm == v.getPrecoKm() &&
    			this.vMedia == v.getVMedia() &&
    			this.posicao.equals(v.getPos())
    			);
    }
}
