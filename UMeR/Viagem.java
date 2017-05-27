
/**
 * Escreva a descrição da classe Historico aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
public class Viagem
{
    private int distancia;
    private int pontuacao;
    private int custo;
    private int tempo;
    private int desvio;
    /*
     * Construtores
     */
    public Viagem(){
        this.distancia = 0;
        this.pontuacao = 0;
        this.custo = 0;
        this.tempo = 0;
        this.desvio = 0;
    }
    public Viagem(int d, int p, int c, int t, int dv){
        this.distancia = d;
        this.pontuacao = p;
        this.custo = c;
        this.tempo = t;
        this.desvio = dv;
    }
    public Viagem(Viagem v){
        this.distancia = v.getDist();
        this.pontuacao = v.getPont();
        this.custo = v.getCusto();
        this.tempo = v.getTempo();
        this.desvio = v.getDesvio();
    }

    /*
     * gets e sets(?)
     */
    public int getDist(){
        return this.distancia;
    }
    public int getPont(){
        return this.pontuacao;
    }
    public int getCusto(){
        return this.custo;
    }
    public int getTempo(){
        return this.tempo;
    }
    public int getDesvio(){
        return this.desvio;
    }
    
    /*
     * Overwrites:
     * Clone, toString, equals.
     */
    public Viagem clone(){
        return new Viagem(distancia, pontuacao, custo, tempo, desvio);
    }
    
    public String toString(){
        return ("Viagem:\n Distancia: " + distancia + ".\n Pontuação: " + pontuacao + ".\n Custo: " + custo + ".\n Tempo: "+ tempo + ".\n Desvio: " + desvio + ".\n");
    }
    
    public boolean equals(Object o){
       if(o == this)
            return true;
       if(o == null || this.getClass() != o.getClass())
            return false;
       Viagem v = (Viagem) o;
       if(this.distancia == v.getDist() && this.pontuacao == v.getPont() && this.custo == v.getCusto() && this.tempo == v.getTempo() && this.desvio == v.getDesvio()) return true;
       
       return false;
    }
}









































