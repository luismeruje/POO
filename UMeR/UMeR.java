
/**
 * Escreva a descrição da classe UMeR aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
public class UMeR
{
    private Map <String,Viatura> viaturas;
    private Map <String,Utilizador> clientes;
    private Map <String,Utilizador> motoristas;
    
    public UMeR(){
        viaturas = new HashMap<String,Viatura>();
        clientes = new HashMap<String,Utilizador>();
        motoristas = new HashMap<String,Utilizador>();
    }
    
    //true-> sucesso false->já existe cliente com este email na lista
    public boolean adicionaCliente(String email, String nome, String morada, Date nascimento, String password, int x, int y){
        boolean resultado;
        
        Posicao p = new Posicao(x,y);
        if(!(resultado = clientes.containsKey(email))){
            Utilizador cliente = new Utilizador(email,nome,password,morada,nascimento,p);
            clientes.put(email,cliente);
        }
        
        return !resultado;
    }
    
    public Utilizador getCliente(){return null;}
    
    public boolean adicionaMotorista(){return false;}
    
    public Utilizador getMotorista(){return null;}
    
    public boolean escreverFicheiro(){return false;}
}
