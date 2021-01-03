/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.awt.BorderLayout;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author BETOUCHE Ammar
 */
public class Hierarchie extends JFrame {
 public String chemin;
  public  int cpt=0;
    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws OntologyLoadException

    {
        // TODO code application logic here
    }

 public Hierarchie(String chemin) throws OntologyLoadException
  {

     try {
         JTree arbre = new JTree();
         JScrollBar scroll = new JScrollBar();
         DefaultMutableTreeNode racine = new DefaultMutableTreeNode("owl:Thing");
         
         OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(chemin);
         Collection classe = owlModel.getUserDefinedOWLNamedClasses();
         List str = new LinkedList();
         str = (List) classe;
         for(Iterator it =str.iterator(); it.hasNext();)
         {
             OWLNamedClass cls = (OWLNamedClass)it.next();
             str = (List) cls.getNamedSuperclasses();
             
             for(Iterator ik =str.iterator(); ik.hasNext();)
             {
                 OWLNamedClass cl = (OWLNamedClass)ik.next();
                 if ((cl.getLocalName().toString()).equals("Thing"))
                 {
                     DefaultMutableTreeNode node1 = new DefaultMutableTreeNode(cls.getLocalName().toString());
                     racine.add(node1);
                     ajouter_concept(cls ,node1);
                 }
                 else {str.remove(cls.getLocalName().toString());}
             }
         }
         DefaultTreeModel model = new DefaultTreeModel(racine);
         arbre.setModel(model);
         getContentPane().add(arbre);
         getContentPane().add(scroll ,BorderLayout.EAST);
     } catch (Exception ex) {
         Logger.getLogger(Hierarchie.class.getName()).log(Level.SEVERE, null, ex);
     }
    
 }

    public void ajouter_concept(OWLNamedClass cls , DefaultMutableTreeNode node1) throws OntologyLoadException
    {   

        List st = new LinkedList();
        st= (List) cls.getNamedSubclasses();
       try
       {
          for(Iterator ir =st.iterator(); ir.hasNext();)
        {  
           OWLNamedClass c = (OWLNamedClass)ir.next();
           if (c.isSubclassOf(cls))
           {           
            DefaultMutableTreeNode node2 = new DefaultMutableTreeNode(c.getLocalName().toString());
            node1.add(node2);
            ajouter_concept(c,node2);
           }
           else node1.add(new DefaultMutableTreeNode(c.getLocalName()));
       }
       } catch (NullPointerException e){}
    }
    public void Setclass(String uri)
    {
       chemin = uri;
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

