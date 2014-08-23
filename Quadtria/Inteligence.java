package Inteligence;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import Quadtria.*;

/**
 * Alpha Beta
 * 
 */
public class Inteligence {
	public Node rootNode;
	public int nodenum = 0;
	public int totalnodenum = 0;
	private boolean DEBUG = false;
	private boolean DEBUGNODENO = true;
	private boolean DEBUGBOARD = false;
	private int maxNodes=50000;
	private int minmaxlevel =5; // nível da depth da arvore de minimax
	private Vector<Integer[]> minplays;
	private Vector<Integer> minvals;
	private Vector<Integer[]> maxplays;
	private Vector<Integer> maxvals;

	int nivel=10;
	int prev_nivel=10;
	public Inteligence() {
	}

	public Inteligence(Board b,int dificulty) {
		Board newB = new Board(b);
		rootNode = new Node(newB);
		minmaxlevel =dificulty;
		minplays = new Vector<Integer[]>();
		maxplays = new Vector<Integer[]>();
		minvals = new Vector<Integer>();
		maxvals = new Vector<Integer>();
	}

	public Integer[] Move() {
		Node bestNode = new Node();
		/*Vector<Integer> defBoard;
		if (rootNode.board.intMoveSymbol == 1)
			defBoard = new Vector<Integer>(rootNode.board.getpecasJogo(1));
		else
			defBoard = new Vector<Integer>(rootNode.board.getpecasJogo(2));*/
		/*for (int x = 0; x < 5; x++) {
			// //////////////////////////////////
			if (DEBUG)
				System.out
						.println("A verificar Na Posicao: " + defBoard.get(x));
			// /////////////////////////////////
			Vector<Integer> possible = rootNode.board.connectionsGet(defBoard
					.get(x));
			for (int i = 0; i < possible.size(); i++) {
				Node n = new Node();
				n.copy(rootNode);
				if (n.board.hipoMove(n.board.getIntBoard(), defBoard.get(x),
						possible.get(i), n.board.intMoveSymbol)) {// mudança aki
					// ///////////////////////////////////////////////
					if (DEBUG)
						System.out.println("Mover Para: " + possible.get(i));
					// //////////////////////////////////////////////
					rootNode.AddChildren(n);
					n.moveBox[0] = defBoard.get(x);
					n.moveBox[1] = possible.get(i);
					n.Move(n.moveBox[0], n.moveBox[1]);
					// /////////////////////////////////
					if (DEBUGBOARD)
						n.board.Print();
					if (DEBUG)
						new Scanner(System.in).nextLine();
					// //////////////////////////////////
					int val = minimaxAB(n, true, minmaxlevel, -1000, 1000);
					// /////////////////////////////////
					if (DEBUG && DEBUGNODENO) {
						System.out.println("Total de Jogadas: " + nodenum);
						nodenum = 0;
						new Scanner(System.in).nextLine();
					}
					// ////////////////////////////////
					if (val > max) {
						max = val;
						bestNode = n;

					}
					if(val==-1000&&prev_nivel<nivel){
						prev_nivel=nivel;
						System.out.println("New nivel:"+nivel);
						bestNode = n;
					}
				}
			}
		}*/
		minimaxAB(rootNode, false, minmaxlevel, -1000, 1000);
		for(int i=0;i<rootNode.childrenNode.size();i++)
		{
			if(rootNode.childrenNode.get(i).point==rootNode.point)
				{
					bestNode = rootNode.childrenNode.get(i);
					break;
				}
		}
		//////////////////////////
		if (DEBUGNODENO)
			System.out.println("Total de Jogadas(Nodes): " + totalnodenum);
		//////////////////////////
		/*if (DEBUG)
			new Scanner(System.in).nextLine();*/
		return bestNode.moveBox;

	}

