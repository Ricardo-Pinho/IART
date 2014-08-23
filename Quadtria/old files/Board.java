package Quadtria;

import java.util.Hashtable;
import java.util.Vector;

//Criação do tabuleiro
public class Board {
	private Hashtable<Integer, Vector<Integer>> connections;
	private int size = 7;
	private int intBoard[] = new int[size * size + 1];
	public int intMoveSymbol = 1; // 0=empty;1=Player1;2=Player2
	public int moveCount = 0;
	private Vector<Integer> pecasJog1;
	private Vector<Integer> pecasJog2;
	public boolean validCorner1 = false;
	public boolean validCorner2 = false;

	public void initPecas() {
		pecasJog1 = new Vector<Integer>();
		pecasJog1.add(0);
		pecasJog1.add(2);
		pecasJog1.add(8);
		pecasJog1.add(14);
		pecasJog1.add(16);

		pecasJog2 = new Vector<Integer>();
		pecasJog2.add(32);
		pecasJog2.add(34);
		pecasJog2.add(40);
		pecasJog2.add(46);
		pecasJog2.add(48);

	}

	public int[] numMax(int player)
	{
		int first=0;
		int sec=0;
		int third=0;
		int fourth=0;
		int max=0;
		if(player==1){
			for (int i=0;i<pecasJog1.size();i++)
			{
				if(pecasJog1.get(i)==0|pecasJog1.get(i)==2|pecasJog1.get(i)==8|pecasJog1.get(i)==14|pecasJog1.get(i)==16)
					first++;
				else if(pecasJog1.get(i)==4|pecasJog1.get(i)==6|pecasJog1.get(i)==12|pecasJog1.get(i)==18|pecasJog1.get(i)==20)
					{
						sec++;
						if(sec>max) max=sec;
					}
				else if(pecasJog1.get(i)==28|pecasJog1.get(i)==30|pecasJog1.get(i)==36|pecasJog1.get(i)==42|pecasJog1.get(i)==44)
					{
						third++;
						if(third>max) max=third;
					}
				else
				{
					fourth++;
					if(fourth>max) max=fourth;
				}		
			}
		}
		else {
			for (int i=0;i<pecasJog1.size();i++)
			{
				if(pecasJog1.get(i)==0|pecasJog1.get(i)==2|pecasJog1.get(i)==8|pecasJog1.get(i)==14|pecasJog1.get(i)==16)
					first++;
				else if(pecasJog1.get(i)==4|pecasJog1.get(i)==6|pecasJog1.get(i)==12|pecasJog1.get(i)==18|pecasJog1.get(i)==20)
					{
						sec++;
						if(sec>max) max=sec;
					}
				else if(pecasJog1.get(i)==28|pecasJog1.get(i)==30|pecasJog1.get(i)==36|pecasJog1.get(i)==42|pecasJog1.get(i)==44)
					{
						third++;
						if(third>max) max=third;
					}
				else
				{
					fourth++;
					if(fourth>max) max=fourth;
				}		
			}
		}
		int num[]=new int[2];
		num[0]=max;
		num[1]=first;
		return num;
	}
	public int[] getIntBoard() {
		return intBoard;
	}

	public int numPiece1Quad(int player)
	{
		int num=0;
		if(intBoard[0]==player) num++;
		if(intBoard[2]==player) num++;
		if(intBoard[8]==player) num++;
		if(intBoard[14]==player) num++;
		if(intBoard[16]==player) num++;
		return num;
	}
	public int numPiece2Quad(int player)
	{
		int num=0;
		if(intBoard[4]==player) num++;
		if(intBoard[6]==player) num++;
		if(intBoard[12]==player) num++;
		if(intBoard[18]==player) num++;
		if(intBoard[20]==player) num++;
		return num;
	}
	public int numPiece3Quad(int player)
	{
		int num=0;
		if(intBoard[28]==player) num++;
		if(intBoard[30]==player) num++;
		if(intBoard[36]==player) num++;
		if(intBoard[42]==player) num++;
		if(intBoard[44]==player) num++;
		return num;
	}
	public int numPiece4Quad(int player)
	{
		int num=0;
		if(intBoard[32]==player) num++;
		if(intBoard[34]==player) num++;
		if(intBoard[40]==player) num++;
		if(intBoard[46]==player) num++;
		if(intBoard[48]==player) num++;
		return num;
	}
	
