package foobar2part1;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Math;

public class Solution{
	//Returns the number of moves needed for a chess knight to move from src square to dest square
	//If no legal moves available to reach destination return -1.
	public static int solution(int src, int dest) {
		if(src < 0 || src > 63 || dest < 0 || dest > 63) {
			return -1;
		}
		//Valid knight moves add to square
		int[] validMoves = {17, 15, 10, 6, -6, -10, -15, -17};
		
		//List of visited Nodes
		ArrayList<Integer> visitedSquares = new ArrayList<>();
		
		//Queue of nodes to be visited starting with source node
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(src, 0));
		
		while(!q.isEmpty()) {
			//check fist node in queue and return if it is destination
			Node node = q.remove();
			if(node.getSquare() == dest) return node.getDist();
			
			
			//Visit node if hasn't been visited
			if(!visitedSquares.contains(node.getSquare())) {
				visitedSquares.add(node.getSquare());
				
				//iterate over all possible moves checking whether they are valid
				//and add valid moves to the queue
				for(int i = 0; i < 8; i++) {
					Node tempNode = isValid(node.getSquare(), node.getSquare() + validMoves[i]);
					if(tempNode != null) {
						tempNode.setDist(node.getDist() + 1);
						q.add(tempNode);
					}
				}
			}
		}
		
		return -1;
	}
	
	//returns Node for ending square if move is valid or a null node if invalid
	public static Node isValid(int start, int end ) {
		//invalid move
		if(end < 0 || end > 63) return null;
		
		//compute row and column of starting and ending square for move
		int startRow = start / 8;
		int startCol = start % 8;
		int endRow = end / 8;
		int endCol = end % 8;
		
		//returns node if the end square is 2 rows and 1 column away
		//or 1 row and 2 columns away, otherwise returns null
		if((Math.abs(startRow - endRow) == 2 && Math.abs(startCol - endCol) == 1)) {
			return new Node(end);
		}else if ((Math.abs(startRow - endRow) == 1 && Math.abs(startCol - endCol) == 2)) {
			return new Node(end);
		}else {
			return null;
		}
	}
	
	private static class Node{
		private int square;
		private int dist;
		
		public Node(int square ) {
			this.square = square;
		}
		
		public Node(int square, int dist ) {
			this.square = square;
			this.dist = dist;
		}
		
		public void setDist(int dist) {
			this.dist = dist;
		}
		
		public int getDist() {
			return this.dist;
		}
		
		public int getSquare() {
			return this.square;
		}
	}
}