	private int minimaxAB(Node node, boolean min, int depth, int alpha, int beta) {
		int result = boardPoint(node);
		if (result!=0 || depth == 0 ||totalnodenum>maxNodes) {
				totalnodenum++;
			//////////////////////////////////////////
				if (DEBUG)
					nodenum++;
			//////////////////////////////////////////
			if(DEBUG&&depth!=0&&boardPoint(node) == -1000){
				if (depth < nivel)
					nivel = depth;
				System.out.println("was not by depth");
			}
			if(result!=0)
				node.point = result;
			else
				node.point = heuristic(node);
			return node.point;
		} else {
			
			Vector<Integer> defBoard;
			if (node.board.intMoveSymbol == 1)
				defBoard = new Vector<Integer>(node.board.getpecasJogo(1));
			else
				defBoard = new Vector<Integer>(node.board.getpecasJogo(2));
			if (min == true) {
				boolean STOP=false;
				
				
				for(int i = 0; i < minplays.size();i++)
				{
					if (defBoard.contains(minplays.get(i)[0])&&node.board.hipoMove(node.board.getIntBoard(),
							minplays.get(i)[0], minplays.get(i)[1],
							node.board.intMoveSymbol)) {
						Node n = new Node();
						n.copy(node);
						node.AddChildren(n);
						n.moveBox[0] = minplays.get(i)[0];
						n.moveBox[1] = minplays.get(i)[1];
						n.board.SetMove(n.moveBox[0], n.moveBox[1]);
						if (DEBUG)
							System.out.println("Enemy Moving min:"
									+ n.moveBox[0] + "-" + n.moveBox[1]);
						int val = minimaxAB(n, false, depth - 1, alpha,
								beta);

						if (val < beta) {
						
							beta = val;
							n.parent.point = val;
						} 

						if (beta <= alpha) {
							if(DEBUG)
								System.out.println("this one was pruned by the system");
							updateMinplay(node,n.moveBox,val);
							STOP=true;
							break;
						} 
					}
				}
				
				for (int x = 0; x < 5&&STOP==false; x++) {
					Vector<Integer> possible = node.board
							.connectionsGet(defBoard.get(x));
					// /////////////////////////////////////////////////////////////////////
					if (DEBUGBOARD) {
						System.out.println("------------------------------");
						System.out.println("A Simular Move: " + defBoard.get(x)
								+ " Que é o " + x + " ramo");
						node.board.Print();
						System.out.println("------------------------------");
					}
					// ////////////////////////////////////////////////////////////////////
					for (int i = 0; i < possible.size(); i++) {
						if (!containsMinPlay(node,defBoard.get(x),possible.get(i))&&node.board.hipoMove(node.board.getIntBoard(),
								defBoard.get(x), possible.get(i),
								node.board.intMoveSymbol)) {// mudança aki
							Node n = new Node();
							n.copy(node);
							node.AddChildren(n);
							n.moveBox[0] = defBoard.get(x);
							n.moveBox[1] = possible.get(i);
							n.board.SetMove(n.moveBox[0], n.moveBox[1]);
							/////////////////////////////////////////////
							if (DEBUGBOARD) {
								System.out.println("**********************");
								n.board.Print();
							}
							if (DEBUG)
								System.out.println("Enemy Moving:"
										+ n.moveBox[0] + "-" + n.moveBox[1]);
							if (DEBUGBOARD)
								new Scanner(System.in).nextLine();
							/////////////////////////////////////////////
							int val = minimaxAB(n, false, depth - 1, alpha,
									beta);

							if (val < beta) {
							
								beta = val;
								n.parent.point = val;
							} 

						
							if (beta <= alpha) {
								if(DEBUG)
									System.out.println("this one was pruned");
								insertMinplay(n.moveBox[0], n.moveBox[1],val);
								STOP=true;
								break;
							} 
						}
					}
				}
				return beta;
			}

			else if (min == false) {
				boolean STOP=false;
				
				for (int i = 0; i < maxplays.size(); i++) {
					
					if (defBoard.contains(maxplays.get(i)[0])&&node.board.hipoMove(node.board.getIntBoard(),
							maxplays.get(i)[0], maxplays.get(i)[1],
							node.board.intMoveSymbol)) {
						Node n = new Node();
						n.copy(node);
						node.AddChildren(n);
						n.moveBox[0] = maxplays.get(i)[0];
						n.moveBox[1] = maxplays.get(i)[1];
						n.board.SetMove(n.moveBox[0], n.moveBox[1]);
						if (DEBUG)
							System.out.println("Me Moving max:" + n.moveBox[0]
									+ "-" + n.moveBox[1]);
						int val = minimaxAB(n, true, depth - 1, alpha, beta);
						if (val > alpha) {
							alpha = val;
							n.parent.point = val;
						} 
						
						if (beta <= alpha) {
							if(DEBUG)
								System.out.println("this one was pruned by the system");
							updateMaxplay(node,n.moveBox,val);
							STOP=true;
							break;
						} 

					}
				}
				for (int x = 0; x < 5&&STOP==false; x++) {

					Vector<Integer> possible = node.board
							.connectionsGet(defBoard.get(x));
					/////////////////////////////////////////////////////
					if (DEBUGBOARD) {
						System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						System.out.println("A Simular Move: " + defBoard.get(x)
								+ " Que é o " + x + " ramo");
						node.board.Print();
						System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
					}
					////////////////////////////////////////////////////
					for (int i = 0; i < possible.size(); i++) {
						if (!containsMaxplay(node,defBoard.get(x),possible.get(i))&&node.board.hipoMove(node.board.getIntBoard(),
								defBoard.get(x), possible.get(i),
								node.board.intMoveSymbol)) {// mudança aki
							Node n = new Node();
							n.copy(node);
							node.AddChildren(n);
							n.moveBox[0] = defBoard.get(x);
							n.moveBox[1] = possible.get(i);
							n.board.SetMove(n.moveBox[0], n.moveBox[1]);
							/////////////////////////////////////////
							if (DEBUGBOARD) {
								System.out.println("+++++++++++++++++++++");
								n.board.Print();
							}
							if (DEBUG)
								System.out.println("Me Moving:" + n.moveBox[0]
										+ "-" + n.moveBox[1]);
							if (DEBUGBOARD)
								new Scanner(System.in).nextLine();
							/////////////////////////////////////////
							int val = minimaxAB(n, true, depth - 1, alpha, beta);
							if (val > alpha) {
								alpha = val;
								n.parent.point = val;
							} 
							
							if (beta <= alpha) {
								if(DEBUG)
									System.out.println("this one was pruned");
								insertMaxplay(n.moveBox[0], n.moveBox[1],val);
								STOP=true;
								break;
							} 

						}
					}

				}
				return alpha;

			}

		}
		return -1000;

	}