	public int inQuad(int num)
	{
		if(num==0|num==2|num==8|num==14|num==16) return 1;
		if(num==4|num==6|num==12|num==18|num==20) return 2;
		if(num==28|num==30|num==36|num==42|num==44) return 3;
		return 4;
	}
	public void setIntBoard(int[] _intBoard) {
		intBoard = _intBoard;
	}

	public void setHash() {

		firstQuad();
		secondQuad();
		thirdQuad();
		fourthQuad();
		/* Printing *//*
					 * Enumeration<Vector<Integer>> e = connections.elements();
					 * while (e.hasMoreElements()) {
					 * System.out.println(e.nextElement()); } Vector<Integer> v1
					 * = connections.get(0); Vector<Integer> v2 =
					 * connections.get(2); for (Integer t : v1)
					 * System.out.println("t:" + t);
					 * System.out.println("----------------------"); for
					 * (Integer t2 : v2) System.out.println("t:" + t2);
					 */
	}

	public void addConnections(int key, Vector<Integer> destiny)
	{
		connections.put(key, destiny);
	}
	
	public Vector<Integer> connectionsGet(int key)
	{
		return connections.get(key);
	}
	
	// inicia o 1º Quadtrande para o setHash()
	private void firstQuad() {

		Vector<Integer> dests = new Vector<Integer>();
		// 0
		dests.add(2);
		dests.add(8);
		dests.add(14);
		addConnections(0, dests);
		// 2
		dests = new Vector<Integer>();
		dests.add(0);
		dests.add(4);
		dests.add(8);
		dests.add(16);
		addConnections(2, dests);
		// 8
		dests = new Vector<Integer>();
		dests.add(0);
		dests.add(2);
		dests.add(14);
		dests.add(16);
		addConnections(8, dests);
		// 14
		dests = new Vector<Integer>();
		dests.add(0);
		dests.add(8);
		dests.add(16);
		dests.add(28);
		addConnections(14, dests);
		// 16
		dests = new Vector<Integer>();
		dests.add(2);
		dests.add(8);
		dests.add(14);
		dests.add(18);
		dests.add(30);
		dests.add(32);
		addConnections(16, dests);
	}

	// inicia o 2º Quadtrande para o setHash()
	private void secondQuad() {
		Vector<Integer> dests = new Vector<Integer>();
		// 4
		dests.add(2);
		dests.add(6);
		dests.add(12);
		dests.add(18);
		addConnections(4, dests);
		// 6
		dests = new Vector<Integer>();
		dests.add(4);
		dests.add(12);
		dests.add(20);
		addConnections(6, dests);
		// 12
		dests = new Vector<Integer>();
		dests.add(4);
		dests.add(6);
		dests.add(18);
		dests.add(20);
		addConnections(12, dests);
		// 18
		dests = new Vector<Integer>();
		dests.add(4);
		dests.add(12);
		dests.add(16);
		dests.add(20);
		dests.add(30);
		dests.add(32);
		addConnections(18, dests);
		// 20
		dests = new Vector<Integer>();
		dests.add(6);
		dests.add(12);
		dests.add(18);
		dests.add(34);
		addConnections(20, dests);
	}

	// inicia o 3º Quadtrande para o setHash()
	private void thirdQuad() {
		Vector<Integer> dests = new Vector<Integer>();
		// 28
		dests.add(14);
		dests.add(30);
		dests.add(36);
		dests.add(42);
		addConnections(28, dests);
		// 30
		dests = new Vector<Integer>();
		dests.add(16);
		dests.add(18);
		dests.add(28);
		dests.add(32);
		dests.add(36);
		dests.add(44);
		addConnections(30, dests);
		// 36
		dests = new Vector<Integer>();
		dests.add(28);
		dests.add(30);
		dests.add(42);
		dests.add(44);
		addConnections(36, dests);
		// 42
		dests = new Vector<Integer>();
		dests.add(28);
		dests.add(36);
		dests.add(44);
		addConnections(42, dests);
		// 44
		dests = new Vector<Integer>();
		dests.add(30);
		dests.add(36);
		dests.add(42);
		dests.add(46);
		addConnections(44, dests);
	}

