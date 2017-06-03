
/**
 * Escreva a descrição da classe Historico aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Viagem
{
    private static long totalViagens = 0; //-> programa começa com nr total de viagens a zero
    
    
    private long identificacao;
    private String motorista;
    private String cliente;
    private double distancia;
    private int pontuacao;
    private double custo;
    private double tempo;
    private double desvio;
    
    
    
    public static void setTotalViagens(long totalViagens){
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

    /*
     * gets e sets
     */
    public long getIdentificacao(){
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
}









































