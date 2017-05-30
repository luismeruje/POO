
/**
 * Escreva a descrição da classe App aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Scanner;
import java.util.InputMismatchException;
public final class App
{
    private UMeR dados;
    private static Scanner input = new Scanner(System.in);
    public static int main(){
        int opcao = -1;
        boolean concluido = false, exit = false;
        
        //TODO: dados.loadFicheiro();
        
        
        while(!exit){
             //TODO:criar método private para limpar o ecrã e chamar aqui
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
                    switch(opcao){
                    case 1:
                        //loginUtilizador();
                        break;
                    case 2:
                        //loginMotorista();
                        break;
                    case 3:
                        //registarUtilizador();
                        break;
                    case 4:
                        //registarMotorista();
                        break;
                    case 5:
                        //estatisticas();
                        break;
                    case 6:
                        //dados.escreveFicheiro();
                        exit = true;
                        break;
                    default:
                        System.out.println("Por favor escolha um número entre 1 e 6.");
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Por favor escolha um número entre 1 e 6.");
                }
                input.nextLine();
            }
        }
        return 0;
    }
    
    public static int loginCliente(){
        return 0;
    }
    
    public static int viagem(){return 0;}
    
    public static int fimViagem(){return 0;}
    
    public static int loginMotorista(){return 0;}
    
    public static int registoCliente(){return 0;}
    
    public static int registoMotorista(){return 0;}
    
    public static int areaCliente(){return 0;}
    
    public static int areaMotorista(){return 0;} 
    
    public static int estatisticas(){return 0;}
}
