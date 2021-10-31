package src;

import jdk.jfr.Timestamp;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.*;


public class App {

        public static void main( String[] args ) throws IOException {
            System.out.println("Comparaison des deux solutions  \n");

            System.out.println("------- Solution imp -------");
            GraphIMP.main(args);




            System.out.println("------- Solution ppc -------");
            try {
                GraphChocoPPC.main(args);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


