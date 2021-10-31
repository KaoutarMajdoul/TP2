package src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.*;

import static src.GraphChocoPPC.timeout;

public class GraphIMP {
	Dataset ds;




	public static void main(String[] args) throws IOException {


		//Dataset ds = new Dataset("regions.col");
		long start = System.currentTimeMillis();


			for (int i = 1; i < 15; i++) {

				Dataset ds = new Dataset("g" + i + ".col");
				src.Graph g2 = new src.Graph(ds.nbRegions + 1);


				System.out.println("COLORIAGE DE GRAPHE : " + "g" + i + ".col");
				//src.Graph g2 = new src.Graph(ds.nbRegions+1);


				for (int j = 0; j < ds.col1.size(); j++) {


					//System.out.println(ds.col1.get(i) + "\t " + ds.col2.get(i));

					int c1 = ds.col1.get(j);
					int c2 = ds.col2.get(j);

					g2.addEdge(c1, c2);
				}


				g2.printGraph();

				backTrackingColoring(g2, 4);

				long tempsExec = System.currentTimeMillis() - start;
				System.out.println("Temps d'execution : " + tempsExec + "ms");

				if(System.currentTimeMillis() > start+120000){
					System.out.println("TIMEOUT 2 min");
				}
			}


	}




	//vérifie que la couleur n'est pas utilisée par les régions voisines
	public static boolean isSafe(int v, src.Graph g, int colors[], int cr) {
		for (int i = 1; i < g.getvCount()+1; i++) {
			if (g.hasEdge(v, i) && cr == colors[i]) {
				return false;
			}
		}
		return true;

	}

	//colorie le graphe
	public static boolean graphColoringUtil(src.Graph g, int m, int colors[], int v) {
		//si on arrive à la derniere région on termine
		if (v == g.getvCount())
			return true;

		// on essaye les couleurs pour une région
		for (int cr = 1; cr <= m; cr++) {

			if (isSafe(v, g, colors, cr)) {
				colors[v] = cr;
				// appel recursif
				if (graphColoringUtil(g, m, colors, v + 1))
					return true;

				//si le coloriage ne marche pas
				colors[v] = 0;
			}
		}


		return false;
	}


	public static void backTrackingColoring(src.Graph g, int m) {
		long start = System.currentTimeMillis();

		int V = g.getvCount();


		int colors[] = new int[V];

		//on init à 0
		Arrays.fill(colors, 0);


		if (!graphColoringUtil(g, m, colors, 0)) {
			System.out.println("Pas de solution");
		}

		printColors(colors);
	}

	// affichage du graphe final
	public static void printColors(int[] colors) {
		for (int i = 1; i < colors.length; i++) {
			switch(colors[i]) {
				case 1:
					//System.out.println("Région " + i + " --->  Couleur " + "bleu");
					break;
				case 2:
					//System.out.println("Région " + i + " --->  Couleur " + "rouge");
					break;
				case 3:
					//System.out.println("Région " + i + " --->  Couleur " + "vert");
					break;
				case 4:
					//System.out.println("Région " + i + " --->  Couleur " + "jaune");
					break;
			}
		}
	}
}