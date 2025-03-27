package gsb.vue;

import gsb.modele.Medicament;
import gsb.modele.Visite;
import gsb.modele.dao.MedecinDao;
import gsb.modele.dao.MedicamentDao;
import gsb.modele.dao.VisiteDao;
import gsb.modele.dao.VisiteurDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JIFVisiteMaj extends JInternalFrame implements ActionListener {
	/**
	 * Commentaire pour <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	private static final String String = null;
	protected JPanel p;
	protected JPanel parentPanel;
	protected JPanel Panelpartie1;
	protected JPanel Panelpartie2;
	protected JPanel Panelpartie3;
	protected JPanel Panelpartie4;
	protected JPanel pBoutons;
	//Bouton Fermer consutation médicaments
	private JButton sortie;
	private JButton ajout;
	private JButton modif;
	
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
    public JIFVisiteMaj(MenuPrincipal fenetreContainer) {
    	p = new JPanel();  // panneau principal de la fenetre
        pBoutons = new JPanel();    // panneau supportant les boutons
        
        //parentPanel = new JPanel(new BorderLayout());
        parentPanel = new JPanel();
        parentPanel.setLayout(new BoxLayout(parentPanel, BoxLayout.Y_AXIS));
        
        Panelpartie1 = new JPanel(new GridLayout(1,2));
        Panelpartie2 = new JPanel(new GridLayout(3,2));
        Panelpartie3 = new JPanel(new GridLayout(1,2));
        Panelpartie4 = new JPanel(new GridLayout(3,3,10,10));
       
        //Bouton Fermer consutation médicaments
        sortie = new JButton("FERMER");
        pBoutons.add(sortie);
        modif = new JButton("Modifier");
        pBoutons.add(modif);        
        
        // Chargez les visites
        String LastNumVisite = RechercheLastNumVisite();
        String NewNumVisite = genererProchainNumVisite(LastNumVisite);
        
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
         
         // JTdateVisite.setMaximumSize(JTreference.getPreferredSize());
         JTdateVisite = new JTextField();
         JTdateVisite.setEditable(false);
         
         JTmatriculeVisiteur = new JTextField();
         JTmatriculeVisiteur.setEditable(false);
         
         JTcodeMedecin = new JTextField();
         JTcodeMedecin.setEditable(false);
         
         JTcommentaire = new JTextField();
         
         
         JTmedOffert1 = new JTextField();
         
         JTquantiteMed1 = new JTextField();
         
         JTmedOffert2 = new JTextField();
         
         JTquantiteMed2 = new JTextField();
         
         Panelpartie1.add(JLreference);
         JTreference.setText(NewNumVisite);
         
         Panelpartie1.add(JTreference);
         
         Panelpartie2.add(JLdateVisite);
         Panelpartie2.add(JTdateVisite);
         Panelpartie2.add(JLmatriculeVisiteur);
         Panelpartie2.add(JTmatriculeVisiteur);
         Panelpartie2.add(JLcodeMedecin);
         Panelpartie2.add(JTcodeMedecin);
         
         
         Panelpartie3.add(JLcommentaire);
         Panelpartie3.add(JTcommentaire);
         
         Panelpartie4.add(new JLabel(""));
         Panelpartie4.add(new JLabel("Dépot légal", SwingConstants.CENTER));
         Panelpartie4.add(new JLabel("Quantité offerte", SwingConstants.CENTER));
         
         Panelpartie4.add(new JLabel("Médicament 1", SwingConstants.RIGHT));
         Panelpartie4.add(JTmedOffert1);
         Panelpartie4.add(JTquantiteMed1);
         
         Panelpartie4.add(new JLabel("Médicament 2", SwingConstants.RIGHT));
         Panelpartie4.add(JTmedOffert2);
         Panelpartie4.add(JTquantiteMed2);
         
         parentPanel.add(Panelpartie1);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie2);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie3);
         parentPanel.add(Box.createVerticalStrut(10));
         parentPanel.add(Panelpartie4);
         //parentPanel.add(Box.createVerticalStrut(10));
         //parentPanel.add(tableauPanel);
         
         
         
         setTitle("Mise à jour d'une visite");
         
        // mise en forme de la fenetre

         p.add(parentPanel);
         p.add(pBoutons);
         Container contentPane = getContentPane();
         contentPane.add(p);
         
         //lors de saisie dans le champs référence
         JTreference.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 String referencevisite = JTreference.getText();
            	 Visite visite = null;
            	 boolean fTrouve=false;
                 visite = Recherchevisite(referencevisite);
                 if (visite == null) {
                 	JOptionPane.showMessageDialog(p, "La référence de la visite saisie n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                 }
                 else {
                	
                	// JOptionPane.showMessageDialog(p, "saisi dans le champs référence", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    // dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
//                	String DateVisite = visite.getDateVisite();
                	// System.out.println("Date visite : " + DateVisite);
                	JTcommentaire.setText(visite.getCommentaire());
                	JTdateVisite.setText(visite.getDateVisite());
                	JTmatriculeVisiteur.setText(visite.getMatriculeVisiteur());
                    JTcodeMedecin.setText(visite.getCodeMedecin());
                    JTmedOffert1.setText(visite.getMedOffert1());
                    JTquantiteMed1.setText(Integer.toString(visite.getQuantiteMed1()));
                    JTmedOffert2.setText(visite.getMedOffert2());
                    JTquantiteMed2.setText(Integer.toString(visite.getQuantiteMed2()));
                	// Panelpartie2.add(JTdateVisite);
                    
                    
                 }
             }
         });
         
         
       //Bouton Fermer consutation médicaments
         sortie.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 dispose(); // Ferme la fenêtre lorsqu'on clique sur "FERMER"
             }
         });

         
         modif.addActionListener((ActionListener) new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
            	 int IndicUpdateVisite=0;
            	 boolean fErreur = false;
            	 
            	 Visite visite = new Visite(JTreference.getText(),
            			                    JTcommentaire.getText(),
            			                    JTmedOffert1.getText(),
            			                    Integer.valueOf(JTquantiteMed1.getText()),
            			                    JTmedOffert2.getText(),
            			                    Integer.valueOf(JTquantiteMed2.getText())
            			                    );
            	 
            	 //String REFERENCEvisite = JTreference.getText();
            	 //// visite.setReference(JTreference.getText());
            	 
            	 //String commentaire = JTcommentaire.getText();
                 //// visite.setCommentaire(JTcommentaire.getText());
            	 
            	 //String medOffert1 = JTmedOffert1.getText();
            	 //// visite.setMedOffert1(JTmedOffert1.getText());
            	 
            	 // int quantiteMed1 = Integer.valueOf(JTquantiteMed1.getText());
                 //// visite.setQuantiteMed1(Integer.valueOf(JTquantiteMed1.getText()));
            	 
            	 //String medOffert2 = JTmedOffert1.getText();
            	 //// visite.setMedOffert2(JTmedOffert2.getText());
            	 
            	 //int quantiteMed2 = Integer.valueOf(JTquantiteMed2.getText());
            	 //// visite.setQuantiteMed2(Integer.valueOf(JTquantiteMed2.getText()));
            	 
            	 if (JTmedOffert1.getText().trim().isEmpty() && JTmedOffert2.getText().trim().isEmpty()) {
            		 IndicUpdateVisite=0;
            	 }
            	 else if (JTmedOffert2.getText().trim().isEmpty()) {
            		 IndicUpdateVisite=1;
            	 }
            	 else if (JTmedOffert1.getText().trim().isEmpty()) {
            		 JOptionPane.showMessageDialog(p, "Veuillez remplir le Médicament 1 avant le Médicament 2", "Erreur", JOptionPane.ERROR_MESSAGE);
            		 fErreur = true;
            	 }
            	 else {
            		 IndicUpdateVisite=2;
            	 }
            	 
            	 if (fErreur == false) {
            	    VisiteDao.MAJUneVisite(p, visite, IndicUpdateVisite);
            	 }
             }
         });

}

	public Visite Recherchevisite(String referencevisite) {

		// boolean fTrouve = false;
		Visite visite = null;

		visite = VisiteDao.recherchevisite(referencevisite);
		
        return visite;
	}
    
    
	public String RechercheLastNumVisite() {
		// TODO Auto-generated method stub
        // Charge les visites depuis la base de données
		String LastNumVisite;
        return LastNumVisite = VisiteDao.getLastNumVisite();
	}
	
	public boolean RechercheMatriculeVisiteur(String codevisiteur) {
		boolean fTrouve = false;
		
		fTrouve = VisiteurDao.RechercheMatriculeVisiteur(codevisiteur);
		return fTrouve;
	}
	
	public boolean RechercheCodeMedecin(String codemedecin) {
		boolean fTrouve = false;
		
		fTrouve = MedecinDao.RechercheCodeMedecin(codemedecin);
		return fTrouve;
	}

	
	
	private static boolean estDateValide(String date) {
        // Vérification du format avec une regex
        if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
        	// ^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$
            return false;
        }

        // Validation de la date en utilisant LocalDate
        try {
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy/MM/dd][yyyy-MM-dd]");
            LocalDate.parse(date, formatter); // Exception si la date est invalide
         // 
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
	
    // Méthode pour générer le prochain numéro de visite
    public String genererProchainNumVisite(String LastNumVisite) {
        int numero = Integer.parseInt(LastNumVisite.substring(1)); // Retirer "V"
        numero++; // Incrémenter
        return String.format("v%04d", numero); // Générer nouveau numéro
        
    }

	public void remplirText(Visite uneVisite) 	
    {  // methode qui permet de remplir les zones de texte a partir des valeurs passees en parametres
        JTreference.setText(uneVisite.getReference());        
        JTdateVisite.setText(uneVisite.getDateVisite());
        JTmatriculeVisiteur.setText(uneVisite.getMatriculeVisiteur());
        JTcodeMedecin.setText(uneVisite.getCodeMedecin());
        JTcommentaire.setText(uneVisite.getCommentaire());
        //JTmedOffert1.setText(uneVisite.getMedOffert1());
        //JTquantiteMed1.setText(String.valueOf(uneVisite.getQuantiteMed1()));
        //JTmedOffert2.setText(uneVisite.getMedOffert2());
        //JTquantiteMed2.setText(String.valueOf(uneVisite.getQuantiteMed2()));

        
        
    }
     
      public void viderText() 	
    {  // methode qui permet de vider les zones de texte 
        JTreference.setText("");        
        JTdateVisite.setText("");
        JTcommentaire.setText("");
        JTmatriculeVisiteur.setText("");    
        JTcodeMedecin.setText("");
        //JTmedOffert1.setText("");
        //JTquantiteMed1.setText("");
        //JTmedOffert2.setText("");
        //JTquantiteMed2.setText("");
     }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


    
}

