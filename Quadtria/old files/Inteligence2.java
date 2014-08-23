
package Inteligence;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

import Quadtria.*;


/**
 *Alpha Beta
 *
 */
public class Inteligence {
    public Node rootNode;
    public int nodenum=0;
    public int totalnodenum=0;
    private boolean DEBUG=false;
    private boolean DEBUGNODENO=false;
    private boolean DEBUGBOARD=false;
    private int minmaxlevel=3; // nível da depth da arvore de minimax (na realidade o minimax é sempre um nivel a mais do que este valor). SE FOR 1 CPUVSCPU NAO TERMINA
    public Inteligence() {
    }

    public Inteligence(Board b) {
    	Board newB = new Board(b);
    	rootNode = new Node(newB);
    }


    public int[] Move() {
        int max = -1000;
        Node bestNode= new Node();
        Vector<Integer> defBoard;
        if(rootNode.board.intMoveSymbol==1)
        	defBoard = new Vector<Integer>(rootNode.board.getpecasJogo(1));
        else
        	defBoard = new Vector<Integer> (rootNode.board.getpecasJogo(2));
        for (int x = 0; x < 5; x++) {
        	////////////////////////////////////
            if(DEBUG)
            System.out.println("A verificar Na Posicao: "+defBoard.get(x));
            ///////////////////////////////////
        	Vector<Integer> possible = rootNode.board.connectionsGet(defBoard.get(x));
        	for(int i = 0; i<possible.size();i++){
            	Node n = new Node();
                n.copy(rootNode);
                if (n.board.hipoMove(n.board.getIntBoard(),defBoard.get(x),possible.get(i), n.board.intMoveSymbol)) {//mudança aki
                		/////////////////////////////////////////////////
                		if(DEBUG)
                		System.out.println("Mover Para: "+possible.get(i));
                		////////////////////////////////////////////////
                		rootNode.AddChildren(n);
		                n.moveBox[0] = defBoard.get(x);
		                n.moveBox[1] = possible.get(i);
		                n.Move(n.moveBox[0], n.moveBox[1]);
		                ///////////////////////////////////
                		if(DEBUGBOARD)
                			n.board.Print();
		                if(DEBUG)
		            	new Scanner(System.in).nextLine();
		                ////////////////////////////////////
		                int val = minimaxAB(n, true, minmaxlevel, -1000, 1000);
		                ///////////////////////////////////
		                if(DEBUG && DEBUGNODENO){
		                System.out.println("Total de Jogadas: "+nodenum);
		                nodenum=0;
		            	new Scanner(System.in).nextLine();
		                }
		                //////////////////////////////////
		                if (val > max) {
		                    max = val;
		                    bestNode = n;
		                    
		            }
	            }
        	}
        }
        /////////////////////////
        if(DEBUGNODENO)
            System.out.println("Total de Jogadas(Nodes): "+totalnodenum);
        ////////////////////////
        return bestNode.moveBox;
        
    }


