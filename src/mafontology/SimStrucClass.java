/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author kadri amine
 */
public class SimStrucClass extends Thread
{
    public static JTable Synonyme, SimInstance, MAP_Valide;
    public static JTable Table_Struc_Class, SimilariteTermRelation;
  
    public static String chemin1 ;
    public static String chemin2 ;
    public static float CoefPere,CoefFrere,CoefFils;
    public static float SeuilTerm,SeuilStruc, Seuil;


    @Override
    @SuppressWarnings("static-access")

public void run()
{

  try
  {
     OWLModel owlModel1 = null;
      try {
          owlModel1 = ProtegeOWL.createJenaOWLModelFromURI(chemin1);
      } catch (Exception ex) {
          Logger.getLogger(SimStrucClass.class.getName()).log(Level.SEVERE, null, ex);
      }
     OWLModel owlModel2 = null;
      try {
          owlModel2 = ProtegeOWL.createJenaOWLModelFromURI(chemin2);
      } catch (Exception ex) {
          Logger.getLogger(SimStrucClass.class.getName()).log(Level.SEVERE, null, ex);
      }
     Collection classes1 = owlModel1.getUserDefinedOWLNamedClasses();

     System.out.println("********************** Similarité Structurelle *************************");
     for (Iterator i = classes1.iterator(); i.hasNext();)
     {
       OWLNamedClass cls1 = (OWLNamedClass) i.next();
       Collection classes2 = owlModel2.getUserDefinedOWLNamedClasses();
       for (Iterator j = classes2.iterator(); j.hasNext();)
       {
          OWLNamedClass cls2 = (OWLNamedClass) j.next();
         float SimStruc = SimilaritéStructurelle(cls1, classes1, cls2, classes2);
         Vector v = new Vector();
         v.add(0, cls1.getLocalName());
         v.add(1, cls2.getLocalName());
         int nbr = Recherche(v,SimInstance, 0, 2);
         if (nbr != -1)
            SimInstance.setValueAt(SimStruc, nbr, 6);
        
       }
     }

     System.out.println("********************** Similarité des relation *************************");
     SimTermRelation thread2 = new SimTermRelation();
     thread2.chemin1 = chemin1;
     thread2.chemin2 = chemin2;
     thread2.SimilariteTermRelation = SimilariteTermRelation;
     thread2.Synonyme = Synonyme;
     thread2.run();

     System.out.println("********************** Phase de Filtrage *************************");
     Filtrage filtre = new Filtrage();
     filtre.BD_Sim = SimInstance;
     filtre.MAP_Valide = MAP_Valide;
     filtre.Table_Struc_Class = Table_Struc_Class;
     filtre.SimilariteTermRelation = SimilariteTermRelation;
     filtre.Seuil=Seuil;
     filtre.SeuilStruc=SeuilStruc;
     filtre.SeuilTerm=SeuilTerm;
     filtre.Filtrage();
     System.out.println("********************** Fin de la phase de Filtrage *************************");
     
     
  }
  catch (OntologyLoadException ex)
  {
    Logger.getLogger(SimStrucClass.class.getName()).log(Level.SEVERE, null, ex);
  }
}
public void FiltreInstance() throws OntologyLoadException
{
        try {
            ArrayList Inst1;
            ArrayList Inst2;
            
            OWLModel owlModel1 = ProtegeOWL.createJenaOWLModelFromURI(chemin1);
            OWLModel owlModel2 = ProtegeOWL.createJenaOWLModelFromURI(chemin2);
            
            Collection classes1 = owlModel1.getUserDefinedOWLNamedClasses();
            for (Iterator i = classes1.iterator(); i.hasNext();)
            {
                OWLNamedClass cls1 = (OWLNamedClass) i.next();
                
                Inst1 = new ArrayList();
                Inst1 = ConstruireInstance(cls1);
                
                Collection classes2 = owlModel2.getUserDefinedOWLNamedClasses();
                for (Iterator j = classes2.iterator(); j.hasNext();)
                {
                    OWLNamedClass cls2 = (OWLNamedClass) j.next();
                    
                    Inst2 = new ArrayList();
                    Inst2 = ConstruireInstance(cls2);
                    
                    if(Inst1.size()!=0 && Inst2.size()!=0)
                    {
                        float nbrdesperes = Inst1.size();
                        float same = (float) SameSet(Inst1, Inst2);
                        float SimInst = same/nbrdesperes;
                        System.out.println(SimInst);
                        if (SimInst >= 1)
                        {
                            for(int k = 0; k < Inst1.size();k++)
                            {
                                Vector v1 = new Vector();
                                v1.add(0, Inst1.get(k));
                                v1.add(1, Inst2.get(k));
                                if (Recherche(v1,Synonyme))
                                {
                                    v1.add(2, "Instance-Instance");
                                    v1.add(3, 1);
                                    System.out.println(v1);
                                    if (Recherche(v1, MAP_Valide)==false)
                                    {
                                        ((DefaultTableModel) MAP_Valide.getModel()).addRow(v1);
                                        System.out.println(v1);
                                    }
                                }
                            }
                            Vector v = new Vector();
                            v.add(0, cls1.getLocalName());
                            v.add(1, cls2.getLocalName());
                            v.add(2, "Concept-Concept");
                            v.add(3, 1);
                            if (Recherche(v,MAP_Valide)==false)
                            {
                                ((DefaultTableModel)MAP_Valide.getModel()).addRow(v);
                                System.out.println(v);
                            }
                        }
                    }
                    
                }
            }    } catch (Exception ex) {
            Logger.getLogger(SimStrucClass.class.getName()).log(Level.SEVERE, null, ex);
        }
}
static ArrayList ConstruireInstance(OWLNamedClass cls) throws OntologyLoadException
{
  ArrayList Inst = new ArrayList();
  Collection instance2 = cls.getInstances(false);
  for ( Iterator i = instance2.iterator(); i.hasNext();)
  {
    OWLIndividual individual2 = (OWLIndividual) i.next();
    Inst.add(individual2.getBrowserText());
  }

  return Inst;
}
public boolean Recherche (Vector V, JTable table)
{
  boolean bool = false;

    for (int i = 0; i < table.getRowCount(); i++)
    {
        String nom1 = table.getValueAt(i, 0).toString();
        String nom2 = table.getValueAt(i, 1).toString();

        if (V.get(0).toString().equals(nom1) && V.get(1).toString().equals(nom2))
        {
            bool = true; break;
        }
    }

 return bool;
}
static float SimilaritéStructurelle(OWLNamedClass cls1, Collection classes1 ,OWLNamedClass cls2, Collection classes2 ) throws OntologyLoadException
{
   float SimStruc = 0;
   float Sim_pere = 0, Sim_frere = 0, Sim_fils = 0;
   ArrayList pere1 = ConstruirePere(cls1,true);
   ArrayList frere1 = ConstruireFrere(cls1,classes1);
   ArrayList fils1 = ConstruireFils(cls1);
   ArrayList pere2 = ConstruirePere(cls2,true);
   ArrayList frere2 = ConstruireFrere(cls2,classes2);
   ArrayList fils2 = ConstruireFils(cls2);
   Vector v3 = new Vector();
   v3.add(0, cls1.getLocalName());
   v3.add(1, cls2.getLocalName());
   v3.add(2, "Concept");

   if(pere1.size() != 0 && pere2.size() != 0)
    {
       int nbrdesperes = pere1.size() + pere2.size();
       int same = (int) SameSet(pere1, pere2)+1;
       int nonsame = nbrdesperes - 2*same;
       Sim_pere = (float)same/(same+nonsame);
    }
    else Sim_pere = 1;

   if(fils1.size() != 0 && fils2.size() != 0)
    {
       int nbrdesfils = fils1.size() + fils2.size();
       int samefils = (int) SameSet(fils1, fils2);
       int nonsamefils = nbrdesfils - 2*samefils;
       Sim_fils = (float)samefils/(samefils+nonsamefils);
    }
    else Sim_fils = 1;

   if(frere1.size() != 0 && frere2.size() != 0)
    {
       int nbrdesfreres = frere1.size() + frere2.size();
       int samefreres = (int) SameSet(frere1, frere2);
       int nonsamefreres = nbrdesfreres - 2*samefreres;
       Sim_frere = (float)samefreres/(samefreres + nonsamefreres);
    }
    else Sim_frere = 1;

    SimStruc = (float) (Sim_pere + Sim_frere + Sim_fils )/3;
    v3.add(3, Sim_pere );
    v3.add(4, Sim_frere);
    v3.add(5, Sim_fils);
    v3.add(6, SimStruc);
    ((DefaultTableModel)Table_Struc_Class.getModel()).addRow(v3);


 return SimStruc;
}
static int SameSet(ArrayList S1, ArrayList S2)
{
  Vector v = new Vector();
  int nbr=0;

  for (int i = 0; i < S1.size(); i++)
    {
      v.add(0, S1.get(i).toString());
      for (int j = 0; j < S2.size(); j++)
       {
         v.add(1, S2.get(j).toString());
         if (Recherche(v, Synonyme, 0, 1)!= -1) nbr++;
       }
    }

 return nbr;
}
static int Recherche (Vector V, JTable table, int col1, int col2)
{
  int Numligne = -1;

    for (int i = 0; i < table.getRowCount(); i++)
    {
        String nom1 = table.getValueAt(i, col1).toString();
        String nom2 = table.getValueAt(i, col2).toString();

        if (V.get(0).toString().equals(nom1) && V.get(1).toString().equals(nom2))
        {
            Numligne = i;
            break;
        }
    }

 return Numligne;
}
static boolean Synonymes(ArrayList tokens1, ArrayList Synset2)
{
  boolean bool=true;
  int i = 0;

  while (i < tokens1.size())
    {
        if(Appartient(tokens1.get(i).toString(), Synset2)) {i++;}
        else {bool = false; break;}
    }

  return bool;
}
static boolean Appartient(String entite, ArrayList Synset2)
{
  boolean bool=false;

  for (int k = 0; k < Synset2.size(); k++)
  {
    if(entite.equals(Synset2.get(k)))
      {
        bool = true;
        break;
      }
  }
 return bool;
}
static ArrayList ConstruirePere(OWLNamedClass cls, boolean bool) throws OntologyLoadException
{
   ArrayList pere = new ArrayList();

   for (Iterator i = cls.getNamedSuperclasses(bool).iterator(); i.hasNext();)
        {
            OWLNamedClass cls1 = (OWLNamedClass) i.next();
            pere.add(cls1.getLocalName());
        }

 return pere;
}
static  ArrayList ConstruireFils(OWLNamedClass cls) throws OntologyLoadException
{
   ArrayList Fils = new ArrayList();

   for (Iterator i = cls.getNamedSubclasses(true).iterator(); i.hasNext();)
        {
            OWLNamedClass cls1 = (OWLNamedClass) i.next();
            Fils.add(cls1.getLocalName());
        }

 return Fils;
}
static ArrayList ConstruireFrere(OWLNamedClass cls, Collection classes) throws OntologyLoadException
{
  ArrayList pere = new ArrayList();
  ArrayList frere = new ArrayList();

  pere = ConstruirePere(cls, false);
  for (Iterator i = classes.iterator(); i.hasNext();)
    {
      OWLNamedClass cls1 = (OWLNamedClass) i.next();
      ArrayList pere1 = new ArrayList();
      if (cls.getLocalName().equals(cls1.getLocalName())==false)
       {
        pere1 = ConstruirePere(cls1,false);
        for (int k = 0; k < pere.size(); k++)
             if (Appartient(pere.get(k).toString(), pere1)) frere.add(cls1.getLocalName());
       }
    }
 return frere;
}
}
