
/**
 * Escreva a descrição da classe UMeR aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.ArrayList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
public class UMeR
{
    //TODO: mudar tipo dos valores do map de motoristas para Motorista?
    private Map <Integer,Viatura> viaturas;
    private Map <String,Utilizador> clientes;
    private Map <String,Motorista> motoristas;
    
    public UMeR(){
        viaturas = new HashMap<Integer,Viatura>();
        clientes = new HashMap<String,Utilizador>();
        motoristas = new HashMap<String,Motorista>();
    }
    
    //true-> sucesso false->já existe cliente com este email na lista
    public boolean adicionaCliente(String email, String nome, String morada, Date nascimento, String password, Posicao p){
        boolean resultado;
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
    
    public boolean adicionaMotorista(String email, String nome, String morada, Date nascimento, String password,Posicao p){
        boolean resultado;
      
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
    
    public Motorista getMotorista(String email){
        Motorista motorista = motoristas.get(email);
        return motorista;
    }
    
    public Viatura addViatura(Viatura v){
        return this.viaturas.put(v.getIdentificacao(), v);
    }
    public Viatura getViatura(int codigo){
        Integer cod = new Integer(codigo);
        return this.viaturas.get(cod);
    }
    public Viatura getViaturaMaisProx(Posicao p){
        Iterator<Viatura> it = this.viaturas.values().iterator();
        Viatura v = new Viatura();
        Viatura car = new Viatura();
        double comp = Double.MAX_VALUE;
        while(it.hasNext()){
            car = it.next();
            if(car.getPos().distancia(p) < comp){
                v = car;
                comp = car.getPos().distancia(p);
            }
        }
        return v;
    }
    
    public List<Viatura> getViaturas(List<Integer> viaturasCodigos){
        Iterator<Integer> it = viaturasCodigos.iterator();
        List <Viatura> cópiaViaturas = new ArrayList<Viatura>();
        int codigo;
        while(it.hasNext()){
            codigo = it.next();
            cópiaViaturas.add(viaturas.get(codigo));
        }
        return cópiaViaturas;
    }
    
    public void escreverFicheiro(){
        Scanner input = new Scanner(System.in);
        try{
            escreveMotoristas();
            escreveClientes();
            //escreveViaturas();
        }
        catch(FileNotFoundException e){
        }
        catch(IOException e){
           System.out.println("Ocorreu um erro na escrita da informação dos motoristas, a informação não será guardada.");
           System.out.print("Pressione ENTER para continuar...");
           input.nextLine();
        }
    }
    
    public void lerFicheiro(){
        Scanner input = new Scanner(System.in);
        try{
            lerMotoristas();
            lerClientes();
            //lerViaturas();
        }
        catch(FileNotFoundException e){
           System.out.println("Um ou mais ficheiros de informação não encontrados.");
           System.out.print("Pressione ENTER para continuar...");
           input.nextLine();
        }
        catch(IOException e){
           System.out.println("Ocorreu um erro na leitura da informação.");
           System.out.print("Pressione ENTER para continuar...");
           input.nextLine();
        }
        catch(ClassNotFoundException e){
           System.out.println("Um ou mais ficheiros de dados corrompidos.");
           System.out.print("Pressione ENTER para continuar...");
           input.nextLine();
        }
    }
    
    public void escreveMotoristas() throws FileNotFoundException, IOException{
        FileOutputStream ficheiro = new FileOutputStream("motoristaData.ser");
        ObjectOutputStream os = new ObjectOutputStream(ficheiro);
        ArrayList<Object> data = new ArrayList<Object>();
        
        Iterator<Motorista> it = motoristas.values().iterator();
        Motorista m = null;
        while(it.hasNext()){
            m = it.next();
            data.add(m.escreverFicheiro());
        }
        os.writeObject(data);
        os.close();
        ficheiro.close();
    }
    
    public void escreveClientes() throws FileNotFoundException, IOException{
        FileOutputStream ficheiro = new FileOutputStream("clienteData.ser");
        ObjectOutputStream os = new ObjectOutputStream(ficheiro);
        ArrayList<Object> data = new ArrayList<Object>();
        
        Iterator<Utilizador> it = clientes.values().iterator();
        Utilizador c = null;
        while(it.hasNext()){
            c = it.next();
            data.add(c.escreverFicheiro());
        }
        os.writeObject(data);
        os.close();
        ficheiro.close();
    }
    
    //Link: https://www.youtube.com/watch?v=-WnNv6LoioQ
    public void lerMotoristas()throws FileNotFoundException, IOException,ClassNotFoundException{
        FileInputStream ficheiro = new FileInputStream("motoristaData.ser");
        ObjectInputStream is = new ObjectInputStream(ficheiro);
        ArrayList<Object> data = new ArrayList<Object>();
        
        data = (ArrayList<Object>)is.readObject();
        
        is.close();
        ficheiro.close();
        
        Object o = null;
        Motorista m = null;
        Iterator<Object> it = data.iterator();
        while(it.hasNext()){
            o = it.next();
            m = Motorista.lerFicheiro(o);
            motoristas.put(m.getEmail(),m);
        }
    }
    //TODO:passar cliente para a sua própria classe.
    public void lerClientes()throws FileNotFoundException, IOException,ClassNotFoundException{
        FileInputStream ficheiro = new FileInputStream("clienteData.ser");
        ObjectInputStream is = new ObjectInputStream(ficheiro);
        ArrayList<Object> data = new ArrayList<Object>();
        
        data = (ArrayList<Object>)is.readObject();
        
        is.close();
        ficheiro.close();
        
        Object o = null;
        Utilizador c = null;
        Iterator<Object> it = data.iterator();
        while(it.hasNext()){
            o = it.next();
            c = Utilizador.lerFicheiro(o);
            clientes.put(c.getEmail(),c);
        }
    }
}