	// inicia o 4º Quadtrande para o setHash()
	private void fourthQuad() {
		Vector<Integer> dests = new Vector<Integer>();
		// 32
		dests.add(16);
		dests.add(18);
		dests.add(30);
		dests.add(34);
		dests.add(40);
		dests.add(46);
		addConnections(32, dests);
		// 34
		dests = new Vector<Integer>();
		dests.add(20);
		dests.add(32);
		dests.add(40);
		dests.add(48);
		addConnections(34, dests);
		// 40
		dests = new Vector<Integer>();
		dests.add(32);
		dests.add(34);
		dests.add(46);
		dests.add(48);
		addConnections(40, dests);
		// 46
		dests = new Vector<Integer>();
		dests.add(32);
		dests.add(40);
		dests.add(44);
		dests.add(48);
		addConnections(46, dests);
		// 48
		dests = new Vector<Integer>();
		dests.add(34);
		dests.add(40);
		dests.add(46);
		addConnections(48, dests);
	}

	public boolean hipoMove(int[] initBoard, int prevNo, int newNo,
			int player) {
		if (prevNo < 0 || prevNo > (initBoard.length-2) || newNo < 0
				|| newNo > (initBoard.length-2) || intBoard[prevNo] == 9 || intBoard[newNo] == 9)
			return false;
		if (intMoveSymbol == 1) {
			if (intBoard[prevNo] != 1)
				return false;
		}
		else {
			if (intBoard[prevNo] != 2)
				return false;
		}
		if (initBoard[newNo] != 0 || initBoard[prevNo] != player) {
			return false;
		}
		/*if (player == 1) {
			finalBoard[newNo] = player;
			finalBoard[prevNo] = 0;
		} else {
			finalBoard[newNo] = player;
			finalBoard[prevNo] = 0;
		}
		return finalBoard;*/
		return true;

	}

	public int[] possMove(int[] initBoard, int prevNo, int newNo,
			int player)
	{
		int[] finalBoard = new int[initBoard.length];
		System.arraycopy(initBoard, 0, finalBoard, 0, finalBoard.length);
		Vector<Integer> possible = connections.get(prevNo);
		if (possible.indexOf(newNo) == -1)
			return null;
		if (player == 1) {
			finalBoard[newNo] = player;
			finalBoard[prevNo] = 0;
		} else {
			finalBoard[newNo] = player;
			finalBoard[prevNo] = 0;
		}
		return finalBoard;
	}
	
	public void printIntBoard(int[] printBoard) {
		for (int x = 0; x < 7; x++) {
			System.out.println();
			for (int y = 0; y < 7; y++) {
				System.out.print("|");
				if (printBoard[y + (7 * x)] != 0)
					if (printBoard[y + (7 * x)] == 1) {
						if ((y + (7 * x)) < 10)
							System.out.print(" [" + (y + (7 * x)) + "] ");
						else
							System.out.print(" [" + (y + (7 * x)) + "]");
					} else if (printBoard[y + (7 * x)] == 2) {
						if ((y + (7 * x)) < 10)
							System.out.print(" (" + (y + (7 * x)) + ") ");
						else
							System.out.print(" (" + (y + (7 * x)) + ")");
					} else
						System.out.print("     ");
				else {
					if ((y + (7 * x)) < 10)
						System.out.print("  " + (y + (7 * x)) + "  ");
					else
						System.out.print("  " + (y + (7 * x)) + " ");
				}
				System.out.print("|");

			}
		}
		System.out.println("");

	}
	public int checkForWin() {
		return checkForWin(intBoard);
	}

	public int checkForWin(int[] board) {
		int win = checkFirstQuad(board);
		if (win != 0) {
		
			return win;
		}
		win = checkSecondQuad(board);
		if (win != 0) {
			return win;
		}
		win = checkThirdQuad(board);
		if (win != 0) {
			return win;
		}
		win = checkFourthQuad(board);
		if (win != 0) {
			return win;
		}
		return 0;
	}

	public Vector<Integer> getpecasJogo(int jogador)
	{
		if(jogador==1)
			return pecasJog1;
		else return pecasJog2;
	}
	
