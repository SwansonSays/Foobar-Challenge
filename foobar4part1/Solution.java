package foobar4part1;

import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static int[] solution(int[][] times, int times_limit) {
    	int size = times.length;
    	int totalBunnies = size - 2;
    	boolean visited[] = new boolean[size];
    	ArrayList<Integer> rescue = new ArrayList<Integer>();
    	
    	if(totalBunnies < 1 || times_limit > 999 || times_limit < 0) {
    		return new int[] {};
    	}
    	
    	//If there is a negative cycle all bunnies can be rescued
    	if(isNegCycle(times, size)) {
    		int fastRescued[] = new int[totalBunnies];
    		for(int i = 0; i < totalBunnies; i++) {
    			fastRescued[i] = i;
    		}
    		return fastRescued;
    	}
    	
    	
    	//Depth-First-Search of tree starting with node 0
    	visited[0] = true;
    	for(int i = 1; i < size - 1; i++) {
    		rescue = dfs(i, times, visited, rescue, times_limit - times[0][i], new ArrayList<Integer>());
    	}
    	
    	//Sort the array list of bunnies returned from the depth first search
    	//and convert to an int array and return
    	Collections.sort(rescue);
    	int rescued[] = new int[rescue.size()];
    	for(int i = 0; i < rescue.size(); i++) {
    		rescued[i] = rescue.get(i);
    	}

    	return rescued;
    }
    
    //Returns are probably wrong but need a way to get the rescue list out of the recusrive search
    public static ArrayList<Integer> dfs(int node, int times[][], boolean visited[], ArrayList<Integer> rescue, int times_limit, ArrayList<Integer> currentList) {
    	
    	
    	//Reached Bulkhead without time left
    	if(node == times.length - 1 && times_limit < 0) {
    		return rescue;
    	}
    	
    	//Reached Bulkhead with time remaining
    	if(node == times.length - 1 && times_limit >= 0) {
    		//If more bunnies are rescued in the currentList, replace rescue with current list
    		if(currentList.size() > rescue.size()) {
    			rescue.clear();
    			for(int bunny : currentList) {
    				rescue.add(bunny);
    			}
    		}
    		
    		return rescue;
    	}
    	
    	//Already Visited Node
    	if(visited[node]) {
    		return rescue;
    	}
    	
    	
    	visited[node] = true;
    	
    	//Add bunny to rescue list
    	currentList.add(node - 1);

    	//Recursively visit next node
    	for(int i = 1; i < times.length; i++) {
    		if(i != node) {
    			rescue = dfs(i, times, visited, rescue, times_limit - times[node][i], currentList);
    		}
    	}
    	
    	//Bunny cannot be saved wit time remaining so remove from list
    	currentList.remove(currentList.size() - 1); 
    	visited[node] = false;
    	return rescue;
    }
    
    //Floyd-Warshall Algo to detect negative cycles and reduce matrix to shortest pairs:
    //Floyd has a higher time complexity than Bellman-Ford but the graph will 
    //always be small and dense so it will be more efficient than Bellman
    public static boolean isNegCycle(int times[][], int size) {
    	for(int k = 0; k < size; k++) {
    		for(int i = 0; i < size; i++) {
    			for(int j = 0; j < size; j++) {
    				if(times[i][k] + times[k][j] < times[i][j]) {
    					times[i][j] = times[i][k] + times[k][j];
    				}
    			}
    		}
    	}

    	//If the distance from a node to itself is -0 a negative cycle exists
    	for(int i = 0; i < size; i++) {
    		if(times[i][i] < 0) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static void printRow(int arr[]) {
    	System.out.print("[");
    	for (int i = 0; i < arr.length; i++) {
    		if(i == arr.length - 1) {
    			System.out.print(arr[i]);
    		} else {
    			System.out.print(arr[i] + ", ");
    		}
    	}
    	System.out.print("]\n");
    }
    
    public static void printMatrix(int arr[][]) {
    	System.out.println("\nLength = " + arr.length);
    	for(int row[] : arr) {
    		printRow(row);
    	}
    	System.out.print("\n");
    }
}