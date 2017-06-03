
/**
 * Escreva a descrição da classe Viatura aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Viatura
{
    private static int totalViaturas = 0;
    
    private String motoristaEmail;
    private int identificacao;
    private double kms;
    private float vMedia;
    private int fiabilidade;
    private float precoKm;
    private Posicao posicao;
    private List <Integer> historico;
    private int lugares;
    
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
    
    private Viatura(String motoristaEmail, int identificacao,double kms,float vMedia,int fiabilidade,float precoKm,Posicao p,List<Integer>historico,int lugares){
        this.identificacao = identificacao;
        this.kms = kms;
        this.vMedia = vMedia;
        this.fiabilidade = fiabilidade;
        this.precoKm = precoKm;
        posicao = p;
        this.historico = historico;
        this.lugares = lugares;
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
        totalViaturas++;
    }
    
    public Viatura(Viatura v){
        this.identificacao = v.getIdentificacao();
        this.kms = v.getKms();
        this.vMedia = v.getVMedia();
        this.fiabilidade = v.getFiabilidade();
        this.precoKm = v.getPrecoKm();
        this.posicao = v.getPos();
        this.historico = v.getHistorico();
        this.motoristaEmail = v.getMotoristaEmail();
        totalViaturas++;
    }
    /*
     * Gets e Sets
     */
    public int getIdentificacao(){return this.identificacao;}
    public double getKms(){return this.kms;}
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
    public void somaKms(double kms){
        this.kms += kms;
    }
    
    public void registarViagem(Integer identificacao, double distancia){
        this.historico.add(identificacao);
        somaKms(distancia);
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
                              + "\n");
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
    
    public ArrayList<Object> escreverFicheiro ()throws FileNotFoundException, IOException{
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(motoristaEmail); //0
        data.add(identificacao); //1
        data.add(kms); //2
        data.add(vMedia); //3
        data.add(fiabilidade); //4
        data.add(precoKm); //5
        data.add(posicao.getX()); //6
        data.add(posicao.getY()); //7
        data.add(historico); //8
        data.add(lugares); //9
        return data;
    }
    
    public static Viatura lerFicheiro(Object viatura)throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Object> data = new ArrayList<Object>();
        data = (ArrayList<Object>) viatura;
        
        String motoristaEmail;
        int identificacao,lugares,fiabilidade;
        double kms,x,y;
        float vMedia, precoKm;
        ArrayList<Integer>historico;
        Posicao p;
        
        motoristaEmail = (String) data.get(0);
        identificacao = (int) data.get(1);
        kms = (double) data.get(2);
        vMedia = (float) data.get(3);
        fiabilidade = (int) data.get(4);
        precoKm = (float) data.get(5);
        x = (double) data.get(6);
        y = (double) data.get(7);
        historico = (ArrayList<Integer>) data.get(8);
        lugares = (int) data.get(9);
        
        p = new Posicao(x,y);
        return new Viatura(motoristaEmail,identificacao,kms,vMedia,fiabilidade,precoKm,p,historico,lugares);
    }
    
    
    
}
