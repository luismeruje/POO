
/**
 * Escreva a descrição da classe Menu aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */

import java.util.Scanner;
import java.util.InputMismatchException;
public final class Menu
{
    private static Scanner input = new Scanner(System.in);
    
    //Contrutor privado para prevenir que sejam construidas instâncias desta classe
    private Menu(){}
    
    
    public static int mainMenu(){
        int opcao = -1;
        boolean concluido = false;
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
                if(opcao <= 5 && opcao >= 1){
                    concluido = true;
                }
                else
                    System.out.println("Por favor escolha um número entre 1 e 5.");
            }
            catch(InputMismatchException e){
                System.out.println("Por favor escolha um número entre 1 e 5.");
            }
            input.nextLine();
        }
        return opcao;
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
