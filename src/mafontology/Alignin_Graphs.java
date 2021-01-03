/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;

import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.ProtegeOWL;
import edu.stanford.smi.protegex.owl.model.OWLModel;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.miv.graphstream.graph.Graph;
import org.miv.graphstream.graph.implementations.DefaultGraph;
import org.miv.graphstream.ui.GraphViewerRemote;
import org.miv.graphstream.ui.swing.SwingGraphViewer;

/**
 *
 * @author BETOUCHE Ammar
 */
class Alignin_Graphs {

public float x = (float) -0.750;
public float y =(float) 1;
public String chaine ;
public String chaine1;
public static Graph g;

//public List str= new LinkedList();
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



    public Alignin_Graphs(String URIS1, String URIS2) throws OntologyLoadException {
           g = new DefaultGraph(false,true);
           GraphViewerRemote gvr =     g.display(false);
           
           gvr.setQuality(4);
           SwingGraphViewer gv = new SwingGraphViewer(); 
          
                   
           g.addNode("owl:Thing").addAttribute("label","owl:Thing");
           g.addNode("owl:Thing ").addAttribute("label","owl:Thing ");
           g.getNode("owl:Thing").setAttribute ("xy",x,y);
           g.getNode("owl:Thing ").setAttribute ("xy",-x,y);
           String S1="owl:Thing";
           String S2="owl:Thing ";
           Const_Graph(g,S1,URIS1);
           Const_Graph(g,S2,URIS2);
           g.addAttribute("ui.stylesheet", styleSheet1);

    }

