
package Inteligence;

import Quadtria.*;

import java.util.*;


public class Node {

    public Integer[] moveBox = new Integer[2];
    public int point;
    public Board board;
    public Node parent;
    public ArrayList<Node> childrenNode = new ArrayList<Node>();
	public Vector<Integer[]> minplays;
	public Vector<Integer[]> maxplays;

    public Node() {
        board = new Board();
        minplays = new Vector<Integer[]>();
        maxplays = new Vector<Integer[]>();
    }

    public Node(Board _board) {
        this.board = _board;
        minplays = new Vector<Integer[]>();
        maxplays = new Vector<Integer[]>();
    }

    public void AddChildren(Node node) {
        childrenNode.add(node);
        node.Parent(this);
    }

    private void Parent(Node node) {
        this.parent = node;
    }

    public void copy(Node n) {
        this.point=n.point;
        this.board.copy(n.board);
    }

    public void Print() {
        System.out.println();
        System.out.println("Point" + this.point);
        System.out.println("moveBox" + this.moveBox);
        this.board.Print();
        System.out.println();
    }
    
    public void Move(int prevNo, int newNo)
    {
    	this.board.SetMove(prevNo, newNo);
    }

}
