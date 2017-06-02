
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
public final class App
{
    private static UMeR dados;
    private static Scanner input = new Scanner(System.in);
    
    public static void main(String[] args){
        int opcao = -1;
        boolean concluido, exit = false, resultado;
        
        //TODO: dados.loadFicheiro(); -> tem que fazer set da variável global de viagens
        dados = new UMeR(); 
        //carregarDados();
        
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
          //              	salvarDados();
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
                            System.out.println("Por favor escolha um número entre 1 e 3.");
                            break;
                   }
                }
                catch(InputMismatchException e){
                   System.out.println("Por favor escolha um número entre 1 e 3."); 
                }
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
        boolean concluido,selecionado = false, exit = false;
        Viatura v = new Viatura();
        while(!exit){
            System.out.println("================== Área de Motorista =================");
            System.out.println("####### Informações #######");
            System.out.printf("====Motorista====\nQuilometros realizados pelo motorista: %d\nPontuação cliente: %d\nPontuação cumprimento horário: %d\nViagens realizadas: %d\nDisponivel: %s\n"
                             ,motorista.getKms(),motorista.getClassificacao(),motorista.getPontuacaoHorario()
                             ,motorista.getNrViagensRealizadas(),motorista.getDisponivel()?"Sim":"Não");
        	if(selecionado){ System.out.printf(v.toString()); }
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
                    	    System.out.println("Introduza especificações do veiculo:\n");
                    	    System.out.println("Velocidade Média:");
                    	    int vm = input.nextInt();
                    	    Random r = new Random();
                    	    int f = r.nextInt(101);
                    	    System.out.println("Preço por quilometro:");
                    	    int pkm = input.nextInt();
                    	    System.out.println("Posicao x y");
                    	    Posicao p = new Posicao(input.nextInt(), input.nextInt());
                    	    Viatura novo = new Viatura(vm,f,pkm,p);
                    	    motorista.setViatura(novo);
                    	    dados.addViatura(novo);
                            concluido = true;
                            break;
                       case 2:
                    	    for(Viatura car: motorista.getViaturas()){
                    	    	System.out.println(car.toString()); 
                    	    }
                    	    System.out.println("Escreva o codigo da viatura desejada:");
                    	    try{
                    	    	v = motorista.getViatura(input.nextInt());
                    	    	selecionado = true;
                    	    	System.out.println("Selecionou:\n" + v.toString());
                    	    	
                    	    }catch(ViaturaNaoDisponivelException e){
                    	    	System.out.println(e.getMessage());
                    	    }
                    	    System.out.println("Pressione ENTER para continuar");
                    	    input.nextLine();
                    	    concluido = true;
                            break;
                       case 3:
                    	   	for(Viatura car: motorista.getViaturas()){
                   	    		System.out.println(car.toString()); 
                   	    	}
                            concluido = true;
                            break;
                       case 4:
                    	   	for(Viagem trip: motorista.getViagens()){
                    		    System.out.println(trip.toString());
                    	   	}
                    	   	System.out.println("Pressione ENTER para continuar");
                    	    input.nextLine();
                            concluido = true;
                            break;
                       case 5:
                    	    motorista.setDisponibilidade(!motorista.getDisponivel());
                            concluido = true;
                            break;
                       case 6:
                            concluido = true;
                            exit = true;
                            break;
                       default:
                            System.out.println("Por favor escolha um número entre 1 e 6.");
                            break;
                   }
                }
                catch(InputMismatchException e){
                   System.out.println("Por favor escolha um número entre 1 e 6."); 
                   input.nextLine();
                }
            }
        }
    } 
    
    public static int estatisticas(){return 0;}
    
//    private static void salvarDados(){
//    	java.net.URL url = App.class.getResource("Data.txt");
//    	File f = new File(url.getFile());
//    	Path path = Paths.get(f.getAbsolutePath());
//    	
//    	try{
//    		//TODO :: Alterar para realmente escrever os dados.
//    		Files.write(path, dados.toString(),StandardOpenOption.TRUNCATE_EXISTING, StandardCharsets.UTF_8);
//    	}
//    	catch(IOException e){
//    		System.out.println("Falha na leitura");
//    	}
//    	
//    	
//    }
//    
//    private static UMeR carregarDados(){
//    	java.net.URL url = App.class.getResource("Data.txt");
//    	File f = new File(url.getFile());
//    	Path path = Paths.get(f.getAbsolutePath());
//    	
//    	try{
//    		//TODO :: Alterar para realmente carregar os dados.
//    		Files.lines(path).forEach(s->System.out.println(s));
//    	}
//    	catch(IOException e){
//    		System.out.println("Falha na leitura");
//    	}
//    	return null;
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
