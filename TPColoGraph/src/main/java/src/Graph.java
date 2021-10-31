package src;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private int numReg;
	private List<Integer>[] adj;
	
	public int getvCount() {
		return numReg;
	}

	public Graph(int numReg) {
		this.numReg = numReg;
		adj = (List<Integer>[]) new List[numReg];
		for (int i = 0; i < numReg; i++)
			adj[i] = new ArrayList<Integer>();
	}

	public void addEdge(int i, int j) {
		adj[i].add(j);
		adj[j].add(i);
	}


	
	public boolean hasEdge(int i, int j) {
		return adj[i].contains(j);
	}

	public List<Integer> neighbours(int reg) {
		return adj[reg];
	}

	public void printGraph() {
		for (int i = 1; i < numReg; i++) {
			List<Integer> edges = neighbours(i);
			//System.out.print(i + ": ");
			for (int j = 0; j < edges.size(); j++) {
			//	System.out.print(edges.get(j) + " ");
			}
			//System.out.println();
		}
	}
}