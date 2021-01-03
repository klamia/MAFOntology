/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mafontology;
import edu.stanford.smi.protege.exception.OntologyLoadException;
import edu.stanford.smi.protegex.owl.model.OWLIndividual;
import edu.stanford.smi.protegex.owl.model.OWLNamedClass;
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import shef.nlp.wordnet.similarity.SimilarityInfo;
import shef.nlp.wordnet.similarity.SimilarityMeasure;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.JaroWinkler;
import uk.ac.shef.wit.simmetrics.similaritymetrics.MongeElkan;
/**
 *
 * @author kadri amine
 */
public class SimInstance extends Thread
{
    public static JTable SimTermClass, Synonyme, MAP_Valide, SimInstance;
    public static JTable SimGeneral;
    public static Semaphore semLex ;
    public static Semaphore semtab ;
    private OWLNamedClass classA;
    private OWLNamedClass classB;
    public float coefCpt,coefLbl,coefComt;
    public float SeuilTerm;
    public int choix;
    @Override
public void run()
{
   try
   {

      SimilaritéClasseClasse();
      SimilaritéInstance();
      SimilaritéClasseInstance(classA, classB);
      SimilaritéInstanceClasse(classA, classB);

   } catch (JWNLException ex) {
           Logger.getLogger(SimInstance.class.getName()).log(Level.SEVERE, null, ex);
   } catch (IOException ex) {
           Logger.getLogger(SimInstance.class.getName()).log(Level.SEVERE, null, ex);
   } catch (Exception ex) {
           Logger.getLogger(SimInstance.class.getName()).log(Level.SEVERE, null, ex);
   }
}

