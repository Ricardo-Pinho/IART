package Quadtria;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// Board b= new Board();
		// b.Print();
		// TODO code application logic here
		boolean fast=false;
		int dificulty=3;
		Game game;
		System.out.println("##################");
		System.out.println("#####Quadtria#####");
		System.out.println("##################");
		Scanner in = new Scanner(System.in);
		 System.out.print("Enter Player 1 Name:");
		
		 String playerOneName = in.nextLine();//in.nextLine();
		 System.out.println("1) One Player Game");
		 System.out.println("2) Two Player Game");
		 System.out.println("3) CPU vs CPU");
		 System.out.print("Select one option:");
		 String option = in.nextLine();
		 if (option.equals("2")) {
		 System.out.print("Enter Player 2 Name:");
		 game = new Game(playerOneName, in.nextLine());
		 } else if(option.equals("1")){
		 game = new Game(playerOneName);
		 }
		 else{
			 game = new Game();
		 }
		//game = new Game("Jog1", "Jog2");
		do {
			 System.out.print("Number of games you want to play:");
			 game.outOff = in.nextInt();
			 if(game.noPlayer==3)
			 {
				 System.out.print("Do you want to play fast (Y/N)? ");
				 option=in.nextLine();
				 if(option.equals("Y")|option.equals("y"))
					 fast=true;
			 }
			do {
				game.NewBoard();
				 //Board.printIntBoard(game.gBoard.getIntBoard());
				int gCon = 0;
				int pNo = 0;

				// TODO eliminar
				pNo = 1;
				do {

					game.gBoard.Print();
					System.out.println(game.PlayerName(pNo) + "'s Move");
					if(game.noPlayer==3&fast==false) in.nextLine();
					int prevNo = 0;
					int boxNo = 0;
					if((game.noPlayer == 1 && pNo == 1) || game.noPlayer == 2)
					{
					System.out
							.print("To play, chose the position of the piece you want to move: ");
					prevNo = in.nextInt();
					System.out
							.print("Now chose the position where you want to place the piece: ");
					boxNo = in.nextInt();
					}
					if (boxNo == -1) {
						// PARA SELECIONAR E IMPRIMIR TODAS AS POSSIBILIDADES
						if (prevNo == -1) {
							printAllPoss(game, pNo);
						} else {
							printAllPoss(game, pNo, prevNo);
						}
						
					} else {
						if (game.noPlayer == 1 && pNo == 2 || game.noPlayer == 3) {
							 //System.out.println("test");
							AI a=null;
							if(pNo==2)
							a = (AI) game.player2;
							else
							a = (AI) game.player1;
							//System.out.println("test1");
							 Integer[] aiMove = a.NextMove(game.gBoard,dificulty,3);
							 //System.out.println("test2");
							 System.out.println("AI Move: "+aiMove[0]+"-"+aiMove[1]);
							 gCon = game.Move(aiMove[0],aiMove[1]); //AI nao funciona para já
							 if (pNo == 1)
									pNo = 2;
								else
									pNo = 1; 
								if (gCon != 0) {
									 game.gBoard.Print();
									 System.out.println();
									 if (gCon == 1 || gCon == 2) {
										 System.out.println(game.PlayerName(gCon) + " Wins");
										 game.Win(gCon);
										}
									 if (gCon == -1)
										 System.out.println(" Draw ");
									 break;
								 }
							}
							
							 else {
								/*int[] prnt = game.gBoard.hipoMove(game.gBoard.getIntBoard(),
										prevNo, boxNo, pNo,false);*/
								if (game.gBoard.hipoMove(game.gBoard.getIntBoard(),
										prevNo, boxNo, pNo)) {//prnt != null) {
									gCon = game.Move(prevNo,boxNo);
									//System.out.println("Victory?:"+game.gBoard.chechForWin());
									if (pNo == 1)
										pNo = 2;
									else
										pNo = 1;
									if (gCon != 0) {
											 game.gBoard.Print();
											 System.out.println();
											 if (gCon == 1 || gCon == 2) {
												 System.out.println(game.PlayerName(gCon) + " Wins");
												 game.Win(gCon);
												}
											 if (gCon == -1)
												 System.out.println(" Draw ");
											 break;
										 }
		
								} else {
									System.out
											.println("--------------------------------");
									System.out.println("\n Jogada impossivel \n");
									System.out
											.println("--------------------------------");
								}
							 }
					}

					// ---------------------------------------------------------------------------------------------
					// if (pNo == 1) {
					// pNo = 2;
					// } else {
					// pNo = 1;
					// }
					// game.gBoard.Print();
					// System.out.println();
					// System.out.println(game.PlayerName(pNo) + "'s Move");
					// System.out.print("To play, chose the position of the piece you want to move: ");
					// int prevNo = in.nextInt();
					// System.out.print("Now chose the position where you want to place the piece: ");
					// int boxNo = in.nextInt();
					// if (game.twoPlayer == false && pNo == 2) {
					// AI a = (AI) game.player2;
					// int[] aiMove = a.NextMove(game.gBoard);
					// System.out.print(aiMove);
					// gCon = game.Move(aiMove[0],aiMove[1]); AI nao funciona para já
					// }
					//
					// else {
					// gCon = game.Move(prevNo,boxNo);
					//
					// }
					//
					// System.out.println();
					// if (gCon == 3) {
					// System.out.println(":::Wrong Move Try Again:::");
					// if (pNo == 1) {
					// pNo = 2;
					// } else {
					// pNo = 1;
					// }
					//
					// } else if (gCon != 0) {
					// game.gBoard.Print();
					// System.out.println();
					// if (gCon == 1 || gCon == 2) {
					// System.out.println(game.PlayerName(pNo) + " Wins");
					// game.Win(pNo);
					// }
					// if (gCon == -1) {
					// System.out.println(" Draw ");
					// }
					// break;
					// }

				} while (true);
			} while (game.endGame());
			 System.out.println(game.PlayerName(1) + " Wins: " +
			 game.Score(1));
			 System.out.println(game.PlayerName(2) + " Wins: " +
			 game.Score(2));
			 System.out.println("Jogadas: "+game.gBoard.moveCount);
			 System.out.println("Out off : " + game.outOff + " games");
			 game.Reset();
			 System.out.println();
			 System.out.println("Want lose more?");
			 System.out.print("1 to continue or any other key to end:");
			 in.nextLine();
		} while (in.nextLine().equals("1"));

	}

	private static void printAllPoss(Game game, int pNo) {
		Vector<int[]> possibilities = getPossibilities(game, pNo);
		int ind = 0;
		System.out
				.println("---------------------Possibilities---------------------");
		for (ind = 0; ind < possibilities.size(); ind++) {
			game.gBoard.printIntBoard(possibilities.get(ind));
			System.out
					.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		System.out
				.println("--------------------------END--------------------------");
	}

	private static void printAllPoss(Game game, int pNo, int prevPos) {
		Vector<int[]> possibilities = getPossibilities(game, pNo, prevPos);
		int ind = 0;
		System.out
				.println("---------------------Possibilities---------------------");
		for (ind = 0; ind < possibilities.size(); ind++) {
			game.gBoard.printIntBoard(possibilities.get(ind));
			System.out
					.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
		System.out
				.println("--------------------------END--------------------------");
	}

	private static Vector<int[]> getPossibilities(Game game, int playerNo) {

		Iterator<Integer> it = null;
		if (playerNo == 1) {
			it = game.gBoard.getpecasJogo(1).iterator();
		} else if (playerNo == 2) {
			it = game.gBoard.getpecasJogo(2).iterator();
		} else {
			System.out.println("No player indicated for possibilities");
		}
		Vector<int[]> possibilities = new Vector<int[]>();

		while (it.hasNext()) {
			int pecaInicial = it.next();

			Vector<Integer> pecas = game.gBoard.connectionsGet(pecaInicial);
			int ind = 0;
			for (ind = 0; ind < pecas.size(); ind++) {
				int pecaFinal = pecas.get(ind);
				/*int[] prnt = game.gBoard.hipoMove(game.gBoard.getIntBoard(),
						pecaInicial, pecaFinal, playerNo,false);*/

				if (game.gBoard.hipoMove(game.gBoard.getIntBoard(),
						pecaInicial, pecaFinal, playerNo)) {//prnt != null) {
					int[] prnt = game.gBoard.possMove(game.gBoard.getIntBoard(),
							pecaInicial, pecaFinal, playerNo);
					possibilities.add(prnt);
				}
			}
		}
		return possibilities;
	}

	private static Vector<int[]> getPossibilities(Game game, int playerNo,
			int pecaInicial) {

	
		Vector<int[]> possibilities = new Vector<int[]>();

		Vector<Integer> pecas = game.gBoard.connectionsGet(pecaInicial);
		int ind = 0;
		for (ind = 0; ind < pecas.size(); ind++) {
			int pecaFinal = pecas.get(ind);
			/*int[] prnt = game.gBoard.hipoMove(game.gBoard.getIntBoard(), pecaInicial,
					pecaFinal, playerNo,false);*/

			if (game.gBoard.hipoMove(game.gBoard.getIntBoard(), pecaInicial,
					pecaFinal, playerNo)) {//prnt != null) {
				int[] prnt = game.gBoard.possMove(game.gBoard.getIntBoard(),
						pecaInicial, pecaFinal, playerNo);
				possibilities.add(prnt);

			}
		}

		return possibilities;
	}
}
