
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
    
    public int getCodigo(){
        return codigo;
    }
    
    public void somaKms(int kms){}
    
    public void registaViagem(Viagem v){
    }
    
    
}
