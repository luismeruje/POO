
/**
 * Escreva a descrição da classe Posicao aqui.
 * 
 * @author (seu nome) 
 * @version (número de versão ou data)
 */
import java.lang.Math;
public class Posicao
{
   private double x;
   private double y;
   
   public Posicao(double x, double y){
       this.x = x;
       this.y = y;
   }
   
   public Posicao(){
       this.x = 0;
       this.y = 0;
   }
   
   public Posicao(Posicao p){
       this.x = p.getX();
       this.y = p.getY();
   }
   
   public double getX(){
       return x;
   }
 
   public double getY(){
       return y;
   }
   
   public void move(double x, double y){
       this.x = x;
       this.y = y;
   }
   
   public double distancia(Posicao p){
       return (Math.sqrt( Math.pow(p.getX()-x,2) + Math.pow(p.getY()-y,2) ));
   }
   
   public boolean equals(Object o){
       if(o == this)
            return true;
       if(o == null || this.getClass() != o.getClass())
            return false;
       Posicao p = (Posicao) o;
       if(p.getX() == x && p.getY()==y)
            return true;
       else
            return false;
   }
   
   public Posicao clone(){
       return new Posicao(x,y);
   }
   
   public String toString(){
       return ("(" + x + "," + y + ")");
   }
}
