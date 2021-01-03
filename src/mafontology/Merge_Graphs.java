/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.miv.graphstream.graph.Graph;
import org.miv.graphstream.graph.implementations.DefaultGraph;
import org.miv.graphstream.ui.GraphViewerRemote;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;

/**
 *
 * @author BETOUCHE Ammar
 */
class Merge_Graphs {

   
    
    
     public float x = (float) -0.750;
    public float y =(float) 1;
    public String chaine ;
    public String chaine1;

    public static final String styleSheet1 =

            "graph {" +
            "   color:green;"+
            "   background-color:AliceBlue;"+
            "}"+
            "node {"+
            "    node-shape: text-ellipse;"+
            "    color      : Yellow;"+
            "    text-color :black;"+
            "    text-style : bold;"+
            "    border-color : black;"+
            "    border-width : 1px;"+
            "    text-size : 14;" +
            "    width: 50px;"+
            "    height: 10px;"+
            "    text-align: center;"+
            "    z-index :2 ;"+
            " }"+
            "edge {"+
            "   edge-shape:cubic-curve;"+
            "   width: 2;"+
            "   arrow-length: 1 ;"+
            "   z-index: 1;"+
            "   color:brown;"+
          //  "sprite#S1{"+
          //  " color:black;"+
           // " widht :5px;"+
            "}";




