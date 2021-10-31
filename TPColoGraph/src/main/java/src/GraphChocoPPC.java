package src;

import org.chocosolver.solver.Model;
import org.chocosolver.solver.variables.IntVar;

import java.io.IOException;




public class GraphChocoPPC {




    Model model;
    IntVar[] tab;
    Dataset data;
    public static long timeout = 120000; //deux minutes

    public static void main(String[] args) throws IOException {


        //new GraphChocoPPC().solve("region.col");
        for(int i = 1 ; i < 15 ; i++){


        String path = "g"+i+".col";
            System.out.println("Coloriage fichier : "  + path );
        new GraphChocoPPC().solve(path);}

    }

    public void solve (String nomFichier) {
        buildModel(nomFichier);
        model.getSolver().showStatistics();
        model.getSolver().limitTime(timeout);
        model.getSolver().solve();

        StringBuilder st = new StringBuilder(String.format("Coloriage de graphe -- \n"));

        for (int i = 0; i < data.nbRegions; i++) {
            int c= i+1;
            st.append("Région " + c + " --->  Couleur " + tab[i].getValue() + "\n");
        }

       // System.out.println(st.toString());
    }

    public void buildModel (String nomFichier) {
        model = new Model();
        data = new Dataset(nomFichier);
        tab = new IntVar[data.nbRegions];

        for (int i = 0; i < tab.length; i++)
            tab[i] = model.intVar(1, data.nbCouleurs);

        addContraintes();
    }



    public void addContraintes () {
        for (int i = 0; i < data.col1.size(); i++)
            model.arithm(tab[data.col1.get(i) - 1], "!=", tab[data.col2.get(i) - 1]).post();
    }











}


