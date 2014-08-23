package Quadtria;

/*
 1     2      6     7   
    5            10
 3     4      8     9
 
 11    12    16     17   
    15           20
 13    14    18     19
 
 */
import java.util.Vector;

public class Position {
	String position;

	Vector<String> adjacent;


	Position(String pos,Vector<String> adj ){
		setPosition(pos);
		setAdjacent(adj);		
	}
	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	
	public Vector<String> getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(Vector<String> adjacent) {
		this.adjacent = adjacent;
	}

	public boolean isAdjacent(String pos){
		if(adjacent.indexOf(pos)==-1)
			return false;
		return true;
		
	}
}