    private int minimaxAB(Node node, boolean min, int depth, int alpha, int beta) {
    	int result = boardPoint(node);
    	if(result!=0)
    	{
    		node.point = result;
            return node.point;
    	}
    	if (/*boardPoint(node) != -2 ||*/ depth == 0) {
    		/////////////////////////////////////////
    		if (DEBUGNODENO && depth <= 0) {
            	totalnodenum++;
            	if(DEBUG)
            	nodenum++;
            }
    		/////////////////////////////////////////
           node.point = heuristic(node);
           return node.point;
        } else {
        	Vector<Integer> defBoard;
            if (min == true) {
            	  
                if(node.board.intMoveSymbol==1)
                  	defBoard = new Vector<Integer> (node.board.getpecasJogo(1));
                  else
                	defBoard = new Vector<Integer> (node.board.getpecasJogo(2));
            	 for (int x = 0; x < 5; x++) {   
                    Vector<Integer> possible = node.board.connectionsGet(defBoard.get(x));  
            	 ///////////////////////////////////////////////////////////////////////	 
                  if(DEBUGBOARD){
                  System.out.println("------------------------------");
                  System.out.println("A Simular Move: "+defBoard.get(x)+" Que é o "+x+" ramo");
                  node.board.Print();
                  System.out.println("------------------------------");
            	 }
                 //////////////////////////////////////////////////////////////////////
                  for(int i = 0; i<possible.size();i++){
	                    Node n = new Node();
	                    n.copy(node);
	                    if (n.board.hipoMove(n.board.getIntBoard(),defBoard.get(x),possible.get(i), n.board.intMoveSymbol)) {//mudança aki
	                        node.AddChildren(n);
	                        n.moveBox[0] = defBoard.get(x);
	                        n.moveBox[1] = possible.get(i);
	                        n.Move(n.moveBox[0], n.moveBox[1]);         
	                        ////////////////////////////////////////////
	                        if(DEBUGBOARD){
	                        System.out.println("**********************");
	                        n.board.Print();
	                    	}
	                        if(DEBUG) {
	                        	String prefix="    ";
	                        	for(int j=0;j<=depth;j++)
	                        	{
	                        		prefix=prefix.substring(0,prefix.length()-1);
	                        	}
	            				/*for(int j=0;i<5;j++){
	            					System.out.println(defBoard.get(j));
	            					 new Scanner(System.in).nextLine();
	            				}*/
	                        System.out.println(prefix+"Enemy Moving:"+ n.moveBox[0]+"-"+n.moveBox[1]+" depth -"+depth);
	                        }
	                        if(DEBUGBOARD)
	                        new Scanner(System.in).nextLine();
	                        ////////////////////////////////////////////
	                        int val = minimaxAB(n, false, depth - 1, alpha, beta);
	
	                        if (val < beta) {
	                            beta = val;
	                            n.parent.point = val;
	                        }
                    	}
                    }
                }
            	if(DEBUG)
            	{
            		System.out.println("+++++++++++++++++++");
            		System.out.println(beta);
            		System.out.println("+++++++++++++++++++");
            	}
                return beta;
            }

            if (min == false) {
                if(node.board.intMoveSymbol==1)
                	defBoard = new Vector<Integer> (node.board.getpecasJogo(1));
                else
              	defBoard = new Vector<Integer> (node.board.getpecasJogo(2));
            	for (int x = 0; x < 5; x++) {   
                    
                    Vector<Integer> possible = node.board.connectionsGet(defBoard.get(x));
                    ////////////////////////////////////////////////////
                    if(DEBUGBOARD){
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    System.out.println("A Simular Move: "+defBoard.get(x)+" Que é o "+x+" ramo");
                    node.board.Print();
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    ///////////////////////////////////////////////////
                    }
                    for(int i = 0; i<possible.size();i++){
  	                    Node n = new Node();
  	                    n.copy(node);
  	                    if (n.board.hipoMove(n.board.getIntBoard(),defBoard.get(x),possible.get(i), n.board.intMoveSymbol)) {//mudança aki
  	                    		node.AddChildren(n);
	  	                        n.moveBox[0] = defBoard.get(x);
	  	                        n.moveBox[1] = possible.get(i);
	  	                        n.Move(n.moveBox[0], n.moveBox[1]);
	  	                        ////////////////////////////////////////
	  	                        if(DEBUGBOARD){
	  	                        System.out.println("+++++++++++++++++++++");
	  	                        n.board.Print();
	  	                        }
	  	                        if (DEBUG){
		                        	String prefix="    ";
		                        	for(int j=0;j<=depth;j++)
		                        	{
		                        		prefix=prefix.substring(0,prefix.length()-1);
		                        	}
	  	                        System.out.println(prefix+"Me Moving:"+ n.moveBox[0]+"-"+n.moveBox[1]+" depth - "+depth);
	  	                        }
	  	                        if(DEBUGBOARD)
	  	                        new Scanner(System.in).nextLine();
		  	                    ////////////////////////////////////////
	  	                      int val = minimaxAB(n, true, depth - 1, alpha, beta);
	                        if (val > alpha) {
	                            alpha = val;
	                            n.parent.point = val;
	                        }
  	                    }
                    }

                }
            	if(DEBUG){
        		System.out.println("*******************");
        		System.out.println(alpha);
        		System.out.println("*******************");
            	}
                return alpha;

            }

        }
        return -1000;


    }


