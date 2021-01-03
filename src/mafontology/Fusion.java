/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;

/**
 *
 * @author BETOUCHE Ammar
 */
public class Fusion {
//public static Vector v1;
//public static Vector v2;
public static String URI1;
public static String URI2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws OntologyLoadException {
        new Merge_Graphs(URI1,/*v1,*/URI2/*,v2*/);
       
        // TODO code application logic here
    }

}
