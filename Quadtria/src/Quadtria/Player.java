
package Quadtria;


public class Player {
    public String playerName;
    public String symbol;
    public int playerWin;
    public int playerLoss;
    public Player(String playerName){
        this.playerName=playerName;
    }
    public void PlayerWins(){
        playerWin++;
    }
    public void PlayerLoses(){
        playerLoss++;
    }
    public int PlayerWinCount(){
        return playerWin;
    }
    public int PlayerLossCount(){
        return playerLoss;
    }

}