    private int boardPoint(Node n) {
    		if (n.board.CheckCondition() == 1) {
	    		 if (n.board.intMoveSymbol==1)
	    			 return -999;
	    		 else
	    			 return 999;
	        }
	        if (n.board.CheckCondition() == 2) {
	        	 if (n.board.intMoveSymbol==1)
	        		 return 999;
	        	 else
	        		 return -999;
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

				if (node.board.hipoMove(node.board.getIntBoard(),
						pecaInicial, pecaFinal, playerNo)) {//prnt != null) {
					int[] prnt = node.board.possMove(node.board.getIntBoard(),
							pecaInicial, pecaFinal, playerNo);
					//printBoard(prnt);
					possibilities.add(prnt);
				}
			}
		}
		return possibilities;
	}
    
    private int heuristic(Node n)
    {
        int playerpiece=0;
        int cornerfinish=0;
        int piecenum[]= new int[2];
        //boolean valid=false;
        //int enemypiece=0;
        //Vector<int[]> mypossibilities= new Vector<int[]>();
        //Vector<int[]> enemypossibilities= new Vector<int[]>();
        /*Verifica quem é a jogar e verifica quas as jogadas possiveis do jogador que jogou por ultimo, visto que ele muda de jogador no vim do move e interessa o ultimo jogador a ter jogado*/
        if(n.board.intMoveSymbol==1){
        	n.board.intMoveSymbol=2;
        	playerpiece=getNodePossibilities(n,2).size();
        	n.board.intMoveSymbol=1;
        	if(n.board.validCorner2)// verifica se pode colocar peças no quadrante original se puder ganha pontos
        		cornerfinish+=5;
        	if(n.board.validCorner1)// se o adversario puder, perde pontos
        		cornerfinish-=5;
        	piecenum=n.board.numMax(1);
        	if(n.board.validCorner1==true&&piecenum[1]>piecenum[0])
        		{
        			piecenum[0]=piecenum[1];
        			//valid=true;
        		}
        	/*switch(n.board.inQuad(n.moveBox[1]))
        	{
        	case 1: enemypiece=n.board.numPiece1Quad(1);break;
        	case 2: enemypiece=n.board.numPiece2Quad(1);break;
        	case 3: enemypiece=n.board.numPiece3Quad(1);break;
        	case 4: enemypiece=n.board.numPiece4Quad(1);break;
        	}*/
        }
        else {
        	n.board.intMoveSymbol=1;
        	playerpiece=getNodePossibilities(n,1).size();
        	n.board.intMoveSymbol=2;
        	if(n.board.validCorner2)// verifica se pode colocar peças no quadrante original se o adversario puder perde pontos
        		cornerfinish-=5;
        	if(n.board.validCorner1)// se puder, ganha pontos
        		cornerfinish+=5;
        	piecenum=n.board.numMax(1);
        	if(n.board.validCorner2==true&&piecenum[1]>piecenum[0])
        		{
        			piecenum[0]=piecenum[1];
        			//valid=true;
        		}
        	/*switch(n.board.inQuad(n.moveBox[1]))
        	{
        	case 1: enemypiece=n.board.numPiece1Quad(2);break;
        	case 2: enemypiece=n.board.numPiece2Quad(2);break;
        	case 3: enemypiece=n.board.numPiece3Quad(2);break;
        	case 4: enemypiece=n.board.numPiece4Quad(2);break;
        	}*/
        }
        piecenum[0]++;
        if(DEBUG){
        System.out.println("	Possibilidades: "+playerpiece);
        //System.out.println("	Enemy Pieces: "+enemypiece);
        }
        if(DEBUGBOARD){
        	n.board.Print();
        	new Scanner(System.in).nextLine();
        }
        /*if(valid){
        int total=playerpiece*piecenum[0]+enemypiece+cornerfinish;
        if(DEBUG)
            System.out.println("	Total: "+total);
    	return total;
        }
        else{
        int total=(playerpiece*10*(piecenum[0]*10))/piecenum[1]+(10*enemypiece)+cornerfinish*10;
        if(DEBUG)
            System.out.println("	Total: "+total);
        return total;
        }*/
        int total=playerpiece*piecenum[0]+cornerfinish;
        if(DEBUG)
            System.out.println("	Total: "+total);
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