	public void setpecasJogo(int jogador,Vector<Integer> pecasjog)
	{
		if(jogador==1)
			pecasJog1=pecasjog;
		else
			pecasJog2=pecasjog;
	}
	private int checkFourthQuad(int[] board) {

		int numberOf1 = 0, numberOf2 = 0;
		if (board[32] == 1)
			numberOf1++;
		else if (board[32] == 2)
			numberOf2++;

		if (board[34] == 1)
			numberOf1++;
		else if (board[34] == 2)
			numberOf2++;

		if (board[40] == 1)
			numberOf1++;
		else if (board[40] == 2)
			numberOf2++;
		if (board[46] == 1)
			numberOf1++;
		else if (board[46] == 2)
			numberOf2++;

		if (board[48] == 1)
			numberOf1++;
		else if (board[48] == 2)
			numberOf2++;
		if (numberOf1 >= 3) {
			if (numberOf1 == 3
					&& ((board[32] == 1 && board[40] == 1 && board[48] == 1) || (board[34] == 1
							&& board[40] == 1 && board[46] == 1))) {
				return 0;
			}
			return 1;
		} else if (validCorner2 == true && numberOf2 >= 3) {// TODO&&
															// freedInitialQuadrant2)//se
															// ja
			// tiver
			// feito aquilo de tirar as peças todas
			if (numberOf2 == 3
					&& ((board[32] == 2 && board[40] == 2 && board[48] == 2) || (board[34] == 2
							&& board[40] == 2 && board[46] == 2))) {
				return 0;
			}

			return 2;
		}

		return 0;
	}

	private int checkThirdQuad(int[] board) {

		int numberOf1 = 0, numberOf2 = 0;
		
		if (board[28] == 1)
			numberOf1++;
		else if (board[28] == 2)
			numberOf2++;
		
		if (board[30] == 1)
			numberOf1++;
		else if (board[30] == 2)
			numberOf2++;
		
		if (board[36] == 1)
			numberOf1++;
		else if (board[36] == 2)
			numberOf2++;
		
		if (board[42] == 1)
			numberOf1++;
		else if (board[42] == 2)
			numberOf2++;
		
		if (board[44] == 1)
			numberOf1++;
		else if (board[44] == 2)
			numberOf2++;
		if (numberOf1 >= 3)
		{
			if (numberOf1 == 3
					&& ((board[28] == 1 && board[36] == 1 && board[44] == 1) || (board[30] == 1
							&& board[36] == 1 && board[42] == 1))) {
				return 0;
			}
			return 1;
		}
		else if (numberOf2 >= 3){
			if (numberOf2 == 3
					&& ((board[28] == 2 && board[36] == 2 && board[44] == 2) || (board[30] == 2
							&& board[36] == 2 && board[42] == 2))) {
				return 0;
			}
			return 2;
		}

		return 0;
	}

	private int checkSecondQuad(int[] board) {
		int numberOf1 = 0, numberOf2 = 0;
		if (board[4] == 1)
			numberOf1++;
		else if (board[4] == 2)
			numberOf2++;

		if (board[6] == 1)
			numberOf1++;
		else if (board[6] == 2)
			numberOf2++;

		if (board[12] == 1)
			numberOf1++;
		else if (board[12] == 2)
			numberOf2++;

		if (board[18] == 1)
			numberOf1++;
		else if (board[18] == 2)
			numberOf2++;

		if (board[20] == 1)
			numberOf1++;
		else if (board[20] == 2)
			numberOf2++;
		if (numberOf1 >= 3)
		{
			if (numberOf1 == 3
					&& ((board[4] == 1 && board[12] == 1 && board[20] == 1) || (board[6] == 1
							&& board[12] == 1 && board[18] == 1))) {
				return 0;
			}
			return 1;
		}
		else if (numberOf2 >= 3){
			if (numberOf2 == 3
					&& ((board[4] == 2 && board[12] == 2 && board[20] == 2) || (board[6] == 2
					&& board[12] == 2 && board[18] == 2))) {
				return 0;
			}
			return 2;
		}

		return 0;
	}

