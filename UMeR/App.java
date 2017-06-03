
/**
 * Escreva a descrição da classe App aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Scanner;

import javax.print.DocFlavor.URL;

import java.util.InputMismatchException;
import java.util.Random;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

//TODO:: TEMPO!
//TODO: Encapsulamento, motorista e viagem devolvem as próprias referências
public final class App
{
    private static UMeR dados;
    private static Scanner input = new Scanner(System.in);
    
    private static int lerNumeroInt(String s, String s2){
        boolean concluido = false;
        int num = 0;
        while(!concluido){
            try{
                System.out.printf("%s",s);
                num = input.nextInt();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println(s2);
            }
            input.nextLine();
        }
        return num;
    }
    
    private static float lerNumeroFloat(String s, String s2){
        boolean concluido = false;
        float num = 0;
        while(!concluido){
            try{
                System.out.printf("%s",s);
                num = input.nextFloat();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println(s2);
            }
            input.nextLine();
        }
        return num;
    }
    
    private static double lerNumeroDouble(String s, String s2){
        boolean concluido = false;
        double num = 0;
        while(!concluido){
            try{
                System.out.printf("%s",s);
                num = input.nextDouble();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println(s2);
            }
            input.nextLine();
        }
        return num;
    }
    
    private static Posicao lerCoordenadas(){
        double x = 0, y = 0;
        Posicao p;
        boolean concluido = false;
        
        while(!concluido){
            try{
                System.out.print("Introduza as suas coordenadas(x y): ");
                x = input.nextDouble();
                y = input.nextDouble();
                concluido = true;
            }
            catch(InputMismatchException e){
                System.out.println("Coordenadas inválidas.Tenha em atenção o formato especificado.");
            }
            input.nextLine();
        }
        return p = new Posicao(x,y);
    }
    
    public static void main(String[] args){
        int opcao = -1;
        boolean exit = false, resultado;
        
        //TODO: dados.loadFicheiro(); -> tem que fazer set da variável global de viagens
        dados = new UMeR(); 
        //carregarDados();
        
        while(!exit){
            limparConsola();
            System.out.println("======= Menu =======");
            System.out.println("1 - Login Utilizador\n"
                              +"2 - Login Motorista\n"
                              +"3 - Registar Utilizador\n"
                              +"4 - Registar Motorista\n"
                              +"5 - Estatisticas\n"
                              +"6 - Sair\n");

            opcao = lerNumeroInt("Indique o número da operação desejada: ","Por favor escolha um número entre 1 e 6.");
            switch(opcao){
                case 1:
                      loginCliente();
                      break;
                case 2:
                      loginMotorista();
                      break;
                case 3:
                      resultado = registarCliente();
                      if(resultado)
                            System.out.println("Cliente registado com sucesso.");
                      else
                            System.out.println("ERRO: O email fornecido já se econtra registado.");
                      System.out.println("Pressione ENTER para continuar ....");
                      input.nextLine();
                      break;
                case 4:
                      resultado = registarMotorista();
                      if(resultado)
                           System.out.println("Motorista registado com sucesso.");
                      else
                           System.out.println("ERRO: O email fornecido já se econtra registado.");
                      System.out.println("Pressione ENTER para continuar ....");
                      input.nextLine();
                      break;
                case 5:
                      //estatisticas();
                      break;
                case 6:
                      //dados.escreveFicheiro();
                      //salvarDados();
                      exit = true;
                      break;
                default:
                      System.out.println("Por favor escolha um número entre 1 e 6.");
                      break;
            }
        }
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
    //TODO: implementar exception no getMotorista
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
        Posicao p;
        boolean concluido = false, resultado = false;
        System.out.println("=======Preencha os campos abaixo=======");
        
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Nome: ");
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
        
        System.out.print("Password: ");
        password = input.nextLine();
        
        System.out.print("Morada: ");
        morada = input.nextLine();
        
        p = lerCoordenadas();
        resultado = dados.adicionaCliente(email,nome,morada,nascimento,password,p);
        return resultado;
    }
    
    public static boolean registarMotorista(){
        String email,nome = new String(), password, morada, aux;
        Date nascimento = null;
        Posicao p;
        boolean concluido = false, resultado = false;
        System.out.println("=======Preencha os campos abaixo=======");
        
        System.out.print("Email: ");
        email = input.nextLine();
        while(!email.contains("@")){
            System.out.println("Por favor insira um email válido.");
            System.out.print("Email: ");
            email = input.nextLine();
        }
        
        System.out.print("Nome: ");
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
        
        System.out.print("Password: ");
        password = input.nextLine();
        
        System.out.print("Morada: ");
        morada = input.nextLine();
        
        p = lerCoordenadas();
        resultado = dados.adicionaMotorista(email,nome,morada,nascimento,password,p);
        return resultado;
    
    }
    
    public static void areaCliente(Utilizador cliente){
        int opcao;
        boolean exit = false;
        
        while(!exit){
            System.out.println("======== Área de Cliente =======");
            System.out.println("1 - Requisitar viagem\n"
                              +"2 - Histórico de viagens\n"
                              +"3 - Terminar sessão");
             
            opcao = lerNumeroInt("Indique o número da operação desejada","Por favor escolha um número entre 1 e 3.");
            switch(opcao){
                case 1:
                    requisitarViagem(cliente);
                    break;
                case 2:
                    historicoViagens(cliente);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Por favor escolha um número entre 1 e 3.");
                    break;
            }
        }
    }
    
    //True-> sucesso; False -> insucesso
    public static boolean requisitarViagem(Utilizador cliente){
        int opcao;
        boolean concluido,done = false, exit = false;
        Viatura req  = new Viatura();
        Posicao end;
        double dist, tempo, preco, desvio;
        int pont = 101;
        while(!exit){
            System.out.println("================== Requisitar Viagem =================");
            System.out.println("####### Menu #######");
            System.out.println("1 - Requisitar Taxi mais próximo\n"
                              +"2 - Requisitar Taxi especifico\n"
                              +"3 - Reservar Taxi\n"
                              +"4 - Voltar\n");
            concluido = false;
            while(!concluido){
                System.out.print("Indique o número da operação desejada: ");
                try{
                   opcao = input.nextInt();
                   input.nextLine();
                   switch(opcao){
                       case 1:
                            System.out.println("Insira as coordenadas para onde se quer deslocar (x y): ");
                            end = new Posicao(input.nextDouble(),input.nextDouble());
                            dist = end.distancia(cliente.getPosicao());
                            req  = dados.getViaturaMaisProx(cliente.getPosicao());
                            System.out.println("O veiculo mais próxmo é:" + req.toString());
                            req.getMotorista().setDisponibilidade(false);
                            req.getPos().move(end.getX(),end.getY());
                            req.getMotorista().getPosicao().move(end.getX(),end.getY());

                            preco = (  dist * req.getPrecoKm()  );
                            desvio = 0;
                            tempo = (  (dist*100)/req.getVMedia() );
                            while(pont<0 || pont >100){
                                System.out.println("Classifique a sua viagem de 0 a 100:");
                                pont = input.nextInt();
                            }
                            cliente.registarViagem(new Viagem(dist, pont, tempo, preco, desvio, req.getMotorista().getNome(), cliente.getNome()));
                            req.registarViagem(new Viagem(dist, pont, tempo, preco, desvio, req.getMotorista().getNome(), cliente.getNome()));
                            
                            done = true;
                            concluido = true;
                            break;
                       case 2:
                            System.out.println("Insira o código do veiculo pretendido:");
                            int cod = input.nextInt();
                            req = dados.getViatura(cod);
                            System.out.println("Insira as coordenadas para onde se quer deslocar (x y): ");
                            end = new Posicao(input.nextDouble(),input.nextDouble());
                            dist = end.distancia(cliente.getPosicao());
                            req.getMotorista().setDisponibilidade(false);
                            req.getPos().move(end.getX(),end.getY());
                            req.getMotorista().getPosicao().move(end.getX(),end.getY());
                            
                            preco = (  dist * req.getPrecoKm()  );
                            desvio = 0;
                            tempo = (  (dist*100)/req.getVMedia() );
                            while(pont<0 || pont >100){
                                System.out.println("Classifique a sua viagem de 0 a 100:");
                                pont = input.nextInt();
                            }
                            cliente.registarViagem(new Viagem(dist, pont, tempo, preco, desvio, req.getMotorista().getNome(), cliente.getNome()));
                            req.registarViagem(new Viagem(dist, pont, tempo, preco, desvio, req.getMotorista().getNome(), cliente.getNome()));
                            done = true;
                            System.out.println("Pressione ENTER para continuar");
                            input.nextLine();
                            concluido = true;
                            break;
                       case 3:
                            concluido = true;
                            break;
                       case 4:
                            concluido = true;
                            exit = true;
                            break;
                       default:
                            System.out.println("Por favor escolha um número entre 1 e 4.");
                            break;
                   }
                   }catch(InputMismatchException e){
                       System.out.println("Por favor escolha um número entre 1 e 4.");
                   }
        
            }
        }
        return done;
    }
    
    public static void historicoViagens(Utilizador cliente){
        int opcao;
        boolean concluido,done = false, exit = false;
        while(!exit){
            System.out.println("================== Histórico de Viagens =================");
            System.out.println("####### Menu #######");
            System.out.println("1 - Vizualizar histórico\n"
                              +"2 - Vizualizar viagem\n"
                              +"3 - Classificar viagem\n"
                              +"4 - Voltar\n");
            concluido = false;
            while(!concluido){
                System.out.print("Indique o número da operação desejada: ");
                try{
                   opcao = input.nextInt();
                   input.nextLine();
                   switch(opcao){
                       case 1:
                            concluido = true;
                            break;
                       case 2:
                            System.out.println("Pressione ENTER para continuar");
                            input.nextLine();
                            concluido = true;
                            break;
                       case 3:
                            concluido = true;
                            break;
                       case 4:
                            concluido = true;
                            exit = true;
                            break;
                       default:
                            System.out.println("Por favor escolha um número entre 1 e 4.");
                            break;
                   }
                   }catch(InputMismatchException e){
                       System.out.println("Por favor escolha um número entre 1 e 4.");
                   }
        
            }
        }
    }
    //TODO: implementar com StringBuilder
    public static void areaMotorista(Motorista motorista){
        int opcao;
        boolean selecionado = false, exit = false;
        Viatura v = new Viatura();
        while(!exit){
            System.out.println("\n================== Área de Motorista =================");
            System.out.println("\n####### Informações #######");
            
            System.out.print("Veiculo em uso: ");
            try{
                System.out.printf("%d\n",motorista.getViaturaEmUso().getCodigo());
            }
            catch(ViaturaNaoDisponivelException e){
                System.out.println("-1");
            }
            System.out.printf("Quilómetros realizados pelo motorista: %d\nPontuação cliente: %d\nPontuação cumprimento horário: %d\nViagens realizadas: %d\nDisponivel: %s\n"
                             ,motorista.getKms(),motorista.getClassificacao(),motorista.getPontuacaoHorario()
                             ,motorista.getNrViagensRealizadas(),motorista.getDisponivel()?"Sim":"Não");
                             
    
            System.out.println("\n####### Menu #######");
            System.out.println("1 - Adicionar veiculo\n"
                              +"2 - Selecionar veiculo\n"
                              +"3 - Listar veiculos\n"
                              +"4 - Histórico de viagens\n"
                              +"5 - Alterar disponibilidade\n"
                              +"6 - Terminar sessão");

            opcao = lerNumeroInt("Indique o número da operação desejada: ", "Por favor escolha um número entre 1 e 6.\n");
            switch(opcao){
                case 1:
                    adicionarViatura(motorista);
                    break;
                case 2:
                    selecionarViatura(motorista);
                    break;
                case 3:
                    imprimirViaturas(motorista);
                    break;
                case 4:
                    imprimirViagens(motorista);
                    break;
                case 5:
                     motorista.setDisponibilidade(!motorista.getDisponivel());
                     break;
                case 6:
                     exit = true;
                     break;
                default:
                     System.out.println("Por favor escolha um número entre 1 e 6.");
                     break;
            }
        }
    } 
    
    public static void adicionarViatura(Motorista motorista){
        float vm = 0, pkm = 0;
        int fiabilidade = 0;
        double x = 0, y = 0;
        Posicao p;
        
        System.out.println("=====Preencha os campos abaixo=====");
        
        vm = lerNumeroFloat("Velocidade Média: ", "");
        
        Random r = new Random();
        fiabilidade = r.nextInt(101);
        
        pkm = lerNumeroFloat("Preço por quilómetro: ","");
        
        p = lerCoordenadas();
        
        Viatura novo = new Viatura(vm,fiabilidade,pkm,p);
        motorista.setViatura(novo);
        dados.addViatura(novo);      
    }
    
    
    public static void selecionarViatura(Motorista motorista){
       int codigo;
       boolean selecionado = false;
       Viatura v;
       codigo = lerNumeroInt("Escreva o codigo da viatura desejada:","");
       try{
          v = motorista.getViatura(codigo);
          motorista.setViaturaEmUso(v);
          System.out.println("Selecionou:\n" + v.toString());          
       }catch(ViaturaNaoDisponivelException e){
             System.out.println(e.getMessage());
       }
       System.out.println("Pressione ENTER para continuar");
       input.nextLine();
    }
    
    
    public static void imprimirViaturas(Motorista motorista){
        int i = 0;
        for(Viatura car: motorista.getViaturas()){
             System.out.printf("\n--- Viatura %d:\n",i);
             System.out.println(car.toString2()); 
             i++;
        }
        System.out.println("Pressione ENTER para continuar");
        input.nextLine();
    }
    
    public static void imprimirViagens(Motorista motorista){
        for(Viagem trip: motorista.getViagens()){
            System.out.println(trip.toString2());
        }
        System.out.println("Pressione ENTER para continuar");
        input.nextLine();
    }
    
    public static int estatisticas(){return 0;}
    
//    private static void salvarDados(){
//      java.net.URL url = App.class.getResource("Data.txt");
//      File f = new File(url.getFile());
//      Path path = Paths.get(f.getAbsolutePath());
//      
//      try{
//          //TODO :: Alterar para realmente escrever os dados.
//          Files.write(path, dados.toString(),StandardOpenOption.TRUNCATE_EXISTING, StandardCharsets.UTF_8);
//      }
//      catch(IOException e){
//          System.out.println("Falha na leitura");
//      }
//      
//      
//    }
//    
//    private static UMeR carregarDados(){
//      java.net.URL url = App.class.getResource("Data.txt");
//      File f = new File(url.getFile());
//      Path path = Paths.get(f.getAbsolutePath());
//      
//      try{
//          //TODO :: Alterar para realmente carregar os dados.
//          Files.lines(path).forEach(s->System.out.println(s));
//      }
//      catch(IOException e){
//          System.out.println("Falha na leitura");
//      }
//      return null;
//    }
    
    private static void limparConsola(){
        try{ 
            Runtime.getRuntime().exec("clear");
        }
        catch(IOException e){
            e.getMessage();
        }
    }
}
