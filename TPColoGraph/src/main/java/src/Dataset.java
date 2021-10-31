package src;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

/*******************************************************************
 *
 * Parse Datasets from files
 *
 *******************************************************************/

public class Dataset {

    public int nbRegions;   // Nb régions / somets du graphe
    public int nbCouleurs;  // Nb couleurs max.
    public List<Integer> col1;  // Prend les valeurs de la colonne de gauche du fichier.
    public List<Integer> col2;  // Prend les valeurs de la colonne de droite du fichier.


    public Dataset(String nomFichier) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("/Users/kaoutar/eclipse-workspace/TPColoGraph/datasets/" + nomFichier));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String s = "";

        // Boucle pour éviter les commentaires et aller à la première ligne de valeurs.
        while (s.equals("") || s.charAt(0) != '@') {
            try {
                s = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String [] ligne = s.split(" ");

        nbRegions = parseInt(ligne[1]);
        nbCouleurs = parseInt(ligne[2]);

        col1 = new ArrayList<>(); // Prend les valeurs de la colonne de gauche du fichier.
        col2 = new ArrayList<>(); // Prend les valeurs de la colonne de droite du fichier.

        int i = 0;
        while (true) {
            try {
                if ((s = reader.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

            ligne = s.split(" ");
            col1.add(parseInt(ligne[0]));
            col2.add(parseInt(ligne[1]));

         
            i++;
        }
    }

    public static void main(String[] args) throws IOException {

    }
}
