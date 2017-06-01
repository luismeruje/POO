
/**
 * Escreva a descrição da classe App aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
public final class App
{
    private static UMeR dados;
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
        int opcao = -1;
        boolean concluido, exit = false, resultado;
        
        //TODO: dados.loadFicheiro(); -> tem que fazer set da variável global de viagens
        dados = new UMeR();
        
        while(!exit){
            limparConsola();
            concluido = false;
            System.out.println("======= Menu =======");
            System.out.println("1 - Login Utilizador\n"
                              +"2 - Login Motorista\n"
                              +"3 - Registar Utilizador\n"
                              +"4 - Registar Motorista\n"
                              +"5 - Estatisticas\n"
                              +"6 - Sair\n");
            while(!concluido){
                System.out.print("Indique o número da operação desejada: ");
                try{
                    opcao = input.nextInt();
                    input.nextLine();
                    switch(opcao){
                        case 1:
                            loginCliente();
                            concluido = true;
                            break;
                        case 2:
                            loginMotorista();
                            concluido = true;
                            break;
                        case 3:
                            resultado = registarCliente();
                            concluido = true;
                            if(resultado)
                                System.out.println("Cliente registado com sucesso.");
                            else
                                System.out.println("ERRO: O email fornecido já se econtra registado.");
                            System.out.println("Pressione ENTER para continuar ....");
                            input.nextLine();
                            break;
                        case 4:
                            resultado = registarMotorista();
                            concluido = true;
                            if(resultado)
                                System.out.println("Motorista registado com sucesso.");
                            else
                                System.out.println("ERRO: O email fornecido já se econtra registado.");
                            System.out.println("Pressione ENTER para continuar ....");
                            input.nextLine();
                            break;
                        case 5:
                            //estatisticas();
                            concluido = true;
                            break;
                        case 6:
                            //dados.escreveFicheiro();
                            concluido = true;
                            exit = true;
                            break;
                        default:
                            System.out.println("Por favor escolha um número entre 1 e 6.");
                            break;
                    }
                }
                catch(InputMismatchException e){
                    input.nextLine();
                    System.out.println("Por favor escolha um número entre 1 e 6.");
                }
            }
        }
        //return 0
    }
    
    public static void loginCliente(){
        String email, password;
        Utilizador cliente;
        
        System.out.println("======= Login - Cliente =======");
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Password: ");
        password = input.nextLine();
        
        cliente = dados.getCliente(email,password);
        if(cliente != null){
            areaCliente(cliente);
        }
        else{
            System.out.println("Email e/ou password inválidos.");
        }
    }
    
    public static int viagem(){return 0;}
    
    public static int fimViagem(){return 0;}
    
    public static void loginMotorista(){
        String email, password;
        Motorista motorista;
        
        System.out.println("======= Login - Motorista =======");
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Password: ");
        password = input.nextLine();
        
        motorista = dados.getMotorista(email,password);
        if(motorista != null){
            areaMotorista(motorista);
        }
        else{
            System.out.println("Email e/ou password inválidos.");
        }
    }
    
    //false->se email já existe;
    //true->sucesso 
    public static boolean registarCliente(){
        String email,nome = new String(), password, morada, aux;
        Date nascimento = null;
        int x = 0,y = 0;
        boolean concluido = false, resultado = false;
        System.out.println("=======Preencha os campos abaixo=======");
        
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Nome:");
        nome = input.nextLine();
        while(nome.length() < 3){
            System.out.println("Por favor escolha um nome com pelo menos 3 caracteres");
            System.out.print("Nome: ");
            nome = input.nextLine();
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition posicao = new ParsePosition(0);
        System.out.print("Data de nascimento(dd/MM/aaaa): ");
        aux = input.nextLine();
        nascimento = formato.parse(aux,posicao);
        while(nascimento == null){
            System.out.println("Data de nascimento inválida. Tenha em atenção o formato especificado.");
            System.out.print("Data de nascimento(dd/MM/aaaa): ");
            aux = input.nextLine();
            nascimento = formato.parse(aux,posicao);
        }
        
        System.out.print("Password:");
        password = input.nextLine();
        
        System.out.print("Morada:");
        morada = input.nextLine();
        
        while(!concluido){
            try{
                System.out.print("Introduza as suas coordenadas(x y): ");
                x = input.nextInt();
                y = input.nextInt();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println("Coordenadas inválidas.Tenha em atenção o formato especificado.");
            }
            input.nextLine();
        }
        resultado = dados.adicionaCliente(email,nome,morada,nascimento,password,x,y);
        return resultado;
    }
    
    public static boolean registarMotorista(){
        String email,nome = new String(), password, morada, aux;
        Date nascimento = null;
        int x = 0,y = 0;
        boolean concluido = false, resultado = false;
        System.out.println("=======Preencha os campos abaixo=======");
        
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Nome:");
        nome = input.nextLine();
        while(nome.length() < 3){
            System.out.println("Por favor escolha um nome com pelo menos 3 caracteres");
            System.out.print("Nome: ");
            nome = input.nextLine();
        }
        
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        ParsePosition posicao = new ParsePosition(0);
        System.out.print("Data de nascimento(dd/MM/aaaa): ");
        aux = input.nextLine();
        nascimento = formato.parse(aux,posicao);
        while(nascimento == null){
            System.out.println("Data de nascimento inválida. Tenha em atenção o formato especificado.");
            System.out.print("Data de nascimento(dd/MM/aaaa): ");
            aux = input.nextLine();
            nascimento = formato.parse(aux,posicao);
        }
        
        System.out.print("Password:");
        password = input.nextLine();
        
        System.out.print("Morada:");
        morada = input.nextLine();
        
        while(!concluido){
            try{
                System.out.print("Introduza as suas coordenadas(x y): ");
                x = input.nextInt();
                y = input.nextInt();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println("Coordenadas inválidas.Tenha em atenção o formato especificado.");
            }
            input.nextLine();
        }
        resultado = dados.adicionaMotorista(email,nome,morada,nascimento,password,x,y);
        return resultado;
    
    }
    
    public static void areaCliente(Utilizador cliente){
        int opcao;
        boolean concluido, exit = false;
        while(!exit){
            System.out.println("======== Área de Cliente =======");
            System.out.println("1 - Requisitar viagem\n"
                              +"2 - Histórico de viagens\n"
                              +"3 - Terminar sessão");
            concluido = false;
            while(!concluido){
                try{
                   opcao = input.nextInt();
                   input.nextLine();
                   switch(opcao){
                       case 1:
                            requisitarViagem(cliente);
                            concluido = true;
                            break;
                       case 2:
                            historicoViagens(cliente);
                            concluido = true;
                            break;
                       case 3:
                            concluido = true;
                            exit = true;
                            break;
                       default:
                            System.out.println("Por favor escolha um número entre 1 e 2.");
                            break;
                   }
                }
                catch(InputMismatchException e){
                   System.out.println("Por favor escolha um número entre 1 e 2."); 
                }
            }
        }
    }
    
    //True-> sucesso; False -> insucesso
    public static boolean requisitarViagem(Utilizador cliente){
        return false;
    }
    
    public static void historicoViagens(Utilizador cliente){
        
    }
    //TODO: implementar com StringBuilder
    public static void areaMotorista(Motorista motorista){
        int opcao;
        boolean concluido, exit = false;
        while(!exit){
            System.out.println("================== Área de Motorista =================");
            System.out.println("####### Informações #######");
            System.out.printf("Código do veiculo em uso: %d\nKms realizados: %d\nPontuação cliente: %d\nPontuação cumprimento horário: %d\nViagens realizadas: %d\nDisponivel: %s\n"
                             ,motorista.getViatura().getCodigo(),motorista.getKms(),motorista.getClassificacao(),motorista.getPontuacaoHorario(),motorista.getNrViagensRealizadas(),motorista.getDisponivel()?"Sim":"Não");
            System.out.println("####### Menu #######");
            System.out.println("1 - Adicionar veiculo\n"
                              +"2 - Selecionar veiculo\n"
                              +"3 - Listar veiculos\n"
                              +"4 - Histórico de viagens\n"
                              +"5 - Alterar disponibilidade\n"
                              +"6 - Terminar sessão");
            concluido = false;
            while(!concluido){
                System.out.print("Indique o número da operação desejada: ");
                try{
                   opcao = input.nextInt();
                   input.nextLine();
                   switch(opcao){
                       case 1:
                            //concluido = true;
                            break;
                       case 2:
                            //concluido = true;
                            break;
                       case 3:
                            //concluido = true;
                            break;
                       case 4:
                            //concluido = true;
                            break;
                       case 5:
                            //concluido = true;
                            break;
                       case 6:
                            concluido = true;
                            exit = true;
                            break;
                       default:
                            System.out.println("Por favor escolha um número entre 1 e 2.");
                            break;
                   }
                }
                catch(InputMismatchException e){
                   System.out.println("Por favor escolha um número entre 1 e 2."); 
                   input.nextLine();
                }
            }
        }
    } 
    
    public static int estatisticas(){return 0;}
    
    private static void limparConsola(){
        try{ 
            Runtime.getRuntime().exec("clear");
        }
        catch(IOException e){
            e.getMessage();
        }
    }
}