    public Merge_Graphs(String URI1,/* Vector v1,*/String URI2 /*,Vector v2*/) throws OntologyLoadException {

         try {
             List str1= new LinkedList();
             List str2=new LinkedList();
             Vector Fus = new Vector();
             /*Construction PF01*/
             OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(URI1);
             str1  =  (List) owlModel.getUserDefinedOWLNamedClasses();
             /*Construction PFO2*/
             OWLModel owlModel1 = ProtegeOWL.createJenaOWLModelFromURI(URI2);
             str2  =  (List) owlModel1.getUserDefinedOWLNamedClasses();
             
             Vector v1 = new Vector();/*Cont1 1er entite du couple*/
             Vector v2 = new Vector();
             int index=0;
             /*
             v2.add(index, "HotelRoom");
             v1.add(index, "Room");
             index++;
             v2.add(index, "OneRoom");
             v1.add(index, "SingleRoom");
             index++;
             v2.add(index, "Suite");
             v1.add(index, "Suite");
             index++;
             v2.add(index, "TownHouse");
             v1.add(index, "TownHouse");
             index++;
             v2.add(index, "NumBeds");
             v1.add(index, "NumBedAttribute");
             index++;
             v2.add(index, "SmokingPreference");
             v1.add(index, "SmokingPreference");
             index++;
             v2.add(index, "OnFloor");
             v1.add(index, "FloorAttribute");
             index++;*/
             /*
             v1.add(index, "Person");
             v2.add(index, "Person");
             index++;
             
             v1.add(index, "TwoLeggedThing");
             v2.add(index, "BipedalThing");
             index++;
             
             v1.add(index, "TwoLeggedPerson");
             v2.add(index, "BipedalPerson");
             index++;
             
             v1.add(index, "Animal");
             v2.add(index, "Animal");
             index++;
             
             v1.add(index, "Male");
             v2.add(index, "Male");
             index++;
             
             v1.add(index, "Female");
             v2.add(index, "Female");
             index++;
             
             v1.add(index, "Man");
             v2.add(index, "Man");
             index++;
             
             v1.add(index, "Woman");
             v2.add(index, "Woman");
             index++;
             
             v1.add(index, "HumanBeing");
             v2.add(index, "Person");
             
             */
             
             v1.add(index, "Equipment");
             v2.add(index, "Equipment");
             index++;
             
             v1.add(index, "Cable");
             v2.add(index, "cable");
             index++;
             
             v1.add(index, "CoaxCable");
             v2.add(index, "Coax");
             index++;
             
             v1.add(index, "CrossOverCable");
             v2.add(index, "CrossOver");
             index++;
             
             v1.add(index, "StraightThroughCable");
             v2.add(index, "StraightThrough");
             index++;
             
             v1.add(index, "NetworkNode");
             v2.add(index, "networkNode");
             index++;
             
             v1.add(index, "Computer");
             v2.add(index, "Computer");
             index++;
             
             v1.add(index, "PC");
             v2.add(index, "PC");
             index++;
             
             v1.add(index, "Server");
             v2.add(index, "Server");
             index++;
             
             v1.add(index, "SwitchEquipement");
             v2.add(index, "CentralHub");
             index++;
             
             v1.add(index, "Hub");
             v2.add(index, "Hub");
             index++;
             
             v1.add(index, "Router");
             v2.add(index, "Router");
             index++;
             
             v1.add(index, "Switch");
             v2.add(index, "Switch");
             index++;
             
             v1.add(index, "Software");
             v2.add(index, "Software");
             index++;
             
             v1.add(index, "OfficeSoftware");
             v2.add(index, "Office");
             index++;
             
             v1.add(index, "OperatingSystem");
             v2.add(index, "OS");
             index++;
             
             v1.add(index, "ServerSoftware");
             v2.add(index, "ServerSoftware");
             index++;
             
             v1.add(index, "FTPServer");
             v2.add(index, "FTP");
             index++;
             
             v1.add(index, "OtherServer");
             v2.add(index, "Other");
             index++;
             
             v1.add(index, "SSHServer");
             v2.add(index, "SSH");
             index++;
             
             v1.add(index, "TelenetServer");
             v2.add(index, "Telnet");
             index++;
             
             v1.add(index, "WebServer");
             v2.add(index, "Web");
             index++;
             
             v1.add(index, "NodePair");
             v2.add(index, "PairOfNodes");
             
             
             /*Construction de PFFus*/
             
             int i=0;int k=0;int l=0; Vector position = new Vector();
             
             for (;i < Construire(str1).size();i++)
             { String str = (String) Construire(str1).get(i);
             if((str).equals("#"))
             {i++;k=i;position.add(k);
             Fus.add((String) Construire(str1).get(i));
             if (Appartient((String) (Construire(str1).get(i)), Construire(str2))) i++;
             }
             for (;i < Construire(str1).size();i++)
             {
                 Fus.add((String) Construire(str1).get(i));
             }
             }
             Fus.add("#");
             Vector position2 = new Vector();
             int j=0;
             for ( ;j< Construire(str2).size();j++)
             {
                 if(((String) Construire(str2).get(j)).equals("#")){j++;l=j;position2.add(l);}
                 else if (Appartient((String) (Construire(str2).get(j)), Fus)){j++;}
                 else  {
                     String st1=(String) Construire(str2).get(j);
                     int POS =GetPos(st1,v1);
                     if (POS <0)
                     {
                         if (ifSynonyme(st1,v2,v1)) {j++;}
                         else
                         {
                             
                             if ((URI1.endsWith("networkA.owl"))||(URI1.endsWith("networkB.owl")))
                             {
                                 String ch1 = (String)(Construire(str1).get(k));
                                 String ch2 = (String)(Construire(str2).get(l));
                                 if (ch1.equals(ch2))
                                 {
                                     Fus.add(j,(String) Construire(str2).get(j));
                                 }
                                 Fus.add((String) Construire(str2).get(j));
                                 j++;
                             }
                             else if ((URI1.endsWith("animalsA.owl"))||(URI1.endsWith("animalsB.owl")))
                             {
                                 for (int w=0; w< position.size();w++)
                                 {   for (int z= 0 ;z< position2.size();z++)
                                 {
                                     int a = position.get(w).hashCode();
                                     int b = position2.get(z).hashCode();
                                     String ch1 = (String)(Construire(str1).get(a));
                                     String ch2 = (String)(Construire(str2).get(b));
                                     if (ch1.equals(ch2))
                                     {
                                         Fus.add(b+1,(String) Construire(str2).get(j));
                                     }
                                     else{
                                         Fus.add((String) Construire(str2).get(j));
                                         j++;
                                     }
                                 }
                                 }
                             }
                             if ((URI1.endsWith("hotelA.owl"))||(URI1.endsWith("hotelB.owl")))
                             {
                                 String ch1 = (String)(Construire(str1).get(k));
                                 String ch2 = (String)(Construire(str2).get(l));
                                 if (ch1.equals(ch2))
                                 {
                                     Fus.add(j,(String) Construire(str2).get(j));
                                 }
                                 Fus.add((String) Construire(str2).get(j));
                                 j++;
                             }
                         }
                     }
                 }
             }
             /*Affichage des Vecteurs*/
             
             for (int e=0; e< Construire(str1).size();e++)
             {
                 
                 //  System.out.println(Construire(str1).get(e));
             }
             
             for (int e=0; e< Construire(str2).size();e++)
             {
                 
                 //    System.out.println(Construire(str2).get(e));
             }
             
             
             Fus.add(0,"owl:Thing");
             Fus.add(1, "#");

             for (int e=0; e< Fus.size();e++)
             {
                 if ((Fus.get(e).toString()).equals("$"))
                 {
                     Fus.remove(e);
                     Fus.add(1,Fus.get(e-1));
                 }
             }
             /*special case of neworks ontologies*/
             if ((URI1.endsWith("networkA.owl"))||(URI1.endsWith("networkB.owl")))Fus.removeElementAt(88);

             /*Affichage de Fus Vecteur Fusion des deux ontologies*/

             for (int e=0; e< Fus.size();e++)
             {
                 System.out.println(Fus.get(e));
             }
             Build_graph(Fus,x,y);
         } catch (Exception ex) {
             Logger.getLogger(Merge_Graphs.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

 static Vector Construire (List str1)
    {
        Vector v = new Vector();
       List str2=new LinkedList();
        for(Iterator it =str1.iterator(); it.hasNext();)
         {
          OWLNamedClass cls = (OWLNamedClass)it.next();
          v.add("#");
          v.add(cls.getLocalName().toString());
          List str3=new LinkedList();
          str3 = (List) cls.getNamedSuperclasses();
           for (Iterator i =str3.iterator(); i.hasNext(); )
             {
             OWLNamedClass c = (OWLNamedClass) i.next();
                if (c.getLocalName().equals("Thing")) v.add( "$");
             }
          str2 = (List) cls.getNamedSubclasses();
          for (Iterator ik = str2.iterator(); ik.hasNext();)
                  {
                    OWLNamedClass cl = (OWLNamedClass) ik.next();
                    v.add(cl.getLocalName().toString());
                  }
         }
      return v ;
    }


 static int GetPos(String entite, Vector Synset2)
  {
  int Pos=-1;
  for (int k = 0; k < Synset2.size(); k++)
  {
    if(entite.equals(Synset2.get(k)))
      {
        Pos=k;
        break;
      }
  }
 return Pos;
}
 static boolean Appartient(String entite, Vector Synset2)
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

  static boolean ifSynonyme(String st,Vector v2, Vector v1)
  {
  boolean bool=false;int pos=0;
    for (int i=0; i<v2.size();i++)
   {
     if (st.equals(v2.get(i))){ pos=i;break;}
   }
   if  (JaroWinkler (st, (String) v1.get(pos))> 0.8) bool=true;
   return bool;
  }
   static float JaroWinkler (String ch1,String ch2)
   {
     float resultat = 0;
    Set<AbstractStringMetric> metrics = new HashSet<AbstractStringMetric>();
    metrics.add(new JaroWinkler());
    for (AbstractStringMetric metric : metrics)
       resultat = metric.getSimilarity(ch1, ch2);
    return resultat;
    }

    static void Build_graph(Vector Fus,float x,float y)
    {
       Graph g = new DefaultGraph(false,true);
        GraphViewerRemote gvr = g.display(false);
        gvr.setQuality(4);
         x = (float) -0.750;
         y =(float) 1;
         g.addNode(Fus.get(0).toString()).addAttribute("label","owl:Thing");
         g.getNode(Fus.get(0).toString()).setAttribute("xy",x,y);
         Merge(g,Fus,x,y);
         g.addAttribute("ui.stylesheet", styleSheet1);
    }

    static void Merge (Graph g,Vector Fus,float x,float y)
    {
     System.out.println(Fus.size());
     x=(float) -0.75;y=(float) 0.8;int Pos=0;
     float tab1 []= new float [2];
        tab1[0]=0;tab1[1]=0;
     for (int s=1 ;s < Fus.size();s++)
      {


         if (((Fus.get(s)).equals("#"))&& ((Fus.get(s+2)).equals("#")))
           { /*Cas d'un classe feuille*/
            s=s+3;Pos=s;x= (float) (x - 0.05) ;
           }
         else  if (((Fus.get(s)).equals("#"))&& (((Fus.get(s+2)).toString()).length()>1))
             {
              g.addNode(Fus.get(s+1).toString()).addAttribute("label",Fus.get(s+1).toString());
              s++; Pos =s;
              /*Special Case Network Ontologies*/
              if ((Fus.get(s+1).toString().equals("HardwareFirewall"))||(Fus.get(s+1).toString().equals("HardwareSniffer")) ||(Fus.get(s+1).toString().equals("CrossOverCable"))||(Fus.get(s+1).toString().equals("StraightThroughCable"))||(Fus.get(s+1).toString().equals("CoaxCable"))||((Fus.get(s+1).toString().equals("WirelessBridge")))||(Fus.get(s+1).toString().equals("WAP"))) {y =(float) (y + 0.2);}
              /*Special Case Animals Ontologies*/
              if (Fus.get(s+1).equals("TwoLeggedPerson"))  {y =(float) (y + 0.1);}
              if (Fus.get(s-2).equals("HumanBeing"))  {y =(float) (y + 0.3);}
              y = (float) (y - 0.1);
             }
          else
             {
              g.addEdge((Fus.get(s).toString()).concat((Fus.get(Pos).toString())),Fus.get(s).toString(),Fus.get(Pos).toString(),true);
         //   g.getEdge((Fus.get(s).toString()).concat((Fus.get(Pos).toString()))).addAttribute("label","Is-a");
              g.addNode(Fus.get(s).toString()).addAttribute("label",Fus.get(s).toString());
              g.getNode(Fus.get(s).toString()).setAttribute("xy",x,y);
              x= (float) (x + 0.05) ;
             }
           // g.getNode("Person").setAttribute("xy", -0.5,0.6);
      }
    }  
}
