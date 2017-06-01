
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
    //TODO: mudar tipo dos valores do map de motoristas para Motorista?
    private Map <String,Viatura> viaturas;
    private Map <String,Utilizador> clientes;
    private Map <String,Motorista> motoristas;
    
    public UMeR(){
        viaturas = new HashMap<String,Viatura>();
        clientes = new HashMap<String,Utilizador>();
        motoristas = new HashMap<String,Motorista>();
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
    
    //Devolve null se o cliente não está registado ou se a password está errada.
    //TODO: throw exception loginInvalido
    public Utilizador getCliente(String email, String password){
        Utilizador cliente = clientes.get(email);
        if(cliente != null && !password.equals(cliente.getPassword()))
            cliente = null; 
        return cliente;
    }
    
    public boolean adicionaMotorista(String email, String nome, String morada, Date nascimento, String password, int x, int y){
        boolean resultado;
        
        Posicao p = new Posicao(x,y);
        if(!(resultado = motoristas.containsKey(email))){
            Motorista motorista = new Motorista(email,nome,password,morada,nascimento,p);
            motoristas.put(email,motorista);
        }
        
        return !resultado;
    }
    
    //TODO: throw exception motoristaInexistente
    public Motorista getMotorista(String email, String password){
        Motorista motorista = motoristas.get(email);
        if(motorista != null && !password.equals(motorista.getPassword()))
            motorista = null; //Motorista é posto a NULL se a password estiver errada.
        return motorista;
    }
    
    public boolean escreverFicheiro(){return false;}
}