	private int checkFirstQuad(int[] board) {
		int numberOf1 = 0, numberOf2 = 0;
		if (board[0] == 1)
			numberOf1++;
		else if (board[0] == 2)
			numberOf2++;

		if (board[2] == 1)
			numberOf1++;
		else if (board[2] == 2)
			numberOf2++;

		if (board[8] == 1)
			numberOf1++;
		else if (board[8] == 2)
			numberOf2++;

		if (board[14] == 1)
			numberOf1++;
		else if (board[14] == 2)
			numberOf2++;

		if (board[16] == 1)
			numberOf1++;
		else if (board[16] == 2)
			numberOf2++;
		if (validCorner1 == true  && numberOf1 >= 3) {// TODO&&
													// freedInitialQuadrant1)//se
													// ja tiver feito
			// aquilo de tirar as peças todas
			if (numberOf1 == 3
					&& ((board[0] == 1 && board[8] == 1 && board[16] == 1) || (board[2] == 1
							&& board[8] == 1 && board[14] == 1))) {
				return 0;
			}
			return 1;
		} else if (numberOf2 >= 3) {
			if (numberOf2 == 3
					&& ((board[0] == 2 && board[8] == 2 && board[16] == 2) || (board[2] == 2
							&& board[8] == 2 && board[14] == 2))) {
				return 0;
			}
			return 2;
		}
		return 0;
	}
	public Board() {
		int i = 0;
		while (i < size) {
			for (int x = 0; x <= size; x++) {
				if (i == 1 || i == 5) {
					if (x == (0) || x == (2) || x == (3) || x == (4)
							|| x == (6))
						intBoard[size * i + x] = 9;
					else
						intBoard[size * i + x] = 0;
				} else if (i == 3) {
						intBoard[size * i + x] = 9;
				} else {
					if (x == (1) || x == (3) || x == (5))
						intBoard[size * i + x] = 9;
					else
						intBoard[size * i + x] = 0;
				}

			}
			i++;
		}
		intBoard[size * 0 + 0] = intBoard[size * 0 + 2] = intBoard[size * 1 + 1] = intBoard[size * 2 + 0] = intBoard[size * 2 + 2] = 1;
		intBoard[size * (size - 3) + 4] = intBoard[size * (size - 3) + 6] = intBoard[size
				* (size - 2) + 5] = intBoard[size * (size - 1) + 4] = intBoard[size
				* (size - 1) + 6] = 2;
		connections = new Hashtable<Integer, Vector<Integer>>();
		setHash();
		initPecas();
	}

	/*public Board(int[] _intBoard, int size) {
		for (int x = 0; x <= (size * size); x++) {
			intBoard[x] = _intBoard[x];
		}
		connections = new Hashtable<Integer, Vector<Integer>>();
		setHash();
		initPecas();
	}*/

	public Board(Board board) {
		moveCount = board.moveCount;
		intMoveSymbol = board.intMoveSymbol;
		size = board.size;
		System.arraycopy(board.intBoard, 0, this.intBoard, 0, (board.size
				* board.size + 1));
		connections=board.connections;
		pecasJog1= new Vector<Integer>(board.pecasJog1);
		pecasJog2= new Vector<Integer>(board.pecasJog2);
		validCorner1=board.validCorner1;
		validCorner2 = board.validCorner2;
	}

	public void copy(Board _board) {
		moveCount = _board.moveCount;
		intMoveSymbol = _board.intMoveSymbol;
		size = _board.size;
		System.arraycopy(_board.intBoard, 0, this.intBoard, 0, (_board.size
				* _board.size + 1));
		pecasJog1 = new Vector<Integer>(_board.pecasJog1);
		pecasJog2 = new Vector<Integer>(_board.pecasJog2);
		validCorner1=_board.validCorner1;
		validCorner2 = _board.validCorner2;
	}

	// efectua a jogada no tabuleiro
	public void SetMove(int prevNo, int boxNo) {
		if (intMoveSymbol == 1) {
			intBoard[boxNo] = intMoveSymbol;
			intBoard[prevNo] = 0;
			if (validCorner1 == false) {
				Allplayed(1);
			}
			intMoveSymbol = 2;
			recordMove(1, prevNo, boxNo);
		}
		else {
			intBoard[boxNo] = intMoveSymbol;
			intBoard[prevNo] = 0;
			if (validCorner2 == false) {
				Allplayed(2);
			}
			intMoveSymbol = 1;
			recordMove(2, prevNo, boxNo);
		}
		moveCount++;

	}

