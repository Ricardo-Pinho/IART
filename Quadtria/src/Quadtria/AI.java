

package Quadtria;
import Inteligence.*;

public class AI extends Player{
    public AI(String PlayerName){
        super(PlayerName);
        
    }
    public Integer[] NextMove(Board gBoard,int dificulty, int option){
        Inteligence intel= new Inteligence(gBoard,dificulty);
        //System.out.println("test2");
        return intel.Move(option);
    }

}
