/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import edu.stanford.smi.protegex.owl.model.OWLObjectProperty;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.didion.jwnl.JWNLException;

/**
 *
 * @author kadri amine
 */
public class SimTermRelation extends Thread
{
  public static String chemin1 ;
  public static String chemin2 ;
  public static JTable Synonyme, SimilariteTermRelation;
  public static Semaphore semSyn ;
  public static Semaphore semLex ;
  public static int choix;

    @Override
public void run()
{
        try {
            SimilaritéSyntaxiqueRelation();
        } catch (OntologyLoadException ex) {
            Logger.getLogger(SimTermRelation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SimTermRelation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JWNLException ex) {
            Logger.getLogger(SimTermRelation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SimTermRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

  }
public void SimilaritéSyntaxiqueRelation() throws OntologyLoadException, IOException, JWNLException, Exception
{
  ArrayList Domaine1 ;
  ArrayList Range1 ;
  ArrayList Domaine2 ;
  ArrayList Range2 ;
  ArrayList Token1 ;
  ArrayList Token2 ;
  float SimDom = 0 , SimRang = 0, SimRelation = 0, SimSynRel = 0, SimLex = 0;

  OWLModel owlModel1 = ProtegeOWL.createJenaOWLModelFromURI(chemin1);
  OWLModel owlModel2 = ProtegeOWL.createJenaOWLModelFromURI(chemin2);

  Collection Props1 = owlModel1.getUserDefinedOWLObjectProperties();
  for (Iterator i = Props1.iterator(); i.hasNext();)
    {
      OWLObjectProperty slot1 = (OWLObjectProperty )i.next();
      Domaine1 = ConstruireDomaine(slot1);
      Range1 = ConstruireRange(slot1);

      Collection Props2 = owlModel2.getUserDefinedOWLObjectProperties();
      for (Iterator k = Props2.iterator(); k.hasNext();)
      {
        OWLObjectProperty slot2 = (OWLObjectProperty )k.next();

        Domaine2 =ConstruireDomaine(slot2);
        Range2 = ConstruireRange(slot2);

        Token1 = SimInstance.Normalisation(slot1.getLocalName());
        Token2 = SimInstance.Normalisation(slot2.getLocalName());
        SimSynRel = SimInstance.Similarité(Token1, Token2, choix);

        if(Domaine1.size() != 0 && Domaine2.size() != 0)
         {
           float nbrdesperes = Domaine1.size() + Domaine2.size();
           float same = (float) SameSet(Domaine1, Domaine2);
           float nonsame = nbrdesperes - 2*same;
           SimDom = same/(same+nonsame);
         }
         else SimDom = 1;

         if(Range1.size() != 0 && Range1.size() != 0)
         {
           float nbrdesperes = Range1.size() + Range2.size();
           float same = (float) SameSet(Range1, Range2);
           float nonsame = nbrdesperes - 2*same;
           SimRang = same/(same+nonsame);
         }
         else SimRang = 1;

         SimRelation = (SimDom + SimRang)/2;

         Vector v3 = new Vector();
         v3.add(0, slot1.getLocalName());
         v3.add(1, slot2.getLocalName());
         v3.add(2, "Relation");
         v3.add(3, SimDom );
         v3.add(4, SimRang);
         v3.add(5, SimSynRel);
         v3.add(6, SimRelation);
         ((DefaultTableModel) SimilariteTermRelation.getModel()).addRow(v3);
      }
    }
}
static ArrayList ConstruireDomaine(OWLObjectProperty slot)
{
   ArrayList Domaine1 = null ;
   Domaine1 = new ArrayList();
   Collection Domain1 = slot.getDomains(true);
   for (Iterator j = Domain1.iterator(); j.hasNext();)
    {
      OWLNamedClass cls1 = (OWLNamedClass) j.next();
      Domaine1.add(cls1.getLocalName());
    }

  return Domaine1;
}
static ArrayList ConstruireRange(OWLObjectProperty slot)
{
   ArrayList Range1 = null ;
   Collection Rang1 = slot.getRanges(true);
   Range1 = new ArrayList();
   for (Iterator j = Rang1.iterator(); j.hasNext();)
   {
     OWLNamedClass cls1 = (OWLNamedClass) j.next();
     Range1.add(cls1.getLocalName());
   }

   return Range1;
}
static float SameSet(ArrayList S1, ArrayList S2)
{
  Vector v = new Vector();
  float nbr=0;

  for (int i = 0; i < S1.size(); i++)
    {
      v.add(0, S1.get(i).toString());
      for (int j = 0; j < S2.size(); j++)
       {
         v.add(1, S2.get(j).toString());
         if (Recherche(v, Synonyme)!= -1) nbr++;
       }
    }

 return nbr;
}
static int Recherche (Vector V, JTable table)
{
  int Numligne = -1;

    for (int i = 0; i < table.getRowCount(); i++)
    {
        String nom1 = table.getValueAt(i, 0).toString();
        String nom2 = table.getValueAt(i, 1).toString();

        if (V.get(0).toString().equals(nom1) && V.get(1).toString().equals(nom2))
        {
            Numligne = i;
            break;
        }
    }

 return Numligne;
}
}