	public boolean recordMove(int player, int prevNo, int boxNo) {
		if (player == 1) {
			pecasJog1.remove(pecasJog1.indexOf(prevNo));
			pecasJog1.add(boxNo);
		} else if (player == 2) {
			pecasJog2.remove(pecasJog2.indexOf(prevNo));
			pecasJog2.add(boxNo);
		} else {
			System.err.println("recordMove:Non Existing Player");
			return false;
		}
		return true;
	}

	public void Allplayed(int player) {
		if (player==1)
		{
			if (intBoard[0]!=1&&intBoard[2]!=1&&intBoard[8]!=1&&intBoard[14]!=1&&intBoard[16]!=1)
					validCorner1=true;
		
		}
		else
		{
			if (intBoard[32]!=2&&intBoard[34]!=2&&intBoard[40]!=2&&intBoard[46]!=2&&intBoard[48]!=2)
				validCorner2=true;
		}
	}
	
	// condições de fim do jogo
	public int CheckCondition()// [1=Player1],[2=Player2],[0=no
								// condition]
	{
		return checkForWin();
//		if (intBoard[0] == intBoard[14] & intBoard[14] == intBoard[8]
//				& intBoard[8] != 0 & (validCorner1||intBoard[0]==2)){
//				return intBoard[0];
//		} else if (intBoard[2] == intBoard[16] & intBoard[16] == intBoard[8]
//				& intBoard[8] != 0 & (validCorner1||intBoard[2]==2)){
//				return intBoard[2];
//		} else if (intBoard[4] == intBoard[12] & intBoard[12] == intBoard[18]
//				& intBoard[18] != 0) {
//				return intBoard[4];
//		} else if (intBoard[6] == intBoard[12] & intBoard[12] == intBoard[20]
//				& intBoard[20] != 0) {
//				return intBoard[6];
//		} else if (intBoard[28] == intBoard[36] & intBoard[36] == intBoard[42]
//				& intBoard[42] != 0) {
//				return intBoard[28];
//		} else if (intBoard[30] == intBoard[36] & intBoard[36] == intBoard[44]
//				& intBoard[44] != 0) {
//				return intBoard[30];
//		} else if (intBoard[32] == intBoard[40] & intBoard[40] == intBoard[46]
//				& intBoard[46] != 0 & (validCorner2||intBoard[32]==1)){
//				return intBoard[32];
//		} else if (intBoard[34] == intBoard[40] & intBoard[40] == intBoard[48]
//				& intBoard[48] != 0& (validCorner2||intBoard[34]==1)){
//				return intBoard[34];
//		} else if (intBoard[0] == intBoard[8] & intBoard[8] == intBoard[2]
//				& intBoard[2] != 0 & (validCorner1||intBoard[0]==2)){
//				return intBoard[0];
//		} else if (intBoard[14] == intBoard[8] & intBoard[8] == intBoard[16]
//				& intBoard[16] != 0 & (validCorner1||intBoard[14]==2)){
//				return intBoard[14];
//		} else if (intBoard[4] == intBoard[12] & intBoard[12] == intBoard[6]
//				& intBoard[6] != 0) {
//				return intBoard[4];
//		} else if (intBoard[18] == intBoard[12] & intBoard[12] == intBoard[20]
//				& intBoard[20] != 0) {
//				return intBoard[18];
//		} else if (intBoard[28] == intBoard[36] & intBoard[36] == intBoard[30]
//				& intBoard[30] != 0) {
//				return intBoard[28];
//		} else if (intBoard[42] == intBoard[36] & intBoard[36] == intBoard[44]
//				& intBoard[44] != 0) {
//				return intBoard[42];
//		} else if (intBoard[32] == intBoard[40] & intBoard[40] == intBoard[34]
//				& intBoard[34] != 0& (validCorner2||intBoard[32]==1)){
//				return intBoard[32];
//		} else if (intBoard[46] == intBoard[40] & intBoard[40] == intBoard[48]
//				& intBoard[48] != 0& (validCorner2||intBoard[46]==1)){
//				return intBoard[46];
//		} else if (intBoard[0] == intBoard[14] & intBoard[14] == intBoard[16]
//				& intBoard[16] != 0 & (validCorner1||intBoard[0]==2)){
//				return intBoard[0];
//		} else if (intBoard[2] == intBoard[16] & intBoard[16] == intBoard[14]
//				& intBoard[14] != 0 & (validCorner1||intBoard[2]==2)){
//				return intBoard[2];
//		} else if (intBoard[4] == intBoard[20] & intBoard[20] == intBoard[18]
//				& intBoard[18] != 0) {
//				return intBoard[4];
//		} else if (intBoard[6] == intBoard[18] & intBoard[18] == intBoard[20]
//				& intBoard[20] != 0) {
//				return intBoard[6];
//		} else if (intBoard[28] == intBoard[44] & intBoard[44] == intBoard[42]
//				& intBoard[42] != 0) {
//				return intBoard[28];
//		} else if (intBoard[30] == intBoard[42] & intBoard[42] == intBoard[44]
//				& intBoard[44] != 0) {
//				return intBoard[30];
//		} else if (intBoard[32] == intBoard[48] & intBoard[48] == intBoard[46]
//				& intBoard[46] != 0& (validCorner2||intBoard[32]==1)){
//				return intBoard[32];
//		} else if (intBoard[34] == intBoard[46] & intBoard[46] == intBoard[48]
//				& intBoard[48] != 0& (validCorner2||intBoard[34]==1)){
//				return intBoard[34];
//		} else if (intBoard[0] == intBoard[14] & intBoard[14] == intBoard[2]
//				& intBoard[2] != 0 & (validCorner1||intBoard[0]==2)){
//				return intBoard[0];
//		} else if (intBoard[0] == intBoard[16] & intBoard[16] == intBoard[2]
//				& intBoard[2] != 0 & (validCorner1||intBoard[2]==2)){
//				return intBoard[2];
//		} else if (intBoard[4] == intBoard[18] & intBoard[18] == intBoard[6]
//				& intBoard[18] != 0) {
//				return intBoard[4];
//		} else if (intBoard[6] == intBoard[20] & intBoard[20] == intBoard[4]
//				& intBoard[20] != 0) {
//				return intBoard[6];
//		} else if (intBoard[28] == intBoard[30] & intBoard[30] == intBoard[42]
//				& intBoard[42] != 0) {
//				return intBoard[28];
//		} else if (intBoard[30] == intBoard[28] & intBoard[28] == intBoard[44]
//				& intBoard[44] != 0) {
//				return intBoard[30];
//		} else if (intBoard[32] == intBoard[46] & intBoard[46] == intBoard[34]
//				& intBoard[34] != 0& (validCorner2||intBoard[32]==1)){
//				return intBoard[32];
//		} else if (intBoard[34] == intBoard[32] & intBoard[32] == intBoard[48]
//				& intBoard[48] != 0& (validCorner2||intBoard[34]==1)){
//				return intBoard[34];
//		}
//		return 0;
	}

