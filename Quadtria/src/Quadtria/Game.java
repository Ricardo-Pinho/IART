package Quadtria;


public class Game {
	public Board gBoard;
	public Player player1;
	public Player player2;
	public int Player1Score;
	public int Player2Score;
	public int noPlayer;
	public int gameCount;
	public int outOff;
	
	//cpuvscpu
	public Game(){
		gBoard = new Board();
		this.player1 = new AI("AI1");
		this.player2 = new AI("AI2");
		this.noPlayer = 3;
	}
	
	// um jogador
	public Game(String player1Name) {

		gBoard = new Board();
		this.player1 = new Player(player1Name);
		this.player2 = new AI("AI");
		this.noPlayer = 1;
	
	}

	// dois jogadores
	public Game(String player1Name, String player2Name) {
		gBoard = new Board();
		this.player1 = new Player(player1Name);
		this.player2 = new Player(player2Name);
		this.noPlayer = 2;
		
	}

	// novo tabuleiro para um novo jogo
	public void NewBoard() {
		gBoard = new Board();
		gameCount++;
	}

	// verifica se a jogada e valida, efectua a jogada e verifica se o jogo
	// terminou ou nao
	public int Move(int prevbox, int box) {
		/*if (gBoard.SetMove(prevbox, box) == false)
			return 3;*/
		gBoard.SetMove(prevbox, box);
		return gBoard.CheckCondition();
	}

	// devolve o nome do jogador
	public String PlayerName(int playerNo) {
		if (playerNo == 1)
			return player1.playerName;
		return player2.playerName;
	}

	// incrementa o contador de vitorias
	public void Win(int playerNo) {
		if (playerNo == 1)
			this.Player1Score++;
		else
			this.Player2Score++;

	}

	// devolve o score
	public int Score(int playerNo) {
		if (playerNo == 1)
			return this.Player1Score;
		else
			return this.Player2Score;

	}

	// faz reset ao score e ao número de jogos feitos
	public void Reset() {
		this.Player1Score = 0;
		this.Player2Score = 0;
		this.gameCount = 0;

	}

	// verifica se ja foi efectuado o número certo de jogos, se sim, pergunta se
	// quer continuar a jogar
	public boolean endGame() {
		if (outOff == gameCount) {
			return false;
		}
		return true;

	}

}
