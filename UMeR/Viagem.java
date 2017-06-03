
/**
 * Escreva a descrição da classe Historico aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Viagem
{
    private static int totalViagens = 0; //-> programa começa com nr total de viagens a zero
    
    
    private int identificacao;
    private String motorista;
    private String cliente;
    private double distancia;
    private int pontuacao;
    private double custo;
    private double tempo;
    private double desvio;
    
    
    
    public static void setTotalViagens(int totalViagens){
        Viagem.totalViagens = totalViagens;
    }
    
    
    
    /*
     * Construtores
     */
    public Viagem(){
        this.motorista = "";
        this.cliente = "";
        this.distancia = 0;
        this.pontuacao = 0;
        this.custo = 0;
        this.tempo = 0;
        this.desvio = 0;
    }
    public Viagem(double d, int p, double c, double t, double dv, String mot, String cliente){
        this.distancia = d;
        this.pontuacao = p;
        this.custo = c;
        this.tempo = t;
        this.desvio = dv;
        this.motorista = mot;
        this.cliente = cliente;
        this.identificacao = totalViagens;
        totalViagens ++;
    }
    
    public Viagem(Viagem v){
        this.distancia = v.getDist();
        this.pontuacao = v.getPont();
        this.custo = v.getCusto();
        this.tempo = v.getTempo();
        this.desvio = v.getDesvio();
        this.motorista = v.getMot();
        this.identificacao = totalViagens;
        totalViagens ++;
    }
    
    public Viagem(int identificacao,String motorista,String cliente,double distancia,int pontuacao,double custo,double tempo,double desvio){
        this.identificacao = identificacao;
        this.motorista = motorista;
        this.cliente = cliente;
        this.distancia = distancia;
        this.pontuacao = pontuacao;
        this.custo = custo;
        this.tempo = tempo;
        this.desvio = desvio;
    }

    /*
     * gets e sets
     */
    public int getIdentificacao(){
        return this.identificacao;
    }
     
    public double getDist(){
        return this.distancia;
    }
    public int getPont(){
        return this.pontuacao;
    }
    public double getCusto(){
        return this.custo;
    }
    public double getTempo(){
        return this.tempo;
    }
    public double getDesvio(){
        return this.desvio;
    }
    public String getMot(){
        return this.motorista;
    }
    public String getCliente(){
        return this.cliente;
    }
    public long getTotalViagens(){
        return Viagem.totalViagens;
    }
    
    public void setPont(int pontos){
        this.pontuacao = pontos;
    }
    
    /*
     * Overwrites:
     * Clone, toString, equals.
     */
    public Viagem clone(){
        return new Viagem(this);
    }
    
    public String toString2(){
        return ("====Viagem====\n Distancia: " + distancia + ".\n Pontuação: " + pontuacao + ".\n Custo: " + custo + ".\n Tempo: "+ tempo + ".\n Desvio: " + desvio + ".\n");
    }
    
    public boolean equals(Object o){
       if(o == this)
            return true;
       if(o == null || this.getClass() != o.getClass())
            return false;
       Viagem v = (Viagem) o;
       if(this.identificacao == v.getIdentificacao() ) return true;
       return false;
    }
    public ArrayList<Object> escreverFicheiro ()throws FileNotFoundException, IOException{
        ArrayList<Object> data = new ArrayList<Object>();
        data.add(identificacao); //0
        data.add(motorista); //1
        data.add(cliente); //2
        data.add(distancia); //3
        data.add(pontuacao); //4
        data.add(custo); //5
        data.add(tempo); //6
        data.add(desvio); //7
        return data;
    }
    
    public static Viagem lerFicheiro(Object viagem)throws FileNotFoundException, IOException, ClassNotFoundException{
        ArrayList<Object> data = new ArrayList<Object>();
        data = (ArrayList<Object>) viagem;
        
        int identificacao, pontuacao;
        String motorista, cliente;
        double distancia, custo, tempo, desvio;
        
        identificacao = (int) data.get(0);
        motorista = (String) data.get(1);
        cliente = (String) data.get(2);
        distancia = (double) data.get(3);
        pontuacao = (int) data.get(4);
        custo = (double) data.get(5);
        tempo = (double) data.get(6);
        desvio = (double)data.get(7);
        
        return new Viagem(identificacao,motorista,cliente,distancia,pontuacao,custo,tempo,desvio);
    }
}