	// print do tabuleiro
	public void Print() {
		if(validCorner1==false)
			System.out.println("[-]");
		else
			System.out.println("[V]");
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < 7; y++) {
				System.out.print("|");
				if (intBoard[y + (size * x)] != 0)
					if (intBoard[y + (7 * x)] == 1) {// jogador 1
						if ((y + (size * x)) < 10)
							System.out.print(" [" + (y + (size * x)) + "] ");
						else
							System.out.print(" [" + (y + (size * x)) + "]");
					} else if (intBoard[y + (7 * x)] == 2) { // jogador 2
						if ((y + (size * x)) < 10)
							System.out.print(" (" + (y + (size * x)) + ") ");
						else
							System.out.print(" (" + (y + (size * x)) + ")");
					} else
						System.out.print("     ");
				else {
					if ((y + (size * x)) < 10)
						System.out.print("  " + (y + (size * x)) + "  ");
					else
						System.out.print("  " + (y + (size * x)) + " ");
				}
				System.out.print("|");

			}
			System.out.println();
		}
		if(validCorner2==false)
			System.out.println("                                              "+"(-)");
		else
			System.out.println("                                              "+"(V)");
	}

	public int[] getPiecePosition(int player) {
		int k = 0;
		int array[] = new int[5];
		for(int i = 0; i<intBoard.length;i++)
		{
			if(intBoard[i]==player)
				{
					array[k]=i;
					k++;
				}
		}
		return array;
	}

}