	private boolean containsMaxplay(Node node,Integer prev, Integer next) {
		for(int i=0;i<node.maxplays.size();i++)
		{
			if(prev==node.maxplays.get(i)[0]&&next==node.maxplays.get(i)[1])
				return true;
		}
		return false;
	}

	private void updateMaxplay(Node node,Integer[] movebox, int val) {
		for(int i=0;i<maxplays.size();i++)
		{
			if(movebox[0]==maxplays.get(i)[0]&&movebox[1]==maxplays.get(i)[1])
			{
				/*for(int k=0;k<maxvals.size();k++)
				{
					if(maxvals.get(k)>val)
					{
						maxplays.remove(i);
						maxvals.remove(i);
						maxvals.add(k, val);
						maxplays.add(k, play);
						return;
					}
				}*/
				return;
			}
		}
		node.maxplays.add(movebox);
	}

	private boolean containsMinPlay(Node node,Integer prev, Integer next) {
		for(int i=0;i<node.minplays.size();i++)
		{
			if(prev==node.minplays.get(i)[0]&&next==node.minplays.get(i)[1])
					return true;
		}
		return false;
	}

	private void updateMinplay(Node node,Integer[]movebox, int val) {
		for(int i=0;i<minplays.size();i++)
		{
			if(movebox[0]==minplays.get(i)[0]&&movebox[1]==minplays.get(i)[1])
			{
				/*for(int k=0;k<minvals.size();k++)
				{
					if(minvals.get(k)>val)
					{
						minplays.remove(i);
						minvals.remove(i);
						minvals.add(k, val);
						minplays.add(k, play);
						return;
					}
				}*/
			}
		}
		node.minplays.add(movebox);
	}

	private void insertMinplay(int prev, int next, int val) {
		Integer[] play = new Integer[2];
		play[0]=prev;
		play[1]=next;
		if(minplays.isEmpty())
		{
			minplays.add(play);
			minvals.add(val);
			return;
		}
		for(int k=0;k<minvals.size();k++)
				{
					if(minvals.get(k)>val)
					{
						minplays.add(k, play);
						minvals.add(k, val);
						return;
					}
				}
	}