    @SuppressWarnings("empty-statement")

public void SetClasse (OWLNamedClass classe1, OWLNamedClass classe2)
{
    classA = classe1;
    classB = classe2;
}
public void SimilaritéClasseClasse() throws OntologyLoadException, IOException, JWNLException, Exception
{
    float Sim_Syn = 0, SimLexCpt = 0, Sim_Term = 0;
    ArrayList tokenscpt1 = new ArrayList();
    ArrayList tokenslbl1 = new ArrayList();
    ArrayList tokenscmt1 = new ArrayList();
    ArrayList tokenscpt2 = new ArrayList();
    ArrayList tokenslbl2 = new ArrayList();
    ArrayList tokenscmt2 = new ArrayList();

    tokenscpt1 = Normalisation(classA.getLocalName());
    tokenslbl1 = Normalisation(classA.getLabels().toString());
    tokenscmt1 = Normalisation(classA.getComments().toString());
    tokenscpt2 = Normalisation(classB.getLocalName());
    tokenslbl2 = Normalisation(classB.getLabels().toString());
    tokenscmt2 = Normalisation(classB.getComments().toString());

    // *************************** Similarité Terminologique *************************** //
    Vector v1 = new Vector();
    v1.add(0, classA.getLocalName());
    v1.add(1, "Concept");
    v1.add(2, classB.getLocalName());
    v1.add(3, "Concept");

    // Calcul des similarité
    Sim_Syn = SimilaritéSyntaxique(tokenscpt1,tokenscpt2,tokenslbl1,tokenslbl2,tokenscmt1,tokenscmt2);
    SimLexCpt = SimilaritéLexical(tokenscpt1, tokenscpt2);
    Sim_Term = (float) (SimLexCpt+Sim_Syn)/2;

    v1.add(4, Sim_Syn);
    v1.add(5, SimLexCpt);
    v1.add(6, 0);

    Vector v = new Vector();
    v.add(v1.get(0));
    v.add(v1.get(2));
    v.add(2,"Concept-Concept");

    Vector v2 = new Vector();
    v2.add(v1.get(0));
    v2.add(v1.get(2));
    v2.add(2,"Concept-Concept");
    v2.add(3, Sim_Term);
    v2.add(4, 0);
    v2.add(5, 0);
    v2.add(6, 0);

    semtab.acquire();
    ((DefaultTableModel) SimInstance.getModel()).addRow(v1);
 //   ((DefaultTableModel) SimGeneral.getModel()).addRow(v2);

    if (Sim_Syn == 1)
     {
       v.add(3, 1);
       if (Recherche(v,Synonyme)==false)
       {
           ((DefaultTableModel) Synonyme.getModel()).addRow(v);
           ((DefaultTableModel) MAP_Valide.getModel()).addRow(v);System.out.println(v);
       }
     }
     else
        if (SimLexCpt == 1 || Sim_Term >= SeuilTerm)
         {
           v.add(3, 1);
           if (Recherche(v,Synonyme)==false)
               ((DefaultTableModel) Synonyme.getModel()).addRow(v);
         }
   semtab.release();
 }
public void SimilaritéClasseInstance(OWLNamedClass class1, OWLNamedClass class2) throws JWNLException, IOException, Exception
{
  ArrayList tokenInst2 = new ArrayList();
  ArrayList tokenscpt1 = Normalisation(class1.getLocalName());
  float Sim_Syn = 0, SimLexCpt = 0;

  Collection instance2 = class2.getInstances(false);
  for ( Iterator i = instance2.iterator(); i.hasNext();)
  {
    OWLIndividual individual2 = (OWLIndividual) i.next();
    tokenInst2 = Normalisation(individual2.getBrowserText());


    // *************************** Similarité Terminologique *************************** //
      if (tokenInst2.isEmpty()== false)
      {
         Vector v1 = new Vector();
         v1.add(0, class1.getLocalName());
         v1.add(1, "Concept");
         v1.add(2, individual2.getBrowserText());
         v1.add(3, "Instance");

         // Calcul des similarité
         Sim_Syn = SimilaritéTokens(tokenscpt1, tokenInst2);
         SimLexCpt = SimilaritéLexical(tokenscpt1, tokenInst2 );

         v1.add(4, Sim_Syn);
         v1.add(5, SimLexCpt);
         v1.add(6, 0);
         Vector v = new Vector();
         v.add(v1.get(0));
         v.add(v1.get(2));
         v.add(2, "Concept-Instance");

         semtab.acquire();
         ((DefaultTableModel) SimInstance.getModel()).addRow(v1);
         if (Sim_Syn == 1)
         {
           v.add(3, 1);
           if (Recherche(v,Synonyme)==false)
            {
              ((DefaultTableModel) Synonyme.getModel()).addRow(v);
              ((DefaultTableModel) MAP_Valide.getModel()).addRow(v);System.out.println(v);
            }
         }
         else
            if (SimLexCpt == 1)
            {
              v.add(3, 1);
              if (Recherche(v,Synonyme)==false)
                  ((DefaultTableModel) Synonyme.getModel()).addRow(v);
            }
        semtab.release();
     }
   }
}
public void SimilaritéInstanceClasse(OWLNamedClass class1, OWLNamedClass class2) throws JWNLException, IOException, Exception
{
  ArrayList tokenInst1 = new ArrayList();
  ArrayList tokenscpt1 = Normalisation(class2.getLocalName());
  float Sim_Syn = 0, SimLexCpt = 0;

  Collection instances = class1.getInstances(false);
  for ( Iterator jt = instances.iterator(); jt.hasNext();)
    {
      OWLIndividual individual = (OWLIndividual) jt.next();
      tokenInst1 = Normalisation(individual.getBrowserText());

      // *************************** Similarité Terminologique *************************** //
      if (tokenInst1.isEmpty()== false)
      {
         Vector v1 = new Vector();
         v1.add(0, individual.getBrowserText());
         v1.add(1, "Instance");
         v1.add(2, class2.getLocalName());
         v1.add(3, "Concept");

         // Calcul des similarité
         Sim_Syn = SimilaritéTokens(tokenInst1, tokenscpt1);
         SimLexCpt = SimilaritéLexical(tokenInst1, tokenscpt1);

         v1.add(4, Sim_Syn);
         v1.add(5, SimLexCpt);
         v1.add(6, 0);
         Vector v = new Vector();
         v.add(v1.get(0));
         v.add(v1.get(2));
         v.add(2, "Instance-Concept");

         semtab.acquire();
         ((DefaultTableModel) SimInstance.getModel()).addRow(v1);
         if (Sim_Syn == 1)
         {
           v.add(3, 1);
           if (Recherche(v,Synonyme)==false)
            {
              ((DefaultTableModel) Synonyme.getModel()).addRow(v);
              ((DefaultTableModel) MAP_Valide.getModel()).addRow(v);System.out.println(v);
            }
         }
         else
            if (SimLexCpt == 1)
            {
              v.add(3, 1);
              if (Recherche(v,Synonyme)==false)
                  ((DefaultTableModel) Synonyme.getModel()).addRow(v);
            }
        semtab.release();
     }
   }
}
public void SimilaritéInstance() throws JWNLException, IOException, Exception
{
  ArrayList tokenInst1 = new ArrayList();
  ArrayList tokenInst2 = new ArrayList();
  float Sim_Syn = 0, SimLexCpt = 0;

  Collection instance1 = classA.getInstances(false);
  for ( Iterator i = instance1.iterator(); i.hasNext();)
  {
    OWLIndividual individual1 = (OWLIndividual) i.next();
    tokenInst1 = Normalisation(individual1.getBrowserText());

    Collection instance2 = classB.getInstances(false);
    for ( Iterator j = instance2.iterator(); j.hasNext();)
    {
      OWLIndividual individual2 = (OWLIndividual) j.next();
      tokenInst2 = Normalisation(individual2.getBrowserText());

    // *************************** Similarité Terminologique *************************** //
      if (tokenInst1.isEmpty()== false && tokenInst2.isEmpty()== false)
      {
         Vector v1 = new Vector();
         v1.add(0, individual1.getBrowserText());
         v1.add(1, "Instance");
         v1.add(2, individual2.getBrowserText());
         v1.add(3, "Instance");

         // Calcul des similarité
         Sim_Syn = SimilaritéTokens(tokenInst1, tokenInst2);
         SimLexCpt = SimilaritéLexical(tokenInst1, tokenInst2);

         v1.add(4, Sim_Syn);
         v1.add(5, SimLexCpt);
         v1.add(6, 0);
         Vector v = new Vector();
         v.add(v1.get(0));
         v.add(v1.get(2));
         v.add(2,"Instance-Instance");

         semtab.acquire();
         ((DefaultTableModel) SimInstance.getModel()).addRow(v1);
         if (Sim_Syn == 1)
         {
           v.add(3, 1);
           if (Recherche(v,Synonyme)==false)
            {
              ((DefaultTableModel) Synonyme.getModel()).addRow(v);
              ((DefaultTableModel) MAP_Valide.getModel()).addRow(v);System.out.println(v);
            }
         }
         else
            if (SimLexCpt == 1)
            {
              v.add(3, 1);
              if (Recherche(v,Synonyme)==false)
                  ((DefaultTableModel) Synonyme.getModel()).addRow(v);
            }
        semtab.release();
      }
    }
  }
}
static boolean Recherche (Vector V, JTable table)
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
public float SimilaritéSyntaxique(ArrayList cpt1, ArrayList cpt2, ArrayList lbl1, ArrayList lbl2, ArrayList cmt1, ArrayList cmt2)
{
   float Sim_Syn = 0,x = coefCpt, y = coefLbl, z = coefComt;
   float SimCpt = SimilaritéTokens(cpt1, cpt2);
   float SimLbl = SimilaritéTokens(lbl1, lbl2);
   float SimCom = SimilaritéTokens(cmt1, cmt2);

   if (y == 0 && z == 0)
        Sim_Syn = SimCpt ;
   else
      if(y == 0)
           Sim_Syn = (float) (SimCpt * x + SimCom * z);
      else
           Sim_Syn = (float) (SimCpt * x + SimLbl * y + SimCom * z);

return Sim_Syn;
}
static float SimilaritéLexical(ArrayList tokens1, ArrayList tokens2) throws JWNLException, IOException, Exception
{
   float Sim = 0;

   semLex.acquire();
   JWNL.initialize(new FileInputStream("C:/JWordNetSim/test/wordnet.xml"));
   Map<String,String> params = new HashMap<String,String>();
   params.put("simType","shef.nlp.wordnet.similarity.Lin");
   params.put("infocontent","file:C:/JWordNetSim/test/ic-bnc-resnik-add1.dat");
   SimilarityMeasure sim = SimilarityMeasure.newInstance(params);
   String chaine = tokens1.get(0).toString();
   for (int i = 1; i < tokens1.size(); i++)  chaine = chaine +" "+tokens1.get(i).toString()+" ";
   String chaine2 = tokens2.get(0).toString();
   for (int i = 1; i < tokens2.size(); i++)  chaine2 = chaine2 +" "+tokens2.get(i).toString()+" ";
   SimilarityInfo x = sim.getSimilarity(chaine, chaine2);

   if (x != null)
       Sim = (float) sim.getSimilarity(chaine, chaine2).getSimilarity();
    else
       Sim = 0;
   semLex.release();
return Sim;
}
public float SimilaritéTokens(ArrayList tokens1, ArrayList tokens2)
{
    float x,y,Sim2 = 0,Sim3 = 0, Sim;

    if (tokens1.isEmpty() || tokens2.isEmpty())
        Sim = 0;
    else
    {
      // le calcule entre les deux ligne par JaroWinkler
      x = Similarité(tokens1, tokens2, 3);
      y = Similarité(tokens2, tokens1, 3);
      Sim2 = x+y;

      // le calcule entre les deux ligne par MongeElkan
      x = Similarité(tokens1, tokens2, 2);
      y = Similarité(tokens2, tokens1, 2);
      Sim3 = x+y;

      // calcul de la similarité Syntaxique
      Sim = (float) (Sim2 + Sim3 )/2;
    }
if (choix == 2)
    Sim = Sim2;
else
    if (choix == 3)
        Sim = Sim3;

return Sim;
}
static float Similarité (ArrayList tokens1, ArrayList tokens2, int choix)
{
    float Sim=0,max=0;
    float[] Val=new float[1000];
    int t=0,d=0;

    while (t < tokens1.size())
       {

         for (int k = 0; k < tokens2.size(); k++)
         {
           switch (choix)
           {
               case 2 : // calcul de similarité par la methode Jaro-Winkler
                   Val[d]=MongeElkan(tokens1.get(t).toString().toLowerCase(),tokens2.get(k).toString().toLowerCase());
                   break;
               case 3 : // calcul de similarité par la methode Monge-Elkan
                   Val[d]=JaroWinkler(tokens1.get(t).toString().toLowerCase(),tokens2.get(k).toString().toLowerCase());
                   break;
           }
           d++;
         }
  // on calcule le maximum dans Val et enregistrer le resultat dans Sim
  for (int i = 0; i < d; i++)
    if (max < Val[i]) max = Val[i];
  Sim = Sim + max;
  d=0;
  max=0;
  t++;
 }
 Sim = Sim /(tokens1.size()+tokens2.size());

 return Sim;
}
static float SimilaritéComentaire(ArrayList tokens1, ArrayList tokens2)
{
  float nbr1 = 0, nbr2 = 0;
  int i = 0;

  while (i < tokens1.size())
    {
        if(Appartient(tokens1.get(i).toString(), tokens2)==false) {nbr1++;i++;}
        else {i++;}
    }
  i=0;
  while (i < tokens2.size())
    {
        if(Appartient(tokens2.get(i).toString(), tokens1)==false) {nbr2++;i++;}
        else {i++;}
    }

  float somme = tokens1.size()+tokens2.size();
  float y = (nbr1+nbr2) /somme;
  float Sim =1-y;
  return Sim;
}
static float JaroWinkler (String ch1,String ch2)
{  float resultat = 0;
    Set<AbstractStringMetric> metrics = new HashSet<AbstractStringMetric>();
    metrics.add(new JaroWinkler());
    for (AbstractStringMetric metric : metrics)
       resultat = metric.getSimilarity(ch1, ch2);
    return resultat;
 }
static float MongeElkan (String ch1,String ch2)
{  float resultat = 0;
    Set<AbstractStringMetric> metrics = new HashSet<AbstractStringMetric>();
    metrics.add(new MongeElkan());
    for (AbstractStringMetric metric : metrics)
      resultat = metric.getSimilarity(ch1, ch2);
    return resultat;
}
static ArrayList Normalisation(String entite) throws IOException
{
  ArrayList tokens1 = new ArrayList();
  ArrayList tokens2 = new ArrayList();
  String str = null;
  int k = 0;
  StringTokenizer tokens = new StringTokenizer(entite);
  while (tokens.hasMoreTokens())
  {
   str = tokens.nextToken () ;
   String[] champs = str.split("\\p{Punct}");
   for (int i = 0 ; i<champs.length ; i++)
   {
    if (Mot_Vide(champs[i])==false)
    {
      tokens1 = tokenisation(champs[i]);
      for (int j = 0; j < tokens1.size(); j++)
          if(Appartient(tokens1.get(j).toString(), tokens2)== false)
          {
            tokens2.add(k, tokens1.get(j).toString());
            k++;
          }
    }
   }
  }
 tokens1.removeAll(tokens1);
 return tokens2;
}
static ArrayList tokenisation(String str)
{
  int deb=1,fin=0,i=0,j=0;
  char ch;
  char tableauCaractere[] = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
  String str1;
  ArrayList tokens1 = new ArrayList();
  while (deb < str.length())
  {
    ch=str.charAt(deb);
    // recherche le caractere dans le tableau des majuscule
    for (int k = 0; k < tableauCaractere.length; k++)
    {
     if(ch == tableauCaractere[k])
     {
      fin = deb;
      // Afficher la sous chaine
      str1=str.substring(i,fin);
      tokens1.add(j, str1.toLowerCase());
      j++;
      i=fin;
      break;
     }
    }
    deb++;
  }
 // Ajout de dernier tokens
 str1 = str.substring(i,str.length());
 tokens1.add(j, str1.toLowerCase());
 j++;
 return tokens1;
}
static boolean Mot_Vide(String str) throws IOException
{
    boolean bool=false;
    String nomFichier="C:\\MotVide.txt";
    InputStream ips1=new FileInputStream(nomFichier);
    InputStreamReader ipsr1=new InputStreamReader(ips1);
    BufferedReader br1=new BufferedReader(ipsr1);
    String ligne1=br1.readLine();

    try {
            FileReader reader = new FileReader(nomFichier);
            while (ligne1 != null)
               {
                   if (str.equals(ligne1)==true)
                   {
                     bool = true;
                     break;
                   }
                  ligne1=br1.readLine();
               }
            br1.close();
            reader.close();
        }
    catch (FileNotFoundException e) {
            System.out.println("Impossible de lire le fichier "+nomFichier+" !");
        }
    catch (IOException e) {
            System.out.println("Erreur de lecture !");
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
}
