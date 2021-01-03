/*
 * MAFOntologyView.java
 */

package mafontology;


import edu.stanford.smi.protege.exception.OntologyLoadException;
import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 * The application's main frame.
 */
public class MAFOntologyView extends FrameView {

  public String URI1;
  public String URI2;
  public float coefCpt,coefLbl,coefComt;
  public float CoefPere,CoefFrere,CoefFils;
  public float SeuilTerm ,SeuilStruc,Seuil;
  public int choix;
  public SimTermClasse thread1 ;
 // public boolean loaded = false,dispose=false;
  public int cpt=0;
  public String[] nom1,nom2;
  public String str1="",str2="";
  public JTable Table_Struc_Class;
    public MAFOntologyView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = MAFOntologyApp.getApplication().getMainFrame();
            aboutBox = new MAFOntologyAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        MAFOntologyApp.getApplication().show(aboutBox);
    }
    @SuppressWarnings({"static-access", "static-access"})

    public void SimTerm () throws InterruptedException
    {
        jProgressBar1.setValue(0);
        jProgressBar1.setStringPainted(true);
        jLabel3.setText(" Initialisation des parametre......");

        thread1 = new SimTermClasse();
        thread1.chemin1 = URI1;
        thread1.chemin2 = URI2;
        thread1.SimTermClass = SimilariteTermClasse;
        thread1.SimilariteTermRelation = SimilariteTermRelation;
        thread1.Synonyme = Synonyme;
        thread1.MAP_Valide = MAP_Valide;
        thread1.Table_Struc_Class = Table_Struc;
        thread1.SimInstance = SimInstance;
        thread1.choix = choix;
        thread1.SeuilTerm = SeuilTerm;    thread1.SeuilStruc = SeuilStruc; thread1.Seuil = Seuil;
        thread1.coefCpt   = coefCpt;      thread1.CoefPere   = CoefPere;
        thread1.coefLbl   = coefLbl;      thread1.CoefFrere  = CoefFrere;
        thread1.coefComt  = coefComt;     thread1.CoefFils   = CoefFils ;

        SimStrucClass.SeuilTerm  = SeuilTerm;
        SimStrucClass.SeuilStruc = SeuilStruc;
        SimStrucClass.Seuil = Seuil;
        SimStrucClass.CoefPere   = CoefPere;
        SimStrucClass.CoefFrere  = CoefFrere;
        SimStrucClass.CoefFils   = CoefFils ;

        thread1.jProgressBar = jProgressBar1;
        thread1.jlabel3 = jLabel3;
        thread1.jbutton5 = jButton5;
        thread1.start();
  }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        Open = new javax.swing.JFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        RunProcess = new javax.swing.JFrame();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jFileChooser1 = new javax.swing.JFileChooser();
        ResultStructurelleClasse = new javax.swing.JFrame();
        jScrollPane6 = new javax.swing.JScrollPane();
        Table_Struc = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        MappingValidee = new javax.swing.JFrame();
        jScrollPane5 = new javax.swing.JScrollPane();
        MAP_Valide = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        ConfigurationTerminologique = new javax.swing.JFrame();
        jButton8 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSpinner26 = new javax.swing.JSpinner();
        jSpinner24 = new javax.swing.JSpinner();
        jLabel28 = new javax.swing.JLabel();
        jSpinner30 = new javax.swing.JSpinner();
        jLabel29 = new javax.swing.JLabel();
        jSpinner31 = new javax.swing.JSpinner();
        jLabel30 = new javax.swing.JLabel();
        SynonymeTerminologique = new javax.swing.JFrame();
        jScrollPane12 = new javax.swing.JScrollPane();
        Synonyme = new javax.swing.JTable();
        ResultatRelation = new javax.swing.JFrame();
        jScrollPane13 = new javax.swing.JScrollPane();
        SimilariteTermRelation = new javax.swing.JTable();
        jButton17 = new javax.swing.JButton();
        ResultatClasse = new javax.swing.JFrame();
        jScrollPane14 = new javax.swing.JScrollPane();
        SimilariteTermClasse = new javax.swing.JTable();
        jButton18 = new javax.swing.JButton();
        menuBar1 = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu1 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu1 = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem1 = new javax.swing.JMenuItem();
        SimiaritéInstance = new javax.swing.JFrame();
        jScrollPane15 = new javax.swing.JScrollPane();
        SimInstance = new javax.swing.JTable();
        Inetrprétation = new javax.swing.JFrame();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton15 = new javax.swing.JButton();
        jFrame2 = new javax.swing.JFrame();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(mafontology.MAFOntologyApp.class).getContext().getResourceMap(MAFOntologyView.class);
        mainPanel.setBackground(resourceMap.getColor("mainPanel.background")); // NOI18N
        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 564, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 370, Short.MAX_VALUE)
        );

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setEnabled(false);
        progressBar.setName("progressBar"); // NOI18N

        org.jdesktop.layout.GroupLayout statusPanelLayout = new org.jdesktop.layout.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
            .add(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(statusMessageLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 394, Short.MAX_VALUE)
                .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(statusPanelLayout.createSequentialGroup()
                .add(statusPanelSeparator, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(statusPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(statusMessageLabel)
                    .add(statusAnimationLabel)
                    .add(progressBar, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(3, 3, 3))
        );

        Open.setTitle(resourceMap.getString("Open.title")); // NOI18N
        Open.setLocationByPlatform(true);
        Open.setName("Open"); // NOI18N
        Open.setResizable(false);

        jLabel1.setFont(resourceMap.getFont("jLabel1.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel2.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField2.setText(resourceMap.getString("jTextField2.text")); // NOI18N
        jTextField2.setName("jTextField2"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setName("jButton4"); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jToggleButton1.setText(resourceMap.getString("jToggleButton1.text")); // NOI18N
        jToggleButton1.setName("jToggleButton1"); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setText(resourceMap.getString("jToggleButton2.text")); // NOI18N
        jToggleButton2.setBorderPainted(false);
        jToggleButton2.setName("jToggleButton2"); // NOI18N
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton3.setText(resourceMap.getString("jToggleButton3.text")); // NOI18N
        jToggleButton3.setName("jToggleButton3"); // NOI18N
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout OpenLayout = new org.jdesktop.layout.GroupLayout(Open.getContentPane());
        Open.getContentPane().setLayout(OpenLayout);
        OpenLayout.setHorizontalGroup(
            OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OpenLayout.createSequentialGroup()
                .addContainerGap()
                .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jToggleButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jToggleButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jToggleButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(OpenLayout.createSequentialGroup()
                        .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel1))
                        .add(73, 73, 73)
                        .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, OpenLayout.createSequentialGroup()
                        .add(jButton3)
                        .add(18, 18, 18)
                        .add(jButton4)
                        .add(124, 124, 124)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(50, 50, 50))
        );

        OpenLayout.linkSize(new java.awt.Component[] {jButton1, jButton2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        OpenLayout.linkSize(new java.awt.Component[] {jButton3, jButton4}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        OpenLayout.setVerticalGroup(
            OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(OpenLayout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jToggleButton2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(11, 11, 11)
                .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(OpenLayout.createSequentialGroup()
                        .add(jToggleButton1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 60, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jToggleButton3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(OpenLayout.createSequentialGroup()
                        .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton1)
                            .add(jLabel1))
                        .add(18, 18, 18)
                        .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jButton2)
                            .add(jLabel2))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(OpenLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButton3)
                            .add(jButton4))))
                .add(18, 18, 18))
        );

        OpenLayout.linkSize(new java.awt.Component[] {jButton1, jButton2}, org.jdesktop.layout.GroupLayout.VERTICAL);

        OpenLayout.linkSize(new java.awt.Component[] {jButton3, jButton4}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jToggleButton1.getAccessibleContext().setAccessibleName(resourceMap.getString("jToggleButton1.AccessibleContext.accessibleName")); // NOI18N
        jToggleButton3.getAccessibleContext().setAccessibleName(resourceMap.getString("jToggleButton3.AccessibleContext.accessibleName")); // NOI18N

        RunProcess.setName("RunProcess"); // NOI18N
        RunProcess.setResizable(false);

        jProgressBar1.setName("jProgressBar1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setName("jButton5"); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout RunProcessLayout = new org.jdesktop.layout.GroupLayout(RunProcess.getContentPane());
        RunProcess.getContentPane().setLayout(RunProcessLayout);
        RunProcessLayout.setHorizontalGroup(
            RunProcessLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RunProcessLayout.createSequentialGroup()
                .add(24, 24, 24)
                .add(RunProcessLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel3)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 357, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, RunProcessLayout.createSequentialGroup()
                .addContainerGap(319, Short.MAX_VALUE)
                .add(jButton5)
                .addContainerGap())
        );
        RunProcessLayout.setVerticalGroup(
            RunProcessLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(RunProcessLayout.createSequentialGroup()
                .add(34, 34, 34)
                .add(jLabel3)
                .add(18, 18, 18)
                .add(jProgressBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 28, Short.MAX_VALUE)
                .add(jButton5)
                .addContainerGap())
        );

        jFileChooser1.setCurrentDirectory(new java.io.File("E:\\Travail\\Memoire\\Ontologies"));
        jFileChooser1.setName("jFileChooser1"); // NOI18N

        ResultStructurelleClasse.setName("ResultStructurelleClasse"); // NOI18N
        ResultStructurelleClasse.setResizable(false);

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        Table_Struc.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        Table_Struc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité 1", "Entité 2", "Type", "Similarité des peres", "Similarité des freres", "Similarité des fils", "Similarité Structurelle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table_Struc.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Table_Struc.setName("Table_Struc"); // NOI18N
        jScrollPane6.setViewportView(Table_Struc);

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ResultStructurelleClasseLayout = new org.jdesktop.layout.GroupLayout(ResultStructurelleClasse.getContentPane());
        ResultStructurelleClasse.getContentPane().setLayout(ResultStructurelleClasseLayout);
        ResultStructurelleClasseLayout.setHorizontalGroup(
            ResultStructurelleClasseLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ResultStructurelleClasseLayout.createSequentialGroup()
                .addContainerGap()
                .add(jButton9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(10, 10, 10))
        );
        ResultStructurelleClasseLayout.setVerticalGroup(
            ResultStructurelleClasseLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ResultStructurelleClasseLayout.createSequentialGroup()
                .add(jScrollPane6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton9)
                .addContainerGap())
        );

        MappingValidee.setName("MappingValidee"); // NOI18N
        MappingValidee.setResizable(false);

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        MAP_Valide.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        MAP_Valide.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité 1", "Entité 2", "Type", "Valeur de Similarité "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MAP_Valide.setName("MAP_Valide"); // NOI18N
        jScrollPane5.setViewportView(MAP_Valide);

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout MappingValideeLayout = new org.jdesktop.layout.GroupLayout(MappingValidee.getContentPane());
        MappingValidee.getContentPane().setLayout(MappingValideeLayout);
        MappingValideeLayout.setHorizontalGroup(
            MappingValideeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, MappingValideeLayout.createSequentialGroup()
                .addContainerGap(876, Short.MAX_VALUE)
                .add(jButton10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(MappingValideeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jScrollPane5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE))
        );
        MappingValideeLayout.setVerticalGroup(
            MappingValideeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, MappingValideeLayout.createSequentialGroup()
                .addContainerGap(462, Short.MAX_VALUE)
                .add(jButton10)
                .addContainerGap())
            .add(MappingValideeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(MappingValideeLayout.createSequentialGroup()
                    .add(jScrollPane5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 452, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(44, Short.MAX_VALUE)))
        );

        ConfigurationTerminologique.setName("ConfigurationTerminologique"); // NOI18N
        ConfigurationTerminologique.setResizable(false);

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton11.setText(resourceMap.getString("jButton11.text")); // NOI18N
        jButton11.setName("jButton11"); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel27.setFont(resourceMap.getFont("jLabel27.font")); // NOI18N
        jLabel27.setText(resourceMap.getString("jLabel27.text")); // NOI18N
        jLabel27.setName("jLabel27"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jSpinner26.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.05f)));
        jSpinner26.setName("jSpinner26"); // NOI18N

        jSpinner24.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.05f)));
        jSpinner24.setName("jSpinner24"); // NOI18N

        jLabel28.setText(resourceMap.getString("jLabel28.text")); // NOI18N
        jLabel28.setName("jLabel28"); // NOI18N

        jSpinner30.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.1f)));
        jSpinner30.setName("jSpinner30"); // NOI18N

        jLabel29.setFont(resourceMap.getFont("jLabel29.font")); // NOI18N
        jLabel29.setText(resourceMap.getString("jLabel29.text")); // NOI18N
        jLabel29.setName("jLabel29"); // NOI18N

        jSpinner31.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), Float.valueOf(0.1f)));
        jSpinner31.setName("jSpinner31"); // NOI18N

        jLabel30.setText(resourceMap.getString("jLabel30.text")); // NOI18N
        jLabel30.setName("jLabel30"); // NOI18N

        org.jdesktop.layout.GroupLayout ConfigurationTerminologiqueLayout = new org.jdesktop.layout.GroupLayout(ConfigurationTerminologique.getContentPane());
        ConfigurationTerminologique.getContentPane().setLayout(ConfigurationTerminologiqueLayout);
        ConfigurationTerminologiqueLayout.setHorizontalGroup(
            ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                .addContainerGap()
                .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 161, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 36, Short.MAX_VALUE)
                        .add(jSpinner31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel9)
                            .add(jLabel23)
                            .add(jLabel28))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 136, Short.MAX_VALUE)
                        .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jSpinner26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jSpinner24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jSpinner30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton8))
                .add(18, 18, 18)
                .add(jButton11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(37, 37, 37))
            .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                    .add(20, 20, 20)
                    .add(jLabel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 209, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        ConfigurationTerminologiqueLayout.linkSize(new java.awt.Component[] {jButton11, jButton8}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        ConfigurationTerminologiqueLayout.setVerticalGroup(
            ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                .add(45, 45, 45)
                .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSpinner26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jSpinner24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jSpinner30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(31, 31, 31)
                        .add(jLabel28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(jLabel27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(3, 3, 3))
                    .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                        .add(jSpinner31, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)))
                .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton8)
                    .add(jButton11))
                .addContainerGap())
            .add(ConfigurationTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(ConfigurationTerminologiqueLayout.createSequentialGroup()
                    .add(21, 21, 21)
                    .add(jLabel29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(222, Short.MAX_VALUE)))
        );

        ConfigurationTerminologiqueLayout.linkSize(new java.awt.Component[] {jButton11, jButton8}, org.jdesktop.layout.GroupLayout.VERTICAL);

        SynonymeTerminologique.setName("SynonymeTerminologique"); // NOI18N

        jScrollPane12.setName("jScrollPane12"); // NOI18N

        Synonyme.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        Synonyme.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité 1", "Entité 2", "Type", "Valeur de Similarité "
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Synonyme.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Synonyme.setName("Synonyme"); // NOI18N
        jScrollPane12.setViewportView(Synonyme);

        org.jdesktop.layout.GroupLayout SynonymeTerminologiqueLayout = new org.jdesktop.layout.GroupLayout(SynonymeTerminologique.getContentPane());
        SynonymeTerminologique.getContentPane().setLayout(SynonymeTerminologiqueLayout);
        SynonymeTerminologiqueLayout.setHorizontalGroup(
            SynonymeTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 969, Short.MAX_VALUE)
            .add(SynonymeTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jScrollPane12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE))
        );
        SynonymeTerminologiqueLayout.setVerticalGroup(
            SynonymeTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 474, Short.MAX_VALUE)
            .add(SynonymeTerminologiqueLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane12, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
        );

        ResultatRelation.setName("ResultatRelation"); // NOI18N

        jScrollPane13.setName("jScrollPane13"); // NOI18N

        SimilariteTermRelation.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        SimilariteTermRelation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité1", "Entité2", "Type", "Similarité Domaine", "Similarité Range", "Similarité Terminologique", "Similarité Relation"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SimilariteTermRelation.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SimilariteTermRelation.setName("SimilariteTermRelation"); // NOI18N
        jScrollPane13.setViewportView(SimilariteTermRelation);

        jButton17.setText(resourceMap.getString("jButton17.text")); // NOI18N
        jButton17.setName("jButton17"); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ResultatRelationLayout = new org.jdesktop.layout.GroupLayout(ResultatRelation.getContentPane());
        ResultatRelation.getContentPane().setLayout(ResultatRelationLayout);
        ResultatRelationLayout.setHorizontalGroup(
            ResultatRelationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ResultatRelationLayout.createSequentialGroup()
                .addContainerGap(992, Short.MAX_VALUE)
                .add(jButton17, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .add(jScrollPane13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
        );
        ResultatRelationLayout.setVerticalGroup(
            ResultatRelationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ResultatRelationLayout.createSequentialGroup()
                .add(jScrollPane13, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jButton17)
                .addContainerGap())
        );

        ResultatClasse.setName("ResultatClasse"); // NOI18N
        ResultatClasse.setResizable(false);

        jScrollPane14.setName("jScrollPane14"); // NOI18N

        SimilariteTermClasse.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        SimilariteTermClasse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité1", "Entité2", "Type", "Similarité Syntaxique", "Similarité Lexical", "Similarité Structurelle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SimilariteTermClasse.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SimilariteTermClasse.setInheritsPopupMenu(true);
        SimilariteTermClasse.setName("SimilariteTermClasse"); // NOI18N
        jScrollPane14.setViewportView(SimilariteTermClasse);
        SimilariteTermClasse.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title0")); // NOI18N
        SimilariteTermClasse.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title1")); // NOI18N
        SimilariteTermClasse.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title2")); // NOI18N
        SimilariteTermClasse.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title3")); // NOI18N
        SimilariteTermClasse.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title4")); // NOI18N
        SimilariteTermClasse.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("SimilariteTermClasse.columnModel.title5")); // NOI18N

        jButton18.setText(resourceMap.getString("jButton18.text")); // NOI18N
        jButton18.setName("jButton18"); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout ResultatClasseLayout = new org.jdesktop.layout.GroupLayout(ResultatClasse.getContentPane());
        ResultatClasse.getContentPane().setLayout(ResultatClasseLayout);
        ResultatClasseLayout.setHorizontalGroup(
            ResultatClasseLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(ResultatClasseLayout.createSequentialGroup()
                .addContainerGap()
                .add(ResultatClasseLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton18, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 1044, Short.MAX_VALUE))
                .addContainerGap())
        );
        ResultatClasseLayout.setVerticalGroup(
            ResultatClasseLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, ResultatClasseLayout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane14, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                .add(18, 18, 18)
                .add(jButton18)
                .addContainerGap())
        );

        menuBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menuBar1.setForeground(resourceMap.getColor("menuBar1.foreground")); // NOI18N
        menuBar1.setFont(resourceMap.getFont("menuBar1.font")); // NOI18N
        menuBar1.setMargin(new java.awt.Insets(10, 10, 10, 10));
        menuBar1.setName("menuBar1"); // NOI18N

        fileMenu1.setText(resourceMap.getString("fileMenu1.text")); // NOI18N
        fileMenu1.setName("fileMenu1"); // NOI18N
        fileMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenu1ActionPerformed(evt);
            }
        });

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem12);
        jMenuItem12.getAccessibleContext().setAccessibleName(resourceMap.getString("jMenuItem12.AccessibleContext.accessibleName")); // NOI18N

        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.setEnabled(false);
        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        fileMenu1.add(jMenuItem13);

        jSeparator3.setName("jSeparator3"); // NOI18N
        fileMenu1.add(jSeparator3);

        exitMenuItem1.setText(resourceMap.getString("exitMenuItem1.text")); // NOI18N
        exitMenuItem1.setName("exitMenuItem1"); // NOI18N
        exitMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItem1ActionPerformed(evt);
            }
        });
        fileMenu1.add(exitMenuItem1);

        menuBar1.add(fileMenu1);
        fileMenu1.getAccessibleContext().setAccessibleName(resourceMap.getString("fileMenu1.AccessibleContext.accessibleName")); // NOI18N

        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenu4.setEnabled(false);
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem14);

        jMenuItem15.setText(resourceMap.getString("jMenuItem15.text")); // NOI18N
        jMenuItem15.setName("jMenuItem15"); // NOI18N
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        menuBar1.add(jMenu4);

        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setEnabled(false);
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem16.setText(resourceMap.getString("jMenuItem16.text")); // NOI18N
        jMenuItem16.setName("jMenuItem16"); // NOI18N
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuItem17.setText(resourceMap.getString("jMenuItem17.text")); // NOI18N
        jMenuItem17.setEnabled(false);
        jMenuItem17.setName("jMenuItem17"); // NOI18N
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem17);

        jSeparator4.setName("jSeparator4"); // NOI18N
        jMenu5.add(jSeparator4);

        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setEnabled(false);
        jMenu6.setName("jMenu6"); // NOI18N

        jMenuItem21.setText(resourceMap.getString("jMenuItem21.text")); // NOI18N
        jMenuItem21.setActionCommand(resourceMap.getString("jMenuItem21.actionCommand")); // NOI18N
        jMenuItem21.setName("jMenuItem21"); // NOI18N
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem21);

        jMenuItem19.setText(resourceMap.getString("jMenuItem19.text")); // NOI18N
        jMenuItem19.setName("jMenuItem19"); // NOI18N
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem19);

        jSeparator5.setName("jSeparator5"); // NOI18N
        jMenu6.add(jSeparator5);

        jMenuItem20.setText(resourceMap.getString("jMenuItem20.text")); // NOI18N
        jMenuItem20.setName("jMenuItem20"); // NOI18N
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem20);

        jSeparator1.setName("jSeparator1"); // NOI18N
        jMenu6.add(jSeparator1);

        jMenuItem22.setText(resourceMap.getString("jMenuItem22.text")); // NOI18N
        jMenuItem22.setName("jMenuItem22"); // NOI18N
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem22);

        jMenu5.add(jMenu6);

        jMenuItem24.setText(resourceMap.getString("jMenuItem24.text")); // NOI18N
        jMenuItem24.setEnabled(false);
        jMenuItem24.setName("jMenuItem24"); // NOI18N
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        menuBar1.add(jMenu5);

        helpMenu1.setText(resourceMap.getString("helpMenu1.text")); // NOI18N
        helpMenu1.setName("helpMenu1"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(mafontology.MAFOntologyApp.class).getContext().getActionMap(MAFOntologyView.class, this);
        aboutMenuItem1.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem1.setName("aboutMenuItem1"); // NOI18N
        helpMenu1.add(aboutMenuItem1);

        menuBar1.add(helpMenu1);

        SimiaritéInstance.setAlwaysOnTop(true);
        SimiaritéInstance.setName("SimiaritéInstance"); // NOI18N

        jScrollPane15.setName("jScrollPane15"); // NOI18N

        SimInstance.setFont(resourceMap.getFont("SimInstance.font")); // NOI18N
        SimInstance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entité 1", "Type1", "Entité2", "Type2", "Simiarité Syntaxique", "Simiarité Lexical", "Similarité Structurelle"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SimInstance.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        SimInstance.setName("SimInstance"); // NOI18N
        jScrollPane15.setViewportView(SimInstance);

        org.jdesktop.layout.GroupLayout SimiaritéInstanceLayout = new org.jdesktop.layout.GroupLayout(SimiaritéInstance.getContentPane());
        SimiaritéInstance.getContentPane().setLayout(SimiaritéInstanceLayout);
        SimiaritéInstanceLayout.setHorizontalGroup(
            SimiaritéInstanceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 808, Short.MAX_VALUE)
            .add(SimiaritéInstanceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jScrollPane15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 808, Short.MAX_VALUE))
        );
        SimiaritéInstanceLayout.setVerticalGroup(
            SimiaritéInstanceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 474, Short.MAX_VALUE)
            .add(SimiaritéInstanceLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane15, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE))
        );

        Inetrprétation.setName("Inetrprétation"); // NOI18N
        Inetrprétation.setResizable(false);

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTextField3.setText(resourceMap.getString("jTextField3.text")); // NOI18N
        jTextField3.setName("jTextField3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane1.setViewportView(jTextArea1);

        jButton15.setText(resourceMap.getString("jButton15.text")); // NOI18N
        jButton15.setName("jButton15"); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout InetrprétationLayout = new org.jdesktop.layout.GroupLayout(Inetrprétation.getContentPane());
        Inetrprétation.getContentPane().setLayout(InetrprétationLayout);
        InetrprétationLayout.setHorizontalGroup(
            InetrprétationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(InetrprétationLayout.createSequentialGroup()
                .add(InetrprétationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(InetrprétationLayout.createSequentialGroup()
                        .add(99, 99, 99)
                        .add(jButton6)
                        .add(18, 18, 18)
                        .add(jButton7)
                        .add(33, 33, 33)
                        .add(jButton15))
                    .add(InetrprétationLayout.createSequentialGroup()
                        .add(145, 145, 145)
                        .add(jLabel4)))
                .add(44, 44, 44))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, InetrprétationLayout.createSequentialGroup()
                .add(119, 119, 119)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(69, 69, 69))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, InetrprétationLayout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 163, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(141, 141, 141))
        );

        InetrprétationLayout.linkSize(new java.awt.Component[] {jButton6, jButton7}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        InetrprétationLayout.setVerticalGroup(
            InetrprétationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, InetrprétationLayout.createSequentialGroup()
                .add(37, 37, 37)
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 83, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 19, Short.MAX_VALUE)
                .add(InetrprétationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton6)
                    .add(jButton7)
                    .add(jButton15))
                .add(23, 23, 23))
        );

        InetrprétationLayout.linkSize(new java.awt.Component[] {jButton6, jButton7}, org.jdesktop.layout.GroupLayout.VERTICAL);

        jFrame2.setName("jFrame2"); // NOI18N

        jButton12.setText(resourceMap.getString("jButton12.text")); // NOI18N
        jButton12.setName("jButton12"); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText(resourceMap.getString("jButton13.text")); // NOI18N
        jButton13.setName("jButton13"); // NOI18N

        jTextField4.setText(resourceMap.getString("jTextField4.text")); // NOI18N
        jTextField4.setName("jTextField4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jButton14.setText(resourceMap.getString("jButton14.text")); // NOI18N
        jButton14.setName("jButton14"); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jFrame2Layout = new org.jdesktop.layout.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jFrame2Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 166, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jButton14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 33, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(23, 23, 23))
            .add(jFrame2Layout.createSequentialGroup()
                .add(216, 216, 216)
                .add(jButton12)
                .add(43, 43, 43)
                .add(jButton13)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jFrame2Layout.createSequentialGroup()
                .add(110, 110, 110)
                .add(jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 19, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jButton14))
                .add(41, 41, 41)
                .add(jFrame2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton12)
                    .add(jButton13))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar1);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ouvrir la premiere ontologie
        JFileChooser choose = new JFileChooser();
        int retour=choose.showOpenDialog(Open);
        if(retour==JFileChooser.APPROVE_OPTION)
          jTextField1.setText(choose.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // ouvrir la deuxieme ontologie
        JFileChooser choose = new JFileChooser();
        int retour=choose.showOpenDialog(Open);
        if(retour==JFileChooser.APPROVE_OPTION)
          jTextField2.setText(choose.getSelectedFile().getAbsolutePath());
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // valider les deux operation
        boolean b = false,c =false;
       if(jTextField1.getText().equals("")||jTextField2.getText().equals(""))
        {
        JOptionPane.showMessageDialog(Open,"Chemin(s) d'ontologie(s) non spécifié(s) correctement","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
        b = true;
        }
        /*Cas d'un cragement de deux ontologies inernes*/
        else if ((b == false)&&(jLabel1.getText().startsWith("Chemin"))&&(jLabel2.getText().startsWith("Chemin")))
        { 
        char[] text =jTextField1.getText().toCharArray();
        for(int i=0; i<text.length; i++) { if(text[i] == (char)92) text[i] = (char)47;}
        String chemin1 = "file:///".concat(String.valueOf(text));
        if (chemin1.endsWith(".owl")== false)   
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField1.setText("");
                       c= true;
                      }
          else if (c== false)
            {
             URI1 = chemin1;
             text =jTextField2.getText().toCharArray();
             for(int i=0; i<text.length; i++) { if(text[i] == (char)92) text[i] = (char)47;}
             String chemin2 = "file:///".concat(String.valueOf(text));
             if (chemin2.endsWith(".owl")== false)   
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField2.setText("");
                       c= true;
                      }
               else if (c==false)
               {
                 URI2 = chemin2;
                 jMenuItem14.setEnabled(true);
                 jMenuItem15.setEnabled(true);
                 jMenuItem16.setEnabled(true);
                 jMenuItem16.setEnabled(true);
                 jMenu4.setEnabled(true);
                 jMenu5.setEnabled(true);
                 Open.dispose();
               } 
           }
        }

         /*Cas d'un cragement de deux ontologies Externes*/
        else if ((b == false)&&((jLabel1.getText().startsWith("URL"))&&(jLabel2.getText().startsWith("URL"))))
        {
        String chemin1 = jTextField1.getText().toString();
        if (chemin1.endsWith(".owl")== false)
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField1.setText("");
                       c= true;
                      }
          if (c==false)
          {
           URI1 = chemin1;
           String chemin2 =jTextField2.getText().toString();
          if (chemin2.endsWith(".owl")== false)   
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField2.setText("");
                       c= true;
                      }   
          else if (c==false)
            { 
              URI2 = chemin2;
              jMenuItem14.setEnabled(true);
              jMenuItem15.setEnabled(true);
              jMenuItem16.setEnabled(true);
              jMenuItem16.setEnabled(true);
              jMenu4.setEnabled(true);
              jMenu5.setEnabled(true);
             Open.dispose();
            }
          }
        }
        
        /*Cas d'un chargement d'une ontologie inetrne avec une ontologie externe*/
         else if ((b == false)&&((jLabel1.getText().startsWith("Chemin"))&&(jLabel2.getText().startsWith("URL"))))
        {
        char[] text =jTextField1.getText().toCharArray();
        for(int i=0; i<text.length; i++) { if(text[i] == (char)92) text[i] = (char)47;}
        String chemin1 = "file:///".concat(String.valueOf(text));
        if (chemin1.endsWith(".owl")== false)
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField1.setText("");
                       c= true;
                      }
          else if (c== false)
            {
             URI1 = chemin1;
             String chemin2 =jTextField2.getText().toString();
             if (chemin2.endsWith(".owl")== false)
                      {
                       JOptionPane.showMessageDialog(Open," format de l'ontologie chargée inconnu!! ","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
                       jTextField2.setText("");
                       c= true;
                      }
            else if (c==false)
              {
              URI2 = chemin2;
              jMenuItem14.setEnabled(true);
              jMenuItem15.setEnabled(true);
              jMenuItem16.setEnabled(true);
              jMenuItem16.setEnabled(true);
              jMenu4.setEnabled(true);
              jMenu5.setEnabled(true);
              Open.dispose();
             }
          }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Annuler les operation effectuer
        Open.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed
    @SuppressWarnings("static-access")
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    if(jButton5.getText().equals("Executer"))
        try
        {
           // Initialisation des tables
           ViderTable(Table_Struc);
           ViderTable(MAP_Valide);
           ViderTable(SimInstance);
           ViderTable(SimilariteTermClasse);
           ViderTable(SimilariteTermRelation);
           ViderTable(Synonyme);
           jProgressBar1.setValue(0);
           jProgressBar1.setStringPainted(true);
           jLabel3.setText(" Initialisation des parametres......");
           SimTerm();
           thread1.sleep(500);
           jButton5.setText("Interrupt");
        }
        catch (InterruptedException ex)
        {
          Logger.getLogger(MAFOntologyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    if(jButton5.getText().equals("Interrupt"))// Interrompre
    {
          int result = JOptionPane.showConfirmDialog((Component) evt.getSource(),
            "Voulez-vous vraiment interrompre l'execution de votre application");
        if (result == JOptionPane.YES_OPTION)
        {
           RunProcess.dispose();
           jProgressBar1.setValue(0);
           jProgressBar1.setStringPainted(true);
           jLabel3.setText(" Initialisation des parametres......");
           jButton5.setText("Executer");
           ViderTable(Table_Struc);
           ViderTable(MAP_Valide);
           ViderTable(SimInstance);
           ViderTable(SimilariteTermClasse);
           ViderTable(SimilariteTermRelation);
           ViderTable(Synonyme);
           thread1.interrupt();
           jMenuItem17.setEnabled(false);
           jMenuItem14.setEnabled(false);
           jMenuItem15.setEnabled(false);
           jMenu6.setEnabled(false);
           jMenuItem24.setEnabled(false);
           jSpinner26.setValue(0);
           jSpinner24.setValue(0);
           jSpinner30.setValue(0);
       }
    }
    if(jButton5.getText().equals("Terminer"))
    {
       RunProcess.dispose();
       jSpinner26.setValue(0);
       jSpinner24.setValue(0);
       jSpinner30.setValue(0);
       jMenuItem13.setEnabled(true);

      
       float x= MAP_Valide.getRowCount();
       int y = Synonyme.getRowCount();

        for (int a=0; a < Table_Struc.getRowCount();a++)
        {
         boolean Val1=false;
         float z =Float.parseFloat(Table_Struc.getValueAt(a, 6).toString());
         if (z >= SeuilStruc)
         {
           for(int b=0;b < Synonyme.getRowCount();b++)
          {
            String ch1=Table_Struc.getValueAt(a,0).toString();
            String ch2=Table_Struc.getValueAt(a,1).toString();
            String ch3=Synonyme.getValueAt(b,0).toString();
            String ch4=Synonyme.getValueAt(b,1).toString();
              if (((ch1.equals(ch3)) && ((ch2.equals(ch4))))
                ||((ch1.equals(ch4))&& ((ch2.equals(ch3))))) Val1 =true;
           }
           if (Val1 ==false)y++;
          }
         Val1=false;
        }



        for (int a=0;a< SimilariteTermClasse.getRowCount();a++)
         {
          boolean Val1=false,Val2 = false;
         float z =Float.parseFloat(SimilariteTermClasse.getValueAt(a, 3).toString());
         if (z >= SeuilTerm)
         {
           for(int b=0;b < Synonyme.getRowCount();b++)
          {
            String ch1=SimilariteTermClasse.getValueAt(a,0).toString();
            String ch2=SimilariteTermClasse.getValueAt(a,1).toString();
            String ch3=Synonyme.getValueAt(b,0).toString();
            String ch4=Synonyme.getValueAt(b,1).toString();
           
              if (((ch1.equals(ch3)) && ((ch2.equals(ch4))))
                ||((ch1.equals(ch4))&& ((ch2.equals(ch3))))) Val1 =true;
             
           }

            for(int c=0;c < Table_Struc.getRowCount();c++)
          {
            String ch1=SimilariteTermClasse.getValueAt(a,0).toString();
            String ch2=SimilariteTermClasse.getValueAt(a,1).toString();
            String ch5=Table_Struc.getValueAt(c,0).toString();
            String ch6=Table_Struc.getValueAt(c,1).toString();
            if (((ch1.equals(ch5)) && ((ch2.equals(ch6))))
                ||((ch1.equals(ch6))&& ((ch2.equals(ch5))))) Val2 =true;

          }
           if ((Val1 ==false)&&(Val2==false))y++;
          }
         Val1=false;Val2=false;
        }
       
       
        for (int a=0;a< SimilariteTermClasse.getRowCount();a++)
         {
         boolean Val1=false,Val2 = false;
         float z =Float.parseFloat(SimilariteTermClasse.getValueAt(a, 4).toString());
         if (z >= SeuilTerm)
         {
           for(int b=0;b < Synonyme.getRowCount();b++)
          {
            String ch1=SimilariteTermClasse.getValueAt(a,0).toString();
            String ch2=SimilariteTermClasse.getValueAt(a,1).toString();
            String ch3=Synonyme.getValueAt(b,0).toString();
            String ch4=Synonyme.getValueAt(b,1).toString();

              if (((ch1.equals(ch3)) && ((ch2.equals(ch4))))
                ||((ch1.equals(ch4))&& ((ch2.equals(ch3))))) Val1 =true;

           }

            for(int c=0;c < Table_Struc.getRowCount();c++)
          {
            String ch1=SimilariteTermClasse.getValueAt(a,0).toString();
            String ch2=SimilariteTermClasse.getValueAt(a,1).toString();
            String ch5=Table_Struc.getValueAt(c,0).toString();
            String ch6=Table_Struc.getValueAt(c,1).toString();
            if (((ch1.equals(ch5)) && ((ch2.equals(ch6))))
                ||((ch1.equals(ch6))&& ((ch2.equals(ch5))))) Val2 =true;
          }
           if ((Val1 ==false)&&(Val2==false))y++;
          }
         Val1=false;Val2=false;
        }
        
       
       
       
       
             for (int a= 0; a <SimilariteTermRelation.getRowCount();a++)
              {
               boolean Val1=false,Val2 = false;
               float z =Float.parseFloat(SimilariteTermRelation.getValueAt(a, 6).toString());
                if (z>= SeuilStruc)
                {
                  for(int b=0;b < Synonyme.getRowCount();b++)
                   {
                  String ch1=SimilariteTermRelation.getValueAt(a,0).toString();
                  String ch2=SimilariteTermRelation.getValueAt(a,1).toString();
                  String ch3=Synonyme.getValueAt(b,0).toString();
                  String ch4=Synonyme.getValueAt(b,1).toString();

                  if (((ch1.equals(ch3)) && ((ch2.equals(ch4))))
                     ||((ch1.equals(ch4))&& ((ch2.equals(ch3))))) Val1 =true;
                   }
                 for(int b=0;b < SimilariteTermClasse.getRowCount();b++)
                   {
                  String ch1=SimilariteTermRelation.getValueAt(a,0).toString();
                  String ch2=SimilariteTermRelation.getValueAt(a,1).toString();
                  String ch5=SimilariteTermClasse.getValueAt(b,0).toString();
                  String ch6=SimilariteTermClasse.getValueAt(b,1).toString();

                  if (((ch1.equals(ch5)) && ((ch2.equals(ch6))))
                     ||((ch1.equals(ch6))&& ((ch2.equals(ch5)))))Val2 =true;
                   }
                if ((Val1 ==false)&&(Val2==false))y++;
                }
                Val1=false;Val2=false;
              }
       
       float par = x/y;
       Inetrprétation.setTitle("Processus d'interprétation");
       Inetrprétation.pack();
       Inetrprétation.setLocationRelativeTo(null);
       Inetrprétation.setVisible(true);
       String ch = String.valueOf((float) par);
       jTextField3.setText(ch);
        float SeuilInt= Float.parseFloat(jSpinner30.getValue().toString());
       if (par >= SeuilInt )jTextArea1.setText("Le choix le plus judicieux est de faire une fusion d'ontologies!!");
       else jTextArea1.setText("L'Aligement d'ontologies est le meuilleur choix!!");
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        MappingValidee.dispose();
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        ResultStructurelleClasse.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    // Verificatrion des parametre terminologique
    Seuil = Float.parseFloat(jSpinner30.getValue().toString());
    SeuilTerm  = Float.parseFloat(jSpinner26.getValue().toString());
    SeuilStruc  = Float.parseFloat(jSpinner24.getValue().toString());
    boolean b = false;
          
          if (SeuilTerm ==0){JOptionPane.showMessageDialog(null, "Seuil Terminologique Invalide", "Attention", JOptionPane.ERROR_MESSAGE);b=true;}
          
          if (SeuilStruc == 0){JOptionPane.showMessageDialog(null, "Seuil Structurel Invalide", "Attention", JOptionPane.ERROR_MESSAGE);b=true;}

          if (Seuil ==0){JOptionPane.showMessageDialog(null, "Seuil Géneral Invalide", "Attention", JOptionPane.ERROR_MESSAGE);b=true;}

          if (b==false) jMenuItem17.setEnabled(true);
           ConfigurationTerminologique.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        ResultatRelation.dispose();
}//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        ResultatClasse.dispose();
}//GEN-LAST:event_jButton18ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        SynonymeTerminologique.dispose();
        SynonymeTerminologique.setTitle(" Ensemble des Synonymes ");
        SynonymeTerminologique.pack();
        SynonymeTerminologique.setLocationRelativeTo(null);
        SynonymeTerminologique.setVisible(true);
}//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // Affichage de la similarité structurelle
        MappingValidee.setTitle(" Les correspondances Validées");
        MappingValidee.pack();
        MappingValidee.setLocationRelativeTo(null);
        MappingValidee.setVisible(true);
}//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
        SimiaritéInstance.dispose();
        SimiaritéInstance.setTitle(" Base des Similarité ");
        SimiaritéInstance.pack();
        SimiaritéInstance.setLocationRelativeTo(null);
        SimiaritéInstance.setVisible(true);
}//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        ResultatRelation.setTitle(" Resultat Similarité Relation ");
        ResultatRelation.pack();
        ResultatRelation.setLocationRelativeTo(null);
        ResultatRelation.setVisible(true);
}//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // Affichage de la similarité structurelle
        ResultStructurelleClasse.setTitle("Resultat de la similarité structurelle des Classes ");
        ResultStructurelleClasse.pack();
        ResultStructurelleClasse.setLocationRelativeTo(null);
        ResultStructurelleClasse.setVisible(true);
}//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // Lancement de pronessus
        RunProcess.setTitle("Run Process");
        RunProcess.pack();
        RunProcess.setLocationRelativeTo(null);
        jButton5.setText("Executer");
        jLabel3.setText("Extraction des entités");
        jMenu6.setEnabled(true);
        jMenuItem24.setEnabled(true);
        // initialisation de la progressbar
        RunProcess.setVisible(true);
}//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // Configurer le processus
        ConfigurationTerminologique.setTitle("Configuration du système");
        ConfigurationTerminologique.pack();
        ConfigurationTerminologique.setLocationRelativeTo(null);
        ConfigurationTerminologique.setVisible(true);       
}//GEN-LAST:event_jMenuItem16ActionPerformed

    @SuppressWarnings("static-access")
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // Ouvrir les deux ontologie


        Open.setTitle("Chargement d'ontologies");
        Open.pack();
        Open.setLocationRelativeTo(null);
        Open.setVisible(true);
        jTextField1.setText("");
        jTextField2.setText("");
        jMenu4.setEnabled(false);
        jMenu5.setEnabled(false);
      
        
}//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
try
        {


          Hierarchie Arbre =new Hierarchie(URI1);
          cpt++;
        //  if (((Arbre.getTitle()).equals(str1))/*( Arbre.isShowing() )*/&& (cpt%2==0)){System.out.println("Fuck");Arbre.dispose();}
          nom1 = jTextField1.getText().split("\\p{Punct}");
          str1 = (nom1[nom1.length-2].concat(".").concat(nom1[nom1.length-1])).toString();
          System.out.println(str1);
          Arbre.setTitle((nom1[nom1.length-2].concat(".").concat(nom1[nom1.length-1])));
          Arbre.setSize(225,332);
          Arbre.setLocation(1,48);
          Arbre.setVisible(true);


          Hierarchie Arbre2 =new Hierarchie(URI2);  
          //if ((Arbre2.getTitle().equals(str2))/*&&( Arbre2.isShowing())*/&& (cpt%2==0)){System.out.println("Fuck");Arbre2.dispose();}
          nom2 = jTextField2.getText().split("\\p{Punct}");
          str2 = (nom2[nom2.length-2].concat(".").concat(nom2[nom2.length-1])).toString();
          System.out.println(str1);
          Arbre2.setTitle((nom2[nom2.length-2].concat(".").concat(nom2[nom2.length-1])));
          Arbre2.setSize(225,332);
          Arbre2.setLocation(1,380);
          Arbre2.setVisible(true);
          
         //  if   (/*(Arbre2.isVisible())&&*/(jMenuItem12.isSelected())){Arbre2.dispose();}

        } catch (OntologyLoadException ex) {
            Logger.getLogger(MAFOntologyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    try {
/*           Vector v1= new Vector();
           Vector v2= new Vector();
           int j=0;
           for  (int i = 0; i < MAP_Valide.getRowCount(); i++)
            {
               String[] ch = MAP_Valide.getValueAt(i, 2).toString().split("\\p{Punct}");
               if(ch[0].equals("Concept") && ch[1].equals("Concept"))
               {
                 v1.add(j, MAP_Valide.getValueAt(i, 0).toString());
                 v2.add(j, MAP_Valide.getValueAt(i, 1).toString());
                 j++;
               }  
            }*/
           Fusion.URI1 = URI1;
           Fusion.URI2 = URI2;
          // Fusion.v1 = v1;
         //  Fusion.v2 = v2;
           Fusion.main(null);

        } catch (OntologyLoadException ex) {
            Logger.getLogger(MAFOntologyView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        ConfigurationTerminologique.dispose();
}//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        try {
            new Alignin_Graphs(URI1, URI2);
             } catch (OntologyLoadException ex) {
            Logger.getLogger(MAFOntologyView.class.getName()).log(Level.SEVERE, null, ex);
        }
           // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        jLabel1.setText("Chemin de L'ontologie 1");
        jLabel2.setText("Chemin de L'ontologie 2");
        jToggleButton2.setEnabled(true);
        if (jToggleButton1.isSelected()==true) jToggleButton1.setSelected(false);
        if (jToggleButton3.isSelected()==true) jToggleButton3.setSelected(false);
        jToggleButton2.setFocusPainted(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        jLabel1.setText("URL de L'ontologie 1");
        jLabel2.setText("URL de L'ontologie 2");
        jToggleButton1.setEnabled(true);
        if (jToggleButton2.isSelected()== true) jToggleButton2.setSelected(false);
        if (jToggleButton3.isSelected()== true) jToggleButton3.setSelected(false);
        jToggleButton1.setFocusPainted(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void exitMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItem1ActionPerformed

        int result = JOptionPane.showConfirmDialog((Component) evt.getSource(),
            "Voulez-vous vraiment quitter votre application");
        if (result == JOptionPane.YES_OPTION) 
        {
           System.exit(0);
           RunProcess.dispose();
           jProgressBar1.setValue(0);
           jProgressBar1.setStringPainted(true);
           jLabel3.setText(" Initialisation des parametres......");
           jButton5.setText("Executer");
           ViderTable(Table_Struc);
           ViderTable(MAP_Valide);
           ViderTable(SimInstance);
           ViderTable(SimilariteTermClasse);
           ViderTable(SimilariteTermRelation);
           ViderTable(Synonyme);
        }
        else if (result == JOptionPane.NO_OPTION) {
          System.out.println("Do nothing");
        }
    }//GEN-LAST:event_exitMenuItem1ActionPerformed

    private void fileMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fileMenu1ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        jLabel1.setText("Chemin de L'ontologie 1");
        jLabel2.setText("URL de L'ontologie 2");
        jToggleButton3.setEnabled(true);
        if (jToggleButton2.isSelected()== true) jToggleButton2.setSelected(false);
        if (jToggleButton1.isSelected()== true) jToggleButton1.setSelected(false);
        jToggleButton3.setFocusPainted(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
             new Alignin_Graphs(URI1, URI2);
            } catch (OntologyLoadException ex) {
            Logger.getLogger(MAFOntologyView.class.getName()).log(Level.SEVERE, null, ex);
           }

           Vector v1= new Vector();
           Vector v2= new Vector();
           int j=0;
           for  (int i = 0; i < MAP_Valide.getRowCount(); i++)
            {
               String[] ch = MAP_Valide.getValueAt(i, 2).toString().split("\\p{Punct}");
               if(ch[0].equals("Concept") && ch[1].equals("Concept"))
               {
                 v1.add(j, MAP_Valide.getValueAt(i, 0).toString());
                 v2.add(j, MAP_Valide.getValueAt(i, 1).toString());
                 j++;
                 Alignin_Graphs.lien(MAP_Valide.getValueAt(i, 0).toString(),MAP_Valide.getValueAt(i, 1).toString(),Float.parseFloat(MAP_Valide.getValueAt(i, 3).toString()));
               }
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        JFileChooser choose = new JFileChooser();
        int retour=choose.showOpenDialog(Open);
        if(retour==JFileChooser.APPROVE_OPTION)
        jTextField1.setText(choose.getSelectedFile().getAbsolutePath());
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
if(jTextField4.getText().equals(""))
        {
         JOptionPane.showMessageDialog(Open,"Chemin(s) d'ontologie(s) non spécifié(s) correctement","Erreur de Chargement",JOptionPane.ERROR_MESSAGE);
        }
else {  char[] text =jTextField4.getText().toCharArray();
        for(int i=0; i<text.length; i++) { if(text[i] == (char)92) text[i] = (char)47;}
        String Chemin = text.toString();
        //new Save.
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Inetrprétation.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed



        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed
// Fonction Necessaire
public void ViderTable(JTable Table)
{
    if (Table.getRowCount()>0)
    {
        Table.selectAll();
        int [] selected = Table.getSelectedRows();
        
        for (int i = selected.length-1; i >= 0 ; i--)
	    ((DefaultTableModel) Table.getModel()).removeRow(selected[i]);
    }
    else
    {     
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFrame ConfigurationTerminologique;
    private javax.swing.JFrame Inetrprétation;
    private javax.swing.JTable MAP_Valide;
    private javax.swing.JFrame MappingValidee;
    private javax.swing.JFrame Open;
    private javax.swing.JFrame ResultStructurelleClasse;
    private javax.swing.JFrame ResultatClasse;
    private javax.swing.JFrame ResultatRelation;
    private javax.swing.JFrame RunProcess;
    private javax.swing.JTable SimInstance;
    private javax.swing.JFrame SimiaritéInstance;
    private javax.swing.JTable SimilariteTermClasse;
    private javax.swing.JTable SimilariteTermRelation;
    private javax.swing.JTable Synonyme;
    private javax.swing.JFrame SynonymeTerminologique;
    private javax.swing.JTable Table_Struc;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSpinner jSpinner24;
    private javax.swing.JSpinner jSpinner26;
    private javax.swing.JSpinner jSpinner30;
    private javax.swing.JSpinner jSpinner31;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar1;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables
    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private JDialog aboutBox;
}
