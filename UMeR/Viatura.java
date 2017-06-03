
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
    
    private Motorista motorista;
    private int codigo;
    private float kms;
    private float vMedia;
    private int fiabilidade;
    private float precoKm;
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
        motorista = new Motorista();
    }
    
    public Viatura(float vMedia, int fiabilidade, float precoKm, Posicao posicao){
        this.codigo = totalViaturas;
        totalViaturas++;
        this.kms = 0;
        this.vMedia = vMedia;
        this.fiabilidade = fiabilidade;
        this.precoKm = precoKm;
        this.posicao = posicao;
        this.historico = new ArrayList<Viagem>();
        this.motorista = new Motorista();
    }
    public Viatura(float vMedia, int fiabilidade, float precoKm, Posicao posicao, Motorista m){
        this.codigo = totalViaturas;
        totalViaturas++;
        this.kms = 0;
        this.vMedia = vMedia;
        this.fiabilidade = fiabilidade;
        this.precoKm = precoKm;
        this.posicao = posicao;
        this.historico = new ArrayList<Viagem>();
        this.motorista = new Motorista(m);
    }
    
    public Viatura(Viatura v){
    	this.codigo = v.getCodigo();
        this.kms = v.getKms();
        this.vMedia = v.getKms();
        this.fiabilidade = v.getFiabilidade();
        this.precoKm = v.getPrecoKm();
        this.posicao = v.getPos();
        this.historico = v.getHistorico();
        this.motorista = v.getMotorista();
    }
    /*
     * Gets e Sets
     */
    public int getCodigo(){return this.codigo;}
    public float getKms(){return this.kms;}
    public float getVMedia(){return this.vMedia;}
    public int getFiabilidade(){return this.fiabilidade;}
    public float getPrecoKm(){return this.precoKm;}
    public Posicao getPos(){return new Posicao(this.posicao);}
    public List<Viagem> getHistorico(){return new ArrayList<Viagem>(this.historico);}
    public Motorista getMotorista(){return this.motorista.clone();}
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
    	this.historico.add(v.clone());
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
    	String s = new String("Codigo: " + this.codigo
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
    	Iterator<Viagem> it = v.getHistorico().iterator();
    	while(it.hasNext()){
    		if(!this.historico.contains(it.next())) return false;
    	}
    	return (this.codigo == v.getCodigo() &&
    			this.fiabilidade == v.getFiabilidade() &&
    			this.kms == v.getKms() &&
    			this.precoKm == v.getPrecoKm() &&
    			this.vMedia == v.getVMedia() &&
    			this.posicao.equals(v.getPos())
    			);
    }
}