    private void Const_Graph(Graph g, String S, String URI) throws OntologyLoadException
    {

        float tab []= new float [1]; List str1= new LinkedList();List str= new LinkedList();
        tab[0]=0;
        if (S.equals("owl:Thing"))
                                   {
            try {
                x=(float) -0.75;y=(float) 0.8;chaine= S.substring(4);
                OWLModel owlModel = ProtegeOWL.createJenaOWLModelFromURI(URI);
                str1  =  (List) owlModel.getUserDefinedOWLNamedClasses();
                List str2= new LinkedList();
                for(Iterator it =str1.iterator(); it.hasNext();)
                {
                    //cpt++;
                    OWLNamedClass cls = (OWLNamedClass)it.next();
                    str2 =  (List) cls.getNamedSuperclasses();
                    for(Iterator ik =str2.iterator(); ik.hasNext();)
                    {
                        OWLNamedClass cl = (OWLNamedClass)ik.next();
                        if (((cl.getLocalName().toString()).equals(chaine)))
                        {
                            String S2= S+ cls.getLocalName().toString();
                            g.addEdge(S2,cls.getLocalName().toString(),S,true);
                            g.getEdge(S2).addAttribute("label","ISA");
                            g.getNode(cls.getLocalName().toString()).addAttribute("label",cls.getLocalName().toString());
                            x= x+ tab[0];
                            g.getNode(cls.getLocalName().toString()).setAttribute("xy",x,y);
                        }
                        tab[0]=(float) 0.18;
                    }
                    Traiter_autre_Noeuds(g,cls,x,y,S);
                }
            } catch (Exception ex) {
                Logger.getLogger(Alignin_Graphs.class.getName()).log(Level.SEVERE, null, ex);
            }
                                    }
        else if (S.equals("owl:Thing "))
                                      {
            try {
                x=(float) 0.75; y= (float) 0.8;chaine1 =S.substring(4);
                
                
                OWLModel owlModel2 = ProtegeOWL.createJenaOWLModelFromURI(URI);
                str  =  (List) owlModel2.getUserDefinedOWLNamedClasses();
                List str3= new LinkedList();
                for(Iterator it =str.iterator(); it.hasNext();)
                {
                    //cpt++;
                    OWLNamedClass cls = (OWLNamedClass)it.next();
                    
                    str3 =  (List) cls.getNamedSuperclasses();
                    for(Iterator ik =str3.iterator(); ik.hasNext();)
                    {
                        OWLNamedClass cl = (OWLNamedClass)ik.next();
                        System.out.println(cl.getLocalName().toString());
                        if ((cl.getLocalName()).equals(chaine))
                        {
                            
                            String S2= S+cls.getLocalName();
                            g.addEdge(S2,cls.getLocalName()+" ",S,true);
                            g.getEdge(S2).addAttribute("label","ISA");
                            g.getNode(cls.getLocalName().toString()+" ").addAttribute("label",cls.getLocalName().toString());
                            x= x+ tab[0];
                            g.getNode(cls.getLocalName().toString()+" ").setAttribute("xy",x,y);
                        }
                        tab[0]= (float) -  0.18;
                    }
                    Traiter_autre_Noeuds(g,cls,x,y,S);
                }
            } catch (Exception ex) {
                Logger.getLogger(Alignin_Graphs.class.getName()).log(Level.SEVERE, null, ex);
            }
                                      }
    }
    private void Traiter_autre_Noeuds(Graph g, OWLNamedClass cls, float x, float y, String S)
    {
        float tab []= new float [1];tab[0]=0;
        float tab1 []= new float [2];tab1[0]=0;tab1[1]=0;
        float tab2 []= new float [2];tab2[0]=0;tab2[1]=0;
        y =  (float)(y - 0.2);
       if (S.equals("owl:Thing"))
       {
         for(Iterator ik = cls.getNamedSubclasses().iterator(); ik.hasNext();)
           {
             OWLNamedClass cl = (OWLNamedClass)ik.next();
                if  ((cl.isSubclassOf(cls)))
              {
               String S1 = cl.getLocalName().toString() + cls.getLocalName().toString()+" ";
               g.addEdge(S1,cl.getLocalName(),cls.getLocalName());
              /*A désactiver si cas d'ontologies Animals sinon programme plante*/
               g.getEdge(S1).addAttribute("label","ISA");
               g.getNode(cl.getLocalName()).addAttribute("label",cl.getLocalName().toString());
               x= x+ tab[0];
               g.getNode(cl.getLocalName().toString()).setAttribute("xy",x,y);
               tab1= g.algorithm().getNodePosition(cls.getLocalName().toString());
               tab2= g.algorithm().getNodePosition(cl.getLocalName().toString());
               if ((tab1[1] <= tab2[1]))Resoudre_Errors(g,cls,cl,x,y,S);
            // Traiter_autre_Noeuds (g,cl,x,y,S);
              }
            tab[0]=(float) 0.3;
          }
       }
       else if (S.equals("owl:Thing ") )// { tab[0]=(float) - 0.090;}
       {
             for(Iterator ik = cls.getNamedSubclasses().iterator(); ik.hasNext();)
           {
             OWLNamedClass cl = (OWLNamedClass)ik.next();

                if  ((cl.isSubclassOf(cls)))
              {
               String S1 = cl.getLocalName().toString() + cls.getLocalName().toString();
               g.addEdge(S1,cl.getLocalName()+" ",cls.getLocalName()+" ");
               g.getEdge(S1).addAttribute("label","ISA");
               g.getNode(cl.getLocalName().toString()+" ").addAttribute("label",cl.getLocalName().toString());
               x= x+ tab[0];
               g.getNode(cl.getLocalName().toString()+" ").setAttribute("xy",x,y);
               tab1= g.algorithm().getNodePosition(cls.getLocalName().toString()+" ");
               tab2= g.algorithm().getNodePosition(cl.getLocalName().toString()+" ");
               if ((tab1[1] <= tab2[1]))Resoudre_Errors(g,cls,cl,x,y,S);
            // Traiter_autre_Noeuds (g,cl,x,y,S);
              }
              tab[0]=(float) - 0.15;
           }
       }
    }
         private void Resoudre_Errors(Graph g,OWLNamedClass cls,OWLNamedClass cl, float x, float y,String S)
    {   y = (float) (y - 0.2);
        float tab1 []= new float [2];
        tab1[0]=0;tab1[1]=0;
        float tab2 []= new float [2];
        tab2[0]=0;tab2[1]=0;
             if (S.equals("owl:Thing"))
             {
             x=(float) (x + 0.18);
             String S1 = cl.getLocalName().toString()+ cls.getLocalName().toString();
             g.addEdge(S1,cl.getLocalName(),cls.getLocalName(),true);
             g.getNode(cl.getLocalName()).addAttribute("label",cl.getLocalName().toString());
             g.getNode(cl.getLocalName()).setAttribute("xy",x,y);
             tab1= g.algorithm().getNodePosition(cls.getLocalName());
             tab2= g.algorithm().getNodePosition(cl.getLocalName());
             if  (tab1[1] <= tab2[1] )Resoudre_Errors(g,cls,cl,x,y,S);
             }
             else if (S.equals("owl:Thing "))
            {
             x=(float) (x - 0.18);
             String S1 = cl.getLocalName().toString()+ cls.getLocalName().toString();
             g.addEdge(S1,cl.getLocalName()+" ",cls.getLocalName()+" ",true);
             g.getEdge(S1).addAttribute("label","ISA");
             g.getNode(cl.getLocalName().toString()+" ").addAttribute("label",cl.getLocalName().toString());
             g.getNode(cl.getLocalName().toString()+" ").setAttribute("xy",x,y);
             tab1= g.algorithm().getNodePosition(cls.getLocalName().toString()+" ");
             tab2= g.algorithm().getNodePosition(cl.getLocalName().toString()+" ");
             if (tab1[1] <= tab2[1] )Resoudre_Errors(g,cls,cl,x,y,S);
            }
    }
  static void Align_graph(Graph g, String ST1, String ST2, float val)
    {
        g.addEdge(ST1+ST2,ST1,ST2+" ");         
        g.getEdge(ST1+ST2).addAttribute("label",val);
        g.getEdge(ST1+ST2).addAttribute("label.text-size",50);
        g.getEdge(ST1+ST2).addAttribute("ui.color",Color.CYAN);
        g.getEdge(ST1+ST2).addAttribute("ui.width",2);
        g.getEdge(ST1+ST2).addAttribute("ui.edge-style", "dotted" );   
    }
 public  static void lien(String ST1, String ST2, float val)
  {
      Align_graph(g, ST1, ST2,val);
  }
    }

