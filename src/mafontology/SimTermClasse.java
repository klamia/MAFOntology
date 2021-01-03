/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import net.didion.jwnl.JWNLException;

/**
 *
 * @author kadri amine
 */
public class SimTermClasse extends Thread
{
  public static String chemin1 ;
  public static String chemin2 ;
  public static JTable SimTermClass, Synonyme, Table_Struc_Class, MAP_Valide, SimilariteTermRelation, SimInstance;
  public static JTable SimGeneral;
  public static JButton run;
  public static JProgressBar jProgressBar;
  public JLabel jlabel3;
  public JButton jbutton5;
  public static Semaphore semLex ;
  public SimInstance thread4;

  public float SeuilTerm,SeuilStruc,Seuil;
  public float coefCpt,coefLbl,coefComt;
  public float CoefPere,CoefFrere,CoefFils;
  public int choix;
    @Override
    @SuppressWarnings("static-access")

public void run()
{
   try
   {
    SimTermRelation.choix = choix;
    System.out.println("********************** Similarité Terminologique *************************");
    jProgressBar.setValue(5);
    jProgressBar.setStringPainted(true);
    jlabel3.setText("Calcul de  similarité Terminologique.......");

    SimilaritéTerminologiqueClasse();
    
    System.out.println("********************** Fin de la Similarité Terminologique *************************");
    
    System.out.println("********************** Similarité Structurelle *************************");
    jProgressBar.setValue(90);
    jProgressBar.setStringPainted(true);
    jlabel3.setText("Calcul de similarité Structurelle.......");

    SimStrucClass thread3 = new SimStrucClass();

    thread3.chemin1 = chemin1;
    thread3.chemin2 = chemin2;
    thread3.Synonyme = Synonyme;
    thread3.SimInstance = SimInstance;
    thread3.MAP_Valide = MAP_Valide;
    thread3.Table_Struc_Class = Table_Struc_Class;
    thread3.SimilariteTermRelation =SimilariteTermRelation;

    thread3.CoefFils   = CoefFils;
    thread3.CoefFrere  = CoefFrere;
    thread3.CoefPere   = CoefPere ;

    thread3.start();

    System.out.println("********************** Fin de la Similarité Structurelle *************************");
    jProgressBar.setValue(100);
    jProgressBar.setStringPainted(true);
    jlabel3.setText("Fin du processus de calcul");
    jbutton5.setText("Terminer");

    } catch (OntologyLoadException ex) {
           Logger.getLogger(SimTermClasse.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
           Logger.getLogger(SimTermClasse.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JWNLException ex) {
           Logger.getLogger(SimTermClasse.class.getName()).log(Level.SEVERE, null, ex);
    } catch (Exception ex) {
           Logger.getLogger(SimTermClasse.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    @SuppressWarnings({"static-access", "static-access", "static-access", "static-access"})

public void SimilaritéTerminologiqueClasse() throws OntologyLoadException, IOException, JWNLException, Exception
{
  Semaphore semaphoreLexical = new Semaphore(1);
  Semaphore semaphoreTables = new Semaphore(1);
    
  OWLModel owlModel1 = ProtegeOWL.createJenaOWLModelFromURI(chemin1);
  OWLModel owlModel2 = ProtegeOWL.createJenaOWLModelFromURI(chemin2);
    
  Collection classes1 = owlModel1.getUserDefinedOWLNamedClasses();
  Collection classes2 = owlModel2.getUserDefinedOWLNamedClasses();
  float nbr = 80/(classes1.size()+classes2.size());
  for (Iterator i = classes1.iterator(); i.hasNext();)
   {
     OWLNamedClass cls1 = (OWLNamedClass) i.next();

     for (Iterator j = classes2.iterator(); j.hasNext();)
       {
         OWLNamedClass cls2 = (OWLNamedClass) j.next();

         thread4 = new SimInstance();
         thread4.SimInstance = SimInstance;
         thread4.semLex = semaphoreLexical;
         thread4.semtab = semaphoreTables;
         thread4.Synonyme = Synonyme;
         thread4.MAP_Valide = MAP_Valide;
         thread4.SetClasse(cls1, cls2);
         thread4.choix = choix;
         thread4.SeuilTerm = SeuilTerm;
         thread4.coefCpt   = coefCpt;
         thread4.coefLbl   = coefLbl;
         thread4.coefComt  = coefComt;

         thread4.start();
         thread4.join();
       }
    thread4.join();
    jProgressBar.setValue((int) (jProgressBar.getValue() + nbr));
    jProgressBar.setStringPainted(true);
  }
thread4.join();
}

}