	private void insertMaxplay(int prev, int next, int val) {
		Integer[] play = new Integer[2];
		play[0]=prev;
		play[1]=next;
		if(maxplays.contains(play))
			return;
		if(maxplays.isEmpty())
		{
			maxplays.add(play);
			maxvals.add(val);
			return;
		}
		for(int k=0;k<maxvals.size();k++)
				{
					if(maxvals.get(k)<val)
					{
						maxplays.add(k, play);
						maxvals.add(k, val);
						return;
					}
				}
	}
	private int boardPoint(Node n) {
		if (rootNode.board.intMoveSymbol == 1) {
			if (n.board.CheckCondition() == 1) {
				return 999;
			}
			if (n.board.CheckCondition() == 2) {
				return -999;
			}
		} else {
			if (n.board.CheckCondition() == 1) {
				return -999;
			}
			if (n.board.CheckCondition() == 2) {
				return 999;
			}
		}
		return 0;

	}

	private Vector<int[]> getNodePossibilities(Node node, int playerNo) {

		Iterator<Integer> it = null;
		if (playerNo == 1) {
			it = node.board.getpecasJogo(1).iterator();
		} else if (playerNo == 2) {
			it = node.board.getpecasJogo(2).iterator();
		} else {
			System.out.println("No player indicated for possibilities");
		}
		Vector<int[]> possibilities = new Vector<int[]>();

		while (it.hasNext()) {
			int pecaInicial = it.next();

			Vector<Integer> pecas = node.board.connectionsGet(pecaInicial);
			int ind = 0;
			for (ind = 0; ind < pecas.size(); ind++) {
				int pecaFinal = pecas.get(ind);

				if (node.board.hipoMove(node.board.getIntBoard(), pecaInicial,
						pecaFinal, playerNo)) {// prnt != null) {
					int[] prnt = node.board.possMove(node.board.getIntBoard(),
							pecaInicial, pecaFinal, playerNo);
					possibilities.add(prnt);
				}
			}
		}
		return possibilities;
	}

	private int heuristic(Node n) {
		int playerpiece = 0;
		int cornerfinish = 0;
		int piecenum[] = new int[2];
		int enemypiece=0;
		int enemypiecenum[]=new int[2];
		boolean valid=false;

		if (rootNode.board.intMoveSymbol == 2) {
			playerpiece = getNodePossibilities(n, 2).size();
			rootNode.board.intMoveSymbol = 1;
			enemypiece = getNodePossibilities(n, 1).size();
			rootNode.board.intMoveSymbol = 2;
			if (n.board.validCorner2)
				cornerfinish += 5;
			if (n.board.validCorner1)
				cornerfinish -= 5;
			piecenum = n.board.numMax(2);
			enemypiecenum = n.board.numMax(1);
			if (n.board.validCorner2 == true && piecenum[1] > piecenum[0]) {
				piecenum[0] = piecenum[1];
				valid=true;
			}
			if (n.board.validCorner1 == true && enemypiecenum[1] > enemypiecenum[0]) 
				enemypiecenum[0] = enemypiecenum[1];
		} else {
			playerpiece = getNodePossibilities(n, 1).size();
			rootNode.board.intMoveSymbol = 2;
			enemypiece = getNodePossibilities(n, 2).size();
			rootNode.board.intMoveSymbol = 1;
			if (n.board.validCorner2)
				cornerfinish -= 5;
			if (n.board.validCorner1)
				cornerfinish += 5;
			piecenum = n.board.numMax(1);
			enemypiecenum = n.board.numMax(2);
			if (n.board.validCorner1 == true && piecenum[1] > piecenum[0]) {
				piecenum[0] = piecenum[1];
				valid=true;
			}
			if (n.board.validCorner2 == true && enemypiecenum[1] > enemypiecenum[0]) 
				enemypiecenum[0] = enemypiecenum[1];
		}
		piecenum[0]++;
		if (DEBUG) {
			System.out.println("	Possibilidades: " + playerpiece);
			System.out.println("	Possibilidades Enimigas: "+ enemypiece);
		}
		if (DEBUGBOARD) {
			n.board.Print();
			new Scanner(System.in).nextLine();
		}
		int total=0;
		if(valid)
			total = playerpiece * piecenum[0] - enemypiece * enemypiecenum[0] + cornerfinish;
		else
			total = playerpiece * piecenum[0] - enemypiece * enemypiecenum[0] -piecenum[1]*3 + cornerfinish;
		if (DEBUG)
			System.out.println("	Total: " + total);
		return total;
	}

	public void printBoard(int[] printBoard) {
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
}
