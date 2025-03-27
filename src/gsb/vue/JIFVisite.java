package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JIFVisite extends JInternalFrame  {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel p;
	protected JPanel parentPanel;
	protected JPanel Panelpartie1;
	protected JPanel Panelpartie2;
	protected JPanel Panelpartie3;
	protected JPanel Panelpartie4;
	protected JPanel pBoutons;
	//Bouton Fermer consutation médicaments
	private JButton sortie;
	
	protected JLabel JLreference;
	protected JLabel JLdateVisite;
	protected JLabel JLcommentaire;
	protected JLabel JLmatriculeVisiteur;
	protected JLabel JLcodeMedecin;
    protected JLabel JLmedOffert1;
    protected JLabel JLquantiteMed1;
    protected JLabel JLmedOffert2;
    protected JLabel JLquantiteMed2;
    
    
	protected JTextField JTreference;
	protected JTextField JTdateVisite;
	protected JTextField JTcommentaire;
	protected JTextField JTmatriculeVisiteur;
	protected JTextField JTcodeMedecin;
    protected JTextField JTmedOffert1;
    protected JTextField JTquantiteMed1;
    protected JTextField JTmedOffert2;
    protected JTextField JTquantiteMed2;
    
    // Constructeur de la classe JIFMedicament
    public JIFVisite(MenuPrincipal fenetreContainer, Visite uneVisite) {
    	p = new JPanel();  // panneau principal de la fenetre
        pBoutons = new JPanel();    // panneau supportant les boutons
        
        //parentPanel = new JPanel(new BorderLayout());
        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        
        Panelpartie1 = new JPanel(new GridLayout(1,2));
        Panelpartie2 = new JPanel(new GridLayout(3,2));
        Panelpartie3 = new JPanel(new GridLayout(1,2));
        Panelpartie4 = new JPanel(new GridLayout(4,2));
       
        //Bouton Fermer consutation médicaments
        sortie = new JButton("FERMER");
        pBoutons.add(sortie);
        
    	
    	 JLreference = new JLabel("Référence");
         JLdateVisite = new JLabel("Date visite");
         JLmatriculeVisiteur = new JLabel("Matricule visiteur");
         JLcodeMedecin = new JLabel("Code Médecin");
         JLcommentaire = new JLabel("Commentaire");
         JLmedOffert1 = new JLabel("Dépot légal Médicament 1");
         JLquantiteMed1 = new JLabel("Quantité offerte Médicament 1");
         JLmedOffert2 = new JLabel("Dépot légal Médicament 2");
         JLquantiteMed2 = new JLabel("Quantité offerte Médicament 2");
         
         JTreference = new JTextField(20);
         JTreference.setEditable(false);
         
         // JTdateVisite.setMaximumSize(JTreference.getPreferredSize());
         JTdateVisite = new         /**JButton closeButton = new JButton("FERMER");
                 closeButton.addActionListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
    **/ JTextField();
         JTdateVisite.setEditable(false);
         
         JTmatriculeVisiteur = new JTextField();
         JTmatriculeVisiteur.setEditable(false);
         
         JTcodeMedecin = new JTextField();
         JTcodeMedecin.setEditable(false);
         
         JTcommentaire = new JTextField();
         JTcommentaire.setEditable(false);
         
         JTmedOffert1 = new JTextField();
         JTmedOffert1.setEditable(false);
         
         JTquantiteMed1 = new JTextField();
         JTquantiteMed1.setEditable(false);
         
         JTmedOffert2 = new JTextField();
         JTmedOffert2.setEditable(false);
         
         JTquantiteMed2 = new JTextField();
         JTquantiteMed2.setEditable(false);
         
         Panelpartie1.add(JLreference);
         Panelpartie1.add(JTreference);
         
         Panelpartie2.add(JLdateVisite);
         Panelpartie2.add(JTdateVisite);
         Panelpartie2.add(JLmatriculeVisiteur);
         Panelpartie2.add(JTmatriculeVisiteur);
         Panelpartie2.add(JLcodeMedecin);
         Panelpartie2.add(JTcodeMedecin);
         
         
         Panelpartie3.add(JLcommentaire);
         Panelpartie3.add(JTcommentaire);
         
         Panelpartie4.add(JLmedOffert1);
         Panelpartie4.add(JTmedOffert1);
         Panelpartie4.add(JLquantiteMed1);
         Panelpartie4.add(JTquantiteMed1);
         Panelpartie4.add(JLquantiteMed2);
         Panelpartie4.add(JTmedOffert2);
         Panelpartie4.add(JLmedOffert2);
         Panelpartie4.add(JTquantiteMed2);
         

      // Panel pour afficher le tableau
         JPanel tableauPanel = new JPanel();
         String[] colonnes = {"", "Dépôt légal", "Quantité offerte"};
         
         int nombreArticles = 2;
         String[][] donnees = new String[nombreArticles][3]; // 3 colonnes :
         
         for (int i = 0; i < nombreArticles; i++) {
             donnees[i][0] = "Médicament " + (i + 1); // Première colonne : étiquette de ligne
             if (i == 0) {
                 donnees[i][1] = uneVisite.getMedOffert1(); 
                 donnees[i][2] = String.valueOf(uneVisite.getQuantiteMed1());
             } 
             else {
            	 if (i == 1) {
                     donnees[i][1] = uneVisite.getMedOffert2(); 
                     donnees[i][2] = String.valueOf(uneVisite.getQuantiteMed2());
            	 }
             }
         }

         JTable tableau = new JTable(donnees, colonnes);
         
         tableau.setFillsViewportHeight(true);
         tableau.setEnabled(false); // Rend le tableau non modifiable
         tableau.getTableHeader().setReorderingAllowed(false); // Empêche de réorganiser les colonnes
         tableau.getTableHeader().setResizingAllowed(false); // Empêche de redimensionner les colonnes
         tableau.setPreferredScrollableViewportSize(
                 new Dimension(400, tableau.getRowHeight() * donnees.length + tableau.getTableHeader().getHeight())
         );
         
         JScrollPane scrollPane = new JScrollPane(tableau);
         tableauPanel.setLayout(new BorderLayout());
         tableauPanel.add(scrollPane, BorderLayout.CENTER);
         
         parentPanel.add(Panelpartie1);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie2);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie3);
         parentPanel.add(Box.createVerticalStrut(10));

         // quatrième partie de l'écran n'est pas utilisé ici 
         //parentPanel.add(Panelpartie4);
         //parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(tableauPanel);
         
         
         
         setTitle("Récapitulatif d'une visite");
         
        // mise en forme de la fenetre

         p.add(parentPanel);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
         
       //Bouton Fermer consutation médicaments
         sortie.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }
         });
         
	}

	public void remplirText(Visite uneVisite) 	
    {  // methode qui permet de remplir les zones de texte a partir des valeurs passees en parametres
        JTreference.setText(uneVisite.getReference());        
        JTdateVisite.setText(uneVisite.getDateVisite());
        JTmatriculeVisiteur.setText(uneVisite.getMatriculeVisiteur());
        JTcodeMedecin.setText(uneVisite.getCodeMedecin());
        JTcommentaire.setText(uneVisite.getCommentaire());
        JTmedOffert1.setText(uneVisite.getMedOffert1());
        JTquantiteMed1.setText(String.valueOf(uneVisite.getQuantiteMed1()));
        JTmedOffert2.setText(uneVisite.getMedOffert2());
        JTquantiteMed2.setText(String.valueOf(uneVisite.getQuantiteMed2()));

        
        
    }
     
      public void viderText() 	
    {  // methode qui permet de vider les zones de texte 
        JTreference.setText("");        
        JTdateVisite.setText("");
        JTcommentaire.setText("");
        JTmatriculeVisiteur.setText("");    
        JTcodeMedecin.setText("");
        JTmedOffert1.setText("");
        JTquantiteMed1.setText("");
        JTmedOffert2.setText("");
        JTquantiteMed2.setText("");
     }

}