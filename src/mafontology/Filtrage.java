/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kadri amine
 */
public class Filtrage
{
  public JTable BD_Sim, MAP_Valide, SimilariteTermRelation, Table_Struc_Class;
  public static JButton run;
  public float SeuilTerm, SeuilStruc, Seuil;

public void Filtrage() throws OntologyLoadException
{
   float Sim = 0;

   for (int i = 0; i < BD_Sim.getRowCount(); i++)
   {
     Sim =Float.parseFloat(BD_Sim.getValueAt(i,6).toString());
     if(Sim >= SeuilStruc )
     {
         Vector v = new Vector();
         String type1 = BD_Sim.getValueAt(i, 1).toString();
         String type2 = BD_Sim.getValueAt(i, 3).toString();
         v.add(0, BD_Sim.getValueAt(i, 0).toString());
         v.add(1, BD_Sim.getValueAt(i, 2).toString());
         v.add(2, (type1.concat("-")).concat(type2));
         v.add(3, Sim);
         if (Recherche(v,MAP_Valide)==false)
         {
          ((DefaultTableModel)MAP_Valide.getModel()).addRow(v);

         }
     }
   }
  // ********************** Filtre TopDown et ButtonUP *************************
  for (int i = 0; i < Table_Struc_Class.getRowCount(); i++)
   {
     float TopDown = 0;
     float ButUP = 0;

     TopDown =Float.parseFloat(Table_Struc_Class.getValueAt(i, 3).toString());
     if ( TopDown == 1)
     {
         ButUP =Float.parseFloat(Table_Struc_Class.getValueAt(i, 5).toString());
         if (ButUP == 1)
         {
           Vector v = new Vector();
           v.add(0, Table_Struc_Class.getValueAt(i, 0).toString());
           v.add(1, Table_Struc_Class.getValueAt(i, 1).toString());
           if (Recherche2(v, BD_Sim) >= Seuil && Recherche(v, MAP_Valide) == false)
           {
             v.add(2, "Concept-Concept");
             v.add(3, 1);
             if (Recherche(v,MAP_Valide)==false)
             {
               ((DefaultTableModel)MAP_Valide.getModel()).addRow(v);

             }
           }
         }
      }
    }

   SimStrucClass Filtre = new SimStrucClass();
   Filtre.FiltreInstance();

 // ********************** Rajoute les classe de similarité des Relation *****************

 for (int i = 0; i < SimilariteTermRelation.getRowCount(); i++)
   {
     Sim = 0;
     Sim =Float.parseFloat(SimilariteTermRelation.getValueAt(i, 6).toString());
     if(Sim == 1)
     {
       Vector v = new Vector();
       v.add(0, SimilariteTermRelation.getValueAt(i, 0).toString());
       v.add(1, SimilariteTermRelation.getValueAt(i, 1).toString());
       v.add(2, "Relation-Relation");
       v.add(3, Sim);
       if (Recherche(v,MAP_Valide)==false)
        {
          ((DefaultTableModel)MAP_Valide.getModel()).addRow(v);
        }
     }
   }
   run.setText("Resultat");
}
static float Recherche2 (Vector V, JTable table)
{
  float SimMax = -1;

    for (int i = 0; i < table.getRowCount(); i++)
    {
        String nom1 = table.getValueAt(i, 0).toString();
        String nom2 = table.getValueAt(i, 2).toString();

        if (V.get(0).toString().equals(nom1) && V.get(1).toString().equals(nom2))
        {
            float simSyn = Float.parseFloat(table.getValueAt(i, 4).toString());
            float simLex = Float.parseFloat(table.getValueAt(i, 5).toString());
            SimMax = Math.max(simSyn, simLex);
            break;
        }
    }
 return SimMax;
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

}